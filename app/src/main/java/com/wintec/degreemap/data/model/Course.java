package com.wintec.degreemap.data.model;

public class Course {
    public static final String PATHWAY_NETWORK_ENGINEERING = "network";
    public static final String PATHWAY_WEB_DEVELOPMENT = "web";
    public static final String PATHWAY_DATABASE_ARCHITECTURE = "database";
    public static final String PATHWAY_SOFTWARE_ENGINEERING = "software";

    private String code;
    private String longName;

    public Course() {
    }

    public Course(String code, String longName) {
        this.code = code;
        this.longName = longName;
    }

    public String getCode() {
        return this.code;
    }

    public String getLongName() {
        return this.longName;
    }
}
