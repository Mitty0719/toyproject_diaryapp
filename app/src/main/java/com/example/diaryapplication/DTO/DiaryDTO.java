package com.example.diaryapplication.DTO;

public class DiaryDTO {
    private int id;
    private String weather;
    private String content;
    private String date;

    public DiaryDTO(){}
    public DiaryDTO(String weather, String content, String date) {
        this.weather = weather;
        this.content = content;
        this.date = date;
    }
    public DiaryDTO(int id, String weather, String content, String date) {
        this.id = id;
        this.weather = weather;
        this.content = content;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DiaryDTO{" +
                "id=" + id +
                ", weather='" + weather + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
