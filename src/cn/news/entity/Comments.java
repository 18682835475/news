package cn.news.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LCB
 * @date 2022/6/27 18:58
 */
public class Comments implements Serializable {
    private Integer cid;
    private Integer cnid;
    private String ccontent;
    private Date cdate;
    private String cip;
    private String cauthor;

    public Comments() {
    }

    public Comments(Integer cid, Integer cnid, String ccontent, Date cdate, String cip, String cauthor) {
        this.cid = cid;
        this.cnid = cnid;
        this.ccontent = ccontent;
        this.cdate = cdate;
        this.cip = cip;
        this.cauthor = cauthor;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCnid() {
        return cnid;
    }

    public void setCnid(Integer cnid) {
        this.cnid = cnid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCauthor() {
        return cauthor;
    }

    public void setCauthor(String cauthor) {
        this.cauthor = cauthor;
    }
}
