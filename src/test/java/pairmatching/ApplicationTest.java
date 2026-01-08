package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";


    @BeforeEach
    void clear() {
        AppRunner.clear();
    }

    @Test
    void 짝수_인원_페어_매칭() {
        assertShuffleTest(
            () -> {
                run("1", "백엔드, 레벨1, 자동차경주", "Q");
                assertThat(output()).contains("태웅 : 백호", "치수 : 태섭");
            },
            Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    void 없는_미션에_대한_예외_처리() {
        assertSimpleTest(
            () -> {
                runException("1", "백엔드, 레벨1, 오징어게임");
                assertThat(output()).contains(ERROR_MESSAGE);
            }
        );
    }

    @Test
    @DisplayName("홀수 인원 페어 매칭")
    void test() {
        assertShuffleTest(
            () -> {
                run("1", "백엔드, 레벨1, 자동차경주", "Q");
                assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 준호");
            },
            Arrays.asList("태웅", "백호", "치수", "태섭", "준호")
        );
    }
//    @Test
//    @DisplayName("같은 레벨에서 이미 페어를 맺은 경 페어 재매칭 시도")
//    void test2() {
//        assertShuffleTest(
//            () -> {
//                run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨2, 장바구니");
//                assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 대협 : 준호", "");
//            },
//            Arrays.asList("태웅", "백호", "치수", "태섭", "대협", "준호"),
//            Arrays.asList("치수", "준호", "태웅", "대협", "백호", "태섭")
//        );
//    }

    @Test
    @DisplayName("이미 매칭 정보가 있는 경우에서 새로운 것 출력")
    void test3() {
        assertShuffleTest(
            () -> {
                run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "네", "Q");
                assertThat(output()).contains(
                    "태웅 : 백호", "치수 : 태섭", "대만 : 준호",
                    "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                        + "네 | 아니오",
                    "치수 : 준호", "태웅 : 대만", "백호 : 태섭");
            },
            Arrays.asList("태웅", "백호", "치수", "태섭", "대만", "준호"),
            Arrays.asList("치수", "준호", "태웅", "대만", "백호", "태섭")
        );
    }

    @Test
    @DisplayName("이미 매칭 정보가 있는 경우 기존 것 출력")
    void test4() {
        assertShuffleTest(
            () -> {
                run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "아니오", "Q");
                assertThat(output()).contains(
                    "태웅 : 백호", "치수 : 태섭", "대협 : 준호",
                    "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                        + "네 | 아니오",
                    "태웅 : 백호", "치수 : 태섭", "대협 : 준호");
            },
            Arrays.asList("태웅", "백호", "치수", "태섭", "대협", "준호"),
            Arrays.asList("치수", "준호", "태웅", "대협", "백호", "태섭")
        );
    }

    @Test
    @DisplayName("패어매칭 조회")
    void test5() {
        assertShuffleTest(
            () -> {
                run("1", "백엔드, 레벨1, 자동차경주", "2", "백엔드, 레벨1, 자동차경주", "Q");
                assertThat(output()).contains(
                    "태웅 : 백호", "치수 : 태섭", "대협 : 준호",
                    "태웅 : 백호", "치수 : 태섭", "대협 : 준호");
            },
            Arrays.asList("태웅", "백호", "치수", "태섭", "대협", "준호")
        );
    }

    @Test
    @DisplayName("패어매칭 조회 실패 - 매칭이력 없음")
    void test6() {
        assertShuffleTest(
            () -> {
                run("2", "백엔드, 레벨1, 자동차경주", "Q");
                assertThat(output()).contains(
                    "[ERROR] 매칭 이력이 없습니다.",
                    "기능을 선택하세요.");
            },
            Arrays.asList("태웅", "백호", "치수", "태섭", "대협", "준호")
        );
    }

    @Test
    @DisplayName("같은 레벨 동안은 같은 페어를 만나지 않는다.")
    void test7() {
        assertShuffleTest(
            () -> {
                run("1", "백엔드, 레벨4, 성능개선", "1", "백엔드, 레벨4, 배포", "Q");
                assertThat(output()).contains(
                    "태웅 : 백호", "치수 : 태섭", "대협 : 준호",
                    "치수 : 준호", "태웅 : 대협", "백호 : 태섭");
            },
            Arrays.asList("태웅", "백호", "치수", "태섭", "대협", "준호"),
            Arrays.asList("태웅", "백호", "치수", "태섭", "대협", "준호"),
            Arrays.asList("치수", "준호", "태웅", "대협", "백호", "태섭")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
