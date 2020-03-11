package entities.privateschool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Assignment {
    private int aid;
    private String atitle;
    private String adescr;
    private LocalDate asdtime;
    private double aoralMark;
    private double atotalMark;
    private Course course;

    public Assignment() {
    }

    public Assignment(int aid, String atitle, String adescr, LocalDate asdtime, double aoralMark, double atotalMark, Course course) {
        this.aid = aid;
        this.atitle = atitle;
        this.adescr = adescr;
        this.asdtime = asdtime;
        this.aoralMark = aoralMark;
        this.atotalMark = atotalMark;
        this.course = course;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getAdescr() {
        return adescr;
    }

    public void setAdescr(String adescr) {
        this.adescr = adescr;
    }

    public LocalDate getAsdtime() {
        return asdtime;
    }

    public void setAsdtime(LocalDate asdtime) {
        this.asdtime = asdtime;
    }

    public double getAoralMark() {
        return aoralMark;
    }

    public void setAoralMark(double aoralMark) {
        this.aoralMark = aoralMark;
    }

    public double getAtotalMark() {
        return atotalMark;
    }

    public void setAtotalMark(double atotalMark) {
        this.atotalMark = atotalMark;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.aid;
        hash = 67 * hash + Objects.hashCode(this.atitle);
        hash = 67 * hash + Objects.hashCode(this.adescr);
        hash = 67 * hash + Objects.hashCode(this.asdtime);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.aoralMark) ^ (Double.doubleToLongBits(this.aoralMark) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.atotalMark) ^ (Double.doubleToLongBits(this.atotalMark) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.course);
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
        final Assignment other = (Assignment) obj;
        if (this.aid != other.aid) {
            return false;
        }
        if (Double.doubleToLongBits(this.aoralMark) != Double.doubleToLongBits(other.aoralMark)) {
            return false;
        }
        if (Double.doubleToLongBits(this.atotalMark) != Double.doubleToLongBits(other.atotalMark)) {
            return false;
        }
        if (!Objects.equals(this.atitle, other.atitle)) {
            return false;
        }
        if (!Objects.equals(this.adescr, other.adescr)) {
            return false;
        }
        if (!Objects.equals(this.asdtime, other.asdtime)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assignment{" + "aid=" + aid + ", atitle=" + atitle + ", adescr=" + adescr + ", asdtime=" + asdtime + ", aoralMark=" + aoralMark + ", atotalMark=" + atotalMark + ", course=" + course + '}';
    }
    

    
}
