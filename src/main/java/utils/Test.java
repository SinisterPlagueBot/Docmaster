package utils;

import com.jee.dao.OracleDataSource;

public class Test {

	public static void main(String[] args) {
		OracleDataSource ds =new OracleDataSource();
		System.out.println(ds.getConnection());
	}

}
