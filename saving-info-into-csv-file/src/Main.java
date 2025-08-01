import dao.impl.StudentDAOImpl;
import model.Student;
import service.StudentService;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static StudentService service = new StudentService(new StudentDAOImpl());
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int opt = -1;
        do {
            System.out.println("----------Menu----------");
            System.out.println("[1] Register Student");
            System.out.println("[2] Update Student");
            System.out.println("[3] Delete Student");
            System.out.println("[4] Student Info");
            System.out.println("[5] List Students");
            System.out.println("[0] Exit");
            System.out.println("------------------------");
            System.out.println("Choose an option: ");
            String optStr = scan.nextLine();
            opt = optStr != "" ? Integer.valueOf(optStr) : -1;
            switch (opt) {
                case 1:
                    registerStudent();
                    backToMenu();
                    break;
                case 2:
                    updateStudent();
                    backToMenu();
                    break;
                case 3:
                    deleteStudent();
                    backToMenu();
                    break;
                case 4:
                    studentInfo();
                    backToMenu();
                    break;
                case 5:
                    listStudent();
                    backToMenu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("This option isn't available");
                    backToMenu();
            }
        } while (opt != 0);
    }
    private static void registerStudent(){
        System.out.println("Register Student");
        System.out.println("Student name: ");
        String name = scan.nextLine();
        System.out.println("Student birthday: (yyyy-mm-dd)");
        String[] birthday = scan.nextLine().split("-");
        int year = Integer.valueOf(birthday[0]), month = Integer.valueOf(birthday[1]), day = Integer.valueOf(birthday[2]);
        LocalDate btd = LocalDate.of(year, month, day);
        int grade;
        do {
            System.out.println("Student grade: (write an integer number)");
            grade = Integer.valueOf(scan.nextLine());
        } while (grade < 1 || grade > 12);
        service.createStudent(name, btd, grade);
        System.out.println("Successfully: student registered");
    }
    private static void updateStudent(){
        System.out.println("Update student");
        System.out.println("Student id:");
        int id = Integer.valueOf(scan.nextLine());
        Student student = service.findStudentById(id);
        if(student == null) {
            System.out.println("Student isn't registered");
            return;
        }
        System.out.println("Update student name? Y/N");
        String answer = getAnswer();
        if(answer.equalsIgnoreCase("Y")) {
            System.out.println("New student name: ");
            student.setName(scan.nextLine());
        }
        System.out.println("Update birthday? Y/N");
        answer = getAnswer();
        if(answer.equalsIgnoreCase("Y")) {
            System.out.println("New student birthday: (yyyy-mm-dd)");
            String[] btd = scan.nextLine().split("-");
            int year = Integer.valueOf(btd[0]), month = Integer.valueOf(btd[1]), day = Integer.valueOf(btd[2]);
            LocalDate birthday = LocalDate.of(year, month, day);
            student.setBirthday(birthday);
            student.setAge(birthday);
        }
        System.out.println("Update student grade? Y/N");
        answer = getAnswer();
        if(answer.equalsIgnoreCase("Y")) {
            int grade = 0;
            do {
                System.out.println("New student grade: ");
                grade = Integer.valueOf(scan.nextLine());
            } while( grade < 1 || grade > 12);
            student.setGrade(grade);
        }
        service.updateStudent(student);
        System.out.println("Successfully: student updated");
    }
    private static void deleteStudent(){
        System.out.println("Delete Student");
        System.out.println("Student id: ");
        int id = Integer.valueOf(scan.nextLine());
        service.deleteStudent(id);
        System.out.println("Successfully: student deleted");
    }

    private static void studentInfo() {
        System.out.println("Student info");
        System.out.println("Student id:");
        int id = Integer.valueOf(scan.nextLine());
        Student student = service.findStudentById(id);
        if(student != null) {
            displayStudentInfo(student);
        } else {
            System.out.println("Student isn't registered");
        }
    }

    private static void listStudent() {
        if(service.getAllStudents() != null) {
            service.getAllStudents().forEach(s -> {
                displayStudentInfo(s);
            });
        } else {
            System.out.println("No students registered yet");
        }
    }
    private static void displayStudentInfo(final Student student) {
        String[] infos = String.valueOf(student).split(",");
        System.out.println("-----------------------");
        System.out.printf("ID %s\nName: %s\nAge: %s\nBirthday: %s\nGrade %s\n",
                infos[0], infos[1], infos[2], infos[3], infos[4]);
        System.out.println("-----------------------");
    }
    private static String getAnswer() {
        String answer = "\n";
        do {
            answer = scan.nextLine();
        } while (answer == "\n");
        return answer;
    }
    private static void backToMenu() {
        System.out.println("Press Enter to back to menu");
        while (scan.nextLine() != "");
    }

}