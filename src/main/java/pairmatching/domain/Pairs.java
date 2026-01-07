package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pairs {
    private final List<Crew> crews = new ArrayList<>();

    public Pairs(Crew first, Crew second) {
        crews.add(first);
        crews.add(second);
    }

    public String getStatus() {
        List<String> names = crews.stream().map(Crew::toString).collect(Collectors.toList());
        return String.join(" : ", names);
    }

    public void addThird(Crew third) {
        if (crews.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 페어매칭은 최대 3명까지 가능합니다.");
        }
        crews.add(third);
    }

    @Override
    public String toString() {
        return "Pairs{" +
            "crews=" + crews +
            '}';
    }
}
