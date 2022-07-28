package cn.news.utils;

/**前台和后台交互数据结构模板
 * @author LCB
 * @date 2022/7/1 9:49
 */
public class ResultCode<T> {
    private Long code; // 响应的代码
    private T data; // 响应数据
    private String message; // 响应的文本

    private ResultCode() {}

    private ResultCode(Long code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * 成功默认消息
     * @return
     */
    public static ResultCode success(){
        return new ResultCode(CommentCode.SUCCESS.getCode(),CommentCode.SUCCESS.getMessage(),null);
    }

    /**
     * 携带数据的成功消息
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ResultCode success(T data){
        return new ResultCode(CommentCode.SUCCESS.getCode(),CommentCode.SUCCESS.getMessage(),data);
    }

    /**
     * 自定义返回消息的成功数据
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ResultCode success(String message,T data){
        return new ResultCode(CommentCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 失败默认消息
     * @return
     */
    public static ResultCode failed(){
        return new ResultCode(CommentCode.FAILED.getCode(),CommentCode.FAILED.getMessage(),null);
    }

    /**
     * 失败默认消息
     * @return
     */
    public static ResultCode failed(String message){
        return new ResultCode(CommentCode.FAILED.getCode(),message,null);
    }



    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
