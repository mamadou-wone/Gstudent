package wone.sn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Test {
    public static Connection getConnect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();
        props.setProperty("user", "odoo");
        props.setProperty("password", "odoo");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gstudent", props);
        return connection;
    }

    public static ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM connection";
        Connection con = getConnect();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
            userList.add(user);
        }
        rs.close();
        preparedStatement.close();
        return userList;
    }

	public static void main(String[] args) throws SQLException {
	      ArrayList<User> userList = getUsers();
	        userList.forEach(user -> {
	             System.out.println("<h1>" + user.getLogin() + "-->" + user.getPassword() + "</h1>");
	        });
	        System.out.println(getConnect());
	}

}
