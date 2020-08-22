import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SQLController {

	
    private final String studentsTable = "students";
    private final String usersTable = "users";
    private Connection conn = null;
    
    public Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        String userName = "root";
        String password = "";
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useTimezone=true&serverTimezone=GMT",
				connectionProps);

        }
        catch(SQLException e) {
            System.out.println("Exception - " + e.getMessage());
        }
        return conn;
    }
    
    private ResultSet executeSelectQuery(Connection conn, String command) {
        Statement stmt;
        ResultSet result = null;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // parse resultset into arraylist of students
    private ArrayList<Student> formatStudents(ArrayList<Student> students, ResultSet result) throws SQLException {
        while (result.next()) {
            Student student = new Student(result.getString("SID"), result.getString("STUD_ID"),
                    result.getString("FNAME"), result.getString("LNAME"));
            students.add(student);
        }
        return students;
    }
    
    	
    public String findUser(String loginID) throws SQLException{
        String foundUser = null;
        String command = "SELECT * FROM " + this.usersTable + " WHERE UID = \'" + loginID + "\'";
        ResultSet result = executeSelectQuery(conn, command);
        if (!result.isBeforeFirst()) {
            return ("No User Found!");
        } else {
            while (result.next()) {
                foundUser = result.getString("UNAME");
            }
            return foundUser;
        }
    }
    
    // get all students from DB
    ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String command = "SELECT * FROM " + this.studentsTable;
        ResultSet result = executeSelectQuery(conn, command);
        return formatStudents(students, result);
    }
    
    // get one student from DB by surname
    ArrayList<Student> getSearchStudent(String surname) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String command = "SELECT * FROM students WHERE LNAME = \'"+ surname + "\'";
        ResultSet result = executeSelectQuery(conn, command);
        return formatStudents(students, result);
    }
    
    
    void run() {
        try {
            conn = this.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database - " + e.getMessage());
        }
    }

}
