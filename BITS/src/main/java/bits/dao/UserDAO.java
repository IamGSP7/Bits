package bits.dao;
import bits.database.DBConnection; import bits.model.User; import java.sql.*;
public class UserDAO {
 public User login(String email,String password)throws SQLException{String q="SELECT id,name,email,password_hash FROM users WHERE email=?";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setString(1,email.trim().toLowerCase());try(ResultSet r=p.executeQuery()){if(r.next()&&PasswordUtil.verify(password,r.getString("password_hash")))return new User(r.getInt("id"),r.getString("name"),r.getString("email"));}}return null;}
 public boolean register(String name,String email,String password)throws SQLException{String q="INSERT INTO users(name,email,password_hash) VALUES(?,?,?)";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setString(1,name.trim());p.setString(2,email.trim().toLowerCase());p.setString(3,PasswordUtil.hash(password));p.executeUpdate();return true;}catch(SQLIntegrityConstraintViolationException e){return false;}}
}
