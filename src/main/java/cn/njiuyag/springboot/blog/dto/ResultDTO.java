package cn.njiuyag.springboot.blog.dto;

import cn.njiuyag.springboot.blog.framework.ErrorCode;

/**
 * @author hjx
 * @date 2020/12/31
 */
public class ResultDTO<T extends Object> {
    private String code;
    private String tips;
    private T data;

    public ResultDTO(String code, String tips) {
        this.code = code;
        this.tips = tips;
        this.data = null;
    }

    public ResultDTO(T data) {
        this.code = ErrorCode.SUCCESS;
        this.tips = "成功";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "code='" + code + '\'' +
                ", tips='" + tips + '\'' +
                ", data=" + data +
                '}';
    }
}
