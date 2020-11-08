package com.wintec.degreemap.data.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.ArrayList;

public class Course extends BaseObservable {
    private String code;
    private ArrayList<String> coRequisite;
    private int credit;
    private String description;
    private String longName;
    private int level;
    private ArrayList<String> preRequisite;
    private int semester;
    private ArrayList<String> pathway;
    private String url;
    private int year;

    // Required empty constructor
    public Course() {
    }

    public Course(String code, ArrayList<String> coRequisite, int credit, String description, String longName, int level, ArrayList<String> preRequisite, int semester, ArrayList<String> pathway, String url, int year) {
        this.code = code;
        this.coRequisite = coRequisite;
        this.credit = credit;
        this.description = description;
        this.longName = longName;
        this.level = level;
        this.preRequisite = preRequisite;
        this.semester = semester;
        this.pathway = pathway;
        this.url = url;
        this.year = year;
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<String> getCoRequisite() {
        return coRequisite;
    }

    public void setCoRequisite(ArrayList<String> coRequisite) {
        this.coRequisite = coRequisite;
    }

    @Bindable
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Bindable
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<String> getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(ArrayList<String> preRequisite) {
        this.preRequisite = preRequisite;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public ArrayList<String> getPathway() {
        return pathway;
    }

    public void setPathway(ArrayList<String> pathway) {
        this.pathway = pathway;
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
