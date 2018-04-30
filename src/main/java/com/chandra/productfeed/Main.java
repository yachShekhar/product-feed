package com.chandra.productfeed;

import com.chandra.productfeed.controller.SellerController;
import com.chandra.productfeed.data.Customer;
import com.chandra.productfeed.data.Feed;
import com.chandra.productfeed.data.Seller;
import com.chandra.productfeed.controller.CustomerController;
import com.chandra.productfeed.core.SortType;

import java.util.Date;
import java.util.List;

public class Main {
    private static SellerController sellerController = new SellerController();
    private static CustomerController customerController = new CustomerController();

    public static void main(String[] args){
        createSeller();
        publishPost();

        createCustomer();

        subscribe();

        feeds();
    }

    private static void createSeller(){
        Seller seller = new Seller();
        seller.setId("s1");
        seller.setName("flipkart");
        sellerController.createSeller(seller);
        System.out.println("seller created"+ seller);
    }

    public static void deleteSeller(){
        String sellerId = "s1";
        sellerController.deleteSeller(sellerId);
        System.out.println("seller deleted"+ sellerId);
    }

    public static void publishPost(){

        Feed feed = generateFeed(1, 1);
        Feed feed1 = generateFeed(2, 2);
        sellerController.publishPost("s1", feed);
//        sellerController.publishPost("s1", feed1);
        System.out.println("feed:"+feed.getId()+ " posted by seller:"+"s1");
    }


    public static void deletePost(){
        String sellerId = "s1", feedId = "f1";
        sellerController.deletePost( sellerId, feedId);
        System.out.println("post deleted by seller");
    }
    private static Feed generateFeed(int seq, int rank){
        Feed feed = new Feed();
        feed.setId("f"+seq);
        feed.setCreatedAt(new Date());
        feed.setRank(rank);
        feed.setValue("feed "+seq);
        return feed;
    }


    private static void createCustomer(){
        Customer customer = new Customer();
        customer.setId("c1");
        customer.setName("chandra");
        customerController.createCustomer(customer);
        System.out.println("customer created"+ customer);
    }

    public static void subscribe(){
        String customerId = "c1", sellerId = "s1";
        customerController.subscribe(customerId, sellerId);
        System.out.println("customer subscribed to seller");
    }

    public static void feeds(){
        String customerId = "c1";
        List<Feed> feedList = customerController.fetchFeed(customerId, SortType.RANK);
        if(feedList != null){
            feedList.forEach(c->{
                System.out.println("id:"+c.getId()+" value "+c.getValue());
            });
        }
    }

}
