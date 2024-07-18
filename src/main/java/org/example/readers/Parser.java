package org.example.readers;

import org.example.models.Type;

public class Parser {
    public Type parse(String str) {
        if (str == null) {
            return Type.UNKNOWN;
        } else if (isInt(str)) {
            return Type.INTEGER;
        } else if (isFloat(str)) {
            return Type.FLOAT;
        } else {
            return Type.STRING;
        }
    }

    private Boolean isInt(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
