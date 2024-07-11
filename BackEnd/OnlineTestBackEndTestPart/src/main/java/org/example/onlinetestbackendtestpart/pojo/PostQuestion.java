package org.example.onlinetestbackendtestpart.pojo;

public class PostQuestion {
    private Integer qid;
    private String qmajor;
    private Integer qtype;
    private String qcontent;
    private String[] qchoice;
    private String qanswer;
    private String qimg;
    private String useranswer;

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

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    public String[] getQchoice() {
        return qchoice;
    }

    public void setQchoice(String[] qchoice) {
        this.qchoice = qchoice;
    }

    public PostQuestion(Integer qid, String qmajor, Integer qtype, String qcontent,String[] qchoice, String qanswer, String qimg, String useranswer) {
        this.qid = qid;
        this.qmajor = qmajor;
        this.qtype = qtype;
        this.qcontent = qcontent;
        this.qanswer = qanswer;
        this.qimg = qimg;
        this.useranswer = useranswer;
    }
    
    public StorageQuestion storageReady()
    {
        StorageQuestion storageQuestion =new StorageQuestion(qid,qmajor,qtype,null,qanswer,qimg);
        String fullContent=qcontent;
        for(String i:qchoice)fullContent+="/"+i;
        storageQuestion.setQcontent(fullContent);
        return storageQuestion;
    }
}
