package vel.data.common.utill;

import java.sql.Connection;
import java.util.Map;

public abstract class Service {
	protected Connection connection;
	protected Map<String, Object> data;
	
	public void init(ServiceConfig config){
		this.connection=config.getConnection();
		this.data=config.getData();
	}
	
	

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public abstract void execute() throws Exception;
	
}
