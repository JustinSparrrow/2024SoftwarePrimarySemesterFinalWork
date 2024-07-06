package org.example.onlinetestbackendtestpart.pojo;

public class Result {
    private int successs;

    public int getSuccesss() {
        return successs;
    }

    public void setSuccesss(int successs) {
        this.successs = successs;
    }

    public Result(int successs) {
        this.successs = successs;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"successs\":" + successs +"\n"+
                '}';
    }
}
