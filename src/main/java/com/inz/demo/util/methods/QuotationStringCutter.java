package com.inz.demo.util.methods;

public class QuotationStringCutter {

    private QuotationStringCutter() {
    }

    public static String cutString(String string) {
        return string.substring(1, string.length() - 1);
    }
}
