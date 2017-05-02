package paket_713_ArtikelMitRelDB2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Hauptfenster extends JFrame {
	private JLabel lblArtikelnummer;
	private JTextField tfArtikelnummer;
	private JLabel lblBezeichnung;
	private JTextField tfBezeichnung;
	private JLabel lblPreis;
	private JTextField tfPreis;
	private JLabel lblBestand;
	private JLabel lblBestandsbewegung;
	private JTextField tfBestandsbewegung;
	private JButton btnErfassen;
	private JButton btnndern;
	private JButton btnSuchen;
	private JButton btnLeeren;
	private JButton btnBeenden;
	
	private Artikel meinArtikel;
	private DBZugriff meinZugriff;
	private DBVerbindung meineVerbindung;
	
	private JLabel lblStatus;
	private JLabel lblIdle;
	private JButton btnTest;
	
	public void leeren ()
	{
		
		/*
		if (tfArtikelnummer.getText().isEmpty() && tfBezeichnung.getText().isEmpty() && tfPreis.getText().isEmpty() && tfBestandsbewegung.getText().isEmpty())
		{
			Object[] options = {"Tja,","welcher","Button","bewirkt","nichts","und","welcher","formatiert","die","Festplatte","?"};
			int n = JOptionPane.showOptionDialog(
                    null, "Die Felder sind doch schon leer!",
                    "Strafe muss sein",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,options,options[0]);
			if (n == JOptionPane.YES_OPTION) {
				// JOptionPane.showMessageDialog(null, "HARDDRIVE FORMAT INITIALISED","FATAL ERROR",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "War 'n Witz",":-)",JOptionPane.INFORMATION_MESSAGE);
			} else if (n == JOptionPane.NO_OPTION) {
				// JOptionPane.showMessageDialog(null, "HARDDRIVE FORMAT INITIALISED","FATAL ERROR",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "War 'n Witz",":-)",JOptionPane.INFORMATION_MESSAGE);
			} else {
				// JOptionPane.showMessageDialog(null, "HARDDRIVE FORMAT INITIALISED","FATAL ERROR",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "War 'n Witz",":-)",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		*/
		tfArtikelnummer.setText("");
		tfBezeichnung.setText("");
		tfPreis.setText("");
		tfBestandsbewegung.setText("");
		lblBestand.setText("Bestand: -");
	}
	public void anzeigen ()
	{
		tfArtikelnummer.setText(meinArtikel.getArtikelnr());
		tfBezeichnung.setText(meinArtikel.getBezeichnung());
		tfPreis.setText(String.valueOf(meinArtikel.getPreis()));
		tfBestandsbewegung.setText("");
		lblBestand.setText("Bestand: "+String.valueOf(meinArtikel.getBestand())+"");
		
	}
	
	public Hauptfenster() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("U:\\Gesicherte Dateien\\01WINF\\EBilder\\1489760120_Task_Manager.png"));
		
		meinArtikel = new Artikel();
		meinZugriff = new DBZugriff(meinArtikel);
		
		setType(Type.NORMAL);
		setResizable(false);
		setTitle("Artikelverwaltung");
		this.setBounds(700, 300, 258, 317);
		getContentPane().setLayout(null);
		
		lblArtikelnummer = new JLabel("Artikelnummer:");
		lblArtikelnummer.setBounds(10, 11, 113, 14);
		getContentPane().add(lblArtikelnummer);
		
		tfArtikelnummer = new JTextField();
		tfArtikelnummer.setHorizontalAlignment(SwingConstants.CENTER);
		tfArtikelnummer.setBounds(133, 8, 111, 20);
		getContentPane().add(tfArtikelnummer);
		tfArtikelnummer.setColumns(10);
		
		lblBezeichnung = new JLabel("Bezeichnung:");
		lblBezeichnung.setBounds(10, 36, 113, 14);
		getContentPane().add(lblBezeichnung);
		
		tfBezeichnung = new JTextField();
		tfBezeichnung.setHorizontalAlignment(SwingConstants.CENTER);
		tfBezeichnung.setBounds(133, 33, 111, 20);
		getContentPane().add(tfBezeichnung);
		tfBezeichnung.setColumns(10);
		
		lblPreis = new JLabel("Preis:");
		lblPreis.setBounds(10, 61, 113, 14);
		getContentPane().add(lblPreis);
		
		tfPreis = new JTextField();
		tfPreis.setHorizontalAlignment(SwingConstants.CENTER);
		tfPreis.setBounds(133, 58, 111, 20);
		getContentPane().add(tfPreis);
		tfPreis.setColumns(10);
		
		lblBestand = new JLabel("Bestand: -");
		lblBestand.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBestand.setBounds(10, 101, 119, 20);
		getContentPane().add(lblBestand);
		
		lblBestandsbewegung = new JLabel("Bestandsbewegung:");
		lblBestandsbewegung.setBounds(10, 140, 128, 14);
		getContentPane().add(lblBestandsbewegung);
		
		tfBestandsbewegung = new JTextField();
		tfBestandsbewegung.setHorizontalAlignment(SwingConstants.CENTER);
		tfBestandsbewegung.setBounds(148, 137, 96, 20);
		getContentPane().add(tfBestandsbewegung);
		tfBestandsbewegung.setColumns(10);
		
		btnErfassen = new JButton("Erfassen");
		btnErfassen.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnErfassen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					meinArtikel.setArtikelnr(tfArtikelnummer.getText());
				    meinArtikel.setBezeichnung(tfBezeichnung.getText());
				    meinArtikel.setPreis(Double.parseDouble(tfPreis.getText()));
				    if(meinArtikel.setBestand(Integer.parseInt(tfBestandsbewegung.getText())) == false)
				    {
				      JOptionPane.showMessageDialog(null, "Kein negativer Bestand!");  
				      leeren();
				    }
				    else
				    {            
				      switch(meinZugriff.erfasseArtikel(meinArtikel.getArtikelnr(),
				             meinArtikel.getBezeichnung(),meinArtikel.getPreis(),
				             meinArtikel.getBestand()))
				      { 
				        case 0:
				          JOptionPane.showMessageDialog(null, "Daten wurden gespeichert!");
				          anzeigen();   
				          break;
				        case 1:    
          				  JOptionPane.showMessageDialog(null, "Falsche Artikelnummer!");
          				  leeren();    
          				  break;
        				case 2:   
          				  JOptionPane.showMessageDialog(null, "Fehler in DB-Verbindung");
                          leeren();    
                          break;
				      }
				    }      		
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Fehlende Erfassungsdaten!");
				    leeren();
				}
			}
		});
		btnErfassen.setBounds(10, 176, 110, 25);
		getContentPane().add(btnErfassen);
		
		btnndern = new JButton("\u00C4ndern");
		btnndern.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnndern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
			      {
			        meinArtikel.setArtikelnr(tfArtikelnummer.getText());
			        meinArtikel.setBezeichnung(tfBezeichnung.getText());
			        meinArtikel.setPreis(Double.parseDouble(tfPreis.getText()));
			        if(meinArtikel.aendern(Integer.parseInt(tfBestandsbewegung.getText()))==false)
			        {
			          JOptionPane.showMessageDialog(null, "Bestand reicht nicht!");
			          leeren();
			        }
			        else
			        {
			          switch(meinZugriff.aendereArtikel(meinArtikel.getArtikelnr(),
			                 meinArtikel.getBezeichnung(),meinArtikel.getPreis(),
			                 meinArtikel.getBestand()))
			          { 
			            case 0:
			              JOptionPane.showMessageDialog(null, "Daten wurden gespeichert!");
			              anzeigen();   
			              break;
			            case 1:    
			              JOptionPane.showMessageDialog(null, "Falsche Artikelnummer!");
			              leeren();    
			              break;
			            case 2:   
			                JOptionPane.showMessageDialog(null, "Fehler in DB-Verbindung");
			                leeren();    
			                 break;
			              }
			            }
			          }
			          catch(Exception ex)
			          {
			            JOptionPane.showMessageDialog(null, "Fehlende Erfassungsdaten!");
			            leeren(); 	
			          }
			}
		});
		btnndern.setBounds(134, 176, 110, 25);
		getContentPane().add(btnndern);
		
		btnSuchen = new JButton("Suchen");
		btnSuchen.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(meinZugriff.sucheArtikel(tfArtikelnummer.getText()))
				  { 
				    case 0:   
				      anzeigen(); 
				      JOptionPane.showMessageDialog(null, "Artikel gefunden!","OK",JOptionPane.INFORMATION_MESSAGE);
				      break;
				    case 1:    
				      JOptionPane.showMessageDialog(null, "Falsche Artikelnummer!");
				      leeren();    
				      break;
				    case 2:   
				      JOptionPane.showMessageDialog(null, "Fehler in DB-Verbindung");
				      leeren();    
				      break;
				  }     
			}
		});
		btnSuchen.setBounds(10, 207, 110, 25);
		getContentPane().add(btnSuchen);
		
		btnLeeren = new JButton("Leeren");
		btnLeeren.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLeeren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leeren();
			}
		});
		btnLeeren.setBounds(134, 207, 110, 25);
		getContentPane().add(btnLeeren);
		
		btnBeenden = new JButton("Beenden");
		btnBeenden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnBeenden.setBounds(10, 237, 234, 23);
		getContentPane().add(btnBeenden);
		
		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 265, 46, 14);
		getContentPane().add(lblStatus);
		
		lblIdle = new JLabel("unknown");
		lblIdle.setForeground(new Color(255, 153, 51));
		lblIdle.setBounds(55, 265, 106, 14);
		getContentPane().add(lblIdle);
		
		btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					if (meinZugriff.pruefeVerbindung() == true)
					{
						lblIdle.setText("connected");
						lblIdle.setForeground(new Color(46, 139, 87));
						JOptionPane.showMessageDialog(null,"Verbindung zur Datenbank besteht!", "OK",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						lblIdle.setText("not connected");
						lblIdle.setForeground(Color.RED);
						JOptionPane.showMessageDialog(null, "Verbindung mit der lokalen mySQL Datenbank konnte nicht hergestellt werden!\nBitte den mySQL Server überprüfen!","Fehler",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception fehler)
				{
					JOptionPane.showMessageDialog(null,"Fehler");
				}
			}
		});
		btnTest.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnTest.setBounds(148, 263, 96, 19);
		getContentPane().add(btnTest);
	}
}
