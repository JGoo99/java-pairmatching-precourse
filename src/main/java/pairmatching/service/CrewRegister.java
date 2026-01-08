package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.AppRunner;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.util.Parsers;

public class CrewRegister {
    private static List<Crew> BACKEND_CREWS;
    private static List<Crew> FRONTEND_CREWS;
    private static List<String> BACKEND_CREW_NAMES;
    private static List<String> FRONTEND_CREW_NAMES;

    public static void init() {
        List<String> backendCrews = Parsers.parseCrews("src/main/resources/backend-crew.md");
        List<String> frontendCrews = Parsers.parseCrews("src/main/resources/frontend-crew.md");
        validateDuplicate(backendCrews);
        validateDuplicate(frontendCrews);
        BACKEND_CREWS = Parsers.parseCrewList(backendCrews, Course.BACKEND);
        FRONTEND_CREWS = Parsers.parseCrewList(frontendCrews, Course.FRONTEND);
        BACKEND_CREW_NAMES = backendCrews;
        FRONTEND_CREW_NAMES = frontendCrews;
    }

    public static void main(String[] args) {
        AppRunner.init();
    }

    private static void validateDuplicate(List<String> backendCrews) {
        if (backendCrews.stream().distinct().count() != backendCrews.size()) {
            throw new IllegalArgumentException("[ERROR] 크루들의 이름은 중복될 수 없습니다. 크루 파일을 확인해주세요.");
        }
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
            return Randoms.shuffle(BACKEND_CREW_NAMES).stream()
                .map(x -> findByName(x, Course.BACKEND))
                .collect(Collectors.toList());
        }
        return Randoms.shuffle(FRONTEND_CREW_NAMES).stream()
            .map(x -> findByName(x, Course.FRONTEND))
            .collect(Collectors.toList());
    }

    private static Crew findByName(String name, Course course) {
        if (course.equals(Course.BACKEND)) {
            return BACKEND_CREWS.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 크루 이름 입니다."));
        }
        return FRONTEND_CREWS.stream()
            .filter(x -> x.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 크루 이름 입니다."));
    }
}
