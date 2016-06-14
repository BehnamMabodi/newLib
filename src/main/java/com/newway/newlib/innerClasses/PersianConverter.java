package com.newway.newlib.innerClasses;

/**
 * Created by Golden Phoenix on 04/09/2015.
 */
public abstract class PersianConverter {
    public static String NumberToPersian(String str){
    char[] persianChars = {'۰','١','۲','۳','۴','۵','۶','۷','۸','٩'};
    StringBuilder builder = new StringBuilder();
    for(int i =0;i<str.length();i++) {
        if (Character.isDigit(str.charAt(i))) {
            builder.append(persianChars[(int) (str.charAt(i)) - 48]);
        } else {
            builder.append(str.charAt(i));
        }

        // if else must enabled if character is not digits
    }
        return builder.toString();
    }
    public static String NumberToPersian(String str , boolean convert) {
        if(convert)
            return NumberToPersian(str);
        else
            return str;
    }
}
