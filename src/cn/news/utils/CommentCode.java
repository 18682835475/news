package cn.news.utils;

/**
 * @author LCB
 * @date 2022/7/1 9:54
 */
public enum CommentCode {
    SUCCESS(200L,"成功"),
    FAILED(500L,"失败"),
    FAILED_ERROR_URL(404L,"路径不存在!"),
    FAILED_ERROR_DATA(501L,"后台服务异常，请联系管理员!");
    private Long code;
    private String message;


    CommentCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
