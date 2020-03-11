package entities.privateschool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Student {
    private int sid;
    private String sFName;
    private String sLName;
    private LocalDate sdob;
    private double sfees;

    public Student() {
    }

    public Student(int sid,String sFName, String sLName, LocalDate sdob, double sfees) {
        this.sid = sid;
        this.sFName = sFName;
        this.sLName = sLName;
        this.sdob = sdob;
        this.sfees = sfees;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsFName() {
        return sFName;
    }

    public void setsFName(String sFName) {
        this.sFName = sFName;
    }

    public String getsLName() {
        return sLName;
    }

    public void setsLName(String sLName) {
        this.sLName = sLName;
    }

    public LocalDate getSdob() {
        return sdob;
    }

    public void setSdob(LocalDate sdob) {
        this.sdob = sdob;
    }

    public double getSfees() {
        return sfees;
    }

    public void setSfees(double sfees) {
        this.sfees = sfees;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.sid;
        hash = 61 * hash + Objects.hashCode(this.sFName);
        hash = 61 * hash + Objects.hashCode(this.sLName);
        hash = 61 * hash + Objects.hashCode(this.sdob);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.sfees) ^ (Double.doubleToLongBits(this.sfees) >>> 32));
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
        final Student other = (Student) obj;
        if (this.sid != other.sid) {
            return false;
        }
        if (Double.doubleToLongBits(this.sfees) != Double.doubleToLongBits(other.sfees)) {
            return false;
        }
        if (!Objects.equals(this.sFName, other.sFName)) {
            return false;
        }
        if (!Objects.equals(this.sLName, other.sLName)) {
            return false;
        }
        if (!Objects.equals(this.sdob, other.sdob)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "sid=" + sid + ", sFName=" + sFName + ", sLName=" + sLName + ", sdob=" + sdob + ", sfees=" + sfees + '}';
    }

    
    
}
