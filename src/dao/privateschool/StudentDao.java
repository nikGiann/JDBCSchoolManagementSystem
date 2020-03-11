/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.privateschool;

import java.util.List;
import entities.privateschool.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nikos
 */
public class StudentDao extends SuperDao implements InterfaceDao<Student> {
    
    private static final String FINDALL = "SELECT * FROM student";
    private static final String FINDSTUDENTBYID = "SELECT * FROM student WHERE sid = ? ";
    private static final String INSERTSTUDENT = "INSERT INTO student (sfname,slname,sdob,sfees) VALUES (?,?,?,?)";
    private static final String DELETESTUDENT = "DELETE FROM student WHERE sid = ?";
    private static final String UPDATESTUDENT = "UPDATE student SET sfname=? where sid = ?";
    

    @Override
    public boolean create(Student s) {
        
        PreparedStatement pst = null;
        
        boolean created = false;
        try {
            
            pst = getConnection().prepareStatement(INSERTSTUDENT);
            pst.setString(1, s.getsFName());
            pst.setString(2, s.getsLName());
            pst.setObject(3, s.getSdob());
            pst.setDouble(4, s.getSfees());
                        int result = pst.executeUpdate(); 
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }

        return created;
    }

    @Override
    public List<Student> findAll() {
        Statement stmt = null;
        ResultSet rs = null;

        List<Student> list = new ArrayList();

        try {
            
            stmt = getConnection().createStatement();    
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int sid = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate sdob = rs.getDate(4).toLocalDate();   
                Double sfees = rs.getDouble(5);
                Student s = new Student(sid, fname, lname, sdob, sfees);
                list.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return list;
    }

    @Override
    public Student findById(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Student s = null;
        try {
            pstm = getConnection().prepareStatement(FINDSTUDENTBYID);
            pstm.setInt(1, id);   //Phgaine sto 1o erwtimatiko ? panw sthn dhlwsh kai bale thn timh id an eixa 2 ? ua ekana pstm.setString(2,cname) as poume
            rs = pstm.executeQuery();
            rs.next();
            int sid = rs.getInt(1);
            String sfname = rs.getString(2);
            String slname = rs.getString(3);
            LocalDate sdob = rs.getDate(4).toLocalDate();
            Double sfees = rs.getDouble(5);
            s = new Student(sid, slname , sfname, sdob, sfees);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return s;
    }

    @Override
    public boolean update(Student s) {
        PreparedStatement pst = null;
        boolean updated = false;
        try {
            pst = getConnection().prepareStatement(UPDATESTUDENT);
            pst.setInt(1, s.getSid());
            pst.setString(2, s.getsFName());
            pst.setString(3, s.getsLName());
            pst.setObject(4, s.getSdob());
            pst.setDouble(5, s.getSfees());
            int result = pst.executeUpdate();   //Epistrefei poses eggrafes tha ginoyn.
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = getConnection().prepareStatement(DELETESTUDENT);
            pst.setInt(1, id);
            int result = pst.executeUpdate();   //Epistrefei poses eggrafes tha ginoyn.
            if (result > 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return deleted;
    }

   
    
    
}
