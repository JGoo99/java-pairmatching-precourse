package pairmatching;

import pairmatching.domain.Course;
import pairmatching.domain.Level;

public class MatchingInput {
    private final Course course;
    private final Level level;
    private final String missionName;

    public MatchingInput(Course course, Level level, String missionName) {
        this.course = course;
        this.level = level;
        this.missionName = missionName;
    }
}
