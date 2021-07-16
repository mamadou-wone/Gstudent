package wone.sn;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DataBase {
	protected static String url = "jdbc:postgresql://localhost:5432/gstudent";
	protected static String user = "odoo";
	protected static String password = "odoo";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		Connection connection = DriverManager.getConnection(url, props);
		return connection;
	}

	public static ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> userList = new ArrayList<>();
		String query = "SELECT * FROM connection";
		Connection con = getConnection();
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

	public static String hashPassword(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static ArrayList<Etudiant> getEtudiant() throws SQLException {
		ArrayList<Etudiant> studentList = new ArrayList<Etudiant>();
		String query = "SELECT * FROM student";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Etudiant etudiant = new Etudiant(rs.getInt("id"), rs.getString("name"), rs.getString("first_name"),
					rs.getString("email"), rs.getString("address"), rs.getString("date_of_birth"));
			studentList.add(etudiant);
		}
		rs.close();
		preparedStatement.close();
		return studentList;
	}

	public static int addStudent(String name, String firstName, String email, String address, String dateOfBirth)
			throws SQLException {
		String insert = "INSERT INTO student(date_of_birth, email, first_name, name, address) VALUES(?,?,?,?,?)";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(insert);
		preparedStatement.setString(1, dateOfBirth);
		preparedStatement.setString(2, email);
		preparedStatement.setString(3, firstName);
		preparedStatement.setString(4, name);
		preparedStatement.setString(5, address);
		int row = preparedStatement.executeUpdate();
		return row;
	}

	public static int addStudentSportif(int studentID, String sportName) throws SQLException {
		String insert = "INSERT INTO student_sportif(student_id, sport_name) VALUES(?,?)";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(insert);
		preparedStatement.setInt(1, studentID);
		preparedStatement.setString(2, sportName);
		int row = preparedStatement.executeUpdate();
		return row;
	}

	public static ArrayList<EtudiantSportif> getStudentSportif() throws SQLException {
		ArrayList<EtudiantSportif> studentSportif = new ArrayList<EtudiantSportif>(); 
		String query = "SELECT * FROM student INNER JOIN student_sportif ON student.id = student_sportif.student_id";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			EtudiantSportif etudiantSportif = new EtudiantSportif(rs.getInt("id"), rs.getString("name"), rs.getString("first_name"),
					rs.getString("email"), rs.getString("address"), rs.getString("date_of_birth"), rs.getString("sport_name"));
			studentSportif.add(etudiantSportif);
		}
		rs.close();
		preparedStatement.close();
		return studentSportif;
	}

	public static int addStanger(int studentID, String country) throws SQLException {
		String insert = "INSERT INTO stranger_student(student_id, country) VALUES(?, ?)";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(insert);
		preparedStatement.setInt(1, studentID);
		preparedStatement.setString(2, country);
		int row = preparedStatement.executeUpdate();
		return row;
	}
	
	public static ArrayList<EtudiantEtranger> getStranger() throws SQLException{
		ArrayList<EtudiantEtranger> strangerStudent = new ArrayList<EtudiantEtranger>(); 
		String query = "SELECT * FROM student INNER JOIN stranger_student ON student.id = stranger_student.student_id";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			EtudiantEtranger etudiantEtranger = new EtudiantEtranger(rs.getInt("id"), rs.getString("name"), rs.getString("first_name"),
					rs.getString("email"), rs.getString("address"), rs.getString("date_of_birth"), rs.getString("country"));
			strangerStudent.add(etudiantEtranger);
		}
		rs.close();
		preparedStatement.close();
		return strangerStudent;
	}

	public static int updateStudent(String name, String firstName, String email, String address, String dateOfBirth,
			int id) throws SQLException {
		String update = "UPDATE student SET date_of_birth= ? , email = ? , first_name= ? , name = ? , address = ? WHERE id = ?";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(update);
		preparedStatement.setString(1, dateOfBirth);
		preparedStatement.setString(2, email);
		preparedStatement.setString(3, firstName);
		preparedStatement.setString(4, name);
		preparedStatement.setString(5, address);
		preparedStatement.setInt(6, id);
		int row = preparedStatement.executeUpdate();
		return row;
	}

	public static int deleteStudent(int id) throws SQLException {
		String delete = "DELETE FROM student WHERE id = ?";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(delete);
		preparedStatement.setInt(1, id);
		int row = preparedStatement.executeUpdate();
		return row;
	}

	public static int addUser(String username, String password) throws SQLException, NoSuchAlgorithmException {
		String insert = "INSERT INTO connection(login, password) VALUES(?,?)";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(insert);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhashPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, hashPassword(encodedhashPassword));
		int row = preparedStatement.executeUpdate();
		return row;
	}

	public static User login(String username, String password) throws SQLException {
		String query = "SELECT * FROM connection WHERE login = ? AND password = ?";
		Connection con = getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet rs = preparedStatement.executeQuery();
		User user = null;
		while (rs.next()) {
			user = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
		}
		rs.close();
		return user;
	}
}
