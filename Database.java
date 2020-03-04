package allTogether;
import java.sql.*;

public class Database {

	private String url = "jdbc:mysql://localhost:3306/cyberminerdb?serverTimezone=CST";
	private String user = "student";
	private String password = "student";
	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	
	public Database()
	{
		try 
		{
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(url, user, password);

			// 2. Create a statement
			myStmt = myConn.createStatement();
		}
		catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
		}
	}
	
	boolean truncateDatabase() 
	{
		try 
		{
			myStmt.executeUpdate("TRUNCATE cyberminer");
		}
		catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
		}
		return true;
	}

	public boolean add(String s_index, String s_rest_string, String s_url, String s_title, String s_description) 
	{		
		String sql = "INSERT INTO cyberminer"
				+ " (`index`, rest_string, url, title, description, date_added)"
				+ " values ('" + s_index + "', '" + s_rest_string + "', '" + s_url + "',"
						+ " '" + s_title + "', '" + s_description + "', CURDATE())";
		try 
		{
			myStmt.executeUpdate(sql);
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
		}
		return true;
	}

	public boolean search(String s_mySearch) 
	{
		try 
		{
			String query = "SELECT * FROM cyberminer WHERE `index` = '"+s_mySearch+"'";
			myRs = myStmt.executeQuery(query);
			return true;
		}
		catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return false;
		}
	}

	public String getSerialNo() 
	{
		if (myRs == null)
		{
			return null;
		}
		try 
		{
			return myRs.getString("serial_no");
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return null;
		}
	}

	public String getIndex() 
	{
		if (myRs == null) 
		{
			return null;
		}
		try 
		{
			return myRs.getString("index");
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return null;
		}
	}

	public String getRestString() 
	{
		if (myRs == null)
		{
			return null;
		}
		try 
		{
			return myRs.getString("rest_string");
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return null;
		}
	}

	public String getURL() 
	{
		if (myRs == null)
		{
			return null;
		}
		try 
		{
			return myRs.getString("url");
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return null;
		}
	}

	public String getTitle() 
	{
		if (myRs == null)
		{
			return null;
		}
		try 
		{
			return myRs.getString("title");
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return null;
		}
	}

	public String getDescription() 
	{
		if (myRs == null)
		{
			return null;
		}
		try 
		{
			return myRs.getString("description");
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return null;
		}
	}

	public String getDateAdded() 
	{
		if (myRs == null)
		{
			return null;
		}
		try 
		{
			return myRs.getString("date_added");
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return null;
		}
	}

	public boolean next() 
	{
		if (myRs == null)
		{
			return false;
		}
		try 
		{
			return myRs.next();
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return false;
		}
	}

	public boolean previous() 
	{
		if (myRs == null)
		{
			return false;
		}
		try 
		{
			return myRs.previous();
		}
		catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return false;
		}
	}
	
	public boolean isFirst()
	{
		if (myRs == null) 
		{
			return false;
		}
		try 
		{
			return myRs.isFirst();
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return false;
		}
	}
	
	public boolean isLast()
	{
		if (myRs == null) 
		{
			return false;
		}
		try 
		{
			return myRs.isLast();
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return false;
		}
	}
	
	public boolean isBeforeFirst()
	{
		if (myRs == null)
		{
			return false;
		}
		try 
		{
			return myRs.isBeforeFirst();
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return false;
		}
	}
	
	public boolean isAfterLast() 
	{
		if (myRs == null)
		{
			return false;
		}
		try
		{
			return myRs.isAfterLast();
		}catch (Exception exc) 
		{
			System.out.println("++++++Exception Caught++++++");
			exc.printStackTrace();
			return false;
		}
	}
	
	public ResultSet getRS()
	{
		return myRs;
	}
	
	boolean hasNoiseWord(String string) 
	{

		String noise = ",a,able,about,across,after,all,almost,also" +
				",am,among,an,and,any,are,as,at,be,because,been,but" +
				",by,can,cannot,could,dear,did,do,does,either,else" +
				",ever,every,for,from,get,got,had,has,have,he,her,hers" +
				",him,his,how,however,i,if,in,into,is,it,its,just,least" +
				",let,like,likely,may,me,might,most,must,my,neither,no" +
				",nor,not,of,off,often,on,only,or,other,our,own,rather" +
				",said,say,says,she,should,since,so,some,than,that,the" +
				",their,them,then,there,these,they,this,tis,to,too,twas" +
				",us,wants,was,we,were,what,when,where,which,while,who" +
				",whom,why,will,with,would,yet,you,your,";

		return noise.contains("," + string.toLowerCase() + ",");
	}
}

