/* Interface développée par Cédric Plantet */

package view;

import Sockets.Admin;
import database.DBRoom;
import launcher.Main;
import model.Room;
import model.RoomList;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoomManagement extends JPanel implements ActionListener,MouseListener {

    /* Liste des salles */
    private RoomList ListRoom;

    /* Panel */
    private JPanel listPanel;
    private JPanel newButtonPanel;
    private JPanel roomPanel;
    private JScrollPane scrollPane;
    /* Boutons */
    private JButton returnButton;
    private JButton newButton;

    private GlobalFrame frame;

    private static volatile RoomManagement INSTANCE = new RoomManagement(Main.frame);
    private RoomManagement(GlobalFrame frame){

        this.frame = frame;

        /* Récuperation des salles */
        ListRoom = Main.ListRoom;

        /* Déclaration JPanel - JScrollPane */
        listPanel = new JPanel();
        roomPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel decoPanel = new JPanel();
        newButtonPanel = new JPanel();
        scrollPane = new JScrollPane(roomPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        /* Déclaration Layouts */
        BorderLayout mainLayout = new BorderLayout(10, 10);
        BorderLayout centerLayout = new BorderLayout(4, 4);
        FlowLayout decoLayout = new FlowLayout(FlowLayout.LEADING);
        GridBagLayout listLayout = new GridBagLayout();
        roomPanel.setLayout(listLayout);
        roomPanel.setBackground(Color.LIGHT_GRAY);



        /* Contraintes GridBag */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        /* Déclaration Boutons */
        returnButton = new JButton("Retour");
        returnButton.setBackground(ColorPerso.rouge);
        returnButton.setForeground(Color.white);

        newButton = new JButton("Nouvelle Salle");
        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.addActionListener(this);
        newButton.addMouseListener(this);

        /* Affichage des salles */
        this.createList();

        /* Setup Marges */
        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border listPadding = BorderFactory.createEmptyBorder(20, 10, 10, 10);
        this.setBorder(mainPadding);
        listPanel.setBorder(listPadding);

        /* Setup listPanel */
        listPanel.setLayout(centerLayout);
        listPanel.add(scrollPane,BorderLayout.CENTER);
        listPanel.add(newButtonPanel, BorderLayout.PAGE_END);
        listPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        listPanel.setBackground(Color.LIGHT_GRAY);



        newButtonPanel.add(newButton);
        newButtonPanel.setBackground(Color.LIGHT_GRAY);
        //roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        /* Setup Titre */
        JLabel titre = new JLabel("MJ - Gestion des salles");
        titre.setFont(FontPerso.ArialBold);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */
        decoPanel.setLayout(decoLayout);
        decoPanel.setBackground(ColorPerso.DARK_GRAY);
        decoPanel.add(returnButton);
        returnButton.addActionListener(this);
        returnButton.addMouseListener(this);

        /* Setup Fenêtre gestion des salles */
        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.DARK_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.add(listPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(decoPanel, BorderLayout.PAGE_END);
        this.setVisible(true);


    }

    public final static RoomManagement getInstance(GlobalFrame frame) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new RoomManagement(frame);
                }
            }
        }
        else {
            INSTANCE.frame=frame;
            Main.ListRoom=DBRoom.getRooms(Main.idUser);
            INSTANCE.ListRoom = Main.ListRoom;
            INSTANCE.createList();
            INSTANCE.returnButton.setBackground(ColorPerso.rouge);
        }
        return INSTANCE;
    }

    /**
     * La méthode ajoutSalle() permet l'ajout d'une salle à l'interface de gestion des salles.
     * Elle renvoie un JPanel contenant les informations de la salle
     *
     * @param salle salle à ajouter
     * @param gbc GridBagConstraints
     * @param i
     * @return un JPanel avec la salle
     */

    private JPanel ajoutSalle(Room salle, GridBagConstraints gbc, int i) {

        GridBagConstraints gbcTitle = new GridBagConstraints();
        GridBagConstraints gbcName = new GridBagConstraints();
        GridBagConstraints gbcLaunch = new GridBagConstraints();
        GridBagConstraints gbcChoose = new GridBagConstraints();


        /* Contraintes GridBag */
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = salle.getId() - 1;
        gbc.gridx = 0;

        /* Ajout Panel */
        JPanel panelSalle = new JPanel();
        JPanel panelName = new JPanel();
        JPanel panelTitle = new JPanel();
        JPanel panelLaunch = new JPanel();
        JPanel panelChoose = new JPanel();

        /* Construction Panel Salle */
        GridLayout grille = new GridLayout(1,4,20,0);


        JLabel nomSalle = new JLabel("Salle " + i + " :");
        nomSalle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nomJeu;
        if (salle.getGame() != null) {
            if(salle.getCompetitive()) {
                nomJeu = new JLabel("Competitif : "+salle.getGame().getTitre());
            }
            else {
                nomJeu = new JLabel(salle.getGame().getTitre());
            }
        } else {
            nomJeu = new JLabel("Pas de jeu");
        }
        nomJeu.setHorizontalAlignment(SwingConstants.CENTER);

        JButton boutonJeu = new JButton("Choisir Jeu");
        boutonJeu.setBackground(ColorPerso.jaune);
        boutonJeu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomManagement.this.frame.gameManagementDisplay(RoomManagement.this.frame, salle.getId());
            }
        });
        boutonJeu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                boutonJeu.setBackground(ColorPerso.jauneHoover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boutonJeu.setBackground(ColorPerso.jaune);
            }
        });

        JButton boutonLancer = new JButton("Lancer");
        boutonLancer.setBackground(ColorPerso.vert);


        if (salle.getGame() == null /* || salle.getUserInside() == -1 */) {
            boutonLancer.setEnabled(false);
            boutonLancer.setBackground(Color.darkGray);
            //boutonLancer.setText("Ouvrir la salle");

        }

        boutonLancer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton lancer " + salle.getId());
                frame.playerManagementDisplay(frame,salle.getGame().getId(), 1,false,false,false);


                /*if (boutonLancer.getText()=="Ouvrir la salle"){
                    boutonLancer.setText("En attente");
                    boutonLancer.repaint();

                }*/
            }
        });

        if (boutonLancer.isEnabled()) {
            boutonLancer.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    boutonLancer.setBackground(ColorPerso.vertHoover);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    boutonLancer.setBackground(ColorPerso.vert);
                }
            });
        }


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
        else if(e.getSource()== newButton){
            this.majRoom();
        }

    }

    private void majRoom() {
        ListRoom.addRoom(DBRoom.getMax()+1,null,false);
        this.createList();
    }

    private void createList() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);
        roomPanel.removeAll();

        for (int i = 0; i < ListRoom.getSize(); i++) {
            listPanel.remove(newButtonPanel);
            JPanel panelSalle = this.ajoutSalle(ListRoom.getRoom(i), gbc,i+1);
            panelSalle.setPreferredSize(new Dimension(listPanel.getWidth() - 45, 75));
            roomPanel.add(panelSalle, gbc);
            listPanel.add(newButtonPanel, BorderLayout.PAGE_END);
            listPanel.revalidate();
            listPanel.repaint();
        }

        listPanel.revalidate();
        listPanel.repaint();
        frame.repaint();

    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==returnButton) {
            returnButton.setBackground(ColorPerso.rougeHoover);
        }
        else if(e.getSource()==newButton){
            newButton.setBackground(Color.BLACK);
            newButton.setOpaque(true);
            newButton.setForeground(Color.white);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==returnButton) {
            returnButton.setBackground(ColorPerso.rouge);
        }
        else if(e.getSource()==newButton){
            newButton.setOpaque(false);
            newButton.setForeground(Color.black);
        }
    }
}
