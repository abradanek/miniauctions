package views;

import models.Auction;
import models.AuctionTree;

import java.util.Map;

public class AuctionView {
    public static void giveAuctionName() {
        System.out.println("Enter auction name: ");
    }
    public static void giveAuctionDescription() {
        System.out.println("Enter auction details: ");
    }
    public static void givePrice() {
        System.out.println("Enter product price: ");
    }
    public static void giveDigits() { System.out.println("Please enter digits: "); }
    public static void getSpecificCategoryId() {
        System.out.println("Input category Id from list.");
    }

    public static void viewAllAuctions(Map<Integer, Auction> auctions) {
        for (Integer key : auctions.keySet()) {
            System.out.println(key + " " + auctions.get(key));
        }
    }

    public static void viewCategories(AuctionTree root) {
        System.out.println(root);
        for (AuctionTree child : root.getChildren()) {
            for (int i = 0; i < child.branchesToRoot(); i++) {
                System.out.print("   ");
            }
            viewCategories(child);
        }
    }





}
