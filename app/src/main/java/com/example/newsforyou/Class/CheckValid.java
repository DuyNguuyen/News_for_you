package com.example.newsforyou.Class;

import android.text.TextUtils;
import android.util.Patterns;

public class CheckValid {
    public static boolean isValidName(String name){
        return !TextUtils.isEmpty(name);
    }

    public static boolean isValidEmailAddress(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password){
        return !TextUtils.isEmpty(password) && password.length() >= 8;
    }

    public static boolean isMatchPassword(String password, String rePassword){
        return password.equals(rePassword);
    }
}
