package model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Course {
    private final int id;
    private final String name;
    private final int capacity;

    private final CopyOnWriteArrayList<Student> enrolledStudents = new CopyOnWriteArrayList<>();
    private final Queue<Student> waitingList = new ConcurrentLinkedQueue<>();

    public Course(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public CopyOnWriteArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Queue<Student> getWaitingList() {
        return waitingList;
    }

    public boolean hasSpace() {
        return enrolledStudents.size() < capacity;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void addToWaitingList(Student student) {
        waitingList.add(student);
    }

    public Student getNextFromWaitingList() {
        return waitingList.poll();
    }
}
