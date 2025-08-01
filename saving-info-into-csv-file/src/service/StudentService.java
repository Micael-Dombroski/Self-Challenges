package service;

import dao.StudentDAO;
import model.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;
    public StudentService(final StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    public void createStudent(final String name, final LocalDate birthday, final int grade) {
        Student student = new Student(name, birthday, grade);
        studentDAO.saveStudent(student);
    }
    public Student findStudentById(final int id) {
        Student student = studentDAO.findById(id);
        return student;
    }
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }
    public void updateStudent(final Student student) {
        studentDAO.updateStudent(student);
    }
    public void deleteStudent(final int id) {
        studentDAO.deleteStudent(id);
    }
}
