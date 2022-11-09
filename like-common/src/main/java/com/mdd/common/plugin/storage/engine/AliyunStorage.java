package com.mdd.common.plugin.storage.engine;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectRequest;
import com.mdd.common.exception.OperateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 阿里云存储
 */
public class AliyunStorage {

    @Autowired
    OSS ossClient;

    /**
     * 存储配置
     */
    private final Map<String, String> config;

    /**
     * 构造方法
     */
    public AliyunStorage(Map<String, String> config) {
        this.config = config;
    }

    public AliyunStorage() {
        this.config = null;
    }
    /**
     * 鉴权令牌
     *
     * @author fzr
     * @return String
     */
    public OSS ossClient() {
        String endpoint        = "https://oss-cn-shenzhen.aliyuncs.com";
        String accessKeyId     = this.config.get("accessKey");
        String accessKeySecret = this.config.get("secretKey");
       return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 上传文件
     *
     * @param multipartFile 文件对象
     * @param key 文件名称 20220331/11.png
     */
    public void upload(MultipartFile multipartFile, String key) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    this.config.get("bucket"), key,
                    new ByteArrayInputStream(multipartFile.getBytes())
            );
            this.ossClient().putObject(putObjectRequest);
        } catch (OSSException oe) {
            throw new OperateException(oe.getMessage());
        } catch (Exception ce) {
            throw new OperateException(ce.getMessage());
        } finally {
            if (this.ossClient() != null) {
                this.ossClient().shutdown();
            }
        }
    }

    public Map<String, String> policy(String bucket, String endpoint, String accessId) {
        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        final String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
        String dir = format + "/";
        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessId", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }
        return respMap;
    }

}
