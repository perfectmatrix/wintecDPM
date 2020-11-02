package com.wintec.degreemap.data.model;

public class Course {
    public static final String PATHWAY_NETWORK_ENGINEERING = "network";
    public static final String PATHWAY_WEB_DEVELOPMENT = "web";
    public static final String PATHWAY_DATABASE_ARCHITECTURE = "database";
    public static final String PATHWAY_SOFTWARE_ENGINEERING = "software";
    public static final String PATHWAY_CORE = "core";

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

    public static String getPathwayLabel(String pathway) {
        String label = "";

        switch (pathway) {
            case Course.PATHWAY_NETWORK_ENGINEERING:
                label = "Network Engineering";
                break;
            case Course.PATHWAY_WEB_DEVELOPMENT:
                label = "Web Development";
                break;
            case Course.PATHWAY_DATABASE_ARCHITECTURE:
                label = "Database Architecture";
                break;
            case Course.PATHWAY_SOFTWARE_ENGINEERING:
                label = "Software Engineering";
                break;
            default:
                label = "Core";
                break;
        }

        return label;
    }
}
