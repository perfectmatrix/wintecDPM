package com.wintec.degreemap.data.model;

public class Course {
    public static final int PATHWAY_NETWORK_ENGINEERING = 0;
    public static final int PATHWAY_WEB_DEVELOPMENT = 1;
    public static final int PATHWAY_DATABASE_ARCHITECTURE = 2;
    public static final int PATHWAY_SOFTWARE_ENGINEERING = 3;

    private String code;
    private String longName;

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
