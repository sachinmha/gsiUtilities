package vel.data.common.utill;

import java.sql.Connection;
import java.util.Map;

public interface ServiceMathod {

	public void performTask(Map<String, Object> data, Connection connection);
}
