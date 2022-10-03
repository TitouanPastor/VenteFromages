package IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modèlePourEtudiants.Article;
import modèlePourEtudiants.Articles;
import modèlePourEtudiants.Fromage;
import modèlePourEtudiants.Panier;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;

public class UnFromage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Article a;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private JLabel lblQuantité;
	private JLabel lblPrix;
	private JComboBox comboBox;
	private JSpinner spinnerQuantité;
	private JButton okButton;
	private JButton cancelButton;
	private String prixTotal;

	/**
	 * Create the dialog.
	 * 
	 * @param fromage
	 * @param accueil
	 */
	public UnFromage(Fromage fromage, Panier panier, Articles mesArticles, Accueil accueil) {
		getContentPane().setBackground(new Color(255, 228, 181));

		setBounds(100, 100, 630, 311);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 228, 181));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel(fromage.getDésignation());
		lblNewLabel.setBackground(new Color(255, 228, 181));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 25));
		contentPanel.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 181));
		contentPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		textPane.setFont(new Font("Roboto", Font.PLAIN, 16));
		textPane.setText(fromage.getDescription());

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 181));
		contentPanel.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

		comboBox = new JComboBox(fromage.getArticles().toArray());
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Roboto", Font.PLAIN, 15));
		panel1.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 228, 181));
		panel1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JLabel lblNewLabel_2 = new JLabel("Quantité : ");
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_2);

		spinnerQuantité = new JSpinner();
		spinnerQuantité.setModel(new SpinnerNumberModel(1, 0, 55, 1));
		spinnerQuantité.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel_2.add(spinnerQuantité);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 228, 181));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		lblPrix = new JLabel("Prix : 4\u20AC");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 18));
		buttonPane.add(lblPrix);

		okButton = new JButton("Ajouter au panier");

		okButton.setForeground(new Color(0, 153, 0));
		okButton.setBackground(new Color(255, 255, 255));
		okButton.setFont(new Font("Roboto", Font.BOLD, 18));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Annuler");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(new Color(255, 255, 255));
		cancelButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		lblQuantité = new JLabel("/50");
		lblQuantité.setFont(new Font("Roboto", Font.ITALIC, 14));
		panel_2.add(lblQuantité);

		// Initialisation
		a = (Article) comboBox.getSelectedItem();
		lblPrix.setText("prix :" + 0 + "€");
		okButton.setEnabled(false);
		lblQuantité.setText(" /" + a.getQuantitéEnStock());
		spinnerQuantité.setModel(new SpinnerNumberModel(0, 0, a.getQuantitéEnStock(), 1));

		// appel des lecteurs
		lecteurComboBox(mesArticles);
		lecteurAnnuler();
		lecteurAjouterAuPanier(panier, mesArticles, accueil);
		lecteurSpinnerQuantité((Article) comboBox.getSelectedItem());

	}

	private void lecteurSpinnerQuantité(Article article) {
		spinnerQuantité.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int quantitéSpinner = (int) spinnerQuantité.getValue();
				prixTotal = df.format((float) (int) quantitéSpinner * article.getPrixTTC());
				lblPrix.setText("prix :" + prixTotal + "€");
				if (quantitéSpinner == 0) {
					okButton.setEnabled(false);
				} else {
					okButton.setEnabled(true);
				}
			}
		});
	}

	private void lecteurAjouterAuPanier(Panier panier, Articles mesArticles, Accueil accueil) {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantitéSpinner = (int) spinnerQuantité.getValue();
				if (quantitéSpinner != 0) {
					panier.addArticleToPanier(mesArticles, a, quantitéSpinner);
					System.out.println(
							"Ajout de l'article : " + a + " au panier. \nQuantité : " + quantitéSpinner + "\n");
					a = (Article) comboBox.getSelectedItem();
					spinnerQuantité.setModel(new SpinnerNumberModel(0, 0, a.getQuantitéEnStock(), 1));
					lblQuantité.setText(" /" + a.getQuantitéEnStock());
					lblPrix.setText("prix :" + a.getPrixTTC() + "€");
					accueil.setPrixPanierAccueil((float) panier.getPrixSousTotal());
					quantitéSpinner = (int) spinnerQuantité.getValue();
					if (quantitéSpinner == 0) {
						okButton.setEnabled(false);
					}
				}
			}
		});
	}

	private void lecteurAnnuler() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void lecteurComboBox(Articles mesArticles) {
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				a = (Article) comboBox.getSelectedItem();
				spinnerQuantité.setModel(new SpinnerNumberModel(0, 0, a.getQuantitéEnStock(), 1));
				lblQuantité.setText(" /" + a.getQuantitéEnStock());
				okButton.setEnabled(false);
			}
		});
	}

}
