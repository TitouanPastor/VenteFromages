package IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modèlePourEtudiants.Articles;
import modèlePourEtudiants.Client;
import modèlePourEtudiants.Panier;
import modèlePourEtudiants.Récapitulatif;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class PanierDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField TFNom;
	private JTextField TFPrenom;
	private JTextField TFNum;
	private JTextField TFMail;
	private JTextField TFAdresse1;
	private JTextField TFAdresse2;
	private JTextField TFComplement;
	private JTextField TFCp;
	private JTextField TFVille;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Client client;
	private JTextArea textAreaRecap = new JTextArea();
	private JLabel verifNotOk;
	private JButton boutonModifierPanier;
	private JButton boutonViderPanier;
	private JRadioButton radioLivraisonExpress;
	private JRadioButton radioLivraisonDomicile;
	private JRadioButton radioLivraisonRelais;
	private JButton btnValider;
	private JButton btnRetour;
	private JButton cancelButton;
	private JTabbedPane tabbedPane;

	/**
	 * Create the dialog.
	 * @param accueil 
	 */
	public PanierDialog(Panier panierclient, Articles mesArticles, Accueil accueil) {
		setBounds(100, 100, 777, 560);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 228, 181));
		tabbedPane.setFont(new Font("Roboto", Font.BOLD, 15));
		tabbedPane.setEnabled(false);
		contentPanel.add(tabbedPane);

		JPanel ongletPanier = new JPanel();
		ongletPanier.setBackground(new Color(255, 228, 181));
		tabbedPane.addTab("Panier", new ImageIcon(PanierDialog.class.getResource("/IHM/outil-commercial-de-panier-dachat.png")), ongletPanier, null);
		ongletPanier.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		ongletPanier.add(scrollPane_1, BorderLayout.CENTER);

		JTextArea textAreaPanier = new JTextArea(panierclient.toString());
		textAreaPanier.setLineWrap(true);
		textAreaPanier.setBackground(new Color(255, 228, 181));
		textAreaPanier.setFont(new Font("Roboto", Font.PLAIN, 14));
		textAreaPanier.setEditable(false);
		scrollPane_1.setViewportView(textAreaPanier);

		JPanel modifierPanier = new JPanel();
		ongletPanier.add(modifierPanier, BorderLayout.SOUTH);

		boutonModifierPanier = new JButton("Modifier mon panier");
		boutonModifierPanier.setFont(new Font("Roboto", Font.BOLD, 16));
		modifierPanier.add(boutonModifierPanier);

		boutonViderPanier = new JButton("Vider mon panier");
		boutonViderPanier.setForeground(new Color(153, 0, 0));
		boutonViderPanier.setFont(new Font("Roboto", Font.BOLD, 16));
		modifierPanier.add(boutonViderPanier);

		JPanel ongletInfoClient = new JPanel();
		ongletInfoClient.setBackground(new Color(255, 228, 181));
		tabbedPane.addTab("Information", new ImageIcon(PanierDialog.class.getResource("/IHM/information.png")), ongletInfoClient, null);
		ongletInfoClient.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel PnomPrenom = new JPanel();
		PnomPrenom.setBackground(new Color(255, 228, 181));
		ongletInfoClient.add(PnomPrenom);
		PnomPrenom.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel Pnom = new JPanel();
		Pnom.setBackground(new Color(255, 228, 181));
		PnomPrenom.add(Pnom);
		Pnom.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelNom = new JLabel("Nom :");
		Pnom.add(LabelNom);

		TFNom = new JTextField();
		TFNom.setText("");
		TFNom.setColumns(10);
		Pnom.add(TFNom);

		JPanel Pprenom = new JPanel();
		Pprenom.setBackground(new Color(255, 228, 181));
		PnomPrenom.add(Pprenom);
		Pprenom.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelPrenom = new JLabel("Pr\u00E9nom :");
		Pprenom.add(LabelPrenom);

		TFPrenom = new JTextField();
		TFPrenom.setText("");
		TFPrenom.setColumns(10);
		Pprenom.add(TFPrenom);

		JPanel Pnumero = new JPanel();
		Pnumero.setBackground(new Color(255, 228, 181));
		ongletInfoClient.add(Pnumero);
		Pnumero.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelNumero = new JLabel("Num\u00E9ro de t\u00E9l\u00E9phone :");
		LabelNumero.setBackground(new Color(255, 228, 181));
		Pnumero.add(LabelNumero);

		TFNum = new JTextField();
		TFNum.setText("");
		TFNum.setColumns(10);
		Pnumero.add(TFNum);

		JPanel Pmail = new JPanel();
		Pmail.setBackground(new Color(255, 228, 181));
		ongletInfoClient.add(Pmail);
		Pmail.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelEmail = new JLabel("Email :");
		Pmail.add(LabelEmail);

		TFMail = new JTextField();
		TFMail.setText("");
		TFMail.setColumns(10);
		Pmail.add(TFMail);

		JPanel Padresse1 = new JPanel();
		Padresse1.setBackground(new Color(255, 228, 181));
		ongletInfoClient.add(Padresse1);
		Padresse1.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelAdresse1 = new JLabel("Adresse 1 :");
		Padresse1.add(LabelAdresse1);

		TFAdresse1 = new JTextField();
		TFAdresse1.setText("");
		TFAdresse1.setColumns(10);
		Padresse1.add(TFAdresse1);

		JPanel Padresse2 = new JPanel();
		Padresse2.setBackground(new Color(255, 228, 181));
		ongletInfoClient.add(Padresse2);
		Padresse2.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelAdresse2 = new JLabel("Adresse 2 :");
		Padresse2.add(LabelAdresse2);

		TFAdresse2 = new JTextField();
		TFAdresse2.setText("");
		TFAdresse2.setColumns(10);
		Padresse2.add(TFAdresse2);

		JPanel PcomplemtAdresse = new JPanel();
		PcomplemtAdresse.setBackground(new Color(255, 228, 181));
		ongletInfoClient.add(PcomplemtAdresse);
		PcomplemtAdresse.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelComplement = new JLabel("Compl\u00E9ment d'adresse :");
		PcomplemtAdresse.add(LabelComplement);

		TFComplement = new JTextField();
		TFComplement.setText("");
		TFComplement.setColumns(10);
		PcomplemtAdresse.add(TFComplement);

		JPanel PcpVille = new JPanel();
		ongletInfoClient.add(PcpVille);
		PcpVille.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel Pcp = new JPanel();
		Pcp.setBackground(new Color(255, 228, 181));
		PcpVille.add(Pcp);
		Pcp.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelCp = new JLabel("Code postal :");
		Pcp.add(LabelCp);

		TFCp = new JTextField();
		TFCp.setText("");
		TFCp.setColumns(10);
		Pcp.add(TFCp);

		JPanel Pville = new JPanel();
		Pville.setBackground(new Color(255, 228, 181));
		PcpVille.add(Pville);
		Pville.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel LabelVille = new JLabel("Ville :");
		Pville.add(LabelVille);

		TFVille = new JTextField();
		TFVille.setText("");
		TFVille.setColumns(10);
		Pville.add(TFVille);

		JPanel ongletLivraison = new JPanel();
		tabbedPane.addTab("Livraison", new ImageIcon(PanierDialog.class.getResource("/IHM/livraison-rapide.png")), ongletLivraison, null);
		ongletLivraison.setLayout(new GridLayout(3, 0, 0, 0));

		radioLivraisonExpress = new JRadioButton("Livraison express (8.89 €)");
		radioLivraisonExpress.setBackground(new Color(255, 228, 181));
		buttonGroup.add(radioLivraisonExpress);
		radioLivraisonExpress.setHorizontalAlignment(SwingConstants.CENTER);
		ongletLivraison.add(radioLivraisonExpress);

		radioLivraisonDomicile = new JRadioButton("Livraison à domicile (4.75 €)");
		radioLivraisonDomicile.setBackground(new Color(255, 228, 181));
		buttonGroup.add(radioLivraisonDomicile);
		radioLivraisonDomicile.setHorizontalAlignment(SwingConstants.CENTER);
		ongletLivraison.add(radioLivraisonDomicile);

		radioLivraisonRelais = new JRadioButton("Livraison relais (5.99 €)");
		radioLivraisonRelais.setBackground(new Color(255, 228, 181));
		buttonGroup.add(radioLivraisonRelais);
		radioLivraisonRelais.setHorizontalAlignment(SwingConstants.CENTER);
		ongletLivraison.add(radioLivraisonRelais);

		JPanel ongletRecap = new JPanel();
		ongletRecap.setBackground(new Color(255, 228, 181));
		tabbedPane.addTab("R\u00E9capitulatif", new ImageIcon(PanierDialog.class.getResource("/IHM/recapitulatif-dachat.png")), ongletRecap, null);
		ongletRecap.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		ongletRecap.add(scrollPane);
		textAreaRecap.setBackground(new Color(255, 228, 181));
		textAreaRecap.setFont(new Font("Roboto", Font.PLAIN, 15));
		scrollPane.setViewportView(textAreaRecap);
		textAreaRecap.setLineWrap(true);
		textAreaRecap.setEditable(false);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		verifNotOk = new JLabel("Il manque des informations !");
		verifNotOk.setForeground(Color.RED);
		verifNotOk.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 16));
		buttonPane.add(verifNotOk);
		verifNotOk.setVisible(false);

		btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Roboto", Font.BOLD, 16));
		buttonPane.add(btnValider);
		btnValider.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Roboto", Font.PLAIN, 16));
		buttonPane.add(btnRetour);

		cancelButton = new JButton("Quitter");
		cancelButton.setFont(new Font("Roboto", Font.PLAIN, 16));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		//Initialisation
		btnRetour.setEnabled(false);
		if (panierclient.getPrixSousTotal() == 0) {
			btnValider.setEnabled(false);
		}
		
		//Appel des lecteurs
		lecteurSuivant(panierclient);
		lecteurRetour();
		lecteurQuitter();
		lecteurRadioExpress(panierclient);
		lecteurRadioDomicile(panierclient);
		lecteurRadioRelais(panierclient);
		lecteurModifierPanier();
		lecteurViderPanier(panierclient, mesArticles, accueil);
	}

	//Clique sur vider le panier
	private void lecteurViderPanier( Panier panierclient, Articles mesArticles, Accueil accueil) {
		boutonViderPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panierclient.réinitialisationPanier(mesArticles);
				System.out.println("Panier réinitialisé !");
				accueil.setPrixPanierAccueil(0);
				dispose();
			}
		});
	}

	//Clique sur modification du panier
	private void lecteurModifierPanier() {
		boutonModifierPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}

	//Clique sur les différents radios
	private void lecteurRadioRelais(Panier panierclient) {
		radioLivraisonRelais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panierclient.setPrixLivraison(5.99F);
				System.out.println(panierclient.getPrixLivraison());
			}
		});
	}

	private void lecteurRadioDomicile(Panier panierclient) {
		radioLivraisonDomicile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panierclient.setPrixLivraison(4.75F);
				System.out.println(panierclient.getPrixLivraison());
			}
		});
	}

	private void lecteurRadioExpress(Panier panierclient) {
		radioLivraisonExpress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panierclient.setPrixLivraison(8.89F);
				System.out.println(panierclient.getPrixLivraison());
			}
		});
	}

	//Clique sur quitter
	private void lecteurQuitter() {
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}

	//CLique sur retour
	private void lecteurRetour() {
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.getSelectedIndex();
				if (index > 0) {
					tabbedPane.setSelectedIndex(index - 1);
					if (index == 3) {
						btnValider.setEnabled(true);
					}
					//On désactive le bouton retour si on est sur le premier tab
					if (index == 1) {
						btnRetour.setEnabled(false);
					}
				}
			}
		});
	}

	//Clique sur suivant
	private void lecteurSuivant(Panier panierclient) {
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.getSelectedIndex();
				if (index < 3) {
								
					// Données client
					if (index == 1) {
						client = new Client(TFNom.getText(), TFPrenom.getText(), TFNum.getText(),
								TFMail.getText(), TFAdresse1.getText(), TFAdresse2.getText(), TFComplement.getText(),
								TFCp.getText(), TFVille.getText());
						System.out.println(client.verificationChamps());
						
						//Vérification des données remplies
						if (client.verificationChamps()) {
							verifNotOk.setVisible(false);
							tabbedPane.setSelectedIndex(index + 1);
						} else {
							System.out.println(client);
							verifNotOk.setVisible(true);
						}
					} else {
						tabbedPane.setSelectedIndex(index + 1);
					}

					//Du tab 3 au tab 4 (récapitulatif)
					if (index == 2) {
						btnValider.setEnabled(false);
						//Création du récapitulatif pour le toString
						textAreaRecap.setText(new Récapitulatif(client, panierclient).toString());
					} else if (index == 0) {
						btnRetour.setEnabled(true);
					}
				} else {
					tabbedPane.setSelectedIndex(index - 1);
					btnValider.setEnabled(true);
				}
			}
		});
	}

}
