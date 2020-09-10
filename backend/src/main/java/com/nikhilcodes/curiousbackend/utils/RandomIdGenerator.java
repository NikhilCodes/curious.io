package com.nikhilcodes.curiousbackend.utils;

import java.util.Random;

public class RandomIdGenerator {
    public static int generate() {
        int upperBound = 999999999;
        int lowerBound = 1000;
        return lowerBound + new Random().nextInt(upperBound - lowerBound);
    }
}
