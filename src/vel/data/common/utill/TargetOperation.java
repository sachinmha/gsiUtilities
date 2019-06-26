package vel.data.common.utill;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class TargetOperation {

	private List<ServiceConfig> serviceList;
	private Map<String,Object> data;
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public List<ServiceConfig> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceConfig> serviceList) {
		this.serviceList = serviceList;
	}
	
	public void execute()throws Exception{
		int x=0;
		
		for(ServiceConfig conf:serviceList){
			
			conf.setData(data);
			Service ss=conf.getService();
			System.out.println(data );
			ss.execute();
			System.out.println("Success  :: ");
		}
		
	}
	
	
}
