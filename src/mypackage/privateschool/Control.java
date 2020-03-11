package mypackage.privateschool;

import dao.privateschool.AssPerCoPerStDao;
import dao.privateschool.AssignmentDao;
import dao.privateschool.CourseDao;
import dao.privateschool.StudentDao;
import dao.privateschool.StudentDao;
import dao.privateschool.StudentPerCourseDao;
import dao.privateschool.TrainerDao;
import dao.privateschool.TrainerPerCourseDao;
import entities.privateschool.AssPerCoPerSt;
import entities.privateschool.Trainer;
import entities.privateschool.Assignment;
import entities.privateschool.Course;
import entities.privateschool.Student;
import entities.privateschool.StudentPerCourse;
import entities.privateschool.TrainerPerCourse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Control {

    Student st = new Student();
    Course course = new Course();
    Assignment as = new Assignment();

    List<Student> listOfStudents = new ArrayList();
    List<Course> listOfCourses = new ArrayList();
    List<Assignment> listOfAssignments = new ArrayList();
    List<Trainer> listOfTrainers = new ArrayList();
    List<StudentPerCourse> listOfStudentsPerCourses = new ArrayList();
    List<AssPerCoPerSt> listOfAssignPerCoursePerStud = new ArrayList();

    CourseDao cdao = new CourseDao();
    StudentDao sdao = new StudentDao();
    AssignmentDao adao = new AssignmentDao();
    TrainerDao tdao = new TrainerDao();
    StudentPerCourseDao spcdao = new StudentPerCourseDao();
    TrainerPerCourseDao tpcdao = new TrainerPerCourseDao();
    AssPerCoPerStDao apcpsdao = new AssPerCoPerStDao();

    public void menu() {
        /**
         * ************************************************
         */
        System.out.println("|      MENU SELECTION         |");
        System.out.println("| Options:                    |");
        System.out.println("|        1. Sign up           |");
        System.out.println("|        2. Search            |");
        System.out.println("|        3. Exit              |");

        int userChoice = 0;
        Scanner input = new Scanner(System.in);
        userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                menuSignUp();
                break;
            case 2:
                menuSearch();
                break;
            case 3:
                return;
        }
        this.menu();
    }

    public void menuSignUp() {
        /**
         * ************************************************
         */
        System.out.println("|         MENU SELECTION                             |");
        System.out.println("| Options:                                           |");
        System.out.println("|      1. Sign up a Student                          |");
        System.out.println("|      2. Sign up a Course                           |");
        System.out.println("|      3. Sign up a Trainer                          |");
        System.out.println("|      4. Sign up an Assignement                     |");
        System.out.println("|      5. Enroll Students to Courses                 |");
        System.out.println("|      6. Enroll Trainers to Courses                 |");
        System.out.println("|      7. Enroll Assignments per Student per Course  |");
        System.out.println("|      8. Back                                       |");

        int userChoice = 0;
        Scanner input = new Scanner(System.in);
        userChoice = input.nextInt();
        switch (userChoice) {
            case 1:

                signUpStudent(st);
                addStudentPerCourse();

                break;
            case 2:

                signUpCourse(course);

                break;
            case 3:
                Trainer tr = new Trainer();
                signUpTrainer(tr);
                addTrainerPerCourse();
                break;
            case 4:
                Assignment as = new Assignment();
                signUpAssignment(as);

                break;
            case 5:
                enrollStudentToCourse();

                break;
            case 6:
                addTrainerPerCourse();
                break;
            case 7:

                break;
            case 8:
                return;
        }

        this.menuSignUp();

    }

    public void menuSearch() {
        /**
         * **************************************************
         */
        System.out.println("|         MENU SELECTION                                  |");
        System.out.println("| Options:                                                |");
        System.out.println("|        1. List Of Courses                               |");
        System.out.println("|        2. List Of Students                              |");
        System.out.println("|        3. List Of Trainers                              |");
        System.out.println("|        4. List Of Assignments                           |");
        System.out.println("|        5. List Of Students per Course                   |");
        System.out.println("|        6. List Of Assignments per Course                |");
        System.out.println("|        7. List Of Trainers per Course                   |");
        System.out.println("|        8. List Of Students belong mone than one courses |");
        System.out.println("|        9. List Of Assignments per Course per Student    |");
        System.out.println("|        10. Back                                         |");

        int userChoice = 0;
        Scanner input = new Scanner(System.in);
        userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                listOfCourses = cdao.findAll();
                for (Course c : listOfCourses) {
                    System.out.println(c);
                }
                break;
            case 2:
                listOfStudents = sdao.findAll();
                for (Student st : listOfStudents) {
                    System.out.println(st);
                }
                break;
            case 3:
                listOfTrainers = tdao.findAll();
                for (Trainer t : listOfTrainers) {
                    System.out.println(t);
                }
                break;
            case 4:
                listOfAssignments = adao.findAll();
                for (Assignment as : listOfAssignments) {
                    System.out.println(as);
                }
                break;
            case 5:
                listOfStudentsPerCourses = spcdao.findAll();
                for (StudentPerCourse spc : listOfStudentsPerCourses) {
                    System.out.println(spc);
                }
                break;
            case 6:
                adao.findAllAssignmentsPerCourse();
                break;
            case 7:
                tpcdao.findAllTrainersPerCourse();
                break;
            case 8:
                spcdao.findAllStudentsWithMoreCourses();
                break;
            case 9:
                
                listOfAssignPerCoursePerStud = apcpsdao.findAll();
                for (AssPerCoPerSt a : listOfAssignPerCoursePerStud) {
                    System.out.println(a);
                }
                
                break;
            case 10:
                return;

        }

        this.menuSearch();
    }

    public void enrollStudentToCourse() {

        int userChoice = 1;
        Scanner input = new Scanner(System.in);

        while (userChoice == 1) {
            int studentChoice = 0;
            int courseChoice = 0;
            System.out.println("Available Students");
            listOfStudents = sdao.findAll();
            int i = 1;
            for (Student s : listOfStudents) {

                System.out.println(s.getSid() + "." + s);
                i++;
            }
            System.out.println("Select Student id");
            studentChoice = input.nextInt();

            System.out.println("Available Courses");
            listOfCourses = cdao.findAll();
            int j = 1;
            for (Course c : listOfCourses) {

                System.out.println(c.getCid() + "." + c);
                j++;
            }
            System.out.println("Select Course id");
            courseChoice = input.nextInt();

            StudentPerCourse spc = new StudentPerCourse();
            Course course = new Course();
            course = cdao.findById(courseChoice);
            spc.setCourse(course);
            Student st = new Student();
            st = sdao.findById(studentChoice);
            spc.setStudent(st);
            spcdao.create(spc);
            System.out.println("Student added to course");
            System.out.println("Want to enroll another Student to Course? (1=Yes 2=No)");
            userChoice = input.nextInt();

        }
    }

    public void addStudentPerCourse() {

        System.out.println("Do you want to enroll student to course? (1=Yes 2=No)");
        int userChoice = 0;
        Scanner input = new Scanner(System.in);
        userChoice = input.nextInt();

        while (userChoice == 1) {
            int studentChoice = 0;
            int courseChoice = 0;
            System.out.println("Available Students");
            listOfStudents = sdao.findAll();
            int i = 1;
            for (Student s : listOfStudents) {

                System.out.println(s.getSid() + "." + s);
                i++;
            }
            System.out.println("Select Student id");
            studentChoice = input.nextInt();

            System.out.println("Available Courses");
            listOfCourses = cdao.findAll();
            int j = 1;
            for (Course c : listOfCourses) {

                System.out.println(c.getCid() + "." + c);
                j++;
            }
            System.out.println("Select Course id");
            courseChoice = input.nextInt();

            StudentPerCourse spc = new StudentPerCourse();
            Course course = new Course();
            course = cdao.findById(courseChoice);
            spc.setCourse(course);
            Student st = new Student();
            st = sdao.findById(studentChoice);
            spc.setStudent(st);
            spcdao.create(spc);
            System.out.println("Student added to course");

            
            System.out.println("Want to enroll to another Course? (1=Yes 2=No)");
            userChoice = input.nextInt();

        }

    }

    public Student signUpStudent(Student st) {
        Scanner input = new Scanner(System.in);
        System.out.println("Write the Firstname of the student");
        st.setsFName(input.next());
        System.out.println("Write the Lastname of the student");
        st.setsLName(input.next());
        System.out.println("Write the Date of the student's Birth like: yyyy-MM-dd");
        st.setSdob(LocalDate.parse(input.next()));
        System.out.println("Write the Tuition Fees of the student");
        st.setSfees(input.nextDouble());
        sdao.create(st);
        System.out.println("\nStudent added!");
        return st;
    }

    public Course signUpCourse(Course course) {
        Scanner input = new Scanner(System.in);
        System.out.println("Write the Title of the course");
        course.setCtitle(input.next());
        System.out.println("Write the stream of the course");
        course.setCstream(input.next());
        System.out.println("Write the type of the course");
        course.setCtype(input.next());
        System.out.println("Write the Start Date of the course like: yyyy-MM-dd");
        course.setCstrdate(LocalDate.parse(input.next()));
        System.out.println("Write the End Date of the course like: yyyy-MM-dd");
        course.setCenddate(LocalDate.parse(input.next()));
        cdao.create(course);
        System.out.println("\nCourse added!");
        return course;
    }

    public Trainer signUpTrainer(Trainer tr) {
        Scanner input = new Scanner(System.in);
        System.out.println("Write the Firstname of the trainer");
        tr.setTfname(input.next());
        System.out.println("Write the Lastname of the trainer");
        tr.setTlname(input.next());
        System.out.println("Write the Subject of the trainer");
        tr.setTsub(input.next());
        tdao.create(tr);
        System.out.println("\nTrainer added!");
        return tr;

    }

    public Assignment signUpAssignment(Assignment as) {
        int userChoice = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Available Courses");
        listOfCourses = cdao.findAll();
        int i = 1;
        for (Course c : listOfCourses) {

            System.out.println(c.getCid() + "." + c);
            i++;
        }
        System.out.println("Select Course");
        userChoice = input.nextInt();
        Course course = new Course();
        course = cdao.findById(userChoice);
        as.setCourse(course);

        System.out.println("Write the Title of the Assignment");
        as.setAtitle(input.next());

        System.out.println("Write the description of the Assignment ");
        as.setAdescr(input.next());
        System.out.println("Write the subDateTime of the Assignment like: yyyy-MM-dd");
        as.setAsdtime(LocalDate.parse(input.next()));
        System.out.println("Write the oral mark of the Assignment");
        as.setAoralMark(input.nextDouble());
        System.out.println("Write the total mark of the Assignment");
        as.setAtotalMark(input.nextInt());
        adao.create(as);
        System.out.println("\nAssignment added!");
        return as;

    }

    public void addTrainerPerCourse() {
        
        int userChoice = 1;
        Scanner input = new Scanner(System.in);
        

        while (userChoice == 1) {
            int trainerChoice = 0;
            int courseChoice = 0;
            System.out.println("Available Trainers");
            listOfTrainers = tdao.findAll();
            int i = 1;
            for (Trainer tr : listOfTrainers) {

                System.out.println(tr.getTid() + "." + tr);
                i++;
            }
            System.out.println("Select Trainer id");
            trainerChoice = input.nextInt();

            System.out.println("Available Courses");
            listOfCourses = cdao.findAll();
            int j = 1;
            for (Course c : listOfCourses) {

                System.out.println(c.getCid() + "." + c);
                j++;
            }
            System.out.println("Select Course");
            userChoice = input.nextInt();

            TrainerPerCourse tpc = new TrainerPerCourse();
            Course course = new Course();
            course = cdao.findById(userChoice);
            tpc.setCourse(course);
            Trainer trainer = new Trainer();
            trainer = tdao.findById(trainerChoice);
            tpc.setTrainer(trainer);
            tpcdao.create(tpc);
            System.out.println("Trainer added to course");

            System.out.println("Want to enroll to another Trainer to Course? (1=Yes 2=No)");
            userChoice = input.nextInt();

        }

        
    }

    }
