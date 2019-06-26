package vel.data.common.utill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sun.jdbc.rowset.CachedRowSet;

public class DBDataProvider extends Provider {

private	CachedRowSet rowSet=null;
private Connection connection;
private String query;
private HashMap<String, Integer> columnsType;

public DBDataProvider(SourceData source,Configuration config)throws Exception{
	this.query=source.getDbQuery();
	connection=config.getConnectionMap().get(source.getConnectionRef()).getConnection();
	populateData();
}

@Override
public Map<String, Object> getRow() {
	Map<String, Object> data=null;
	try{
	if(this.rowSet!=null && this.rowSet.next()){
		data=papulateRow();
	}
	}
	catch (Exception e) {
	e.printStackTrace();
	}
	return data;
}


private Map<String, Object> papulateRow()throws Exception{
	Map<String, Object> data=new HashMap<String, Object>();
	for(String cols:header){
		int type=columnsType.get(cols);
		if(type==Types.DATE){
			data.put(cols, DateUtil.getDate(this.rowSet.getDate(cols)));
		}else if(type==Types.TIMESTAMP){
			data.put(cols, DateUtil.getDateTime(this.rowSet.getTimestamp(cols)));
		}else{
			data.put(cols, this.rowSet.getString(cols));
		}
		
	}
	
	return data;
	
}

private void populateData()throws Exception{
	Statement stmt=connection.createStatement();
	ResultSet set=stmt.executeQuery(query);
	ResultSetMetaData metadata=set.getMetaData();
	setColumnNames(metadata);
	rowSet=new CachedRowSet();
	rowSet.populate(set);
	set.close();
	stmt.close();
}


private void setColumnNames(ResultSetMetaData metadata)throws Exception{
	columnsType=new HashMap<String, Integer>();
	header=new ArrayList<String>();
	for(int i=1;i<=metadata.getColumnCount();i++){
		String colName=metadata.getColumnName(i);
		int type=metadata.getColumnType(i);
		header.add(colName);
		columnsType.put(colName, type);
	}
	
}
public void close(){
	try{
	this.connection.close();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
}
