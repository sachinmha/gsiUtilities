package vel.data.common.utill;

import java.util.*;

public class Test {

	public static void main(String[] args) throws Exception {

		run();
	}

	public static void run() {
		try {

			Configuration c = Configuration.getInstance();
			SourceData source = c.getSource();
			source.prepare(c);

			Map<String, Object> data = source.getProvider().getRow();

			DataFormator formator = c.getDataFormator();
			DataValidation validator = c.getValidation();
			TargetOperation target = c.getTargetOpration();
			while (data != null) {

				if (validator != null) {

					validator.setData(data);
					if (validator.validate()) {

						if (formator != null) {
							formator.setData(data);
							formator.format();
						}
						target.setData(data);
						target.execute();

					}
				}
				data = source.getProvider().getRow();
			}

			System.out.println("finish");
			// System.out.println(EmpValidate.melist);
			source.getProvider().close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

}
