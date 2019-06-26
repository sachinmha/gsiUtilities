package vel.data.common.utill;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SingleValueService extends DBService{

	private static Map<String,Map<String, Map<String,Object>>> dataMap=new HashMap<String, Map<String,Map<String,Object>>>();
	
	public void execute()throws Exception{
	data.putAll(getCacheValue());
	
	}
	
	private Map<String,Object> populateRow(ResultSet set)throws SQLException{
		Map<String,Object> map=new HashMap<String, Object>();
		ResultSetMetaData metadata=set.getMetaData();
		for(int i=1;i<=metadata.getColumnCount();i++){
		if(	metadata.getColumnType(i)==Types.DATE){
			Date dt=set.getDate(metadata.getColumnName(i));
			if(dt!=null){
				map.put(metadata.getColumnName(i),DateUtil.getDate(dt));	
			}else{
			//	map.put(metadata.getColumnName(i),DateUtil.getDate(new Date()));	
			}
			
			
		} else if(	metadata.getColumnType(i)==Types.TIMESTAMP){
			Date dt=set.getTimestamp(metadata.getColumnName(i));
			if(dt!=null){
				map.put(metadata.getColumnName(i),DateUtil.getDateTime(dt));	
			}else{
			//	map.put(metadata.getColumnName(i),DateUtil.getDateTime(new Date()));	
			}
		} else {
			map.put(metadata.getColumnName(i),set.getString(metadata.getColumnName(i)));
		} 
		}
		return map;
	}
	
	private Map<String,Object> getCacheValue()throws Exception{
		Map<String,Object> val=null;
		Object vals=data.get(params);
		if(vals!=null && vals.toString().trim().length()>0){
		if(dataMap.get(query)==null){
			dataMap.put(query, new HashMap<String, Map<String,Object>>());
		}
	val=	dataMap.get(query).get(vals.toString());
	
    if(val==null){
	
       setParams();
		
		if(flag){
		ResultSet set=this.statement.executeQuery();
		if(set!=null && set.next()){
			val=	populateRow(set);
			dataMap.get(query).put(vals.toString(),val);
			
		}else{
			dataMap.get(query).put(vals.toString(),new HashMap<String, Object>());
		}
		}
		this.statement.close();
	
       }
		
		}
		if(val==null)
		val=new HashMap<String, Object>();
		
		return val;
	}
	
	protected void setParams() throws SQLException{
		
		
		statement=this.connection.prepareStatement(query);
	String name=params;
	int index=1;
				Object value=data.get(name);
				
				if (value==null) {
					
					 this.statement.setNull(index, Types.VARCHAR);
				}else{
					if(value.toString().trim().length()>0){
						flag=true;
					}
				if ( value instanceof String ){
					
			  			this.statement.setString(index, (String)value);
			  	} else if ( value instanceof Integer ){
					Integer paramVal = (Integer)value;
					this.statement.setInt(index, paramVal.intValue());
			  	}
			  	
			  	else if ( value instanceof Boolean ){
					Boolean paramVal = (Boolean)value;
					this.statement.setBoolean(index, paramVal.booleanValue());
			  	} else if ( value instanceof java.util.Date ){
					java.util.Date dateUtil = (java.util.Date)value;
					java.sql.Timestamp timestampSql = new java.sql.Timestamp(dateUtil.getTime());
					this.statement.setTimestamp(index, timestampSql);
			  	} else if ( value instanceof java.math.BigDecimal ){
			  		this.statement.setBigDecimal(index, (BigDecimal)value);
				} else if ( value instanceof java.lang.Double){
					Double paramVal = (Double)value;
					this.statement.setDouble(index, paramVal.doubleValue());
				} else if ( value instanceof java.lang.Float){
					Float paramVal = (Float)value;
					this.statement.setFloat(index, paramVal.floatValue());
				} } 
				index++;
			
			
		
	}
	

}
