package com.wintec.degreemap.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.util.ArrayList;
import java.util.Arrays;

import static com.wintec.degreemap.util.Constants.KEY_COMPLETED_MODULES;
import static com.wintec.degreemap.util.Constants.PATHWAY_CORE;
import static com.wintec.degreemap.util.Constants.PATHWAY_CORE_LABEL;
import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE_LABEL;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING_LABEL;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING_LABEL;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT_LABEL;

public final class Helpers {
    public static String getFileExtension(Context context, Uri uri) {
        if (uri == null)
            return "";

        ContentResolver contentResolver = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public static String getPathwayLabel(ArrayList<String> pathway) {
        return pathway != null ? pathway.toString()
                .replace(PATHWAY_NETWORK_ENGINEERING, PATHWAY_NETWORK_ENGINEERING_LABEL)
                .replace(PATHWAY_WEB_DEVELOPMENT, PATHWAY_WEB_DEVELOPMENT_LABEL)
                .replace(PATHWAY_DATABASE_ARCHITECTURE, PATHWAY_DATABASE_ARCHITECTURE_LABEL)
                .replace(PATHWAY_SOFTWARE_ENGINEERING, PATHWAY_SOFTWARE_ENGINEERING_LABEL)
                .replace(PATHWAY_CORE, PATHWAY_CORE_LABEL)
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
