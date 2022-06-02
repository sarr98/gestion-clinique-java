package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static Matcher matcher;

    public static boolean isUsername(String text) {
        String expression = "^[a-zA-Z\\s]*$";
        CharSequence inputStr = text;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    public static boolean adresse(String adresse) {
      /*  Boolean reponse = false;
        if (adresse.matches("^[A-Z a-z 0-9]+$")) {
            reponse =  true;
        }
        return reponse;*/
        String expression = "^[a-zA-Z\\s]*$";
        CharSequence inputStr = adresse;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    public static boolean iscin(String nci) {
        String nciRegex = "(^[1|2])([0-9]{3})(19[4-9]\\d|20[0-1]\\d|2020|2021)([0-9]{4})";
        Pattern pat = Pattern.compile(nciRegex);
        if (nci == null || nci == "")
            return false;

        return pat.matcher(nci).matches();
    }

    public static boolean isTel(String telephone) {
        String telephoneRegex = "^(\\+|00)(221)(70|76|77|78|33)([0-9]{6})";
        Pattern pat = Pattern.compile(telephoneRegex);
        if (telephone == null || telephone == "" )
            return false;


        return pat.matcher(telephone).matches();
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
