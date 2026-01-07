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

//        List<String> crewNames; // 파일에서 로드한 크루 이름 목록
//        List<String> shuffledCrew = Randoms.shuffle(crewNames); // 섞인 크루 이름 목록
    }
}
