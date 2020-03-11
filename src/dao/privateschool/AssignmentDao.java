/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.privateschool;

import entities.privateschool.Assignment;
import entities.privateschool.Course;
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
public class AssignmentDao extends SuperDao implements InterfaceDao<Assignment> {

    private static final String FINDALL = "SELECT * FROM Assignment";
    private static final String FINDASSIGNMENTBYID = "SELECT * FROM assignment WHERE aid = ? ";
    private static final String FINDASSIGNMENTSBYCID = "SELECT * FROM assignment WHERE cid = ? ";
    private static final String INSERTASSIGNMENT
            = "INSERT INTO assignment (atitle, adescr, asdtime, aoralmark, atotalmark, cid) VALUES (?,?,?,?,?,?)";
    private static final String DELETEASSIGNMENT = "DELETE FROM assignment WHERE aid = ?";
    private static final String UPDATEASSIGNMENT = "UPDATE assignment SET atitle=? where aid = ?";
    private static final String FINDALLASSIGNMENTSPERCOURSE = "SELECT c.cid, c.ctitle, c.cstream, c.ctype, aid, atitle, adescr FROM course c, assignment a WHERE c.cid=a.cid order by c.cid";

    @Override
    public boolean create(Assignment a) {
        PreparedStatement pst = null;

        boolean created = false;
        try {

            pst = getConnection().prepareStatement(INSERTASSIGNMENT);
            pst.setString(1, a.getAtitle());
            pst.setString(2, a.getAdescr());
            pst.setObject(3, a.getAsdtime());;
            pst.setDouble(4, a.getAoralMark());
            pst.setDouble(5, a.getAtotalMark());
            pst.setInt(6, a.getCourse().getCid());
            int result = pst.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }

        return created;
    }

    @Override
    public List<Assignment> findAll() {
        Statement stmt = null;
        ResultSet rs = null;

        List<Assignment> list = new ArrayList();

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int aid = rs.getInt(1);
                String atitle = rs.getString(2);
                String adescr = rs.getString(3);
                LocalDate asubDateTime = rs.getDate(4).toLocalDate();
                Double aoralmark = rs.getDouble(5);
                Double atotalmark = rs.getDouble(6);
                int cid = rs.getInt(7);
                Course course = getCourseById(cid);
                Assignment a = new Assignment(aid, atitle, adescr, asubDateTime, aoralmark, atotalmark, course);
                list.add(a);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return list;
    }

    public List<Assignment> findAllAssByCId(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Assignment> list = new ArrayList();
        try {

            pstm = getConnection().prepareStatement(FINDASSIGNMENTSBYCID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int aid = rs.getInt(1);
                String atitle = rs.getString(2);
                String adescr = rs.getString(3);
                LocalDate asubDateTime = rs.getDate(4).toLocalDate();
                Double aoralmark = rs.getDouble(5);
                Double atotalmark = rs.getDouble(6);
                int cid = rs.getInt(7);
                Course course = getCourseById(cid);
                Assignment a = new Assignment(aid, atitle, adescr, asubDateTime, aoralmark, atotalmark, course);
                list.add(a);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return list;
    }

    @Override
    public Assignment findById(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Assignment a = null;
        try {
            pstm = getConnection().prepareStatement(FINDASSIGNMENTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int aid = rs.getInt(1);
            String atitle = rs.getString(2);
            String adescr = rs.getString(3);
            LocalDate asdtime = rs.getDate(4).toLocalDate();
            Double aoralmark = rs.getDouble(5);
            Double atotalmark = rs.getDouble(6);
            int cid = rs.getInt(7);
            Course course = getCourseById(cid);
            a = new Assignment(aid, atitle, adescr, asdtime, aoralmark, atotalmark, course);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return a;
    }

    @Override
    public boolean update(Assignment a) {
        PreparedStatement pst = null;
        boolean updated = false;
        try {
            pst = getConnection().prepareStatement(UPDATEASSIGNMENT);
            pst.setInt(1, a.getAid());
            pst.setString(2, a.getAtitle());
            pst.setString(3, a.getAdescr());
            pst.setObject(4, a.getAsdtime());
            pst.setDouble(5, a.getAoralMark());
            pst.setDouble(6, a.getAtotalMark());
            pst.setInt(5, a.getCourse().getCid());
            int result = pst.executeUpdate();
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = getConnection().prepareStatement(DELETEASSIGNMENT);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            if (result > 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public void findAllAssignmentsPerCourse() {
        Statement stmt = null;
        ResultSet rs = null;

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALLASSIGNMENTSPERCOURSE);
            while (rs.next()) {
                int cid = rs.getInt(1);
                String ctitle = rs.getString(2);
                String cstream = rs.getString(3);
                String ctype = rs.getString(4);
                int aid = rs.getInt(5);
                String atitle = rs.getString(6);
                String adescr = rs.getString(7);
                System.out.println("Course id: " + cid + " Title: " + ctitle + "Stream: " + cstream + " Type: " + ctype
                        + " Assignment id: " + aid + " Title: " + atitle + " Description: " + adescr);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }

    }

}
