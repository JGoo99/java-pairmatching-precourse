package pairmatching;

import pairmatching.domain.MatchingInput;
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
        init();
        while (true) {

            out.printMenu();
            String menu = in.readMenu();
            if (menu.equals("Q")) {
                break;
            }

            PairMatchingService.run(menu);
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
