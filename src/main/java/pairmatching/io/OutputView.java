package pairmatching.io;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Pairs;
import pairmatching.service.MissionRegister;

public class OutputView {
    public static void askReMatching() {
        System.out.println("\n매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
            + "네 | 아니오");
    }

    public void printMenu() {
        System.out.println("\n기능을 선택하세요.\n"
            + "1. 페어 매칭\n"
            + "2. 페어 조회\n"
            + "3. 페어 초기화\n"
            + "Q. 종료");
    }

    public void printCourse() {
        System.out.println("#############################################");
        System.out.println("과정: " + Course.getStatus());
    }

    public void printMissions() {
        System.out.print("미션:");
        System.out.println(MissionRegister.getStatus());
        System.out.println("#############################################");
    }

    public void askMatchingRequirement() {
        System.out.println("\n과정, 레벨, 미션을 선택하세요.\n"
            + "ex) 백엔드, 레벨1, 자동차경주");
    }

    public static void printResult(List<Pairs> result) {
        System.out.println("페어 매칭 결과입니다.");
        System.out.println(
            result.stream()
                .map(Pairs::getStatus)
                .collect(Collectors.joining("\n")));
    }
}
