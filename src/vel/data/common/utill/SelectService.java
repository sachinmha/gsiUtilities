package vel.data.common.utill;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.Map;

public class SelectService extends DBService{

	
	public void execute()throws Exception{
	
		setParams();
		if(flag){
		ResultSet set=this.statement.executeQuery();
		if(set!=null && set.next()){
			populateRow(set);
		}
		}
		this.statement.close();
	}
	
	private void populateRow(ResultSet set)throws SQLException{
		ResultSetMetaData metadata=set.getMetaData();
		for(int i=1;i<=metadata.getColumnCount();i++){
		if(	metadata.getColumnType(i)==Types.DATE){
			Date dt=set.getDate(metadata.getColumnName(i));
			if(dt!=null){
				this.data.put(metadata.getColumnName(i),DateUtil.getDate(dt));	
			}else{
				//this.data.put(metadata.getColumnName(i),DateUtil.getDate(new Date()));	
			}
			
			
		} else if(	metadata.getColumnType(i)==Types.TIMESTAMP){
			Date dt=set.getTimestamp(metadata.getColumnName(i));
			if(dt!=null){
				this.data.put(metadata.getColumnName(i),DateUtil.getDateTime(dt));	
			}else{
				//this.data.put(metadata.getColumnName(i),DateUtil.getDateTime(new Date()));	
			}
		} else {
			this.data.put(metadata.getColumnName(i),set.getString(metadata.getColumnName(i)));
		} 
		}
	}
	
}
