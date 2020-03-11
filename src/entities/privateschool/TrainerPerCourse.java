/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.privateschool;

import entities.privateschool.Trainer;
import entities.privateschool.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nikos
 */
public class TrainerPerCourse {
    private int tpcid;
    private Course course;
    private Trainer trainer;

    public TrainerPerCourse() {
    }

    public TrainerPerCourse(int tpcid, Trainer trainer, Course course) {
        this.tpcid = tpcid;
        this.trainer = trainer;
        this.course = course;
        
    }

    public int getTpcid() {
        return tpcid;
    }

    public void setTpcid(int tpcid) {
        this.tpcid = tpcid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.tpcid;
        hash = 67 * hash + Objects.hashCode(this.course);
        hash = 67 * hash + Objects.hashCode(this.trainer);
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
        final TrainerPerCourse other = (TrainerPerCourse) obj;
        if (this.tpcid != other.tpcid) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.trainer, other.trainer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrainersPerCourse{" + "tpcid=" + tpcid + ", course=" + course + ", trainer=" + trainer + '}';
    }
    
    
        
    
}
