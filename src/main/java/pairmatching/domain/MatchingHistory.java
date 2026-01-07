package pairmatching.domain;

import java.util.List;

public class MatchingHistory {
    private final MatchingInput matchingInput;
    private final List<Pairs> result;

    public List<Pairs> getResult() {
        return result;
    }

    public MatchingInput getMatchingInput() {
        return matchingInput;
    }

    public MatchingHistory(MatchingInput matchingInput, List<Pairs> result) {
        this.matchingInput = matchingInput;
        this.result = result;
    }
}
