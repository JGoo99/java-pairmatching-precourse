package pairmatching.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pairmatching.domain.MatchingInput;
import pairmatching.util.Parsers;
import pairmatching.util.Retry;

public class InputView {
    public static boolean readYesOrNo() {
        return Retry.untilSuccess(() -> {
            String yesOrNo = readNonBlankLine();
            return Parsers.parseYesOrNo(yesOrNo);
        });
    }

    public static String readNonBlankLine() {
        return Retry.untilSuccess(() -> Parsers.parseNonBlank(readTrimmedLineOrThrow()));
    }

    private static String readTrimmedLineOrThrow() {
        String line = Console.readLine();
        if (line == null) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다." + "(입력값: \"" + line + "\")");
        }
        return line.trim();
    }

    public String readMenu() {
        return Retry.untilSuccess(() -> {
            String menu = readNonBlankLine();
            Pattern pattern = Pattern.compile("^(1|2|3|Q)+$");
            Matcher matcher = pattern.matcher(menu);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("[ERROR] 기능 입력 형식이 잘못되었습니다." + "(입력값: " + menu + ")");
            }
            return menu;
        });
    }

    public MatchingInput readMatchingInput() {
        return Retry.untilSuccess(() -> {
            String line = readNonBlankLine();
            return Parsers.parseMatchingRequirement(line);
        });
    }
}
