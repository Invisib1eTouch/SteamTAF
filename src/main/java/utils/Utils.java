package utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static int getRandomNumber(int minInclusive, int maxExclusive) {
        return RandomUtils.nextInt(minInclusive, maxExclusive);
    }

    public static Double getNumberFormString(String text){
        Matcher m = Pattern.compile("\\d+(?:\\.?\\d*)").matcher(Objects.requireNonNull(text));
        if (m.find())
            return Double.parseDouble(m.group(0));
        throw new IllegalStateException(String.format("No pattern match found in provided text: %s", text));
    }

    public static String replaceStringWithoutTradeMark(String text){
        return text.replace("™", "");
    }
}
