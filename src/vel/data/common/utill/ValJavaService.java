package vel.data.common.utill;

public class ValJavaService extends Service{

	private String className;
	private Validator method;
	private boolean flag;
	
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
				if(object instanceof Validator){
					method=(Validator)object;
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
		this.flag=   this.method.validate(getData(), connection);
		
	}
	
	public boolean getFlag(){
		boolean fl=this.flag;
		this.flag=true;
		return fl;
		
	}

}
