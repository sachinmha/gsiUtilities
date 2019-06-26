package vel.data.common.utill;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Configuration {
	private static Configuration configuration;
	private HashMap<String, DBConnection> connectionMap;
	private DataFormator dataFormator;
	private TargetOperation targetOpration;
	private SourceData source;
    private DataValidation validation;
	private Configuration(){}
	
	
	public SourceData getSource() {
		return source;
	}


	public void setSource(SourceData source) {
		this.source = source;
	}


	public DataValidation getValidation() {
		return validation;
	}


	public void setValidation(DataValidation validation) {
		this.validation = validation;
	}


	public HashMap<String, DBConnection> getConnectionMap() {
		return connectionMap;
	}
	public void setConnectionMap(HashMap<String, DBConnection> connectionMap) {
		this.connectionMap = connectionMap;
	}
	public DataFormator getDataFormator() {
		return dataFormator;
	}
	public void setDataFormator(DataFormator dataFormator) {
		this.dataFormator = dataFormator;
	}
	public TargetOperation getTargetOpration() {
		return targetOpration;
	}
	public void setTargetOpration(TargetOperation targetOpration) {
		this.targetOpration = targetOpration;
	}

	public static Configuration getInstance()throws Exception{
		if(	configuration==null){

			configuration=new Configuration();
			configuration.load();
		}
		return configuration;
	}


	private void load()throws Exception{
		try{

			InputStream input = new FileInputStream("D:\\NewGSIWorkspace\\dataUtil\\src\\vel\\data\\common\\utill\\config.xml");
			SAXReader _read = new SAXReader();
			Document _dom = _read.read(input);
			Element root=_dom.getRootElement();
			loadConnection(root);
			loadSourceData(root);
			loadValidation(root);
			loadFormators(root);
			loadTarget(root);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void loadValidation(Element root){

		List<Element> formatorList=	root.elements("data-validation");

		if(formatorList!=null &&formatorList.size()>0 ){
			validation=new DataValidation();
			Element format=formatorList.get(0);
			List<Element> serviceList=format.elements("service");
			if(serviceList!=null && serviceList.size()>0){
				validation.setServiceList(new ArrayList<ServiceConfig>());
				ServiceConfig config=null;
				for(Element service: serviceList){
					config=loadService(service);
					if(config.getType().equals("java")){
						config.setType("javaVal");
					}else{
						config.setType("dbVal");
					}
					validation.getServiceList().add(config);
				}

			}

		}





	}



	private void loadTarget(Element root){
		List<Element> targetList=	root.elements("target-operation");

		if(targetList!=null &&targetList.size()>0 ){
			this.targetOpration=new TargetOperation();
			Element target=targetList.get(0);
			List<Element> serviceList=target.elements("service");
			if(serviceList!=null && serviceList.size()>0){
				this.targetOpration.setServiceList(new ArrayList<ServiceConfig>());
				ServiceConfig config=null;
				for(Element service: serviceList){
					this.targetOpration.getServiceList().add(loadService(service));
				}
			}
		}


	}


	private void loadFormators(Element root){

		List<Element> formatorList=	root.elements("data-format-services");

		if(formatorList!=null &&formatorList.size()>0 ){
			dataFormator=new DataFormator();
			Element format=formatorList.get(0);
			List<Element> serviceList=format.elements("service");
			if(serviceList!=null && serviceList.size()>0){
				dataFormator.setServices(new ArrayList<ServiceConfig>());
				ServiceConfig config=null;
				for(Element service: serviceList){
					config=loadService(service);
					dataFormator.getServices().add(config);
				}

			}

		}





	}










	private ServiceConfig loadService(Element serv){
		ServiceConfig config=null;
		if(serv!=null){
			config=new ServiceConfig(this);
			config.setType(serv.attributeValue("type"));
			config.setConnectionRef(serv.attributeValue("connection"));
			System.out.println(config.getConnectionRef());
			List<Element> lst=serv.elements("params");
			Element ele=null;
			if(lst!=null && lst.size()>0){
				ele=lst.get(0);
				config.setParams(ele.getTextTrim());
			}
			lst=serv.elements("query");

			if(lst!=null && lst.size()>0){
				ele=lst.get(0);
				config.setQuery(ele.getTextTrim());
			}
			lst=serv.elements("class");

			if(lst!=null && lst.size()>0){
				ele=lst.get(0);
				config.setClassName(ele.attributeValue("name"));
				
			}	

		}

		return config;

	}






	private void loadSourceData(Element root){
		List<Element> sourceList=	root.elements("data-source");

		if(sourceList!=null &&sourceList.size()>0 ){
			this.source=new SourceData();
			Element source=sourceList.get(0);
			List<Element> excelList=source.elements("excelservice");
			if(excelList!=null && excelList.size()>0){
				Element excel=excelList.get(0);
				this.source.setType("excel");
				this.source.setFilePath(excel.attributeValue("filePath"));
				this.source.setSheetName(excel.attributeValue("sheetIndex"));
			}else{
				List<Element> dbList=source.elements("dbservice");
				if(dbList!=null && dbList.size()>0){
					Element db=dbList.get(0);
					this.source.setType("db");
					this.source.setConnectionRef(db.attributeValue("connection"));
					List<Element> lst=db.elements("query");
					Element ele=null;
					if(lst!=null && lst.size()>0){
						ele=lst.get(0);
						this.source.setDbQuery(ele.getTextTrim());
					}



				}
			}



		}

	}






	private void loadConnection(Element root)throws Exception{
		List<Element> connectionsList=	root.elements("database-connections");

		if(connectionsList!=null){
			connectionMap=new HashMap<String, DBConnection>();

			List<Element> connections=	connectionsList.get(0).elements("connection");
			for(Element con:connections){
				DBConnection dbcon=new DBConnection();
				dbcon.setName(con.attributeValue("name"));
				List<Element> lst=con.elements("driver-class");
				Element ele=null;
				if(lst!=null && lst.size()>0){
					ele=lst.get(0);
					dbcon.setDriverName(ele.getTextTrim());
				}
				lst=con.elements("password");

				if(lst!=null && lst.size()>0){
					ele=lst.get(0);
					dbcon.setPassword(ele.getTextTrim());
				}
				lst=con.elements("jdbc-url");

				if(lst!=null && lst.size()>0){
					ele=lst.get(0);
					dbcon.setJdbcURL(ele.getTextTrim());
				}
				lst=con.elements("user");

				if(lst!=null && lst.size()>0){
					ele=lst.get(0);
					dbcon.setUserName(ele.getTextTrim());
				}
				lst=con.elements("init-param");

				if(lst!=null && lst.size()>0){
					ele=lst.get(0);
					dbcon.setInitParam(ele.getTextTrim());
				}
				connectionMap.put(dbcon.getName(),dbcon);
			}
		}
	}



	public static void main(String[] args)throws Exception {
		Configuration c=new Configuration();
		c.load();
		System.out.println("finish");
	}

}
