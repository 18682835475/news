package cn.news.entity;

/**
 * @author LCB
 * @date 2022/6/27 18:52
 */
public class Topic {
    private Integer tid;
    private String tname;

    public Topic() {
    }

    public Topic(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
