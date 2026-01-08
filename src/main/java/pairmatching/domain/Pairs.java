package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pairs {
    private final List<Crew> crews = new ArrayList<>();

    public Pairs(Crew first, Crew second, Level level) {
        if (first.isMet(level, second)) {
            throw new IllegalArgumentException("[ERROR] 두 크루는 매칭 이력이 있습니다.");
        }
        crews.add(first);
        crews.add(second);
    }

    public String getStatus() {
        List<String> names = crews.stream().map(Crew::toString).collect(Collectors.toList());
        return String.join(" : ", names);
    }

    public void addThird(Level level, Crew third) {
        if (crews.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 페어매칭은 최대 3명까지 가능합니다.");
        }
        for (Crew crew : crews) {
            if (third.isMet(level, crew)) {
                throw new IllegalArgumentException("[ERROR] 해당 크루는 매칭 이력이 있습니다.");
            }
        }
        crews.add(third);
    }

    public void metProcess(Level level) {
        for (int i = 0; i < crews.size() - 1; i++) {
            Crew cur = crews.get(i);
            for (int j = i + 1; j < crews.size(); j++) {
                Crew next = crews.get(j);
                cur.addMetCrew(level, next);
                next.addMetCrew(level, cur);
            }
        }
    }

    @Override
    public String toString() {
        return "Pairs{" +
            "crews=" + crews +
            '}';
    }
}
