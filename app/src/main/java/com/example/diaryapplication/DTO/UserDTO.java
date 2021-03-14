package com.example.diaryapplication.DTO;

import java.util.Date;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String regDate;

    public UserDTO(){}
    public UserDTO(String name, String email, String password, String regDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegdate() {
        return regDate;
    }

    public void setRegdate(String regdate) {
        this.regDate = regdate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", regdate=" + regDate +
                '}';
    }
}
