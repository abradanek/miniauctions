package controllers;
import models.Auction;
import models.FileHelper;
import java.util.Map;


import static models.FileHelper.readBiggestAuctionNumber;


public class AuctionController {
    private static int auctionNumber = readBiggestAuctionNumber(FileHelper.getPATHNAMEAUCTON());

    public static void createAuction(Map<Integer, Auction> auctions, Integer number, Auction auction) {
        auctions.put(number, auction);

    }

    public synchronized static int getAuctionNumber() {
        return auctionNumber;
    }

    public synchronized static int setAuctionNumber() {
        auctionNumber++;
        return auctionNumber;
    }




}
