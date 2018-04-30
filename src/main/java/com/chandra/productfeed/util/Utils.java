package com.chandra.productfeed.util;

import java.util.ResourceBundle;
import java.util.UUID;

public class Utils {
    private static  ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private Utils(){
        throw new AssertionError();
    }

    public static String generateId(){
        return UUID.randomUUID().toString();
    }

    public static String feedSortingAttribute(){
        return resourceBundle.getString(Constants.FEED_SORTING_RANK_ATTRIBUTE_KEY_NAME);
    }
}
