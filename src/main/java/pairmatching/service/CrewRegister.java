package pairmatching.service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pairmatching.domain.Crew;
import pairmatching.util.Parsers;

public class CrewRegister {
    private static List<Crew> BACKEND_CREWS;
    private static List<Crew> FRONTEND_CREWS;

    public static void init() {
        List<Crew> backendCrews = Parsers.parseCrews("src/main/resources/backend-crew.md");
        List<Crew> frontendCrews = Parsers.parseCrews("src/main/resources/frontend-crew.md");
        validateDuplicate(backendCrews);
        validateDuplicate(frontendCrews);
        BACKEND_CREWS = backendCrews;
        FRONTEND_CREWS = frontendCrews;
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
}
