package com.wintec.degreemap.data.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String code;
    private ArrayList<String> coRequisite;
    private int credit;
    private String description;
    private String longName;
    private int level;
    private ArrayList<String> preRequisite;
    private int semester;
    private String type;
    private String url;
    private int year;

    public Course() {
    }

    public Course(String code, ArrayList<String> coRequisite, int credit, String description, String longName, int level, ArrayList<String> preRequisite, int semester, String type, String url, int year) {
        this.code = code;
        this.coRequisite = coRequisite;
        this.credit = credit;
        this.description = description;
        this.longName = longName;
        this.level = level;
        this.preRequisite = preRequisite;
        this.semester = semester;
        this.type = type;
        this.url = url;
        this.year = year;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<String> getCoRequisite() {
        return coRequisite;
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

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getPreRequisite() {
        return preRequisite;
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
