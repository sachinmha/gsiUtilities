package vel.data.common.utill;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;



public abstract class DBService  extends Service{

	protected String query;
	protected String params;
	
	protected PreparedStatement statement;
	protected boolean flag=false;
	
	public void init(ServiceConfig config){
		this.query=config.getQuery();
		this.params=config.getParams();
		super.init(config);
	}
	
	
	
	protected void setParams() throws SQLException{
		statement=this.connection.prepareStatement(query);
		if(this.params!=null){
			String param[]=this.params.split("~");
			int index=1;
			for(String name:param){
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
	}
	


	
}
