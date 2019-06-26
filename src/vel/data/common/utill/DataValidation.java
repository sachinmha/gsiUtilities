package vel.data.common.utill;

import java.util.List;
import java.util.Map;

public class DataValidation {

private 	List<ServiceConfig> serviceList;

private Map<String, Object> data;

public List<ServiceConfig> getServiceList() {
	return serviceList;
}

public void setServiceList(List<ServiceConfig> serviceList) {
	this.serviceList = serviceList;
}

public Map<String, Object> getData() {
	return data;
}

public void setData(Map<String, Object> data) {
	this.data = data;
}


public boolean validate()throws Exception{
	boolean flag=true;
	
	if(serviceList!=null){
		for(ServiceConfig sc:serviceList){
			Service serv=sc.getService();
			if(serv instanceof ValDBService){
				ValDBService val=(ValDBService)serv;
				val.setData(data);
				val.execute();
				if(!val.isFlag()){
					return false;
				}
			} else if(serv instanceof ValJavaService){
				ValJavaService val=(ValJavaService)serv;
				val.setData(data);
				val.execute();
				if(!val.getFlag()){
					return false;
				}
			}
		}
		
	}
	
	return flag;
	
	
}


}
