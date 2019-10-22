package controllers;

import exceptions.NoSuchLoginOrPasswordException;
import models.User;

import java.util.Map;

public class UserController {

    public static boolean isLoginPresent(String login, Map<String, User> user) {
        return user.containsKey(login);
    }  // check if login already exist in our base

    public static boolean isLoginConectToPassword(String login, String password, Map<String, User> users) {
        boolean isConnected = false;
        try {
            isConnected = users.get(login).getPassword().equals(password);
        } catch (NullPointerException npe) {
            System.out.println("Wrong login or password");
        }
        return isConnected;
    } // check if password fit to login

    public static void userVerication(String login, String password, Map<String, User> users) {
        if (!isLoginConectToPassword(login, password, users) || !isLoginPresent(login, users)) {
            throw new NoSuchLoginOrPasswordException();
        }
    } // one error if we type wrong password or login
}
