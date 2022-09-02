package site.coding.junitproject.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookIdGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Connection con = session.connection();
        System.out.println("con = " + con);
        String seqName = "BOOK_SEQ";
        try {
            PreparedStatement ps = con.prepareStatement("SELECT next value for " + seqName + " from dual");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id2 = "SG"+rs.getInt("NEXT VALUE FOR PUBLIC.BOOK_SEQ");
                return id2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Serializable generate(SessionImplementor session, Object obj) throws HibernateException{
//        Connection con = session.connection();
//        String seqName="BOOK_SEQ";
//        try{
//            PreparedStatement ps=con.prepareStatement("SELECT next value for " + seqName + " from dual");
//            ResultSet rs=ps.executeQuery();
//            if(rs.next()){
//                String id2 = "SG"+rs.getInt("NEXT VALUE FOR PUBLIC.BOOK_SEQ");
//                return id2;
//            }
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
}
