package bits.dao;
import bits.database.DBConnection; import bits.model.Category; import java.sql.*; import java.util.*;
public class CategoryDAO {
 public List<Category> list(int userId)throws SQLException{List<Category> out=new ArrayList<>();try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT id,name FROM categories WHERE user_id=? ORDER BY name")){p.setInt(1,userId);try(ResultSet r=p.executeQuery()){while(r.next())out.add(new Category(r.getInt(1),r.getString(2)));}}return out;}
 public void add(int userId,String name)throws SQLException{try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO categories(user_id,name) VALUES(?,?)")){p.setInt(1,userId);p.setString(2,name.trim());p.executeUpdate();}}
}
