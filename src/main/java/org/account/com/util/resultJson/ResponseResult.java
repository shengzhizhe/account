package org.account.com.util.resultJson;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {
    private boolean success;
    private String message;
    private T data;
    private int code = 200;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        try {
            this.message = new String(message.getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            this.message = message;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResponseResult() {
        super();
    }

    public ResponseResult(boolean success, String message, T data, int code) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }
}
