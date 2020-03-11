package entities.privateschool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Course {
    private int cid;
    private String ctitle;
    private String cstream;
    private String ctype;
    private LocalDate cstrdate;
    private LocalDate cenddate;

    public Course() {
    }

    public Course(int cid, String ctitle, String cstream, String ctype, LocalDate cstrdate, LocalDate cenddate) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.cstream = cstream;
        this.ctype = ctype;
        this.cstrdate = cstrdate;
        this.cenddate = cenddate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCstream() {
        return cstream;
    }

    public void setCstream(String cstream) {
        this.cstream = cstream;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public LocalDate getCstrdate() {
        return cstrdate;
    }

    public void setCstrdate(LocalDate cstrdate) {
        this.cstrdate = cstrdate;
    }

    public LocalDate getCenddate() {
        return cenddate;
    }

    public void setCenddate(LocalDate cenddate) {
        this.cenddate = cenddate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.ctitle);
        hash = 31 * hash + Objects.hashCode(this.cstream);
        hash = 31 * hash + Objects.hashCode(this.ctype);
        hash = 31 * hash + Objects.hashCode(this.cstrdate);
        hash = 31 * hash + Objects.hashCode(this.cenddate);
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
        final Course other = (Course) obj;
        if (!Objects.equals(this.ctitle, other.ctitle)) {
            return false;
        }
        if (!Objects.equals(this.cstream, other.cstream)) {
            return false;
        }
        if (!Objects.equals(this.ctype, other.ctype)) {
            return false;
        }
        if (!Objects.equals(this.cstrdate, other.cstrdate)) {
            return false;
        }
        if (!Objects.equals(this.cenddate, other.cenddate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + "cid=" + cid + ", ctitle=" + ctitle + ", cstream=" + cstream + ", ctype=" + ctype + ", cstrdate=" + cstrdate + ", cenddate=" + cenddate + '}';
    }


    
}
