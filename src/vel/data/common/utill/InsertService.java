package vel.data.common.utill;

public class InsertService  extends DBService{

	public void execute()throws Exception{
		setParams();
		this.statement.executeUpdate();
		this.statement.close();
	}
	
	
}
