package entities.privateschool;

import java.util.Objects;



public class StudentPerCourse {
    private int spcid;    
    private Course course;
    private Student student;

    public StudentPerCourse() {
    }

    public StudentPerCourse(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public StudentPerCourse(int spcid, Course course, Student student) {
        this.spcid = spcid;
        this.course = course;
        this.student = student;
    }

    public int getSpcid() {
        return spcid;
    }

    public void setSpcid(int spcid) {
        this.spcid = spcid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.spcid;
        hash = 71 * hash + Objects.hashCode(this.course);
        hash = 71 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentPerCourse other = (StudentPerCourse) obj;
        if (this.spcid != other.spcid) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentPerCourse{" + "spcid=" + spcid + ", course=" + course + ", student=" + student + '}';
    }

    
    

}
