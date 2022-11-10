package com.mdd.generator.constant;

public class SqlConstants {

    /** 数据库字符串类型 */
    public static final String[] COLUMN_TYPE_STR = {"char", "varchar", "nvarchar", "varchar2"};

    /** 数据库文本类型 */
    public static final String[] COLUMN_TYPE_TEXT = {"tinytext", "text", "mediumtext", "longtext"};

    /** 数据库时间类型 */
    public static final String[] COLUMN_TYPE_TIME = {"datetime", "time", "date", "timestamp"};

    /** 数据库数字类型 */
    public static final String[] COLUMN_TYPE_NUMBER = {"tinyint", "tinyint unsigned", "smallint", "smallint unsigned", "mediumint", "mediumint unsigned", "int", "int unsigned", "number", "integer","integer unsigned", "bigint", "float", "double", "decimal"};

    /** 数据库数字类型转换为java Integer */
    public static final String[] COLUMN_TYPE_NUMBER_INT = {"tinyint", "tinyint unsigned", "smallint", "smallint unsigned", "mediumint", "mediumint unsigned", "int", "int unsigned", "number", "integer","integer unsigned"};

    /** 数据库数字类型转换为java Long */
    public static final String[] COLUMN_TYPE_NUMBER_LONG = {"bigint","bigint unsigned"};

    /** 数据库数字类型转换为java Float */
    public static final String[] COLUMN_TYPE_NUMBER_FLOAT = {"float"};

    /** 数据库数字类型转换为java Double */
    public static final String[] COLUMN_TYPE_NUMBER_DOUBLE = {"double"};

    /** 数据库数字类型转换为java BigDecimal */
    public static final String[] COLUMN_TYPE_NUMBER_BIGDECIMAL = {"decimal"};

    /** 数据库数字类型转换为java Boolean */
    public static final String[] COLUMN_TYPE_BOOL = {"bit"};

    /** 时间日期字段名 */
    public static final String[] COLUMN_TIME_NAME = {"create_time", "update_time", "delete_time", "start_time", "end_time"};

    /** 页面不需要插入字段 */
    public static final String[] COLUMN_NAME_NOT_ADD = {"id", "is_delete", "create_time", "update_time", "delete_time"};

    /** 页面不需要编辑字段 */
    public static final String[] COLUMN_NAME_NOT_EDIT = {"is_delete", "create_time", "update_time", "delete_time"};

    /** 页面不需要列表字段 */
    public static final String[] COLUMN_NAME_NOT_LIST = {"id", "intro", "content", "is_delete", "delete_time"};

    /** 页面不需要查询字段 */
    public static final String[] COLUMN_NAME_NOT_QUERY = {"is_delete", "create_time", "update_time", "delete_time"};

}
