package javaKraft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class kraftDialog extends JDialog {
	
	private boolean sendData;
	private kraftDialogInfo kraftInfo = new kraftDialogInfo();
	
	private JComboBox comboBoxMetier , comboBoxRace , comboBoxSexe;
	private JLabel labelNom , labelMetier , labelRace , labelSexe;
	private JTextField nom;

	public kraftDialog( JFrame parent , String title , boolean modal )
	{
		super( parent , title , modal );
		this.setSize( 550 , 270 );
		this.setLocationRelativeTo( null );
		this.setResizable( false );
		this.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		this.initComponent();
	}

	public kraftDialogInfo showKraftDialog()
	{
		sendData = false;
		setVisible( true ); 
		
		return kraftInfo;      
	}

	private void initComponent()
	{
		 // panelNom : JPanel du nom
		JPanel panelNom = new JPanel();
		panelNom.setBackground( Color.white );
		panelNom.setPreferredSize( new Dimension( 220 , 60 ) );
		nom = new JTextField();
		nom.setPreferredSize( new Dimension( 100, 25 ) );
		panelNom.setBorder( BorderFactory.createTitledBorder( "Nom du personnage" ) );
		labelNom = new JLabel( "Saisir un nom :" );
		panelNom.add( labelNom );
		panelNom.add( nom );
		
		 // panelRace : JPanel de la race
		JPanel panelRace = new JPanel();
		panelRace.setBackground( Color.white );
		panelRace.setPreferredSize( new Dimension( 220 , 60 ) );
		panelRace.setBorder( BorderFactory.createTitledBorder( "Race du personnage" ) );
		comboBoxRace = new JComboBox();
		comboBoxRace.addItem( "Elfe" );
		comboBoxRace.addItem( "Humain" );
		comboBoxRace.addItem( "Orc" );
		labelRace = new JLabel( "Race : " );
		panelRace.add( labelRace );
		panelRace.add( comboBoxRace );

		 // panelSexe : JPanel du sexe
		JPanel panelSexe = new JPanel();
		panelSexe.setBackground( Color.white );
		panelSexe.setPreferredSize( new Dimension( 220 , 60 ) );
		panelSexe.setBorder( BorderFactory.createTitledBorder( "Sexe du personnage" ) );
		comboBoxSexe = new JComboBox();
		comboBoxSexe.addItem( "Masculin" );
		comboBoxSexe.addItem( "Feminin" );
		labelSexe = new JLabel( "Sexe : " );
		panelSexe.add( labelSexe );
		panelSexe.add( comboBoxSexe );
		
		 // panelSexe : JPanel du sexe
		JPanel panelMetier = new JPanel();
		panelMetier.setBackground( Color.white );
		panelMetier.setPreferredSize( new Dimension( 220 , 60 ) );
		panelMetier.setBorder( BorderFactory.createTitledBorder( "Metier du personnage" ) );
		comboBoxMetier = new JComboBox();
		comboBoxMetier.addItem( "guerrier" );
		comboBoxMetier.addItem( "mage" );
		comboBoxMetier.addItem( "voleur" );
		labelMetier = new JLabel( "Metier : " );
		panelMetier.add( labelMetier );
		panelMetier.add( comboBoxMetier );
		
		 // content : JPanel contenant les JPanel des specs
		JPanel content = new JPanel();
	    content.setBackground( Color.white );
	    content.add( panelNom );
	    content.add( panelRace );
	    content.add( panelSexe );
	    content.add( panelMetier );

	     // control : JPanel des boutons
		JPanel control = new JPanel();
		
		 // le bouton creer
		JButton boutonCreate = new JButton( "Creer" );
		boutonCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				 // validation uniquement si un nom a ete saisi
				if ( !nom.getText().equals( "" ) )
				{
					kraftInfo = new kraftDialogInfo( (String)comboBoxMetier.getSelectedItem() ,
									nom.getText() ,
									(String)comboBoxRace.getSelectedItem() ,
									(String)comboBoxSexe.getSelectedItem() );
					setVisible( false );
				}
			}     
		});

		 // le bouton Quitter
		JButton boutonQuit = new JButton( "Quitter" );
		boutonQuit.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				setVisible( false );
			}      
		});

		 // ajout des boutons au JPanel control
		control.add( boutonCreate );
		control.add( boutonQuit );

		//this.getContentPane().add( panelIcon , BorderLayout.WEST );
		getContentPane().add( content , BorderLayout.CENTER );
		getContentPane().add( control , BorderLayout.SOUTH );
	}  
}