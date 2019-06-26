package vel.data.common.utill;

public class SourceData {

	private String type;
	private Provider provider;
	private String filePath;
	private String connectionRef;
	private String dbQuery;
	private String sheetName;
	
	
	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getDbQuery() {
		return dbQuery;
	}
	public void setDbQuery(String dbQuery) {
		this.dbQuery = dbQuery;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Provider getProvider() {
		
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getConnectionRef() {
		return connectionRef;
	}
	public void setConnectionRef(String connectionRef) {
		this.connectionRef = connectionRef;
	}
	
	
	public void prepare(Configuration config){
		try{
		if(this.type.equals("excel")){
			  ExcelReader reader=new ExcelReader(this.filePath);
		        if(this.sheetName==null)
			     this.provider=new DataProvider(reader, 0);  
		        else
		        	 this.provider=new DataProvider(reader, 0,this.sheetName);  	
		}
		else if(this.type.equals("db")){
			System.out.println("gggg");
			this.provider=new DBDataProvider(this, config);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
