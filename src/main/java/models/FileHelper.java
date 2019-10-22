package models;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHelper {
    private static final String SEPARATOR = ";";

    public static String getPATHNAME() {
        return PATHNAME;
    }

    private static final String PATHNAME = "src/main/resources/test123.txt";

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
}