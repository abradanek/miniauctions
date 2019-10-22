import controllers.UserController;
import exceptions.NoSuchLoginOrPasswordException;
import models.FileHelper;
import models.User;
import views.MainView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static controllers.UserController.isLoginPresent;
import static controllers.UserController.userVerication;
import static models.User.createUser;
import static views.MainView.initView;
import static views.MainView.wronganswer;
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

        System.out.println("Welcome!");
        while (state != State.EXIT) {
            switch (state) {
                case INIT:
                    users = FileHelper.readFromFile(FileHelper.getPATHNAME()); // download list of registered users
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
                    } catch (NoSuchLoginOrPasswordException nlpe){
                        state = State.INIT;
                        break;
                    }
                    user = new User(login, password);  // set logged user
                    state = State.LOGGED_IN;
                    break;

                case LOGGED_IN:
                    System.out.println("you are good!");

                    break;
                case REGISTER:
                    enterLogin();
                     login = sc.nextLine().trim();
                     if (isLoginPresent(login,users)) {     //if login present - error
                         loginExist();
                         break;
                     }else {                               // if there is no login like we type we can create acc
                         enterPassword();
                         password = sc.nextLine().trim();
                         createUser(login,password);
                         state= State.INIT;
                         break;
                     }

                case EXIT:
                    break;

            }

        }

    }
}
