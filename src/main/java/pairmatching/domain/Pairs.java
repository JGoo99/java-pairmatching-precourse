package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.util.Parsers;

public class Pairs {
    private final List<String> names = new ArrayList<>();

    public Pairs(String first, String second, String third) {
        this(first, second);
        names.add(third);
    }

    public Pairs(String first, String second) {
        names.add(first);
        names.add(second);
    }

    public String getStatus() {
        return String.join(" : ", names);
    }
}
