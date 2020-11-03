package com.wintec.degreemap.util;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.wintec.degreemap.data.model.Course;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.KEY_COMPLETED_MODULES;
import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public final class Helpers {

    public static String getPathwayLabel(String pathway) {
        String label = "";

        switch (pathway) {
            case PATHWAY_NETWORK_ENGINEERING:
                label = "Network Engineering";
                break;
            case PATHWAY_WEB_DEVELOPMENT:
                label = "Web Development";
                break;
            case PATHWAY_DATABASE_ARCHITECTURE:
                label = "Database Architecture";
                break;
            case PATHWAY_SOFTWARE_ENGINEERING:
                label = "Software Engineering";
                break;
            default:
                label = "Core";
                break;
        }

        return label;
    }

    // Load completed modules from SharedPreferences
    public static ArrayList<String> getCompletedModules(SharedPreferences prefs) {
        String completedModulesList = prefs.getString(KEY_COMPLETED_MODULES, "");

        // Get previously selected modules if there is any
        if(!completedModulesList.isEmpty()) {
            return new ArrayList<>(Arrays.asList(TextUtils.split(completedModulesList, ",")));
        } else {
            return new ArrayList<>();
        }
    }
}
