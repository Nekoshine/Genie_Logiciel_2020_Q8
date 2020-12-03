package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RoomManagement extends JPanel{

    /* Déclarations JPanel */

    private final JPanel listPanel;
    private final JPanel titlePanel;
    private final JPanel decoPanel;
    private final JPanel newButtonPanel;
    private final JPanel roomPanel;

    /* Déclarations Layouts */

    private final GridBagLayout listLayout;
    private final FlowLayout decoLayout;
    private final BorderLayout mainLayout;
    private final BorderLayout centerLayout;
    private final JScrollPane scrollPane;

    /* Déclaration boutons */

    private final JButton returnButton;
    private final JButton newButton;


    private final JLabel titre;
    private int compteur = 0;


    public RoomManagement(){

        /* Déclaration JPanel - JScrollPane */

        listPanel = new JPanel();
        roomPanel = new JPanel();
        titlePanel = new JPanel();
        decoPanel = new JPanel();
        newButtonPanel = new JPanel();
        scrollPane = new JScrollPane(roomPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        /* Déclaration Layouts */

        mainLayout = new BorderLayout(10,10);
        centerLayout = new BorderLayout(4,4);
        decoLayout = new FlowLayout(FlowLayout.LEFT);
        listLayout = new GridBagLayout();
        roomPanel.setLayout(listLayout);

        /* Contraintes GridBag */

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        /* Déclaration Boutons */

        returnButton = new JButton("Retour");
        returnButton.setBackground(ColorPerso.retour);
        returnButton.setForeground(Color.white);


        newButton = new JButton();
        newButton.setBackground(Color.GRAY);
        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        /* Ajout d'une nouvelle salle */

        newButton.setAction(new AbstractAction("Nouvelle Salle") {

            @Override
            public void actionPerformed(ActionEvent e) {

                compteur++; // Compteur du nb de salles

                /* Contraintes GridBag */

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1;
                gbc.gridy = compteur;

                /* Ajout Panel */

                listPanel.remove(newButtonPanel);
                JPanel panelSalle = new JPanel();

                /* Construction PanelSalle */

                GridLayout grille = new GridLayout(1,4,70,50);
                JLabel nomSalle = new JLabel("Salle " + compteur);
                nomSalle.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel nomJeu = new JLabel("Jeu");
                nomJeu.setHorizontalAlignment(SwingConstants.CENTER);

                JButton boutonJeu = new JButton("Choisir Jeu");
                boutonJeu.setBackground(ColorPerso.choixJeu);


                JButton boutonLancer = new JButton("Lancer");
                boutonLancer.setBackground(ColorPerso.lancement);

                /* Ajout Éléments au panel Salle */

                panelSalle.add(nomSalle);
                panelSalle.add(nomJeu);
                panelSalle.add(boutonJeu);
                panelSalle.add(boutonLancer);
                panelSalle.setLayout(grille);

                /* Configuration panelSalle */

                panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                panelSalle.setPreferredSize(new Dimension(listPanel.getWidth()-45,listPanel.getHeight()/4));

                /* Ajout à la liste des salles */

                roomPanel.add(panelSalle,gbc);
                listPanel.add(newButtonPanel,BorderLayout.SOUTH);
                listPanel.revalidate();
                listPanel.repaint();

            }
        });

        /* Setup Marges */

        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border listPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(mainPadding);
        listPanel.setBorder(listPadding);

        /* Setup listPanel */

        listPanel.setLayout(centerLayout);
        listPanel.add(scrollPane,BorderLayout.CENTER);
        listPanel.add(newButtonPanel,BorderLayout.SOUTH);
        listPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));

        newButtonPanel.add(newButton);

        /* Setup Titre */

        titre = new JLabel("MJ - Gestion des salles");
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */

        decoPanel.setLayout(decoLayout);
        decoPanel.setBackground(ColorPerso.gris);
        decoPanel.add(returnButton);

        /* Setup Fenêtre gestion des salles */

        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.gris);
        this.add(listPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(decoPanel, BorderLayout.SOUTH);
        this.setVisible(true);


    }



}
