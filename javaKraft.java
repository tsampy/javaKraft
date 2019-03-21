package javaKraft;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

public class javaKraft extends JDialog {
	
 // variables privees -----------------------------------------------------------------------------
	private static gameBoard plateauDeJeu;
	
	private JButton boutonNewPerso = new JButton("Creer un nouveau personnage...");
	private JButton boutonRun      = new JButton("Lancer le jeu...");
	
	private static ArrayList<personnage> personnages;

 // constructeur ----------------------------------------------------------------------------------
	public javaKraft()
	{
		 // initialisation de la JFrame
		setModal( true );
		setTitle( "JavaCraft version 0.1" );
		setSize( 500 , 100 );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		setLocationRelativeTo( null );      
		getContentPane().setLayout( new FlowLayout() );
		
		 // ajout des bouton au panel
		getContentPane().add( boutonNewPerso );
		getContentPane().add( boutonRun );
		
		 // gestion du bouton Run
		boutonRun.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				 // fermeture de la fenetre modale, et lancement du jeu
				dispose();
			}
		});
		
		 // gestion du bouton Ajouter
		boutonNewPerso.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				kraftDialog messageBoxCreation = new kraftDialog( null , "Creation d'un personnage" , true );
				kraftDialogInfo kraftInfo = messageBoxCreation.showKraftDialog(); 
				JOptionPane jop = new JOptionPane();
				
				 // si un nom a ete saisi
				if (( kraftInfo.getNom() != null ) && ( !kraftInfo.getNom().equals( "" )))
				{
					int typePerso;
					
					switch ( kraftInfo.getRace() )
					{
					case "Elfe":
						personnages.add( new elfe( kraftInfo.getNom() ,
							( kraftInfo.getSexe() == "Masculin" ? true : false ),
							kraftInfo.getMetier() ) );
						break;
					case "Humain":personnages.add( new humain( kraftInfo.getNom() ,
							( kraftInfo.getSexe() == "Masculin" ? true : false ),
							kraftInfo.getMetier() ) );
						break;
					case "Orc":personnages.add( new orc( kraftInfo.getNom() ,
							( kraftInfo.getSexe() == "Masculin" ? true : false ),
							kraftInfo.getMetier() ) );
						break;
					default:personnages.add( new humain( kraftInfo.getNom() ,
							( kraftInfo.getSexe() == "Masculin" ? true : false ),
							kraftInfo.getMetier() ) );
						break;
					}
					
					jop.showMessageDialog( null , kraftInfo.toString() ,
							"personnage cree" , JOptionPane.INFORMATION_MESSAGE );
				}
				
				 // destruction de la messageBox
				messageBoxCreation.dispose();
			}         
		});      
		
		setVisible( true );      
	}
   
 // methodes --------------------------------------------------------------------------------------
	public static void main(String[] main)
	{
		personnages = new ArrayList<>();
		
		 // creation des personnages
		javaKraft messageBoxCreationPersos = new javaKraft();
		
		 // preparation de la boutique, en fonction du nombre de personnages
		gameBoard.prepareBoutique( personnages.size() );
		
		 // creation du plateau de jeu
		plateauDeJeu = new gameBoard();
		 // execution du jeu
		 // si au moins 2 personnages ont ete crees, le jeu les utilise
		if ( personnages.size() >= 2 )
			plateauDeJeu.run( personnages );
		 // sinon, lancement d'une partie generique
		else
			plateauDeJeu.run();
		
		 // si quelqu'un gagne
		if ( plateauDeJeu.someoneWins() )
			System.out.println( "Le personnage vainqueur de cette boucherie est : "
								+ plateauDeJeu.getWinnerName()
								+ ", " + plateauDeJeu.getWinner().craftToString() );
		else
			System.out.println( "Les survivants sont : " + plateauDeJeu.getSurvivorsNames() + "\n" );
		
		System.out.println( "Au plaisir de vous revoir sur javaKraft :)" );
	}   
}