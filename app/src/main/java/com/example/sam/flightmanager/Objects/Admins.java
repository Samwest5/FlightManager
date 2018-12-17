// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// stores admin accounts for manage system activity
package com.example.sam.flightmanager.Objects;

import java.util.HashMap;

public class Admins {
    private static HashMap<String, String> admins = new HashMap<>();

    public Admins() {
        if (admins.isEmpty()) {
            admins.put("admin2", "admin2");
        }
    }

    public static boolean addAdmin(String username, String password) {
        boolean valid = checkInfo(username, password);
        if (valid) {
            admins.put(username, password);
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean checkLogin(String username, String password) {
        if (admins.containsKey(username) && admins.get(username).equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean checkInfo(String user, String pass) {
        int usernameNumNumbers = 0;
        int usernameNumLetters = 0;
        for (char ch : user.toCharArray()) {
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                usernameNumLetters += 1;
            }
            if (ch >= '0' && ch <= '9') {
                usernameNumNumbers += 1;
            }
        }

        int passwordNumLetters = 0;
        int passwordNumNumbers = 0;
        for (char ch : pass.toCharArray()) {
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                passwordNumLetters += 1;
            }
            if (ch >= '0' && ch <= '9') {
                passwordNumNumbers += 1;
            }
        }

        if (usernameNumNumbers < 1 || passwordNumNumbers < 1) {
            return false;
        }
        if (usernameNumLetters < 3 || passwordNumLetters < 3) {
            return false;
        }

        return true;
    }
}
