/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.privateschool;

import entities.privateschool.Course;
import entities.privateschool.Student;
import java.util.List;
import entities.privateschool.StudentPerCourse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nikos
 */
public class StudentPerCourseDao extends SuperDao implements InterfaceDao<StudentPerCourse> {
       
    private static final String FINDALLSTUDWITHMORECOURSES= "SELECT s.sid, s.sfname, s.slname, count(s.sid) as Number \n" +
                                    "from student s, course c, studentpercourse spc \n" +
                                    "where s.sid=spc.sid and c.cid=spc.cid group by s.sid having count(s.sid)>1";
    private static final String FINDALL = "SELECT * FROM studentpercourse order by cid";
    private static final String FINDSTUDENTPERCOURSEBYID = "SELECT * FROM studentpercourse WHERE spcid = ? ";
    private static final String INSERTSTUDENTPERCOURSE = "INSERT INTO studentpercourse (spcid,sid,cid) VALUES (?,?,?)";
    private static final String DELETESTUDENTPERCOURSE = "DELETE FROM studentpercourse WHERE spcid = ?";
    private static final String UPDATESTUDENTPERCOURSE = "UPDATE studentpercourse SET spcid =? , sid =? ,cid=? where spcid = ?";
    
    
    @Override
    public boolean create(StudentPerCourse spc) {
       PreparedStatement pst = null;

        boolean created = false;
        try {

            pst = getConnection().prepareStatement(INSERTSTUDENTPERCOURSE);
            pst.setInt(1, spc.getSpcid());
            pst.setInt(2, spc.getStudent().getSid());
            pst.setInt(3, spc.getCourse().getCid());
            int result = pst.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }

        return created; 
    }

    @Override
    public List<StudentPerCourse> findAll() {
        Statement stmt = null;
        ResultSet rs = null;

        List<StudentPerCourse> list = new ArrayList();

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int spcid =rs.getInt(1);    
                int sid = rs.getInt(2);
                int cid = rs.getInt(3);
                Student student = getStudentById(sid);
                Course course = getCourseById(cid);
                StudentPerCourse spc = new StudentPerCourse(spcid,course, student);
                list.add(spc);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return list;
    }

    @Override
    public StudentPerCourse findById(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StudentPerCourse spc = null;
        try {
            pstm = getConnection().prepareStatement(FINDSTUDENTPERCOURSEBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int spcid = rs.getInt(1);
            int sid = rs.getInt(2);
            int cid = rs.getInt(3);
            Student student = getStudentById(sid);
            Course course = getCourseById(cid);
            spc = new StudentPerCourse(spcid, course, student);
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return spc;
    }

    @Override
    public boolean update(StudentPerCourse spc) {
        PreparedStatement pst = null;
        boolean updated = false;
        try {
            pst = getConnection().prepareStatement(UPDATESTUDENTPERCOURSE);
            pst.setInt(1, spc.getSpcid());
            pst.setInt(2, spc.getStudent().getSid());
            pst.setInt(3, spc.getCourse().getCid());
            int result = pst.executeUpdate();
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = getConnection().prepareStatement(DELETESTUDENTPERCOURSE);
            pst.setInt(1, id);
            int result = pst.executeUpdate();   
            if (result > 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return deleted;
    }
    
    public void findAllStudentsWithMoreCourses() {
        Statement stmt = null;
        ResultSet rs = null;

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALLSTUDWITHMORECOURSES);
            while (rs.next()) {
                int sid =rs.getInt(1);// TODO these
                String sfname = rs.getString(2);
                String slname = rs.getString(3);
                int count =rs.getInt(4);    
                System.out.println("Student{ id: "+sid+" Firstname: "+sfname+" Lastname: "+slname+" NumberOfCourses:"+count+" }");
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        
    }
    
    private Course getCourseById(int id) {
        CourseDao cdao = new CourseDao();
        Course c = cdao.findById(id);
        return c;
    }

    private Student getStudentById(int id) {
        StudentDao sdao = new StudentDao();
        Student s = sdao.findById(id);
        return s;
    }
    
}
