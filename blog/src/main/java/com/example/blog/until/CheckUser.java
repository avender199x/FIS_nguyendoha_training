package com.example.blog.until;

import com.example.blog.entity.User;
import com.example.blog.exception.UserNameException;
import com.example.blog.exception.UserPhoneException;

import java.util.regex.Pattern;

public final class CheckUser {
    public static void check(User user) {
        Boolean checkName = user.getName().length() > 5 && user.getName().length() < 60;
        if (!checkName) {
            throw new UserNameException("name > 5 characters and name < 60 characters");
        }
        Boolean checkPhone = user.getPhone().length() == 10
                && Pattern.compile("^\\d{10}$").matcher(user.getPhone()).matches();
        if (!checkPhone) {
            throw new UserPhoneException("phone = 10 characters and numeric phone");
        }
    }
}
