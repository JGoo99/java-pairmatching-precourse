package pairmatching.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingInput;
import pairmatching.service.MissionRegister;

public final class Parsers {
    private Parsers() {
    }

    public static String parseNonBlank(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다." + "(입력값: \"" + s + "\")");
        }
        return s.trim();
    }

    public static int parseIntStrict(String s) {
        try {
            return Integer.parseInt(parseNonBlank(s));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요." + "(입력값: \"" + s + "\")");
        }
    }

    public static int parseIntInRange(String s, int min, int max) {
        int n = parseIntStrict(s);
        if (n < min || n > max) {
            throw new IllegalArgumentException(
                "[ERROR] 입력 값은 " + min + "~" + max + " 사이여야 합니다." + "(입력값: \"" + s + "\")");
        }
        return n;
    }

    public static long parseLongStrict(String s) {
        try {
            return Long.parseLong(parseNonBlank(s));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요." + "(입력값: \"" + s + "\")");
        }
    }

    public static long parseLongInRange(String s, long min, long max) {
        long n = parseLongStrict(s);
        if (n < min || n > max) {
            throw new IllegalArgumentException(
                "[ERROR] 입력 값은 " + min + "~" + max + " 사이여야 합니다." + "(입력값: \"" + s + "\")");
        }
        return n;
    }

    public static List<Crew> parseCrews(String filename) {
        List<String> crews = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8)) {
            String line;
            int lineNo = 0;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("^(?<name>\\S+)$");
                Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException(
                        "[ERROR] 파일 내용의 형식이 올바르지 않습니다." + " (lineNo: " + lineNo + ") " + "(입력값: \"" + line + "\")");
                }

                String name = Parsers.parseNonBlank(matcher.group("name"));
                crews.add(name);
            }
            return getShuffledCrews(crews, Course.BACKEND);
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일이 유효하지 않습니다." + "(filename: \"" + filename + "\")");
        }
    }

    public static List<Crew> getShuffledCrews(List<String> crews, Course course) {
        List<String> shuffledCrew = Randoms.shuffle(crews);
        return shuffledCrew.stream()
            .map(x -> new Crew(course, x))
            .collect(Collectors.toList());
    }

    public static MatchingInput parseMatchingRequirement(String line) {
        Pattern pattern = Pattern.compile(
            "^(?<course>(백엔드|프론트엔드)+), (?<level>(레벨1|레벨2|레벨3|레벨4|레벨5)+), (?<mission>(자동차경주|로또|숫자야구게임|장바구니|결제|지하철노선도|성능개선|배포)+)$");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                "[ERROR] 파일 내용의 형식이 올바르지 않습니다." + "(입력값: \"" + line + "\")");
        }

        Course course = Course.getByName(matcher.group("course"));
        Level level = Level.getByName(matcher.group("level"));
        String mission = matcher.group("mission");

        if (!MissionRegister.contains(level, mission)) {
            throw new IllegalArgumentException("[ERROR] 해당 레벨과 일치하는 미션은 존재하지 않습니다.");
        }
        return new MatchingInput(course, level, mission);
    }
}
