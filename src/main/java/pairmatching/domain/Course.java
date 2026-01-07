package pairmatching.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static String getStatus() {
        return Arrays.stream(Course.values())
            .map(x -> x.name)
            .collect(Collectors.joining(" | "));
    }

    public static Course getByName(String name) {
        return Arrays.stream(Course.values())
            .filter(x -> x.name.equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 코스가 없습니다."));
    }
    // 추가 기능 구현
}
