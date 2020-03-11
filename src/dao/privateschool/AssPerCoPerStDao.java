/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.privateschool;

import entities.privateschool.AssPerCoPerSt;
import entities.privateschool.Assignment;
import entities.privateschool.Course;
import entities.privateschool.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikos
 */
public class AssPerCoPerStDao extends SuperDao implements InterfaceDao<AssPerCoPerSt> {

    private static final String FINDALL = "SELECT * FROM asspercoperst";
    private static final String FINDASSPERCOPERSTBYID = "SELECT * FROM asspercoperst WHERE acsid = ? ";
    private static final String INSERTASSPERCOPERST = "INSERT INTO asspercoperst (sid, cid, aid, stotalMark, soralMark) VALUES (?,?,?,?,?)";
    private static final String DELETEASSPERCOPERST = "DELETE FROM asspercoperst WHERE acsid = ?";
    private static final String UPDATEASSPERCOPERST = "UPDATE asspercoperst SET sid=?, cid=?, aid=?, stotalMark=?, soralMark=? where acsid= ?";

    @Override
    public boolean create(AssPerCoPerSt e) {
        PreparedStatement pst = null;

        boolean created = false;
        try {

            pst = getConnection().prepareStatement(INSERTASSPERCOPERST);
            pst.setInt(1, e.getApcpsid());
            pst.setInt(2, e.getAssignment().getAid());
            pst.setInt(3, e.getCourse().getCid());
            pst.setInt(4, e.getStudent().getSid());
            int result = pst.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssPerCoPerStDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }

        return created;
    }

    @Override
    public List<AssPerCoPerSt> findAll() {
        Statement stmt = null;
        ResultSet rs = null;

        List<AssPerCoPerSt> list = new ArrayList();

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int acsid = rs.getInt(1);
                int sid = rs.getInt(2);
                int cid = rs.getInt(3);
                int aid = rs.getInt(4);
                double aoralmark = rs.getDouble(5);
                double atotalmark = rs.getDouble(6);
                Assignment assign = getAssignmentById(aid);
                Course course = getCourseById(cid);
                Student student = getStudentById(sid);
                AssPerCoPerSt acs = new AssPerCoPerSt(acsid, assign, course, student,aoralmark, atotalmark);
                list.add(acs);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(AssPerCoPerStDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return list;
    }

    @Override
    public AssPerCoPerSt findById(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        AssPerCoPerSt acs = null;
        try {
            pstm = getConnection().prepareStatement(FINDASSPERCOPERSTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int acsid = rs.getInt(1);
            int sid = rs.getInt(2);
            int cid = rs.getInt(3);
            int aid = rs.getInt(4);
            double aoralmark = rs.getDouble(5);
            double atotalmark = rs.getDouble(6);
            Assignment assign = getAssignmentById(aid);
            Course course = getCourseById(cid);
            Student student = getStudentById(sid);
            acs = new AssPerCoPerSt(acsid, assign, course, student,aoralmark, atotalmark);
        } catch (SQLException ex) {
            Logger.getLogger(AssPerCoPerStDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return acs;
    }

    @Override
    public boolean update(AssPerCoPerSt acs) {
        PreparedStatement pst = null;
        boolean updated = false;
        try {
            pst = getConnection().prepareStatement(UPDATEASSPERCOPERST);
            pst.setInt(1, acs.getApcpsid());
            pst.setInt(2, acs.getAssignment().getAid());
            pst.setInt(3, acs.getCourse().getCid());
            pst.setInt(4, acs.getStudent().getSid());
            int result = pst.executeUpdate();
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssPerCoPerStDao.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = getConnection().prepareStatement(DELETEASSPERCOPERST);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            if (result > 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssPerCoPerStDao.class.getName()).log(Level.SEVERE, null, ex);
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

    private Assignment getAssignmentById(int id) {
        AssignmentDao adao = new AssignmentDao();
        Assignment a = adao.findById(id);
        return a;
    }

    private Student getStudentById(int id) {
        StudentDao sdao = new StudentDao();
        Student s = sdao.findById(id);
        return s;
    }

}
