package com.chandra.productfeed.controller;

import com.chandra.productfeed.core.SortFactory;
import com.chandra.productfeed.core.SortType;
import com.chandra.productfeed.data.Customer;
import com.chandra.productfeed.data.Feed;
import com.chandra.productfeed.util.Utils;
import com.chandra.productfeed.core.CoreProcessor;

import java.util.*;

public class CustomerController {

    public Customer createCustomer(Customer customer){
        if(customer.getId() == null){
            customer.setId(Utils.generateId());
        }
        CoreProcessor.customerTable.put(customer.getId(), customer);
        return customer;
    }

    public void subscribe(String customerId, String sellerId){
        if(CoreProcessor.sellerTable.get(sellerId) != null && CoreProcessor.customerTable.get(customerId) != null){
            Set<String> customerSellerIds = CoreProcessor.sellerCustomerMap.get(sellerId);
            Set<String> sellerCustomerIds = CoreProcessor.customerSellerMap.get(customerId);
            customerSellerIds = customerSellerIds == null ? new HashSet<>() : customerSellerIds;
            sellerCustomerIds = sellerCustomerIds == null ? new HashSet<>() : sellerCustomerIds;

            customerSellerIds.add(customerId);
            sellerCustomerIds.add(sellerId);

            CoreProcessor.sellerCustomerMap.put(sellerId, customerSellerIds);
            CoreProcessor.customerSellerMap.put(customerId, sellerCustomerIds);
        }

    }
    public  void unsubscribe(String customerId, String sellerId){
        Set<String> sellerIds = CoreProcessor.customerSellerMap.get(customerId);
        sellerIds.remove(new Object(){
            @Override public boolean equals(Object obj) {
                return sellerId.equals(obj);
            }
        });
        Set<String> customerIds = CoreProcessor.sellerCustomerMap.get(sellerId);
        customerIds.remove(new Object(){
            @Override public boolean equals(Object obj) {
                return customerId.equals(obj);
            }
        });
    }

    public List<Feed> fetchFeed(String customerId, SortType sortType){
        Set<String> sellerIds = CoreProcessor.customerSellerMap.get(customerId);
        List<Feed> allFeeds = new ArrayList<>();
        if(sellerIds != null){
            sellerIds.forEach(s->{
                List<Feed> feeds = CoreProcessor.sellerFeeds.get(s);
                allFeeds.addAll(feeds);
            });
        }
        SortFactory.getSorting(sortType).sort(allFeeds);
        return allFeeds;
    }


}
