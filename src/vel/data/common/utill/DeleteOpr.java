package vel.data.common.utill;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteOpr {

	public static void main(String[] args)throws Exception {
		Connection con=getConnection();
		BufferedReader input= new BufferedReader(new InputStreamReader(  new FileInputStream("C:\\Users\\Administrator\\Desktop\\HRMS_DATA\\ifms_tables_62.txt")));
		String tname=input.readLine();
		while(tname!=null){
			
			if(tname.trim().length()>2)
				delete(con,tname);
			
			
			 tname=input.readLine();
		}
	
		
		con.close();
	}
	public static void delete(Connection con,String tname)throws Exception{
		try{
			Statement stm=con.createStatement();
			stm.executeUpdate("delete from "+tname+"");
			stm.close();
			System.out.println("Data successfully removed form "+tname);
		}
		catch (Exception e) {
			System.out.println(tname+"   ::   "+e);
			throw e;
		}
	}
	
	public static Connection getConnection()throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@192.168.20.62:1521:XE","DEV_EGOV_ADMIN","password");
	} 
	
}
