package vel.data.common.utill;

public class JavaService extends Service{

	private String className;
	private ServiceMathod method;
	
	@Override
	public void init(ServiceConfig config) {
		this.className=config.getClassName();
		super.init(config);
		
	}
	
	private void initialize(){
		try{
			if(this.className!=null){
				Class c=Class.forName(this.className);
				Object object=c.newInstance();
				if(object instanceof ServiceMathod){
					method=(ServiceMathod)object;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void execute() throws Exception {
		if(this.method==null)
			initialize();
		if(this.method==null)
			throw new Exception("Service Method is null");
		this.method.performTask(getData(), connection);
		
	}

}
