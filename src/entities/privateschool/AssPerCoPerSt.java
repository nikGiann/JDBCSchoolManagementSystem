/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.privateschool;



import java.util.Objects;

/**
 *
 * @author Nikos
 */
public class AssPerCoPerSt {
    private int apcpsid;
    private Assignment assignment;
    private Course course;
    private Student student;
    private Double aoralMark;
    private Double atotalMark;

    public AssPerCoPerSt() {
    }

    public AssPerCoPerSt(Assignment assignment, Course course, Student student) {
        this.assignment = assignment;
        this.course = course;
        this.student = student;
        
    }

    public AssPerCoPerSt(int apcpsid, Assignment assignment, Course course, Student student, Double aoralMark, Double atotalMark) {
        this.apcpsid = apcpsid;
        this.assignment = assignment;
        this.course = course;
        this.student = student;
        this.aoralMark = aoralMark;
        this.atotalMark = atotalMark;
    }

    public int getApcpsid() {
        return apcpsid;
    }

    public void setApcpsid(int apcpsid) {
        this.apcpsid = apcpsid;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
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

    public Double getAoralMark() {
        return aoralMark;
    }

    public void setAoralMark(Double aoralMark) {
        this.aoralMark = aoralMark;
    }

    public Double getAtotalMark() {
        return atotalMark;
    }

    public void setAtotalMark(Double atotalMark) {
        this.atotalMark = atotalMark;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.apcpsid;
        hash = 43 * hash + Objects.hashCode(this.assignment);
        hash = 43 * hash + Objects.hashCode(this.course);
        hash = 43 * hash + Objects.hashCode(this.student);
        hash = 43 * hash + Objects.hashCode(this.aoralMark);
        hash = 43 * hash + Objects.hashCode(this.atotalMark);
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
        final AssPerCoPerSt other = (AssPerCoPerSt) obj;
        if (this.apcpsid != other.apcpsid) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.aoralMark, other.aoralMark)) {
            return false;
        }
        if (!Objects.equals(this.atotalMark, other.atotalMark)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssPerCoPerSt{" + "apcpsid=" + apcpsid + ", assignment=" + assignment + ", course=" + course + ", student=" + student + ", aoralMark=" + aoralMark + ", atotalMark=" + atotalMark + '}';
    }

    
     
}
