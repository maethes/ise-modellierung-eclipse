public class ApprovalService {
	public boolean isApproved(long studentId) {
		SqlProvider sql = new SqlProvider();
		long countOfExams = sql.LoadCountOfExams(studentId);
		//long countOfExams = 50;
		return countOfExams > 20;
	}	
}
