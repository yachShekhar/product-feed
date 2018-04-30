package com.chandra.productfeed.core;

import com.chandra.productfeed.data.Feed;

import java.util.Comparator;
import java.util.List;

public interface Sorting extends Comparator<Feed> {
    public void sort(List<Feed> feeds);
}
