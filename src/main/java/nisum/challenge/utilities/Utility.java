package nisum.challenge.utilities;

import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utility {

    private static final Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private Utility() {
        throw new java.lang.UnsupportedOperationException("Utility class and cannot be instantiated");
    }

    public static boolean validateEmailStructure(String email) {
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }
    public static boolean validatePasswordStructure(String password, String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


}