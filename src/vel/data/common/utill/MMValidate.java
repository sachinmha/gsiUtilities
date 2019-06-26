package vel.data.common.utill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MMValidate implements Validator{

	static int xx=1;
	private static HashMap<String, String>  langData=new HashMap<String, String>();
	static boolean isnew=false;
	static List<String> list=new ArrayList<String>();
	static List<String> melist=new ArrayList<String>();
	static{
		
		
	
	}
	public boolean validate(Map<String, Object> data, Connection connection) {
		boolean flag=true;
		
		if(data==null){
			
			return false;
		}
		else{
		
	String lang=(String)data.get("EMP_ID");
	
		if(lang==null ){
			System.out.println("No Record ... ");
			return false;
		}


		/*
		if(list.contains(lang)){
			System.out.println("Duplicate Data ... ");
			return false;
		} else
		list.add(lang);
		if(isEmpExist(lang,connection)){
			//System.out.println("EmpExist ... ");
			return false;
		} else{
			System.out.println("Not found"+lang);
			melist.add(lang);
			//return false;
		}*/

		}
		return true;
	}
	
	
	private boolean employeeCorrectlyInserted(String lang, Connection connection) {
		boolean flag=false;
		try{
			Statement stm=connection.createStatement();
		ResultSet set=	stm.executeQuery("select wfoid from TL_PMS_MONTHLY_PAYBILL_DETAIL where mpbd_employee_id='"+lang+"' and wfoid is null");
		flag=set.next();
		stm.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			flag=true;
		}
		return flag;
	}


	private boolean isEmpExist(String lang, Connection con) {
		boolean flag=false;
		try{
			Statement stm=con.createStatement();
		ResultSet set=	stm.executeQuery("select PE.PH_PBR_HANDLER_ID from TM_PMS_PBR_HANDLER pe where PE.PH_PBR_HANDLER_ID='"+lang+"'");
		flag=set.next();
		stm.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			flag=true;
		}
		return flag;
	}


	public boolean isExist(String ouid, Connection con){
		boolean flag=false;
		try{
			Statement stm=con.createStatement();
		ResultSet set=	stm.executeQuery("select * from tm_hrms_parent_ou_join where pouj_ouid='"+ouid+"'");
		flag=set.next();
		stm.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			flag=true;
		}
		return flag;
	}
	
	
	
	
	public void removeDecimal(Map<String, Object> data){
		if(data!=null){
			for(String key:data.keySet()){
				setData(data,key);
			}
		}
	}
	public void setData(Map<String, Object> data, String key){
		if(data!=null && data.get(key)!=null ){
			String val=data.get(key).toString().trim();
			if(val.endsWith(".0")){
				val=val.substring(0,val.length()-2);
				
			}
			data.put(key,val);
		}
	}
	
	public boolean validate22(Map<String, Object> data, Connection connection) {
		boolean flag=true;
		if(data==null || data.get("MARITAL_STATUS_ID")==null){
			System.out.println("NULL Value found ::  ");
			return false;
		}
		else{
			String lang=(String)data.get("MARITAL_STATUS_ID");
			lang=lang.trim();
			
			System.out.println("value ::    "+lang);
			if(lang.length()<1 ||  langData.containsKey(lang)){
				System.out.println("Duplicate entry :: "+lang);
				return false;
			}
			langData.put(lang, "OK");
			data.put("MARITAL_STATUS_ID", lang);
		}
		
		
		return flag;
	}
}
