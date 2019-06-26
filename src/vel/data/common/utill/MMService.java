package vel.data.common.utill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MMService implements ServiceMathod {

	static int id=1001;
	@Override
	public void performTask(Map<String, Object> data, Connection connection) {
		
		data.put("ID",id);
		
		
		//id+=1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void performTask22(Map<String, Object> data, Connection connection) {
		// TODO Auto-generated method stub
	//	System.out.println("DesignationService ::  "+id);
	//	data.put("ID", id);
	String poid=(String)	data.get("OU_PARENT");
	String ouId=(String)	data.get("OU_ID");
	
	if(poid!=null){
		if(ouId==null || ouId.trim().length()<2){
	int size= poid.trim().length();
		try{
			System.out.println(poid);
	Statement stm=connection.createStatement();
	ResultSet set=stm.executeQuery("select OUID ouid from augment.augms_ou where OUID like '"+poid.trim()+"%'");
	String nid=poid+"01";
		
	 nid=newChildID(set, size,Integer.parseInt(nid));
	if(nid.equals("1")){
		nid=poid+"01";
	}
		
		System.out.println(poid+"      Child ID Generated   >>  "+nid);
		stm.close();
		data.put("OU_ID",nid);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		else{
			System.out.println(poid+"      Child ID Come from sheet   >>  "+ouId);
		}
	}}
	
	public String newChildID(ResultSet set,int size,int nid) throws Exception{
		String id="";
		size+=2;
		int x=0;
		List<Integer> lst=new ArrayList<Integer>();
			while(set.next()){
				String oid=set.getString("ouid");
				if(oid!=null && oid.trim().length()==size){
					int ab=Integer.parseInt(oid);
					lst.add(ab);
					
				}
			}	
				
			while(lst.contains(nid)){
				nid++;
			}
			id=""+nid;
				return id;
	}
}
