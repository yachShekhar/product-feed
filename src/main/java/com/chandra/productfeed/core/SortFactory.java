package com.chandra.productfeed.core;

public class SortFactory {
    public SortFactory(){
        throw new AssertionError();
    }
    public static Sorting getSorting(SortType sortType){
        if(SortType.RANK == sortType){
            return new RankSorting();
        }else if(SortType.DATE == sortType){
            return new DateSorting();
        }else {
            System.out.println("unknown sortype:"+sortType+" so falling back to default sorting.");
            return new RankSorting();
        }

    }
}
