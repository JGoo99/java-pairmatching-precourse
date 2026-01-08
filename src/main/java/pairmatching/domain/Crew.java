package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Crew {
    private final Map<Level, List<Crew>> metCrews = new HashMap<>();
    private final Course course;
    private final String name;

    public String getName() {
        return name;
    }

    public Crew(Course course, String name) {
        for (Level level : Level.values()) {
            metCrews.put(level, new ArrayList<>());
        }
        this.course = course;
        this.name = name;
    }

    public void addMetCrew(Level level, Crew crew) {
        metCrews.get(level).add(crew);
    }

    public boolean isMet(Level level, Crew crew) {
        return metCrews.get(level).contains(crew);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return course.getName().equals(crew.course.getName()) && Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
