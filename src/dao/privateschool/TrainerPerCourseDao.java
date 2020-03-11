/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.privateschool;

import entities.privateschool.Course;
import entities.privateschool.Trainer;
import entities.privateschool.TrainerPerCourse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikos
 */
public class TrainerPerCourseDao extends SuperDao implements InterfaceDao<TrainerPerCourse> {

    private static final String FINDALL = "SELECT * FROM trainerpercourse";
    private static final String FINDALLTRAINERSPERCOURSE = "select t.tid, t.tfname, t.tlname, c.cid, c.ctitle, c.cstream, c.ctype \n" +
                                                            "from trainer t, course c, trainerpercourse tpc  \n" +
                                                            "where t.tid=tpc.tid and c.cid=tpc.cid order by c.cid";
    private static final String FINDTRAINERPERCOURSEBYID = "SELECT * FROM trainerpercourse WHERE tpcid = ? ";
    private static final String INSERTTRAINERPERCOURSE = "INSERT INTO trainerpercourse (tid,cid) VALUES (?,?)";
    private static final String DELETETRAINERPERCOURSE = "DELETE FROM trainerpercourse WHERE tpcid = ?";
    private static final String UPDATETRAINERPERCOURSE = "UPDATE trainerpercourse SET tpcid =? , tid =? ,cid=? where tpcid = ?";

    @Override
    public boolean create(TrainerPerCourse tpc) {
        PreparedStatement pst = null;

        boolean created = false;
        try {

            pst = getConnection().prepareStatement(INSERTTRAINERPERCOURSE);
            pst.setInt(1, tpc.getTrainer().getTid());
            pst.setInt(2, tpc.getCourse().getCid());
            int result = pst.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }

        return created;
    }

    @Override
    public List<TrainerPerCourse> findAll() {
        Statement stmt = null;
        ResultSet rs = null;

        List<TrainerPerCourse> list = new ArrayList();

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int tpcid = rs.getInt(1);
                int tid = rs.getInt(2);
                int cid = rs.getInt(3);
                Trainer trainer = getTrainerById(tid);
                Course course = getCourseById(cid);
                TrainerPerCourse tpc = new TrainerPerCourse(tpcid, trainer, course);
                list.add(tpc);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return list;
    }

    @Override
    public TrainerPerCourse findById(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        TrainerPerCourse tpc = null;
        try {
            pstm = getConnection().prepareStatement(FINDTRAINERPERCOURSEBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int tpcid = rs.getInt(1);
            int tid = rs.getInt(2);
            int cid = rs.getInt(3);
            Trainer trainer = getTrainerById(tid);
            Course course = getCourseById(cid);
            tpc = new TrainerPerCourse(tpcid, trainer, course);
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return tpc;
    }

    @Override
    public boolean update(TrainerPerCourse tpc) {
        PreparedStatement pst = null;
        boolean updated = false;
        try {
            pst = getConnection().prepareStatement(UPDATETRAINERPERCOURSE);
            pst.setInt(1, tpc.getTpcid());
            pst.setInt(2, tpc.getTrainer().getTid());
            pst.setInt(3, tpc.getCourse().getCid());
            int result = pst.executeUpdate();
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement pst = null;
        boolean deleted = false;
        try {
            pst = getConnection().prepareStatement(DELETETRAINERPERCOURSE);
            pst.setInt(1, id);
            int result = pst.executeUpdate();   
            if (result > 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return deleted;
    }
        

    private Course getCourseById(int id) {
        CourseDao cdao = new CourseDao();
        Course c = cdao.findById(id);
        return c;
    }

    private Trainer getTrainerById(int id) {
        TrainerDao tdao = new TrainerDao();
        Trainer t = tdao.findById(id);
        return t;
    }
    
    public void findAllTrainersPerCourse() {
        Statement stmt = null;
        ResultSet rs = null;

        try {
 
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALLTRAINERSPERCOURSE);
            while (rs.next()) {
                int tid = rs.getInt(1);
                String tfname = rs.getString(2);
                String tlname = rs.getString(3);
                int cid = rs.getInt(4);
                String ctitle = rs.getString(5);
                String cstream = rs.getString(6);
                String ctype = rs.getString(7);
                System.out.println("Course id: "+cid+" Title: "+ctitle+"Stream: "+cstream+" Type: "+ctype+
                        " Trainer id: "+tid+" Firstname: "+tfname+" Lastname: "+tlname);
                
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        
    }
    
    
}
