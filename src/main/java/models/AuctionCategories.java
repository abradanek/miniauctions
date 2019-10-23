package models;

public class AuctionCategories {
    public static AuctionTree setupTree() {
        final AuctionTree root = new AuctionTree(0, "Categories");
        final AuctionTree child1 = new AuctionTree(1, "Cars");
        final AuctionTree child11 = new AuctionTree(11, "To 3,5t");
        final AuctionTree child111 = new AuctionTree(111, "Sedan");
        final AuctionTree child112 = new AuctionTree(112, "SUV");
        final AuctionTree child113 = new AuctionTree(113, "Crossover");
        final AuctionTree child114 = new AuctionTree(114, "Coupe");
        final AuctionTree child115 = new AuctionTree(115, "Convertible");
        final AuctionTree child116 = new AuctionTree(116, "Hatchback");
        final AuctionTree child12 = new AuctionTree(12, "Over 3,5t");
        final AuctionTree child121 = new AuctionTree(121, "Concrete transport truck");
        final AuctionTree child122 = new AuctionTree(122, "Mobile crane");
        final AuctionTree child123 = new AuctionTree(123, "Dump truck");
        final AuctionTree child124 = new AuctionTree(124, "Garbage truck");
        final AuctionTree child125 = new AuctionTree(125, "Tank truck");
        final AuctionTree child126 = new AuctionTree(126, "Tractor unit");
        final AuctionTree child2 = new AuctionTree(2, "Electronics");
        final AuctionTree child21 = new AuctionTree(21, "Computers");
        final AuctionTree child22 = new AuctionTree(21, "Speakers");
        final AuctionTree child23 = new AuctionTree(21, "TV");
        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(child11);
        child11.addChild(child111);
        child11.addChild(child112);
        child11.addChild(child113);
        child11.addChild(child114);
        child11.addChild(child115);
        child11.addChild(child116);
        child1.addChild(child12);
        child12.addChild(child121);
        child12.addChild(child122);
        child12.addChild(child123);
        child12.addChild(child124);
        child12.addChild(child125);
        child12.addChild(child126);
        child2.addChild(child21);
        child2.addChild(child22);
        child2.addChild(child23);
        return root;
    }

}
