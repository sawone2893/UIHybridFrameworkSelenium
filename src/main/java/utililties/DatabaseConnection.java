package utililties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

public class DatabaseConnection {

	public Connection con;
	public Statement stmt;
	public ResultSet rs;
	public ResultSetMetaData rsmd;
	public PreparedStatement preparedStmt;

	//Author : Shabbir
	// This method is to store Resultset data into ArrayList from MS access Databse
	public ArrayList<ArrayList<String>> getRecordInArrayList(String dbURL, String selectQuery) {
		connectionSetupDB(dbURL);
		rs = fetchFromDBTable(selectQuery);
		ArrayList<ArrayList<String>> criteria = db2ArrayList(rs);
		return criteria;

	}
	
	//Author : Shabbir
	// This method is to store Resultset data into ArrayList from Oracle Databse
	public ArrayList<ArrayList<String>> getRecordInArrayListFromOracle(String oracleURL, String selectQuery){
		
		connectionSetupDB(oracleURL);
		rs = fetchFromDBTable(selectQuery);
		ArrayList<ArrayList<String>> criteria = db2ArrayList(rs);
		return criteria;
		
	}

	//Author : Shabbir
	public void deleteRecord(String dbURL, String deleteQuery) {
		connectionSetupDB(dbURL);

		try {
			preparedStmt = con.prepareStatement(deleteQuery);
			int rowsDeleted = preparedStmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println(rowsDeleted + " Record deleted successfully!");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			// Closing database connection
			try {
				if (null != con) {

					// cleanup resources, once after processin
					// and then finally close connection
					con.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

		}

	}

	//Author : Shabbir
	// This method is to update record in the database
	public void updateIntoDBTable(String dbURL, String updateQuery) {

		connectionSetupDB(dbURL);
		try {
			preparedStmt = con.prepareStatement(updateQuery);
			int rowsUpdated = preparedStmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println(rowsUpdated + " Records updated Successfully!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			// Closing database connection
			try {
				if (null != con) {

					// cleanup resources, once after processing
					// and then finally close connection
					con.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

		}

	}

	//Author : Shabbir
	// This method is to setup connect with Oracle database
	public void connectionSetupDB(String oracleURL) {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(oracleURL);
			/*System.out.println("DB Connection Setup");*/
		} catch (ClassNotFoundException cnfex) {
			cnfex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//Author : Shabbir
	// This method is to fetch data from database and store in Resultset
	public ResultSet fetchFromDBTable(String selectQuery) {

		try {

			//System.out.println("Creating JDBC Statement");
			stmt = con.createStatement();

			//System.out.println("Executing SQL Query and retrieving  data into ResultSet");

			rs = stmt.executeQuery(selectQuery);

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		}
		return rs;
	}
	
	
	//Author : Shabbir
	// This method is to insert data into database from ResultSet
	public void insertIntoDBTable(ResultSet rs, String insertQuery) throws SQLException, ParseException {

		preparedStmt = con.prepareStatement(insertQuery);
		int colNum = getTotalColNum(rs);
		while (rs.next()) {

			for (int columnCount = 1; columnCount < colNum; columnCount++) {
				//System.out.println(rs.getString(rsmd.getColumnName(columnCount)));
				//System.out.println(rsmd.getColumnTypeName(columnCount));
				//System.out.println(rsmd.getColumnName(columnCount));
				
				if(rsmd.getColumnTypeName(columnCount).equals("DOUBLE")){
					preparedStmt.setDouble(columnCount,rs.getDouble(rsmd.getColumnName(columnCount)));
				}else if(rsmd.getColumnTypeName(columnCount).equals("INTEGER")){
					preparedStmt.setInt(columnCount,rs.getInt(rsmd.getColumnName(columnCount)));
				}else if(rsmd.getColumnTypeName(columnCount).equals("TIMESTAMP")){
					preparedStmt.setDate(columnCount,rs.getDate(rsmd.getColumnName(columnCount)));
				}else{
					preparedStmt.setString(columnCount, rs.getString(rsmd.getColumnName(columnCount)));
				}
					
			}
			preparedStmt.execute();
		}

	}
	
	//Author : Shabbir
	//This method is to insert row into database directly
	public void insertRow(String dbURL,String insertQuery){
		connectionSetupDB(dbURL);
		try {
			preparedStmt = con.prepareStatement(insertQuery);
			int rowsUpdated = preparedStmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println(rowsUpdated + " Records updated Successfully!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			// Closing database connection
			try {
				if (null != con) {

					// cleanup resources, once after processing
					// and then finally close connection
					con.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}

		
	}
	
	//Author : Shabbir
	// This method is to store data from ResultSet to ArrayList of
	// ArrayList<String>
	public ArrayList<ArrayList<String>> db2ArrayList(ResultSet rs) {
		// ArrayList to store all excel rows
		ArrayList<ArrayList<String>> dbRecords = new ArrayList<ArrayList<String>>();

		try {

			int totalCol = getTotalColNum(rs);
			String cellValue = null;
			int rnum = 0;
			while (rs.next()) {

				// ArrayList is define to store single row
				ArrayList<String> dbRow = new ArrayList<String>();

				for (int i = 1; i < totalCol; i++) {
					cellValue = rs.getString(i);
					dbRow.add(i - 1, cellValue);
				}
				dbRecords.add(rnum, dbRow);
				rnum++;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return dbRecords;
	}
	
	//Author : Shabbir
	public ArrayList<String> returnArrayList(ResultSet rs) {
		// ArrayList to store all excel rows
		ArrayList<String> dbRow = new ArrayList<String>();

		try {

			int totalCol = getTotalColNum(rs);
			String cellValue = null;
			int rnum = 0;
			while (rs.next()) {

				// ArrayList is define to store single row
				

				for (int i = 1; i < totalCol; i++) {
					cellValue = rs.getString(i);
					dbRow.add(i - 1, cellValue);
				}
				rnum++;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return dbRow;
	}

	//Author : Shabbir
	// This method is to get number of columns from database
	public int getTotalColNum(ResultSet rs) {
		int totalCol = 0;

		try {
			rsmd = rs.getMetaData();
			totalCol = rsmd.getColumnCount() + 1;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return totalCol;

	}
	
	/****************************************************************************
	 * Method : Close the Connection 
	 * Author : Shabbir
	 *****************************************************************************/
	public void closeConnection() {

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/****************************************************************************
	 * Method : Close the ResultSet 
	 * Author : Shabbir
	 *****************************************************************************/
	public void closeResultSet() {

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/****************************************************************************
	 * Method : Close the ResultSet 
	 * Author : Shabbir
	 *****************************************************************************/
	public void closeStatement() {

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getQueryResult(String dbUrl,String query)
	{
		connectionSetupDB(dbUrl);
		String data=null;
		try 
		{
			rs = fetchFromDBTable(query);
            while (rs.next())
            {
            	data=rs.getString(1);
            }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}finally {
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return data;
	}
}
