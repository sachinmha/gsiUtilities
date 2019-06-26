package vel.data.common.utill;

import java.util.List;
import java.util.Map;

public abstract class Provider {
	protected List<String> header;
	
	public abstract Map<String, Object> getRow();
	
	public abstract void close();
}
