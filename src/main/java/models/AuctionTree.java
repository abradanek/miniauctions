package models;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AuctionTree {
    private int id;
    private String name;
    private AuctionTree parent;
    private List<AuctionTree> children;
    private List<Auction> auctions;

    public AuctionTree(int id, String name) {
        this.id = id;
        this.name = name;
        auctions = new ArrayList<>();
        children = new LinkedList<>();
    }

    public List<AuctionTree> getChildren() {
        return children;
    }

    private AuctionTree getParent() {
        return parent;
    }

    public void setParent(AuctionTree parent) {
        this.parent = parent;
    }

    public void addChild(AuctionTree child) {
        child.setParent(this);
        this.children.add(child);
    }
    public void addAuction(Auction auction) {
        this.auctions.add(auction);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
    public AuctionTree searchById(int categoryID) {
        if (categoryID == this.id) {
            return this;
        } else {
            for (AuctionTree child : this.children) {
                if (child.searchById(categoryID) != null) {
                    return child.searchById(categoryID);
                }
            }
        }
        return null;
    }
    public boolean isRoot() {
        return parent == null;
    }

    public int branchesToRoot() {
        int level = 0;
        if (!isRoot()) {
            level = this.getParent().branchesToRoot() + 1;
        }
        return level;
    }
    public List<Auction> getAuctions() {
        return auctions;
    }
    @Override
    public String toString() {
        return id + ". " + name;
    }
}
