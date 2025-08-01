package dao;

import model.Student;

import java.util.List;

public interface StudentDAO {
    void saveStudent(Student student);
    Student findById(int id);
    List<Student> findAll();
    void updateStudent(Student student);
    void deleteStudent(int id);
}
