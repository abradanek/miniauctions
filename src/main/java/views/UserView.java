package views;

public class UserView {
    public static void enterLogin() {
        System.out.println("Enter your login: ");
    }

    public static void enterPassword() {
        System.out.println("Enter your password: ");
    }

    public static void wrongLoginOrPassword() {
        System.out.println("Wrong login or password");
    }

    public static void loginExist() {
        System.out.println("Sorry, login already exist. Please enter new one: ");
    }
}
