package vel.data.common.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

	private String name;
	private String driverName;
	private String jdbcURL;
	private String userName;
	private String password;
	private String initParam;
	
	private  Connection connection;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getJdbcURL() {
		return jdbcURL;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInitParam() {
		return initParam;
	}
	public void setInitParam(String initParam) {
		this.initParam = initParam;
	}
	public Connection getConnection() throws Exception{
		if(connection==null){
			createConnection();
		}
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	private void createConnection()throws Exception{
		Class.forName(driverName);
		connection=DriverManager.getConnection(jdbcURL,userName,password);
		if(this.initParam!=null){
			
			initCon();
			
		}
	}
	private void initCon(){
		try{
			Statement stm=this.connection.createStatement();
			stm.execute(initParam);
			stm.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
