package vel.data.common.utill;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class GenerateInsertStatementsDB {
    private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String JDBC_URL = "jdbc:oracle:thin:@192.168.20.11:1521:ORACLE";
    private static final String JDBC_USER = "AUGMENT";
    private static final String JDBC_PASSWD = "password";

    private static final SimpleDateFormat dateFormat = 
                         new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
    	
    	String dbObject="TT_CLAIM_HBA_ADVANCE";
    	
    	String[] tableNameArray={"AUGMS_DBOBJECT","AUGMS_SERVICE",
    			"AUGMS_DBSERVICE","AUGMS_JAVASERVICE"};
    	String[] conditionArray={" where dbobject='"+dbObject+"'"," where dbobject='"+dbObject+"'",
    			" where serviceid in (select serviceID from augms_service where dbobject='"+dbObject+"')", 
    			" where serviceid in (select serviceID from augms_service where dbobject='"+dbObject+"')"};
    	Class.forName(JDBC_DRIVER);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWD);      
        	for(int i=0;i<tableNameArray.length;i++){
        		generateInsertStatements(conn, tableNameArray[i], conditionArray[i]);
        	}      
        }
        finally {
            if (conn != null) conn.close();
        }
        
    }

    private static void generateInsertStatements(Connection conn, String tableName, String condition) 
                        throws Exception {
        log("Generating Insert statements for: " + tableName);
        Statement stmt = conn.createStatement();
        log("SELECT * FROM " + tableName + condition);
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + condition); 
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();
        int[] columnTypes = new int[numColumns];
        String columnNames = "";
        for (int i = 0; i < numColumns; i++) {
            columnTypes[i] = rsmd.getColumnType(i + 1);
            if (i != 0) {
                columnNames += ",";
            }
            columnNames += rsmd.getColumnName(i + 1);
        }

        java.util.Date d = null; 
        String filePath="requisition";
        File directory=new File("D:\\"+filePath+"\\");
        if (! directory.exists()){
            directory.mkdir();
        }
        File file=new File("D:\\"+filePath+"\\"+tableName + "_insert.sql");
        PrintWriter p = new PrintWriter(new FileWriter(file));
        p.println("set define off;");
        while (rs.next()) {
            String columnValues = "";
            for (int i = 0; i < numColumns; i++) {
                if (i != 0) {
                    columnValues += ",";
                }

                switch (columnTypes[i]) {
                    case Types.BIGINT:
                    case Types.BIT:
                    case Types.BOOLEAN:
                    case Types.DECIMAL:
                    case Types.DOUBLE:
                    case Types.FLOAT:
                    case Types.INTEGER:
                    case Types.SMALLINT:
                    case Types.TINYINT:
                        String v = rs.getString(i + 1);
                        columnValues += v;
                        break;

                    case Types.DATE:
                        d = rs.getDate(i + 1); 
                    case Types.TIME:
                        if (d == null) d = rs.getTime(i + 1);
                    case Types.TIMESTAMP:
                        if (d == null) d = rs.getTimestamp(i + 1);

                        if (d == null) {
                            columnValues += "null";
                        }
                        else {
                            columnValues += "TO_DATE('"
                                      + dateFormat.format(d)
                                      + "', 'YYYY/MM/DD HH24:MI:SS')";
                        }
                        break;

                    default:
                        v = rs.getString(i + 1);
                        if (v != null) {
                            columnValues += "'" + v.replaceAll("'", "''") + "'";
                        }
                        else {
                            columnValues += "null";
                        }
                        break;
                }
            }
            p.println(String.format("INSERT INTO %s (%s) values (%s);", 
                                    tableName,
                                    columnNames,
                                    columnValues));
        }
        p.println("commit;");
        p.close();
        log("Finished:::::");
    }

    private static void log(String s) {
        System.out.println(s);
    }
}
