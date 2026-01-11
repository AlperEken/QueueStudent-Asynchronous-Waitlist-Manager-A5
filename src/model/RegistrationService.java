package model;

import controller.Controller;

import java.util.*;
import java.util.concurrent.*;

public class RegistrationService {
    private final Controller controller;
    private final Map<Integer, Course> courses = new ConcurrentHashMap<>();
    private final List<Student> students = Collections.synchronizedList(new ArrayList<>());
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Random random = new Random();

    public RegistrationService(Controller controller) {
        this.controller = controller;
        createTestCourses();
        createTestStudents();
    }

    private void createTestCourses() {
        courses.put(1, new Course(1, "Mathematics", 10));
        courses.put(2, new Course(2, "Programming", 15));
        courses.put(3, new Course(3, "Physics", 12));
    }

    private void createTestStudents() {
        for (int i = 1; i <= 100; i++) {
            students.add(new Student(i, "Student " + i));
        }
    }

    public void startSimulation() {
        for (Student student : students) {
            executor.submit(() -> {
                try {
                    Thread.sleep(random.nextInt(500)); // Simulera fördröjning
                    int courseId = 1 + random.nextInt(3); // Välj en av 3 kurser
                    registerStudent(student, courseId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.submit(this::simulateDropouts);
    }

    private void registerStudent(Student student, int courseId) {
        Course course = courses.get(courseId);

        if (course.hasSpace()) {
            course.enrollStudent(student);
            student.enrollInCourse(course);
            controller.updateView(student.getName() + " enrolled in " + course.getName());
        } else {
            course.addToWaitingList(student);
            controller.updateView(student.getName() + " added to waitlist for " + course.getName());
        }
    }

    private void simulateDropouts() {
        try {
            Thread.sleep(3000); // Vänta lite innan droppar
            for (int i = 0; i < 5; i++) {
                Student enrolled = getRandomEnrolledStudent();
                if (enrolled != null) {
                    Course course = enrolled.getEnrolledCourses().get(0);
                    course.dropStudent(enrolled);
                    enrolled.dropCourse(course);
                    controller.updateView(enrolled.getName() + " dropped from " + course.getName());

                    Student next = course.getNextFromWaitingList();
                    if (next != null) {
                        course.enrollStudent(next);
                        next.enrollInCourse(course);
                        controller.updateView(next.getName() + " enrolled from waitlist to " + course.getName());
                    }
                }
                Thread.sleep(1000); // lite paus mellan droppar
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logCourseStatus();
    }

    private Student getRandomEnrolledStudent() {
        synchronized (students) {
            List<Student> enrolled = new ArrayList<>();
            for (Student s : students) {
                if (!s.getEnrolledCourses().isEmpty()) {
                    enrolled.add(s);
                }
            }
            if (!enrolled.isEmpty()) {
                return enrolled.get(random.nextInt(enrolled.size()));
            }
        }
        return null;
    }

    private void logCourseStatus() {
        List<String> lines = new ArrayList<>();
        for (Course course : courses.values()) {
            String line = course.getName() + ": " +
                    course.getEnrolledStudents().size() + " enrolled of " + course.getCapacity() +
                    ", " + course.getWaitingList().size() + " on waitlist";
            lines.add(line);
        }
        controller.updateStatusList(lines.toArray(new String[0]), true);
    }
}
