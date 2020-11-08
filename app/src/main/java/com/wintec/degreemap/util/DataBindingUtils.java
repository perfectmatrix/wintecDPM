package com.wintec.degreemap.util;

import android.text.TextUtils;

import java.util.ArrayList;

public final class DataBindingUtils {
    public static String arrayToString(ArrayList<String> value) {
        return value == null || value.isEmpty()
                ? "None"
                : value.toString().replace("[", "").replace("]", "");
    }

    public static String arrayToAutoComplete(ArrayList<String> value) {
        return value == null || value.isEmpty()
                ? ""
                : value.toString().replace("[", "").replace("]", "").concat(", ");
    }
}
