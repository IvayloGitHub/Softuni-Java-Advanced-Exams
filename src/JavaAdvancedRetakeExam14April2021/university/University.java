package JavaAdvancedRetakeExam14April2021.university;

import java.util.ArrayList;
import java.util.List;

public class University {
    public List<Student> students;
    public int capacity;

    public University(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStudentCount() {
        return students.size();
    }

    public String registerStudent(Student student) {
        String result = "";
        if (capacity > students.size()) {
            if (students.contains(student)) {
                result = "Student is already in the university";
            } else {
                students.add(student);
                result = String.format("Added student %s %s", student.getFirstName(), student.getLastName());
            }
        } else {
            result = "No seats in the university";
        }
        return result;
    }

    public String dismissStudent(Student student) {
        if (!students.remove(student)) {
            return "Student not found";
        }
        return String.format("Removed student %s %s", student.getFirstName(), student.getLastName());
    }

    public Student getStudent(String firstName, String lastName) {
        return students.stream()
                .filter(student -> student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
                .findFirst().orElse(null);
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s",
                    student.getFirstName(), student.getLastName(), student.getBestSubject()));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
