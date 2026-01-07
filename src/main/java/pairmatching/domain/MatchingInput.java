package pairmatching.domain;

import java.util.Objects;

public class MatchingInput {
    private final Course course;
    private final Level level;
    private final String missionName;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchingInput that = (MatchingInput) o;
        return course == that.course && level == that.level && Objects.equals(missionName, that.missionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, missionName);
    }

    public MatchingInput(Course course, Level level, String missionName) {
        this.course = course;
        this.level = level;
        this.missionName = missionName;
    }
}
