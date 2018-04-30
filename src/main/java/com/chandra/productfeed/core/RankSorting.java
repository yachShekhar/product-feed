package com.chandra.productfeed.core;

import com.chandra.productfeed.data.Feed;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankSorting implements Sorting, Comparator<Feed> {

    @Override
    public int compare(Feed o1, Feed o2) {
            return Integer.valueOf(o1.getRank()).compareTo(Integer.valueOf(o2.getRank()));
    }

    @Override public void sort(List<Feed> feeds) {
        Collections.sort(feeds, this);
    }
}
