package com.wintec.degreemap.data.model;

public class Course {
    public static final String PATHWAY_NETWORK_ENGINEERING = "network";
    public static final String PATHWAY_WEB_DEVELOPMENT = "web";
    public static final String PATHWAY_DATABASE_ARCHITECTURE = "database";
    public static final String PATHWAY_SOFTWARE_ENGINEERING = "software";

    private String code;
    private int credit;
    private String description;
    private String longName;
    private int semester;
    private String type;
    private String url;
    private int year;

    public Course() {
    }

    public Course(String code, int credit, String description, String longName, int semester, String type, String url, int year) {
        this.code = code;
        this.credit = credit;
        this.description = description;
        this.longName = longName;
        this.semester = semester;
        this.type = type;
        this.url = url;
        this.year = year;
    }

    public String getCode() {
        return code;
    }

    public int getCredit() {
        return credit;
    }

    public String getDescription() {
        return description;
    }

    public String getLongName() {
        return longName;
    }

    public int getSemester() {
        return semester;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getYear() {
        return year;
    }
}
