package vel.data.common.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static String DATE_FORMAT="dd/MM/yyyy";
	
	private static String TIMESTAMP_FORMAT="dd/MM/yyyy hh:mm:ss";
	
	
	
	
	public static String getDate(Date dat){
		StringBuffer date=new StringBuffer();
		if(dat!=null){
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			
			 date.append( dateFormat.format(dat));
		}
		return date.toString();
	}
	
	public static String getDateTime(Date dat){
		StringBuffer dateTime=new StringBuffer();
		if(dat!=null){
			DateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
			
			dateTime.append( dateFormat.format(dat));
		}
		return dateTime.toString();
	}
	
	
	public static String getDate(Date dat,String format){
		StringBuffer date=new StringBuffer();
		if(dat!=null){
			DateFormat dateFormat = new SimpleDateFormat(format);
			
			 date.append( dateFormat.format(dat));
		}
		return date.toString();
	}
	
	public static String getDateTime(Date dat,String format){
		StringBuffer dateTime=new StringBuffer();
		if(dat!=null){
			DateFormat dateFormat = new SimpleDateFormat(format);
			
			dateTime.append( dateFormat.format(dat));
		}
		return dateTime.toString();
	}
	public static void main(String[] args) {
		System.out.println(getDate(new Date()));
		System.out.println(getDateTime(new Date()));
	}
}
