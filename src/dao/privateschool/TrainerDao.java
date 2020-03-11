
package dao.privateschool;

import entities.privateschool.Assignment;
import entities.privateschool.Course;
import entities.privateschool.Student;
import entities.privateschool.Trainer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TrainerDao extends SuperDao implements InterfaceDao<Trainer> {
    
    private static final String FINDALL = "SELECT * FROM  trainer";
    private static final String FINDTRAINERBYID = "SELECT * FROM trainer WHERE tid = ? ";
    private static final String INSERTTRAINER = "INSERT INTO trainer (tfname, tlname, tsub) VALUES (?,?,?)";
    private static final String DELETETRAINER = "DELETE FROM trainer WHERE tid = ?";
    private static final String UPDATETRAINER = "UPDATE trainer SET tfname = ?, tlname = ?,  tsub = ? where tid = ?";

    @Override
    public boolean create(Trainer t) {
        PreparedStatement pst = null;
        
        boolean created = false;
        try {
            
            pst = getConnection().prepareStatement(INSERTTRAINER);
            pst.setString(1, t.getTfname());
            pst.setString(2, t.getTlname());
            pst.setString(3, t.getTsub());
                        int result = pst.executeUpdate(); 
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }

        return created;
    }

    @Override
    public List<Trainer> findAll() {
        Statement stmt = null;
        ResultSet rs = null;

        List<Trainer> list = new ArrayList();

        try {

            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int tid = rs.getInt(1);
                String tfname = rs.getString(2);
                String tlname = rs.getString(3);
                String tsub = rs.getString(4);
                Trainer t = new Trainer(tid, tfname, tlname, tsub);
                list.add(t);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return list;
    }

    @Override
    public Trainer findById(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Trainer t = null;
        try {
            pstm = getConnection().prepareStatement(FINDTRAINERBYID);
            pstm.setInt(1, id);   //Phgaine sto 1o erwtimatiko ? panw sthn dhlwsh kai bale thn timh id an eixa 2 ? ua ekana pstm.setString(2,cname) as poume
            rs = pstm.executeQuery();
            rs.next();
            int tid = rs.getInt(1);
            String tfname = rs.getString(2);
            String tlname = rs.getString(3);
            String tsub = rs.getString(4);
            t = new Trainer(tid, tlname , tfname, tsub);
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm);
        }
        return t;
    }

    @Override
    public boolean update(Trainer t) {
        PreparedStatement pst = null;
        boolean updated = false;
        try {
            pst = getConnection().prepareStatement(UPDATETRAINER);
            pst.setInt(1, t.getTid());
            pst.setString(2, t.getTfname());
            pst.setString(3, t.getTlname());
            pst.setString(4, t.getTsub());
            int result = pst.executeUpdate();  
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = getConnection().prepareStatement(DELETETRAINER);
            pst.setInt(1, id);
            int result = pst.executeUpdate(); 
                    if (result > 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return deleted;
    }
    
    
    
    
    
    
}
