package pairmatching.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import pairmatching.domain.Level;

public class MissionRegister {
    private static Map<Level, List<String>> MISSIONS = new HashMap<>();

    public static void init() {
        MISSIONS.put(Level.LEVEL1, new ArrayList<>());
        MISSIONS.get(Level.LEVEL1).add("자동차경주");
        MISSIONS.get(Level.LEVEL1).add("로또");
        MISSIONS.get(Level.LEVEL1).add("숫자야구게임");

        MISSIONS.put(Level.LEVEL2, new ArrayList<>());
        MISSIONS.get(Level.LEVEL2).add("장바구니");
        MISSIONS.get(Level.LEVEL2).add("결제");
        MISSIONS.get(Level.LEVEL2).add("지하철노선도");

        MISSIONS.put(Level.LEVEL4, new ArrayList<>());
        MISSIONS.get(Level.LEVEL4).add("성능개선");
        MISSIONS.get(Level.LEVEL4).add("배포");
    }

    public static boolean contains(String target) {
//        Optional<String> any = DOMAINS.stream()
//            .filter(x -> x.equals(target))
//            .findAny();
//        return any.isPresent();
        return true;
    }

    public static void clear() {
        if (MISSIONS != null) {
            MISSIONS.clear();
        }
    }

    public static String getStatus() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            Level level = Level.getLevelByValue(i);
            sb.append("\n  - " + level.getName() + ": ");
            List<String> missions = MISSIONS.get(level);
            if (missions == null) {
                continue;
            }
            String missionStrings = String.join(" | ", missions);
            sb.append(missionStrings);
        }
        return sb.toString();
    }
}
