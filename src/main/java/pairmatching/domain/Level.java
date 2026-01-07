package pairmatching.domain;

import java.util.Arrays;

public enum Level {
    LEVEL1(1, "레벨1"),
    LEVEL2(2, "레벨2"),
    LEVEL3(3, "레벨3"),
    LEVEL4(4, "레벨4"),
    LEVEL5(5, "레벨5");

    private int value;
    private String name;

    Level(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Level getByName(String name) {
        return Arrays.stream(Level.values())
            .filter(x -> x.name.equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 레벨이 없습니다."));
    }

    public String getName() {
        return name;
    }

    public static Level getLevelByValue(int value) {
        return Arrays.stream(Level.values())
            .filter(x -> x.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 레벨값이 유효하지 않습니다."));
    }

    // 추가 기능 구현
}
