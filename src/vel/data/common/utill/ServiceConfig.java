package vel.data.common.utill;

import java.sql.Connection;
import java.util.Map;

public class ServiceConfig {

	private String query;
	private String params;
	private String className;
	
	private String type;
	private String connectionRef;
	private Connection connection;
	private Map<String, Object> data;
	private Configuration config;
	
	public ServiceConfig(Configuration config){
		this.config=config;
	}
	
	
	
	
	public String getConnectionRef() {
		return connectionRef;
	}
	public void setConnectionRef(String connectionRef) {
		this.connectionRef = connectionRef;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public Service getService()throws Exception{
		Service service=null;
		if(this.connectionRef!=null && config.getConnectionMap()!=null && config.getConnectionMap().containsKey(this.connectionRef)){
		this.connection=config.getConnectionMap().get(connectionRef).getConnection();
	
		}
		if(this.type.equals("java")){
			service=new JavaService();
			service.init(this);
			
		}else if(this.type.equals("javaVal")){
			service=new ValJavaService();
			service.init(this);
		}else if(this.type.equals("dbVal")){
			service=new ValDBService();
			service.init(this);
		}
		else if(this.type.equals("cacheSelectdb")){
			service=new SingleValueService();
			service.init(this);
		}
		else if(this.type.equals("insertdb")){
			service=new InsertService();
			service.init(this);
		}
		else if(this.type.equals("selectdb")){
			service=new SelectService();
			service.init(this);
		}
		else if(this.type.equals("listdb")){
			service=new ListService();
			service.init(this);
		}
		return service;
	}
	
}
