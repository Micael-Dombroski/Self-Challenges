package dao.impl;

import dao.StudentDAO;
import model.Student;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private static int idCount;
    private final String CSV_FILE = "csv/students.csv";
    private final String CSV_HEADER = "id,name,age,birthday,grade";
    private List<Student> students;
    private File f;
    public StudentDAOImpl(){
        try {
            f = new File(CSV_FILE);
            students = new ArrayList<>();
            if(f.exists() && !f.isDirectory())
                setStudents();
            else {
                File parentDir = f.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();  // Create all necessary parent directories
                }
                f.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void saveStudent(Student student) {
        if(student.getId() == -1) {
            student.setId(idCount);
            idCount++;
        }
        students.add(student);
        saveCSV();
    }

    @Override
    public Student findById(int id) {
        if(students.isEmpty())
            return null;
        for(Student student : students)
            if(student.getId() == id)
                return student;
        return null;
    }

    @Override
    public List<Student> findAll() {
        if(students.isEmpty())
            return null;
        return students;
    }

    @Override
    public void updateStudent(Student student) {
        Student existing = findById(student.getId());
        if(existing == null) {
            throw new IllegalArgumentException("This id doesn't exists");
        }
        students.forEach(s -> {
            if(s.getId() == student.getId()) {
                deleteStudent(s.getId());
                saveStudent(s);
            }
        });
        saveCSV();
    }

    @Override
    public void deleteStudent(int id) {
        Student toRemove = findById(id);
        if(toRemove == null) {
            throw new IllegalArgumentException("This id doesn't exists");
        }
        for(Student s : students) {
            if(s.getId() == id) {
                if(students.size() == 1) {
                    students = new ArrayList<>();
                } else
                    students.remove(s);
            }
        }
        saveCSV();
    }
    private void setStudents() {
        BufferedReader reader = null;
        String line;
        int count = 0;
        try {
            reader = new BufferedReader(new FileReader(CSV_FILE));
            if((line = reader.readLine()) != null) {
                while((line = reader.readLine()) != null) {
                    String[] row = line.split(",");
                    Student student = new Student();
                    student.setId(Integer.valueOf(row[0]));
                    student.setName(row[1]);
                    student.setAge(Integer.valueOf(row[2]));
                    String[] btd = row[3].split("-");
                    student.setBirthday(LocalDate.of(Integer.valueOf(btd[0]), Integer.valueOf(btd[1]), Integer.valueOf(btd[2])));
                    student.setGrade(Integer.valueOf(row[4]));
                    students.add(student);
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                idCount = count;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveCSV() {
        if(students.isEmpty())
            f.delete();
        BufferedWriter writer = null;
        File parentDir = f.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            f.delete();
            f.createNewFile();
            writer = new BufferedWriter(new FileWriter(CSV_FILE));
            writer.write(CSV_HEADER);
            writer.newLine();
            for (Student s : students) {
                writer.write(String.format("%d,%s,%d,%s,%d",
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getBirthday().toString(),
                        s.getGrade()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
