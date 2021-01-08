package cn.njiuyag.springboot.blog.framework;

/**
 * @author hjx
 * @date 2020/12/25
 */
public class ErrorCode<T extends Object> {

    /**
     * 正常返回
     */
    public static final String SUCCESS = "00000";

    public static final String PARAMS_INVALID = "00001";



    /**
     * 用户错误
     */
    public static final String USER_ERROR = "A0100";

    public static final String USER_INFO_NOT_FOUND = "A0101";

    /**
     * 密码错误
     */
    public static final String USER_PASSWORD_ERROR = "A0102";

    public static final String USER_DATABASE_OPERATION_ERROR = "A0103";


    /**
     * 分类相关错误
     */
    public static final String CATEGORY_ERROR = "A0200";
    public static final String CATEGORY_NAME_REPEAT = "A0201";



}
