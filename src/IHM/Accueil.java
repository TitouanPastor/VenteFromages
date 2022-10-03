package IHM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import modèlePourEtudiants.Article;
import modèlePourEtudiants.Articles;
import modèlePourEtudiants.Fromage;
import modèlePourEtudiants.GenerationFromages;
import modèlePourEtudiants.Panier;
import modèlePourEtudiants.TypeLait;

import java.awt.GridLayout;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class Accueil {

	private JFrame frame;
	private Panier panierclient;
	private Fromage fromage;
	Articles mesArticles = GenerationFromages.générationBaseFromages();
	private JLabel prix_panier;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private JPanel panier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil window = new Accueil();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		panierclient = new Panier();

		frame = new JFrame();
		frame.setBounds(100, 100, 500, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));

		JPanel nom_recherche = new JPanel();
		nom_recherche.setBackground(Color.WHITE);
		header.add(nom_recherche, BorderLayout.CENTER);
		nom_recherche.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel nom_fromagerie = new JLabel("Joyeux Fromages");
		nom_fromagerie.setBackground(Color.WHITE);
		nom_fromagerie.setIcon(new ImageIcon(Accueil.class.getResource("/IHM/logo-fromage.png")));
		nom_fromagerie.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 27));
		nom_recherche.add(nom_fromagerie);

		panier = new JPanel();
		panier.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(153, 153, 153)));
		panier.setBackground(Color.WHITE);
		header.add(panier, BorderLayout.EAST);
		panier.setLayout(new BoxLayout(panier, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Accueil.class.getResource("/IHM/kisspng-shopping-cart-logo (1).png")));
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panier.add(lblNewLabel);

		prix_panier = new JLabel("0 \u20AC");
		prix_panier.setForeground(new Color(0, 102, 204));
		prix_panier.setFont(new Font("Roboto", Font.PLAIN, 16));
		prix_panier.setHorizontalAlignment(SwingConstants.CENTER);
		panier.add(prix_panier);

		JPanel main = new JPanel();
		main.setBackground(Color.WHITE);
		frame.getContentPane().add(main, BorderLayout.CENTER);
		main.setLayout(new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 228, 181));
		tabbedPane.setFont(new Font("Roboto", Font.BOLD, 16));
		main.add(tabbedPane);

		JPanel filtreTous = new JPanel();
		filtreTous.setBackground(Color.WHITE);
		tabbedPane.addTab("Tous", null, filtreTous, null);
		filtreTous.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		filtreTous.add(scrollPane);

		// List Tous
		JList listTous = new JList(Articles.getListFromageAvecDesignation(mesArticles.getLesFromages()));
		listTous.setBackground(Color.WHITE);
		listTous.setFont(new Font("Roboto", Font.PLAIN, 16));
		scrollPane.setViewportView(listTous);

		JPanel filtreChèvre = new JPanel();
		tabbedPane.addTab("Ch\u00E8vre", new ImageIcon(Accueil.class.getResource("/IHM/chevre.png")), filtreChèvre,
				null);
		filtreChèvre.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		filtreChèvre.add(scrollPane_1);

		// List Chevre
		JList listChevre = new JList(
				Articles.getListFromageAvecDesignation(mesArticles.fromagesAuLaitDe(TypeLait.CHEVRE)));
		listChevre.setFont(new Font("Roboto", Font.PLAIN, 16));
		scrollPane_1.setViewportView(listChevre);

		JPanel filtreBrebis = new JPanel();
		tabbedPane.addTab("Brebis", new ImageIcon(Accueil.class.getResource("/IHM/mouton.png")), filtreBrebis, null);
		filtreBrebis.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		filtreBrebis.add(scrollPane_2);

		//List Brebis
		JList listBrebis = new JList(
				Articles.getListFromageAvecDesignation(mesArticles.fromagesAuLaitDe(TypeLait.BREBIS)));
		listBrebis.setFont(new Font("Roboto", Font.PLAIN, 16));
		scrollPane_2.setViewportView(listBrebis);

		JPanel filtreVache = new JPanel();
		tabbedPane.addTab("Vache", new ImageIcon(Accueil.class.getResource("/IHM/vache-sacree.png")), filtreVache,
				null);
		filtreVache.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane_2_1 = new JScrollPane();
		filtreVache.add(scrollPane_2_1);

		// List Vache
		JList listVache = new JList(
				Articles.getListFromageAvecDesignation(mesArticles.fromagesAuLaitDe(TypeLait.VACHE)));
		listVache.setFont(new Font("Roboto", Font.PLAIN, 16));
		scrollPane_2_1.setViewportView(listVache);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panier.add(panel);

		// Appel des lecteurs
		lecteurLists(listTous, this);
		lecteurLists(listChevre, this);
		lecteurLists(listBrebis, this);
		lecteurLists(listVache, this);
		lecteurPanierDialog(this);

	}

	//Ouvre les détails du fromage
	private void lecteurLists(JList list, Accueil accueil) {
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fromage = mesArticles.getFromageAvecDesignation((String) list.getSelectedValue());
				UnFromage f = new UnFromage(fromage, panierclient, mesArticles, accueil);
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}

	//Ouvre le panier
	private void lecteurPanierDialog(Accueil accueil) {
		panier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PanierDialog panierDialog = new PanierDialog(panierclient, mesArticles, accueil);
				panierDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				panierDialog.setVisible(true);
			}
		});
	}

	// Permet de rafraichir le prix sur l'accueil depuis d'autres classes
	public void setPrixPanierAccueil(float f) {
		prix_panier.setText(df.format(f) + " €");
	}
}
