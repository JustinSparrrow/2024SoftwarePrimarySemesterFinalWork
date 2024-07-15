package org.example.onlinetestbackend.pojo;

public class UserToQuestion {
    private Integer rid;
    private Integer userid;
    private String useranswer;
    private Integer qid;
    private String qanswer;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public UserToQuestion(Integer rid, Integer userid, String useranswer, Integer qid, String qanswer) {
        this.rid = rid;
        this.userid = userid;
        this.useranswer = useranswer;
        this.qid = qid;
        this.qanswer = qanswer;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"rid\":\"" + rid + "\",\n" +
                "\"userid\":\"" + userid + "\",\n" +
                "\"useranswer\":\"" + useranswer + "\",\n" +
                "\"qid\":\"" + qid + "\",\n" +
                "\"qanswer\":\"" + qanswer + "\"\n" +
                '}';
    }
}
