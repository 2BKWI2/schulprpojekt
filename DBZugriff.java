package paket_713_ArtikelMitRelDB2;

import java.sql.*;

public class DBZugriff {
	private Artikel meinArtikel;
	private DBVerbindung meineDBVerbindung;
	
	public DBZugriff (Artikel pArtikel)
	{
		meinArtikel = pArtikel;
		meineDBVerbindung = new DBVerbindung();
	}
	public boolean pruefeVerbindung ()
	{
		boolean mOK = false;
		if (meineDBVerbindung.oeffneDB() == false)
		{
			mOK = false;
		}
		else
		{
			mOK = true;
		}
		return mOK;
	}
	public int erfasseArtikel (String pArtikelnr, String pBezeichnung, double pEkpreis, int pBestand)
	{
		int mOK = 0;
		String mSql;
		mSql = "INSERT INTO Artikel (Artikelnr, Bezeichnung, ekpreis, bestand)";
		mSql = mSql + "VALUES ('" + pArtikelnr + "', '" + pBezeichnung + "', " + pEkpreis + ", " + pBestand + ")";
		
		if(meineDBVerbindung.oeffneDB() == false)
		{
			mOK = 2;
		}
		else
		{
			if(meineDBVerbindung.aendern(mSql) == false)
			{
				mOK = 1;
			}
			else
			{
				if(meineDBVerbindung.schliesseDB() == false)
				{
					mOK = 2;
				}
			}
		}
		return mOK;
	}
	public int sucheArtikel(String pArtikelnummer)
	{ 
	   int mOK=0;
	   artikelleeren();
	   ResultSet ergebnismenge=null;
	   String mSql ;
	   if(meineDBVerbindung.oeffneDB()==false)
	   {
	     mOK=2; 
	   }
	   else
	   {
	     mSql  = "SELECT artikelnr, Bezeichnung, Ekpreis,Bestand FROM Artikel ";
	     mSql  = mSql  + "WHERE artikelnr = '" + pArtikelnummer + "';";
	     ergebnismenge = meineDBVerbindung.lesen(mSql);
	     try
	     {
	       if (ergebnismenge.first()==false)
	       {
	         mOK=1;
	       }
	       else
	       {
	         mOK=0;
	         ergebnismenge.first();
	         meinArtikel.setArtikelnr(ergebnismenge.getString("Artikelnr")) ;
	         meinArtikel.setBezeichnung(ergebnismenge.getString("Bezeichnung")) ;
	         meinArtikel.setPreis(ergebnismenge.getDouble("Ekpreis")) ;
	         meinArtikel.setBestand(ergebnismenge.getInt("Bestand")) ;
	       }
	     }
	     catch (Exception err)
	     {
	       mOK=2;
	     }
	   }
	   if(meineDBVerbindung.schliesseDB()==false)
	   {
	     mOK=2;
	   }
	   return mOK;
	}
	void artikelleeren()
	{
	  meinArtikel.setArtikelnr("") ;
	  meinArtikel.setBezeichnung("") ;
	  meinArtikel.setPreis(0) ;
	  meinArtikel.setBestand(0) ;	
	}
	public int aendereArtikel(String pArtikelnr, String pBezeichnung, 
		       double pEkpreis,int pBestand)
		{
		  int mOK = 0;
		  String mSql ;                                          	
		  mSql  = "UPDATE Artikel SET Bezeichnung = '" + pBezeichnung + "',";
		  mSql  = mSql  + "Ekpreis = '"+ pEkpreis +"',bestand = '" + pBestand +"' WHERE Artikelnr = '" + pArtikelnr + "';";
		  if(meineDBVerbindung.oeffneDB()==false)
		  {
		    mOK=2;
		  }
		  else
		  {
		    if(meineDBVerbindung.aendern(mSql)==false) 
		    {
		      mOK=1;
		    }
		    else
		    {
		      if(meineDBVerbindung.schliesseDB()==false)
		      {
		        mOK=2;
		      }
		    }
		  }
		  return mOK;		                     
		}
}
