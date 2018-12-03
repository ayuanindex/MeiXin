package com.ayuan.vo;

public class Comment {
    private String menuid;
    private String region;
    private String date;
    private String hours;
    private String seconds;
    private String month;
    private String nanos;
    private String timezoneOffset;
    private String year;
    private String minutes;
    private String time;
    private String day;
    private String content;
    private int cid;

    public Comment(String menuid, String region, String date, String hours, String seconds, String month, String nanos, String timezoneOffset, String year, String minutes, String time, String day, String content, int cid) {
        this.menuid = menuid;
        this.region = region;
        this.date = date;
        this.hours = hours;
        this.seconds = seconds;
        this.month = month;
        this.nanos = nanos;
        this.timezoneOffset = timezoneOffset;
        this.year = year;
        this.minutes = minutes;
        this.time = time;
        this.day = day;
        this.content = content;
        this.cid = cid;
    }
    public Comment() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNanos() {
        return nanos;
    }

    public void setNanos(String nanos) {
        this.nanos = nanos;
    }

    public String getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(String timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
