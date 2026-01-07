package pairmatching;

import pairmatching.io.InputView;
import pairmatching.io.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            new AppRunner(new InputView(), new OutputView()).run();
        } finally {
            // Console.close();
        }
    }
}
