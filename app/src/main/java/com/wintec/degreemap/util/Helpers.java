package com.wintec.degreemap.util;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.wintec.degreemap.data.model.Course;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.KEY_COMPLETED_MODULES;
import static com.wintec.degreemap.util.Constants.PATHWAY_CORE;
import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public final class Helpers {

    public static String getPathwayLabel(ArrayList<String> pathway) {
        return pathway != null ? pathway.toString()
                .replace(PATHWAY_NETWORK_ENGINEERING, "Network Engineering")
                .replace(PATHWAY_WEB_DEVELOPMENT, "Web Development")
                .replace(PATHWAY_DATABASE_ARCHITECTURE, "Database Architecture")
                .replace(PATHWAY_SOFTWARE_ENGINEERING, "Software Engineering")
                .replace(PATHWAY_CORE, "Core")
                .replace("[", "")
                .replace("]", "") : "";
    }

    // Load completed modules from SharedPreferences
    public static ArrayList<String> getCompletedModules(SharedPreferences prefs) {
        String completedModulesList = prefs.getString(KEY_COMPLETED_MODULES, "");

        // Get previously selected modules if there is any
        if (!completedModulesList.isEmpty()) {
            return new ArrayList<>(Arrays.asList(TextUtils.split(completedModulesList, ",")));
        } else {
            return new ArrayList<>();
        }
    }
}
