package pairmatching.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {
    private RegexUtils() {
    }

    public static Matcher requireMatches(Pattern pattern, String input, String errorMessage) {
        Objects.requireNonNull(pattern, "pattern");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw iae(errorMessage, input);
        }
        return matcher;
    }

    public static List<MatchResult> requireFindAtLeastOne(Pattern pattern, String input, String errorMessage) {
        List<MatchResult> results = findAll(pattern, input);
        if (results.isEmpty()) {
            throw iae(errorMessage, input);
        }
        return results;
    }

    private static List<MatchResult> findAll(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        List<MatchResult> results = new ArrayList<>();
        while (matcher.find()) {
            results.add(matcher.toMatchResult());
        }
        return results;
    }

    public static IllegalArgumentException iae(String baseMessage, String input) {
        if (input == null) {
            return new IllegalArgumentException(baseMessage + " (입력값: \"null\")");
        }
        return new IllegalArgumentException(baseMessage + " (입력값: \"" + input + "\")");
    }
}
