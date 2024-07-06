package org.example.onlinetestbackendtestpart.pojo;

public class UserToQuestion {
    private String rid;
    private String userid;
    private String useranswer;
    private String qid;
    private String qanswer;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
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
