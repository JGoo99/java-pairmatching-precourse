package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pairmatching.domain.Crew;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.MatchingInput;
import pairmatching.domain.Pairs;
import pairmatching.io.InputView;
import pairmatching.io.OutputView;
import pairmatching.util.Retry;

public class PairMatchingService {
    public static void run(String menu, MatchingInput matchingInput) {
        List<Pairs> result = new ArrayList<>();

        Optional<MatchingHistory> matchingHistory = MatchingRepository.getIfExists(matchingInput);
        if (matchingHistory.isPresent()) {
            OutputView.askReMatching();
            boolean isRematching = InputView.readYesOrNo();
            if (!isRematching) {
                OutputView.printResult(matchingHistory.get().getResult());
                return;
            }
        }

        // TODO : 조회(1) 기능에 이미 만들어진 매칭이라면 재매칭 여부 묻기
        // TODO : 조회(2) 기능에 이미 만들어진 매칭이라면 바로 반환

        List<Crew> crews = CrewRegister.getByCourse(matchingInput.getCourse());
        for (int i = 0; i < crews.size(); i = i + 2) {
            Crew firstCrew = crews.get(i);
            Crew secondCrew = null;
            int j = i + 1;
            if (j <= crews.size() - 1) {
                secondCrew = crews.get(j);
            }

            if (secondCrew == null) {
                Pairs pairs = result.get(result.size() - 1);
                pairs.addThird(firstCrew);
                break;
            }
            result.add(new Pairs(firstCrew, secondCrew));
        }
        MatchingRepository.add(new MatchingHistory(matchingInput, result));
        OutputView.printResult(result);
    }
}
