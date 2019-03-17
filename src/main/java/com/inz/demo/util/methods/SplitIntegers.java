package com.inz.demo.util.methods;

import java.util.Arrays;

public class SplitIntegers {

    public static int[] splitStringToIntArrays(String _string) {

        return Arrays.stream(_string.substring(1, _string.length() - 1).split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
}
