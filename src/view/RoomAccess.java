/* Interface développée par Cédric Plantet */

package view;

import Sockets.Client;
import database.DBRoom;
import database.DBUser;
import launcher.Main;
import model.Room;
import model.RoomList;
import model.User;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class RoomAccess extends JPanel implements ActionListener,MouseListener{

    /* Liste des salles */
    private RoomList ListRoom;

    /* Panel */
    private JPanel listPanel;
    private JPanel roomPanel;

    /* Boutons */
    private JButton returnButton;


    private GlobalFrame frame;
    public User user;

    private static volatile RoomAccess INSTANCE = new RoomAccess(Main.frame,Main.ListRoom);
    private RoomAccess(GlobalFrame frame,RoomList roomList){

        this.frame = frame;
        user = DBUser.getUser(9);


        /* Récuperation des salles */
        ListRoom = roomList;

        /* Déclaration JPanel - JScrollPane */
        listPanel = new JPanel();
        roomPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel decoPanel = new JPanel();


        JScrollPane scrollPane = new JScrollPane(roomPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        /* Déclaration Layouts */
        BorderLayout mainLayout = new BorderLayout(10, 10);
        BorderLayout centerLayout = new BorderLayout(4, 4);
        FlowLayout decoLayout = new FlowLayout(FlowLayout.LEADING);
        GridBagLayout listLayout = new GridBagLayout();
        roomPanel.setLayout(listLayout);

        /* Contraintes GridBag */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        /* Déclaration Boutons */
        returnButton = new JButton("Deconnexion");
        returnButton.setBackground(ColorPerso.rouge);
        returnButton.setForeground(Color.white);


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
        listPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        listPanel.setBackground(Color.LIGHT_GRAY);


        //roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        /* Setup Titre */
        JLabel titre = new JLabel("Joueur - Liste des salles");
        titre.setFont(FontPerso.ArialBold);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */
        decoPanel.setLayout(decoLayout);
        decoPanel.setBackground(ColorPerso.gris);
        decoPanel.add(returnButton);
        decoPanel.setBackground(ColorPerso.DARK_GRAY);
        returnButton.addActionListener(this);
        returnButton.addMouseListener(this);

        /* Setup Fenêtre gestion des salles */


        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.gris);
        this.add(listPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(decoPanel, BorderLayout.PAGE_END);
        this.setBackground(ColorPerso.darkGray);
        this.setVisible(true);

    }

    public static RoomAccess getInstance(GlobalFrame frame, RoomList roomList) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new RoomAccess(frame,roomList);
                }
            }
        }
        else {
            INSTANCE.frame=frame;
            INSTANCE.ListRoom=roomList;
            INSTANCE.createList();
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

    private JPanel ajoutSalle(Room salle, GridBagConstraints gbc, int i){

        /* Contraintes GridBag */
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = salle.getId()-1;
        gbc.gridx = 0;

        /* Ajout Panel */
        JPanel panelSalle = new JPanel();


        /* Construction Panel Salle */
        GridLayout grille = new GridLayout(1,3,70,50);
        JLabel nomSalle = new JLabel("Salle " + i + " :");
        nomSalle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nomJeu;
        if (salle.getGame()!=null){
            if(salle.getCompetitive()) {
                nomJeu = new JLabel("Competitif : "+salle.getGame().getTitre());
            }
            else {
                nomJeu = new JLabel(salle.getGame().getTitre());
            }
        }
        else{
            nomJeu = new JLabel("Pas de jeu");
        }
        nomJeu.setHorizontalAlignment(SwingConstants.CENTER);

        JButton boutonJoin = new JButton();

        if(salle.getUserInside()==-1) {
            boutonJoin.setText("Rejoindre la salle");
            boutonJoin.setBackground(ColorPerso.azur);

        }

        else if (salle.getUserInside()==user.getId()){
            boutonJoin.setText("En attente du MJ . . .");
            boutonJoin.setBackground(ColorPerso.vert);
        }
        else{
            boutonJoin.setText("Occupé. . .");
            boutonJoin.setBackground(ColorPerso.vert);
        }

        if (frame.insideRoom){
            if (salle.getUserInside()!=user.getId()){
                boutonJoin.setEnabled(false);
                boutonJoin.setBackground(ColorPerso.darkGray);
            }
        }

        if (!frame.insideRoom){
            if(salle.getGame()== null || salle.getUserInside()!=-1){
                boutonJoin.setEnabled(false);
                boutonJoin.setBackground(ColorPerso.darkGray);
            }
        }

        boutonJoin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(salle.getUserInside()==-1){
                    frame.insideRoom = true;
                    salle.setUserInside(user.getId());
                    DBRoom.majRoom(salle.getId(),salle.getGame().getId(),salle.getCompetitive(),salle.getUserInside());
                    Client.connectToServer(user.getId());
                }

                else{
                    frame.insideRoom = false;
                    salle.setUserInside(-1);

                    DBRoom.majRoom(salle.getId(),salle.getGame().getId(),salle.getCompetitive(),salle.getUserInside());
                    frame.roomAccessDisplay(frame,ListRoom);
                }


            }
        });

        if(boutonJoin.isEnabled()) {
            boutonJoin.addMouseListener(new MouseListener() {
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
                    //boutonJoin.setBackground(ColorPerso.azurHoover);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //boutonJoin.setBackground(ColorPerso.azur);
                }
            });
        }

        /* Ajout Éléments au panel Salle */
        panelSalle.add(nomSalle);
        panelSalle.add(nomJeu);
        panelSalle.add(boutonJoin);
        panelSalle.setLayout(grille);

        /* Configuration panelSalle */
        panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        return panelSalle;

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == returnButton) {

            frame.connectionMenuDisplay(frame);
        }

    }

    private void createList() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);
        roomPanel.removeAll();
        for (int i = 0; i < ListRoom.getSize(); i++) {

            JPanel panelSalle = this.ajoutSalle(ListRoom.getRoom(i), gbc,i+1);
            panelSalle.setPreferredSize(new Dimension(listPanel.getWidth() - 45, 75));
            roomPanel.add(panelSalle, gbc);
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==returnButton) {
            returnButton.setBackground(ColorPerso.rouge);
        }

    }


}
