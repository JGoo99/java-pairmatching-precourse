package pairmatching.domain;

import java.util.List;

public class MatchingHistory {
    private final MatchingInput matchingInput;
    private final List<Pairs> result;

    public MatchingHistory(MatchingInput matchingInput, List<Pairs> result) {
        this.matchingInput = matchingInput;
        this.result = result;
    }
}
