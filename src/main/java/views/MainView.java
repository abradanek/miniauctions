package views;

public class MainView {
    public static void initView(){
        System.out.println("Pick one:");
        System.out.println("1 - Log in");
        System.out.println("2 - Register");
        System.out.println("0 - Quit");
    }

    public static void wronganswer(){
        System.out.println("Wrong answer! Please type value from list");
    }
    public static void loggedInOptions() {
        System.out.println("1 - View all auctions");
        System.out.println("2 - Find auction");
        System.out.println("3 - Create an auction");
        System.out.println("4 - View category tree");
        System.out.println("0 - Quit");
    }

}
