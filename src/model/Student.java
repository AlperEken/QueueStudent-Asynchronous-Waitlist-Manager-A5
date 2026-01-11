package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final int id;
    private final String name;
    private final List<Course> enrolledCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
    }
}
