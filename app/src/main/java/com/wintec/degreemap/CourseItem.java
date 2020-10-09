package com.wintec.degreemap;

public class CourseItem {

    private String mModuleCode;
    private String mModuleLongName;

    public CourseItem(String moduleCode, String moduleLongName) {
        mModuleCode = moduleCode;
        mModuleLongName = moduleLongName;
    }

    public String getText1() {
        return mModuleCode;
    }
    public String getText2() {
        return mModuleLongName;
    }

}
