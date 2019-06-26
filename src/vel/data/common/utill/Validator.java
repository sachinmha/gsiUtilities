package vel.data.common.utill;

import java.sql.Connection;
import java.util.Map;

public interface Validator {

	public boolean validate(Map<String, Object> data,Connection connection);
}
