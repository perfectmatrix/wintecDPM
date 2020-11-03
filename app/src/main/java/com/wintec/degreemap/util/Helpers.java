package com.wintec.degreemap.util;

import com.wintec.degreemap.data.model.Course;

import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;

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
}
