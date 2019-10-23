package models;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static controllers.AuctionController.getAuctionNumber;

public class FileHelper {
    private static final String SEPARATOR = ";";
    private static final String PATHNAMEUSER = "src/main/resources/user.txt";
    private static final String PATHNAMEAUCTION = "src/main/resources/auction.txt";

    public static String getPATHNAMEUSER() {
        return PATHNAMEUSER;
    }

    public static String getPATHNAMEAUCTON() {
        return PATHNAMEAUCTION;
    }

    // USER
    public String newUser(User user) {
        return user.getLogin() + SEPARATOR + user.getPassword();
    }

    public static void writeNewUserToFile(String who, String filePath) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.print(who + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> readFromFile(String filepath) {
        File file = new File(filepath);
        Map<String, User> users = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                User user = new User(parseEntry(line)[0], parseEntry(line)[1]);
                users.put(user.getLogin(), user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static String[] parseEntry(String line) {
        return line.split(SEPARATOR);
    }// to separate login from password - format in *.txt file is login;password
//AUCTIONS

    public static String newAuction(Auction auction) {
        String newAuction = "";
        newAuction += getAuctionNumber() + SEPARATOR;
        newAuction += auction.getName() + SEPARATOR;
        newAuction += auction.getDescription() + SEPARATOR;
        newAuction += auction.getSettingUser() + SEPARATOR;
        newAuction += auction.getBiddingUser() + SEPARATOR;
        newAuction += auction.getPrice() + SEPARATOR;
        newAuction += auction.getAuctionId() + SEPARATOR;
        newAuction += auction.getCategoryId();
        return newAuction;
    }

    public static Integer readBiggestAuctionNumber(String filepath) {
        File file = new File(filepath);
        int biggestNumber = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                if (Integer.valueOf(parseEntry(line)[0]) > biggestNumber) {
                    biggestNumber = Integer.valueOf(parseEntry(line)[0]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return biggestNumber;
    }
    public static Map<Integer, Auction> readFromFileAuction(String filepath) {
        File file = new File(filepath);
        Map<Integer, Auction> auctions = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                Auction auction = new Auction(parseEntry(line)[1], parseEntry(line)[2],
                        parseEntry(line)[3], Integer.valueOf(parseEntry(line)[5]),
                        Integer.valueOf(parseEntry(line)[6]) - 1000, Integer.valueOf(parseEntry(line)[7]));
                auction.setBiddingUser(parseEntry(line)[4]);
                auctions.put(Integer.parseInt(parseEntry(line)[0]), auction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auctions;
    }

}