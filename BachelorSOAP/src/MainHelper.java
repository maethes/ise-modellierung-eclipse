
public class MainHelper {

	public static void main(String[] args) {
		ApprovalService service = new ApprovalService();
		boolean result = service.isApproved(1);
		System.out.println(result);
	}
}
