package model;

import java.time.LocalDate;

public class Student {
    private int id = -1;
    private String name;
    private int age;
    private LocalDate birthday;
    private int grade;
    public Student(final String name, final LocalDate birthday, final int grade) throws IllegalArgumentException {
        if(birthday.getYear() > LocalDate.now().getYear() ||
            (birthday.getYear() == LocalDate.now().getYear() && birthday.getDayOfYear() > LocalDate.now().getDayOfYear()))
                throw new IllegalArgumentException("Birthday cannot be in the future");
        this.name = name;
        this.birthday = birthday;
        setAge(this.birthday);
        this.grade = grade;
    }
    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }
    public void setAge(LocalDate birthday) throws IllegalArgumentException {
        LocalDate now = LocalDate.now();
        if(now.getYear() < birthday.getYear())
            throw new IllegalArgumentException("Birthday cannot be in the future");
        else if(now.getMonth().getValue() < birthday.getMonth().getValue())
            this.age = now.getYear() - 1 - birthday.getYear();
        else if(now.getMonth().getValue() == birthday.getMonth().getValue() &&
            now.getDayOfMonth() < birthday.getDayOfMonth())
            this.age = now.getYear() - 1 - birthday.getYear();
        else this.age = now.getYear() - birthday.getYear();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(final int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return id +"," + name + "," + age + "," + birthday.toString() + "," + grade;
    }
}
