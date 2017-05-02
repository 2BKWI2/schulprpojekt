package paket_713_ArtikelMitRelDB2;

import java.sql.*;

public class DBVerbindung {
	private Connection dieVerbindung;
	private Statement stmtSQL = null;
	
	public boolean oeffneDB ()
	{
		boolean mOK = true;
		  try
		  {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    dieVerbindung = DriverManager.getConnection("jdbc:mysql://127.0.0.1/haro","root","");
		    stmtSQL = dieVerbindung.createStatement();
		  }
		  catch (Exception ex)
		  {
		    mOK = false;
		  }
		  return mOK;
	}
	public boolean schliesseDB ()
	{
		boolean mOK = true;
		try
		{
			stmtSQL.close();
			dieVerbindung.close();
		}
		catch (Exception err)
		{
			mOK = false;
		}
		return mOK;
	}
	public boolean aendern (String pSQL)
	{
		boolean mOK = true;
		try
		{
			stmtSQL.executeUpdate(pSQL);
		}
		catch (SQLException err)
		{
			mOK = false;
		}
		return mOK;
	}
	public ResultSet lesen(String pSQL)
	{
		ResultSet rs;
		try
		{
		  rs = stmtSQL.executeQuery(pSQL);
		}
		catch(SQLException err)
		{
		  rs = null;
		}
		return rs;
	}
}
