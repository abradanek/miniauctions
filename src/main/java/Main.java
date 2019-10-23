import controllers.AuctionController;
import exceptions.NoSuchLoginOrPasswordException;
import models.Auction;
import models.AuctionTree;
import models.FileHelper;
import models.User;


import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

import static controllers.AuctionController.createAuction;
import static controllers.UserController.isLoginPresent;
import static controllers.UserController.userVerication;
import static models.AuctionCategories.setupTree;
import static models.FileHelper.getPATHNAMEAUCTON;
import static models.FileHelper.newAuction;
import static models.User.createUser;
import static views.AuctionView.*;
import static views.MainView.*;
import static views.UserView.*;

public class Main {
    public enum State {
        INIT,
        REGISTER,
        LOG_IN,
        LOGGED_IN,
        EXIT;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        State state = State.INIT; // our first command window
        Map<String, User> users = new HashMap<>();
        User user = null; //check who is logged in
        AuctionTree auctionTree = setupTree();

        Map<Integer, Auction> auctions = FileHelper.readFromFileAuction(getPATHNAMEAUCTON());
        for (Auction au : auctions.values()) {
            setupTree().searchById(au.getCategoryId()).addAuction(au);
        }
        System.out.println("Welcome!");
        while (state != State.EXIT) {
            switch (state) {
                case INIT:
                    users = FileHelper.readFromFile(FileHelper.getPATHNAMEUSER()); // download list of registered users
                    initView();
                    String answer = sc.nextLine();
                    switch (answer) { // pick what we want to do
                        case ("1"):
                            state = State.LOG_IN;
                            break;
                        case ("2"):
                            state = State.REGISTER;
                            break;
                        case ("0"):
                            state = State.EXIT;
                            break;
                        default:
                            wronganswer();  // if we type diferent key than 0,1,2 we got error
                            break;
                    }
                    break;
                case LOG_IN:
                    enterLogin();
                    String login = sc.nextLine();
                    enterPassword();
                    String password = sc.nextLine();
                    try {
                        userVerication(login, password, users); // to avoid hints that pass or login is wrong we got one error msg
                    } catch (NoSuchLoginOrPasswordException nlpe) {
                        state = State.INIT;
                        break;
                    }
                    user = new User(login, password);  // set logged user
                    state = State.LOGGED_IN;
                    break;

                case LOGGED_IN:
                    loggedInOptions();
                    answer = sc.nextLine();

                    switch (answer) {
                        case "0":
                            state = State.EXIT;
                        case "1":
                            System.out.println();
                            viewAllAuctions(auctions);
                            System.out.println();
                            break;

                        case "2":
                            //FIND AND BID
                        case "3":
                            giveAuctionName();
                            String auname = sc.nextLine();
                            giveAuctionDescription();
                            String audescript = sc.nextLine();
                            int auctionPrice = 0;
                            givePrice();

                            try {
                                auctionPrice = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                giveDigits();
                                break;
                            }

                            int categoryId;

                            do {
                                getSpecificCategoryId();
                                viewCategories(setupTree());
                                categoryId = sc.nextInt();

                            } while (!setupTree().searchById(categoryId).isLeaf());


                            assert user != null;
                            Auction auction =
                                    new Auction(auname, audescript, user.getLogin(),
                                            auctionPrice, AuctionController.getAuctionNumber(), categoryId);
                            setupTree().searchById(categoryId).addAuction(auction);

                            createAuction(auctions, AuctionController.setAuctionNumber(), auction);
                            FileHelper.writeNewUserToFile(newAuction(auction), getPATHNAMEAUCTON());
                            state = State.LOGGED_IN;
                            break;


                        case "4":
                            viewCategories(setupTree());
                            System.out.println();
                            state = State.LOGGED_IN;
                            break;
                    }

                    break;
                case REGISTER:
                    enterLogin();
                    login = sc.nextLine().trim();
                    if (isLoginPresent(login, users)) {     //if login present - error
                        loginExist();
                        break;
                    } else {                               // if there is no login like we type we can create acc
                        enterPassword();
                        password = sc.nextLine().trim();
                        createUser(login, password);
                        state = State.INIT;
                        break;
                    }

                case EXIT:
                    break;

            }

        }

    }
}
