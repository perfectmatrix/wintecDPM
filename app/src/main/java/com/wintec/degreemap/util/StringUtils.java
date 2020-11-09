package com.wintec.degreemap.util;

import java.util.ArrayList;
import java.util.Arrays;

public final class StringUtils {

    public static ArrayList<String> convertToArrayList(String value) {
        if (value.isEmpty() || value == null)
            return new ArrayList<>();

        return new ArrayList<>(Arrays.asList(value.split("\\s*,\\s*")));
    }
}
