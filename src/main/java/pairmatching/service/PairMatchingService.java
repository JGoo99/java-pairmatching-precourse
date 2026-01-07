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
        try {

            if (menu.equals("1")) {
                handleMenu1(matchingInput);
                return;
            }
            if (menu.equals("2")) {
                handleMenu2(matchingInput);
                return;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleMenu2(MatchingInput matchingInput) {
        Optional<MatchingHistory> matchingHistory = MatchingRepository.getIfExists(matchingInput);
        if (!matchingHistory.isPresent()) {
            throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
        }
        OutputView.printResult(matchingHistory.get().getResult());
    }

    private static void handleMenu1(MatchingInput matchingInput) {
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

        int count = 0;
        while (count < 3) {
            try {
                List<Crew> crews = CrewRegister.getShuffledByCourse(matchingInput.getCourse());
                for (int i = 0; i < crews.size(); i = i + 2) {
                    Crew firstCrew = crews.get(i);
                    Crew secondCrew = null;
                    int j = i + 1;
                    if (j <= crews.size() - 1) {
                        secondCrew = crews.get(j);
                    }

                    if (secondCrew == null) {
                        Pairs pairs = result.get(result.size() - 1);
                        pairs.addThird(matchingInput.getLevel(), firstCrew);
                        break;
                    }
                    result.add(new Pairs(firstCrew, secondCrew, matchingInput.getLevel()));
                }
                MatchingRepository.add(new MatchingHistory(matchingInput, result));
                OutputView.printResult(result);
                break;
            } catch (IllegalArgumentException e) {
                count++;
            }
        }
    }
}
