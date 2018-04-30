package com.chandra.productfeed.core;

import com.chandra.productfeed.data.Customer;
import com.chandra.productfeed.data.Feed;
import com.chandra.productfeed.data.Seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoreProcessor {
    public CoreProcessor(){
        throw new AssertionError();
    }

    public static final Map<String, Seller> sellerTable = new HashMap<String, Seller>();

    public static final Map<String, List<Feed>> sellerFeeds = new HashMap<>();

    public static final Map<String, Customer> customerTable = new HashMap<String, Customer>();

    public static final Map<String, Set<String>> sellerCustomerMap = new HashMap<>();

    public static final Map<String, Set<String>> customerSellerMap = new HashMap<>();


}
