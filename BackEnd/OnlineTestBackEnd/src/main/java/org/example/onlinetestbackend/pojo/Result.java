package org.example.onlinetestbackend.pojo;

public class Result {
    private int success;
    private Object data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(int success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Result(int success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return data!=null?
                "{\n" +
                "\"success\":" + success +",\n"+
                "\"data\":" + data.toString() +"\n"+
                '}':
                "{\n" +
                        "\"success\":" + success +",\n"+
                        "\"data\":null\n"+
                        '}';
    }
}
