package pairmatching;

import java.util.List;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.MatchingInput;
import pairmatching.domain.Pairs;
import pairmatching.io.InputView;
import pairmatching.io.OutputView;
import pairmatching.service.CrewRegister;
import pairmatching.service.MatchingRepository;
import pairmatching.service.MissionRegister;
import pairmatching.service.PairMatchingService;

public class AppRunner {
    private final InputView in;
    private final OutputView out;

    public AppRunner(InputView in, OutputView out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        while (true) {
            init();

            out.printMenu();
            String menu = in.readMenu();
            if (menu.equals("Q")) {
                break;
            }

            out.printCourse();
            out.printMissions();

            out.askMatchingRequirement();
            MatchingInput matchingInput = in.readMatchingInput();

            PairMatchingService.run(menu, matchingInput);
        }
    }

    public static void init() {
        MissionRegister.init();
        CrewRegister.init();
    }

    public static void clear() {
        MissionRegister.clear();
        CrewRegister.clear();
        MatchingRepository.clear();
    }
}
