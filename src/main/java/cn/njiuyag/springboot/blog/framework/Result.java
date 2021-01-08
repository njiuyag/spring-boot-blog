package cn.njiuyag.springboot.blog.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hjx
 * @date 2020/12/25
 */
public class Result<T extends Object> {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    private String errorCode;
    private String errorMessage;
    private T data;

    public Result(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = null;
    }

    public Result(T data) {
        this.errorCode = ErrorCode.SUCCESS;
        this.errorMessage = "成功";
        this.data = data;
    }

    public static Result<?> fail(String errorCode, String errorMessage) {
        logger.error("{}:{}", errorCode, errorMessage);
        return new Result<>(errorCode, errorMessage);
    }

    public static <R> Result<R> success(R data) {
        return new Result<>(data);
    }

    public boolean success(){
        return ErrorCode.SUCCESS.equals(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
