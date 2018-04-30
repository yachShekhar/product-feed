package com.chandra.productfeed.controller;

import com.chandra.productfeed.data.Feed;
import com.chandra.productfeed.data.Seller;
import com.chandra.productfeed.core.CoreProcessor;
import com.chandra.productfeed.util.Utils;

import java.util.*;

public class SellerController {

    public Seller createSeller(Seller seller){
        if(seller.getId() == null){
            seller.setId(Utils.generateId());
        }
        CoreProcessor.sellerTable.put(seller.getId(), seller);
        return seller;
    }

    public void deleteSeller(final String id){
        CoreProcessor.sellerTable.remove(id);
        CoreProcessor.sellerFeeds.remove(id);
        Set<String> customers = CoreProcessor.sellerCustomerMap.get(id);
        if(customers != null){
            customers.stream().forEach(c->{
                Set<String> sellers = CoreProcessor.customerSellerMap.get(c);
                if(sellers != null){
                    sellers.remove(new Object(){
                        @Override public boolean equals(Object obj) {
                            return id.equals(obj);
                        }
                    });
                }
            });
        }
    }

    public void publishPost(String sellerId, Feed feed){
        if(CoreProcessor.sellerTable.get(sellerId) == null){
            throw new IllegalArgumentException("Seller:"+sellerId+" not registered");
        }

        if(feed.getId() == null){
            feed.setId(Utils.generateId());
        }
        List<Feed> sellerFeeds = CoreProcessor.sellerFeeds.get(sellerId);
        sellerFeeds = sellerFeeds == null ? new ArrayList<Feed>() : sellerFeeds;
        sellerFeeds.add(feed);
        CoreProcessor.sellerFeeds.put(sellerId, sellerFeeds);
    }

    public void deletePost(String sellerId, String id){
        List<Feed> feeds = CoreProcessor.sellerFeeds.get(sellerId);
        if(feeds != null){
            feeds.remove(new Object(){
               public boolean equals(Object o1){
                   return id.equals(((Feed)o1).getId());
               };
            });
        }
    }

}
