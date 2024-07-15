package org.example.onlinetestbackend.pojo;

public class User {
    private Integer userid;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Integer admin;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public User(Integer userid, String username, String phone, String email, String password, Integer admin) {
        this.userid = userid;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"userid\":\"" + userid+ "\",\n" +
                "\"username\":\"" + username + "\",\n" +
                "\"phone\":" + phone + ",\n" +
                "\"email\":\"" + email + "\",\n" +
                "\"password\":\"" + password+ "\",\n" +
                "\"admin\":" + admin + "\n" +
                '}';
    }
}
