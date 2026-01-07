package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.AppRunner;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.util.Parsers;

public class CrewRegister {
    private static List<Crew> BACKEND_CREWS;
    private static List<Crew> FRONTEND_CREWS;

    public static void init() {
        List<Crew> backendCrews = Parsers.parseCrews("src/main/resources/backend-crew.md", Course.BACKEND);
        List<Crew> frontendCrews = Parsers.parseCrews("src/main/resources/frontend-crew.md", Course.FRONTEND);
        validateDuplicate(backendCrews);
        validateDuplicate(frontendCrews);
        BACKEND_CREWS = backendCrews;
        FRONTEND_CREWS = frontendCrews;
    }

    public static void main(String[] args) {
        AppRunner.init();
    }

    private static void validateDuplicate(List<Crew> backendCrews) {
        if (backendCrews.stream().distinct().count() != backendCrews.size()) {
            throw new IllegalArgumentException("[ERROR] 크루들의 이름은 중복될 수 없습니다. 크루 파일을 확인해주세요.");
        }
    }

    public static boolean contains(String target) {
//        Optional<String> any = DOMAINS.stream()
//            .filter(x -> x.equals(target))
//            .findAny();
//        return any.isPresent();
        return true;
    }

    public static void clear() {
        if (BACKEND_CREWS != null) {
            BACKEND_CREWS.clear();
        }
        if (FRONTEND_CREWS != null) {
            FRONTEND_CREWS.clear();
        }
    }

    public static List<Crew> getShuffledByCourse(Course course) {
        if (course.equals(Course.BACKEND)) {
            return Randoms.shuffle(BACKEND_CREWS);
        }
        return  Randoms.shuffle(FRONTEND_CREWS);
    }
}
