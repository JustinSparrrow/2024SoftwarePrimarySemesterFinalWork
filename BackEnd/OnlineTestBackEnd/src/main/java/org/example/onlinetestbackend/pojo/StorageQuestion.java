package org.example.onlinetestbackend.pojo;

public class StorageQuestion {
    private Integer qid;
    private String qmajor;
    private Integer qtype;
    private String qcontent;
    private String qanswer;
    private String qimg;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getQmajor() {
        return qmajor;
    }

    public void setQmajor(String qmajor) {
        this.qmajor = qmajor;
    }

    public Integer getQtype() {
        return qtype;
    }

    public void setQtype(Integer qtype) {
        this.qtype = qtype;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public String getQimg() {
        return qimg;
    }

    public void setQimg(String qimg) {
        this.qimg = qimg;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"qid\":\"" + qid + "\",\n" +
                "\"qmajor\":\"" + qmajor + "\",\n" +
                "\"qtype\":" + qtype + ",\n" +
                "\"qcontent\":\"" + qcontent + "\",\n" +
                "\"qanswer\":\"" + qanswer + "\"\n" +
                '}';
    }

    public StorageQuestion(Integer qid, String qmajor, Integer qtype, String qcontent, String qanswer, String qimg) {
        this.qid = qid;
        this.qmajor = qmajor;
        this.qtype = qtype;
        this.qcontent = qcontent;
        this.qanswer = qanswer;
        this.qimg = qimg;
    }

    public PostQuestion postReady()
    {
        PostQuestion postQuestion=new PostQuestion(qid,qmajor,qtype,null,null,qanswer,qimg,null);
        String[] strs=qcontent.split("/");
        String[] qchoice=new String[strs.length-1];
        postQuestion.setQcontent(strs[0]);
        for(int i=1;i<strs.length;i++)qchoice[i-1]=strs[i];
        postQuestion.setQchoice(qchoice);
        return postQuestion;
    }
}
