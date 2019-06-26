package vel.data.common.utill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpValidate implements Validator{

	static int xx=1;
	private static HashMap<String, String>  langData=new HashMap<String, String>();
	static boolean isnew=false;
	static List<String> list=new ArrayList<String>();
	static List<String> melist=new ArrayList<String>();
	static List<String> lst=null;
	static{
		
		
	
	}
	public boolean validate(Map<String, Object> data, Connection connection) {
		boolean flag=true;
		
		if(data==null){
			
			return false;
		}
		else{
		
	

		
		
			String emp=(String)data.get("EMP_ID");	
			String yr=(String)data.get("PROP_IMV_PROP_YR");
		//	System.out.println(emp+"-"+yr);	
			if(lst==null){
					lst=new ArrayList<String>();
				}
				if(lst.contains(emp+"-"+yr)){
					return false;
				}else{
						lst.add(emp+"-"+yr);
					String str=(String)data.get("PGM_GRADE_DES");
					if(desg.containsKey(str)){
						data.put("DESIGID", desg.get(str));
					}else{
						return false;
					}
				}
				
			return true;
		}
		
	}
	private boolean isNill(String val){
		if(val==null){
			return true;}
		else if(val.trim().length()<1 || val.trim().equalsIgnoreCase("NILL") || val.trim().equalsIgnoreCase("NIL") || val.trim().equalsIgnoreCase("NA") || val.trim().equalsIgnoreCase("N/A") )
		{
			return false;
		}
		return true;
	}
	
	
	
	static Map<String, String>  desg=new HashMap<String, String>();
	static{
		
		desg.put("Joint Director (Personnel and Administration)", "92502");
		desg.put("Deputy Director General(Chemistry)", "90200");
		desg.put("Director(Geology)", "92100");
		desg.put("Deputy Director General(Geology)", "90200");
		desg.put("Director(Mineral Physics)(Sl.Gr.)", "92100");
		desg.put("Director(Mineral Physics)", "92100");
		desg.put("Director(Finance and Accounts)", "92100");
		desg.put("Assistant Director (P & A)", "92508");
		desg.put("Asstt. Director(P&A)", "92508");
		desg.put("Senior Technical Assistant(Geo Physics)", "92260");
		desg.put("Senior Technical Assistant(GWS)", "92260");
		desg.put("Lab.Assistant(Geol.) Grade-II", "91730");
		desg.put("Lab.Assistant(Geol) Grade-III", "91740");
		desg.put("Lab.Assistant(Geol.) Grade-I", "92100");
		desg.put("Director(Geophysics)", "92100");
		desg.put("Lab.Assistant(GWS) Grade-I", "91720");
		desg.put("Lab.Assistant(GWS) Grade- II", "91730");
		desg.put("Laboratory Assistant Grade II (Geophysics)", "91730");
		desg.put("Lab.Assistant(Chemical) Grade-III", "91740");
		desg.put("Laboratory Assistant Grade III (Geophysics)", "91740");
		desg.put("Multi Tasking Staff (MTS)", "91141");
		desg.put("Deputy Director General(Geophysics)", "90200");
		desg.put("Stenographer Grade-II (PB-1)", "90920");
		desg.put("PERSONAL ASSISTANT", "92360");
		desg.put("Senior Technical Assistant(Geology)", "92260");
		desg.put("Director(Mechanical Engineer)", "92100");
		desg.put("Joint Director (P & A)", "92502");
		desg.put("MTS Messenger MACP Second", "91143");
		desg.put("Director (Geology) (Selection Grade)", "92100");
		desg.put("Director(Mechanical Engineer)(Sl.Gr.)", "92100");
		desg.put("STA (D)-Engineering Stream", "92260");
		desg.put("Senior Technical Assistant(Photo)", "92260");
		desg.put("JTA (D)-Engineering Stream", "91760");
		desg.put("Lab Assistant (D)- Engineering Stream", "91720");
		desg.put("Director(Drilling)(Sl.Gr.)", "92100");
		desg.put("MTS Messenger MACP First", "91142");
		desg.put("Cleaner(ACP Second)", "91143");
		desg.put("Senior Technical Assistant(Survey)", "92260");
		desg.put("Director(Drilling) ", "92100");
		desg.put("Lab.Assistant(Chemical) Grade-I", "91720");
		desg.put("Director(Chemistry)", "92100");
		desg.put("Lab.Assistant(Chemical) Grade-II", "91730");
		desg.put("Senior Technical Assistant(Geol/Sculp)", "92260"); 
		desg.put("Director(Geophysics)(Instrumentation)", "92100");
		desg.put("Director(Geophysics)(Instrumentation) Sl. Gr", "92100");
		desg.put("Laboratory Assistant Grade I (Geophysics)", "91720");
		desg.put("Deputy Director General(Drilling)", "90200");
		desg.put("Director (Personnel and Administration)", "92100");
		desg.put("Junior Technical Assistant(G/S)", "91410");
		desg.put("Lab Assistant (Workshop) Grade-III", "91740");
		desg.put("Lab.Assistant Grade-III", "91740");
		desg.put("Senior Technical Assistant(Blasting)", "92260");
		desg.put("Lab.Assistant Grade-II", "91730");
		desg.put("Senior Technical Assistant(Chemical)", "92260");
		desg.put("Lab.Assistant(Chemical) Grade-III(ACP Second)", "91740");
		desg.put("Deputy Director General(Personnel)", "90010");
		desg.put("Stenographer Grade-III", "92481");	
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
		ResultSet set=	stm.executeQuery("select PE.SEBD_EMPLOYEEID from TL_HRMS_SBOOK_EMP_BANK_DTL pe where PE.SEBD_EMPLOYEEID='"+lang+"'");
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
		ResultSet set=	stm.executeQuery("select * from tm_pms_pbr where pbr_id='"+ouid+"'");
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
