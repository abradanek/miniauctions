package models;

public class User {
    private String login;
    private String password;
    private static FileHelper fileHelper = new FileHelper();


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public static void createUser(String login, String password) {
        User user = new User(login, password);

        FileHelper.writeNewUserToFile(fileHelper.newUser(user),FileHelper.getPATHNAME());
    }
}