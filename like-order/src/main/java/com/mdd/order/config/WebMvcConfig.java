//package com.mdd.order.config;
//
//import com.mdd.common.config.GlobalConfig;
//import com.mdd.common.utils.YmlUtil;
//import com.mdd.order.LikeOrderInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.lang.NonNull;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Resource
//    LikeOrderInterceptor likeOrderInterceptor;
//
//    /**
//     * 配置允许跨域
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(3600);
//    }
//
//    /**
//     * 登录拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(likeOrderInterceptor).addPathPatterns("/**");
//    }
//
//    /**
//     * 资源目录映射
//     */
//    @Override
//    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
//        String directory = YmlUtil.get("like.upload-directory");
//        if (directory == null || directory.equals("")) {
//            directory = GlobalConfig.uploadDirectory;
//        }
//
//        registry.addResourceHandler("/"+ GlobalConfig.publicPrefix +"/**")
//                .addResourceLocations("file:" + directory);
//    }
//
//}
