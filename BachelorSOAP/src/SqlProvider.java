import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlProvider {
	public long LoadCountOfExams(long studentId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = "select count(grades.id)\n" + 
				"from written_exam grades\n" + 
				"inner join subject on subject.id = grades.subject_id\n" + 
				"where grades.student_id = " + studentId;
		long countOfExams = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/gpm_server1?user=root&password=gpm17&useSSL=false");
			statement = connection.createStatement();
			//System.out.println(sql);
		    if (statement.execute(sql)) {
		    	resultSet = statement.getResultSet();
		    	while (resultSet.next()) {
					countOfExams = resultSet.getLong(1);
		        }
		    }
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} catch (SQLException sqlEx) { } // ignore
			resultSet = null;
			if (statement != null) {
		        try {
		        	statement.close();
		        } catch (SQLException sqlEx) { } // ignore
		        statement = null;
		    }
		}
		return countOfExams;
	}	
}
