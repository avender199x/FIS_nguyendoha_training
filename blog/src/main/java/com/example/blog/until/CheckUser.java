package com.example.blog.until;

import com.example.blog.entity.User;

import java.util.regex.Pattern;

public final class CheckUser {
    public static Boolean check(User user) {
        Boolean checkName = user.getName().length() > 5 && user.getName().length() < 40;
        Boolean checkPhone = user.getPhone().length() == 10
                && Pattern.compile("^\\d{10}$").matcher(user.getPhone()).matches();
        return checkName && checkPhone;
    }
}
