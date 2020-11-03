package com.wintec.degreemap.data.model;

public class Course {
    private String key;
    private String code;
    private int credit;
    private String description;
    private String longName;
    private int level;
    private int semester;
    private String type;
    private String url;
    private int year;

    public Course() {
    }

    public Course(String key, String code, int credit, String description, String longName, int level, int semester, String type, String url, int year) {
        this.key = key;
        this.code = code;
        this.credit = credit;
        this.description = description;
        this.longName = longName;
        this.level = level;
        this.semester = semester;
        this.type = type;
        this.url = url;
        this.year = year;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public int getCredit() { return credit; }

    public String getDescription() {
        return description;
    }

    public String getLongName() { return longName; }

    public int getLevel() { return level; }

    public int getSemester() {
        return semester;
    }

    public String getType() { return type; }

    public String getUrl() {
        return url;
    }

    public int getYear() {
        return year;
    }
}
