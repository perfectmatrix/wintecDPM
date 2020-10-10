package com.wintec.degreemap.data.model;

public class CourseItem {
    public static final int PATHWAY_NETWORK_ENGINEERING = 0;
    public static final int PATHWAY_WEB_DEVELOPMENT = 1;
    public static final int PATHWAY_DATABASE_ARCHITECTURE = 2;
    public static final int PATHWAY_SOFTWARE_ENGINEERING = 3;

    private String mModuleCode;
    private String mModuleLongName;

    public CourseItem(String moduleCode, String moduleLongName) {
        mModuleCode = moduleCode;
        mModuleLongName = moduleLongName;
    }

    public String getModuleCode() {
        return mModuleCode;
    }

    public String getModuleLongName() {
        return mModuleLongName;
    }
}
