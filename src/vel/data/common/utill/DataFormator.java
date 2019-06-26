package vel.data.common.utill;

import java.util.Map;
import java.util.List;

public class DataFormator {

	private List<ServiceConfig> services;
	
	private Map<String,Object> data;
	
	public List<ServiceConfig> getServices() {
		return services;
	}

	public void setServices(List<ServiceConfig> services) {
		this.services = services;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void format()throws Exception{
		for(ServiceConfig conf:services){
			conf.setData(data);
			Service ss=conf.getService();
			
			ss.execute();
		}
	}
	
	
}
