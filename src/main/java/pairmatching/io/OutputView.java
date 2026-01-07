package pairmatching.io;

import java.util.Arrays;
import pairmatching.domain.Course;
import pairmatching.service.MissionRegister;

public class OutputView {
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
}
