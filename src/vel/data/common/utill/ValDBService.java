package vel.data.common.utill;

import java.sql.ResultSet;

public class ValDBService extends DBService{
  public boolean flag;
	
	public boolean isFlag() {
		boolean f=flag;
		this.flag=true;
	return f;
}



	public void execute()throws Exception{
	
		setParams();
		ResultSet set=this.statement.executeQuery();
		if(set!=null && set.next()){
			int count=set.getInt(1);
			this.flag=count>0;
		}
		set.close();
		this.statement.close();
	}
	
	
	
}
