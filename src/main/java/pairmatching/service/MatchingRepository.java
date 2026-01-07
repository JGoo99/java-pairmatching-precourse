package pairmatching.service;

import java.util.HashMap;
import java.util.Map;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.MatchingInput;

public class MatchingRepository {
    private static final Map<MatchingInput, MatchingHistory> MATCHING_HISTORY_REPOSITORY = new HashMap<>();

    public static void add(MatchingHistory matchingHistory) {
        MATCHING_HISTORY_REPOSITORY.put(matchingHistory.getMatchingInput(), matchingHistory);
    }

    public void clear() {
        if (!MATCHING_HISTORY_REPOSITORY.isEmpty()) {
            MATCHING_HISTORY_REPOSITORY.clear();
        }
    }
}
