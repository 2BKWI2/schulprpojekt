package paket_713_ArtikelMitRelDB2;
public class Artikel
{
    private Stringss artikelnr;
    private String bezeichnung;
    private double preis;
    private int bestand;
    
    public void setArtikelnr(String pArtikelNr)
    {
        artikelnr=pArtikelNr;
    }
    public String getArtikelnr()
    {
        return artikelnr;
    }
    public String getBezeichnung ()
    {
    	return bezeichnung;
    }
    public double getPreis ()
    {
    	return preis;
    }
    public int getBestand ()
    {
    	return bestand;
    }
    public boolean setBestand (int pBestand)
    {
    	if (pBestand >= 0)
    	{
    		bestand = pBestand;
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public void setBezeichnung(String pBezeichnung)
    {
        bezeichnung=pBezeichnung;
    }
    public void setPreis(double pPreis)
    {
        preis=pPreis;
    }
    public boolean aendern(int pStueck)
    {
        if (bestand + pStueck >= 0)
            {
                bestand = bestand + pStueck;
                return true;
            }
        else
        {
        	return false;
        }
    }
}
