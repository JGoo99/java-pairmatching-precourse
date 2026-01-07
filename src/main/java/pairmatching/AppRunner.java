package pairmatching;

import pairmatching.io.InputView;
import pairmatching.io.OutputView;
import pairmatching.service.CrewRegister;

public class AppRunner {
    private final InputView in;
    private final OutputView out;

    public AppRunner(InputView in, OutputView out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        CrewRegister.init();
    }
}
