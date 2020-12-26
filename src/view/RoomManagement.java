/* Interface développée par Cédric Plantet */

package view;

import controller.RoomController;
import model.Game;
import model.Room;
import model.RoomList;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomManagement extends JPanel implements ActionListener {

    /* Test */
    public RoomList ListRoom;


    /* Déclarations JPanel */

    public JPanel listPanel;
    public JPanel titlePanel;
    public JPanel decoPanel;
    public JPanel newButtonPanel;
    public JPanel roomPanel;


    /* Déclarations Layouts */

    private final GridBagLayout listLayout;
    private final FlowLayout decoLayout;
    private final BorderLayout mainLayout;
    private final BorderLayout centerLayout;
    private final JScrollPane scrollPane;

    /* Déclaration boutons */

    public final JButton returnButton;
    public final JButton newButton;

    private GlobalFrame frame;

    private final JLabel titre;
    private int compteur = 0;


    public RoomManagement(GlobalFrame frame,RoomList list){

        this.frame = frame;

        /* Rcuperation des Salle */

        /*ListRoom.addRoom(1,"Titre du jeu 1");
        ListRoom.addRoom(2,"Titre du jeu 2");
        ListRoom.addRoom(3,"Titre du jeu 3");
        ListRoom.addRoom(4,"Titre du jeu 4");*/
        ListRoom = list;

        /* Déclaration JPanel - JScrollPane */

        listPanel = new JPanel();
        roomPanel = new JPanel();
        titlePanel = new JPanel();
        decoPanel = new JPanel();
        newButtonPanel = new JPanel();
        scrollPane = new JScrollPane(roomPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

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
        returnButton.setBackground(ColorPerso.rouge);
        returnButton.setForeground(Color.white);

        newButton = new JButton("Nouvelle Salle");
        newButton.setBackground(Color.GRAY);
        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.CreateList();


        /* Ajout d'une nouvelle salle */

        newButton.addActionListener(this);

        /*newButton.setAction(new AbstractAction("Nouvelle Salle") {

            @Override
            public void actionPerformed(ActionEvent e) {

                Room salle = new Room(ListRoom.getSize()+1,"Titre du jeu " + (ListRoom.getSize()+1));


                /* Contraintes GridBag *

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1;
                gbc.gridy = salle.getId()-1;
                gbc.gridx = 0;


                /* Ajout Panel *

                listPanel.remove(newButtonPanel);
                JPanel panelSalle = new JPanel();

                /* Construction PanelSalle *

                GridLayout grille = new GridLayout(1,4,70,0);
                JLabel nomSalle = new JLabel("Salle " + salle.getId() + " :");
                nomSalle.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel nomJeu = new JLabel(salle.getGame());
                nomJeu.setHorizontalAlignment(SwingConstants.CENTER);

                JButton boutonJeu = new JButton("Choisir Jeu");
                boutonJeu.setBackground(ColorPerso.jaune);
                boutonJeu.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.gameManagementDisplay(frame, salle.getId());
                    }
                });

                JButton boutonLancer = new JButton("Lancer");
                boutonLancer.setBackground(ColorPerso.vert);
                boutonLancer.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(salle.getId());
                    }
                });

                /* Ajout Éléments au panel Salle *

                panelSalle.add(nomSalle);
                panelSalle.add(nomJeu);
                panelSalle.add(boutonJeu);
                panelSalle.add(boutonLancer);
                panelSalle.setLayout(grille);

                /* Configuration panelSalle *

                panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                panelSalle.setPreferredSize(new Dimension(listPanel.getWidth()-45,75));
                panelSalle.setBounds(0,0+(salle.getId()*75),listPanel.getWidth()-45,75);


                /* Ajout à la liste des salles *

                roomPanel.add(panelSalle,gbc);
                listPanel.add(newButtonPanel,BorderLayout.SOUTH);
                ListRoom.addRoom(salle);
                listPanel.revalidate();
                listPanel.repaint();



            }
        });*/

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
        //roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        /* Setup Titre */

        titre = new JLabel("MJ - Gestion des salles");
        titre.setFont(FontPerso.ArialBold);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */

        decoPanel.setLayout(decoLayout);
        decoPanel.setBackground(ColorPerso.gris);
        decoPanel.add(returnButton);

        returnButton.addActionListener(this);

        /* Setup Fenêtre gestion des salles */

        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.gris);
        this.add(listPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(decoPanel, BorderLayout.SOUTH);
        this.setVisible(true);


    }


    /**
     * La méthode ajoutSalle() permet l'ajout d'une salle à l'interface de gestion des salles.
     * Elle renvoie un JPanel contenant les informations de la salle
     *
     * @param salle
     * @param gbc
     * @return
     */

    JPanel ajoutSalle(Room salle, GridBagConstraints gbc){

        /* Contraintes GridBag */

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridy = salle.getId()-1;
        gbc.gridx = 0;

        /* Ajout Panel */

        JPanel panelSalle = new JPanel();

        /* Construction Panel Salle */

        GridLayout grille = new GridLayout(1,4,70,50);
        JLabel nomSalle = new JLabel("Salle " + salle.getId() + " :");
        nomSalle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nomJeu;
        if (salle.getGame()!=null){
            nomJeu = new JLabel(salle.getGame().getTitre());
        }
        else{
            nomJeu = new JLabel("Pas de jeu");
        }
        nomJeu.setHorizontalAlignment(SwingConstants.CENTER);

        JButton boutonJeu = new JButton("Choisir Jeu");
        boutonJeu.setBackground(ColorPerso.jaune);
        boutonJeu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.gameManagementDisplay(frame, salle.getId());
            }
        });

        JButton boutonLancer = new JButton("Lancer");
        boutonLancer.setBackground(ColorPerso.vert);
        boutonLancer.addActionListener(new AbstractAction() {
            @Override

            /**
             *
             */
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton lancer " +salle.getId());
            }
        });

        /* Ajout Éléments au panel Salle */

        panelSalle.add(nomSalle);
        panelSalle.add(nomJeu);
        panelSalle.add(boutonJeu);
        panelSalle.add(boutonLancer);
        panelSalle.setLayout(grille);

        /* Configuration panelSalle */

        panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

       return panelSalle;

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == returnButton) {

            frame.mainMenuDisplay(frame);
        }
        else if(e.getSource()==newButton){
            this.majRoom();
        }

    }

    private void majRoom() {
        ListRoom.addRoom(ListRoom.getSize()+1,null);
        this.CreateList();
    }

    public void CreateList() {
        /* Chargement des salles */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        for (int i = 0; i < ListRoom.getSize(); i++) {
            listPanel.remove(newButtonPanel);
            JPanel panelSalle = ajoutSalle(ListRoom.getRoom(i), gbc);
            panelSalle.setPreferredSize(new Dimension(listPanel.getWidth() - 45, 75));
            roomPanel.add(panelSalle, gbc);
            listPanel.add(newButtonPanel, BorderLayout.SOUTH);
            listPanel.revalidate();
            listPanel.repaint();
        }

        listPanel.revalidate();
        listPanel.repaint();
        frame.repaint();

    }


}
