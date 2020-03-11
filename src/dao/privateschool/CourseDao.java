/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.privateschool;

import entities.privateschool.Course;
import entities.privateschool.Student;
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
public class CourseDao extends SuperDao implements InterfaceDao<Course> {

    private static final String FINDALL = "SELECT * FROM course";
    private static final String FINDCOURSEBYID = "SELECT * FROM course WHERE cid = ? ";
    private static final String INSERTCOURSE = "INSERT INTO course (ctitle,cstream,ctype,cstrdate,cenddate) VALUES (?,?,?,?,?)";
    private static final String DELETECOURSE = "DELETE FROM course WHERE cid = ?";
    private static final String UPDATECOURSE = "UPDATE course SET sfname=? where cid = ?";

    @Override
    public boolean create(Course c) {
        PreparedStatement pst = null;

        boolean created = false;
        try {

            pst = getConnection().prepareStatement(INSERTCOURSE);
            pst.setString(1, c.getCtitle());
            pst.setString(2, c.getCstream());
            pst.setString(3, c.getCtype());
            pst.setObject(4, c.getCstrdate());
            pst.setObject(5, c.getCenddate());
            int result = pst.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }

        return created;
    }

    @Override
    public List<Course> findAll() {
        Statement stmt = null;
        ResultSet rs = null;

        List<Course> list = new ArrayList();

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int cid = rs.getInt(1);
                String ctitle = rs.getString(2);
                String cstream = rs.getString(3);
                String ctype = rs.getString(4);
                LocalDate cstrdate = rs.getDate(5).toLocalDate();
                LocalDate cenddate = rs.getDate(6).toLocalDate();
                Course c = new Course(cid, ctitle, cstream, ctype, cstrdate, cenddate);
                list.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return list;
    }

    @Override
    public Course findById(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Course c = null;
        try {
            pstm = getConnection().prepareStatement(FINDCOURSEBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int cid = rs.getInt(1);
            String ctitle = rs.getString(2);
            String cstream = rs.getString(3);
            String ctype = rs.getString(4);
            LocalDate cstrdate = rs.getDate(5).toLocalDate();
            LocalDate cenddate = rs.getDate(6).toLocalDate();
            c = new Course(cid, ctitle, cstream, ctype, cstrdate, cenddate);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return c;
    }

    @Override
    public boolean update(Course c) {
        PreparedStatement pst = null;
        boolean updated = false;
        try {
            pst = getConnection().prepareStatement(UPDATECOURSE);
            pst.setInt(1, c.getCid());
            pst.setString(2, c.getCtitle());
            pst.setString(3, c.getCstream());
            pst.setString(4, c.getCtype());
            pst.setObject(5, c.getCstrdate());
            pst.setObject(6, c.getCenddate());
            int result = pst.executeUpdate();   
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = getConnection().prepareStatement(DELETECOURSE);
            pst.setInt(1, id);
            int result = pst.executeUpdate();   //Epistrefei poses eggrafes tha ginoyn.
            if (result > 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return deleted;
    }

}
