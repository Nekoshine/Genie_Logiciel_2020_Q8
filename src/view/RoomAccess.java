/* Interface développée par Cédric Plantet */

package view;

import Sockets.Client;
import database.DBRoom;
import launcher.Main;
import model.Room;
import model.RoomList;
import model.User;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class RoomAccess extends JPanel implements ActionListener,MouseListener {

    /* Liste des salles */
    private RoomList ListRoom;

    /* Panel */
    private final JPanel listPanel;
    private final JPanel roomPanel;

    /* Boutons */
    private final JButton returnButton;
    private JButton rankingButton;

    private GlobalFrame frame;

    public User user;

    private static volatile RoomAccess INSTANCE = new RoomAccess(Main.frame,Main.ListRoom,new User(1,"","",false));
    private RoomAccess(GlobalFrame frame,RoomList roomList,User user){

        this.frame = frame;
        this.user = user;


        /* Récuperation des salles */
        ListRoom = roomList;

        /* Déclaration JPanel - JScrollPane */
        listPanel = new JPanel();
        roomPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel decoPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        JPanel panelRanking = new JPanel();


        JScrollPane scrollPane = new JScrollPane(roomPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        /* Déclaration Layouts */
        BorderLayout mainLayout = new BorderLayout(10, 10);
        BorderLayout centerLayout = new BorderLayout(4, 4);
        FlowLayout buttonLayout = new FlowLayout(FlowLayout.LEADING);
        GridBagLayout listLayout = new GridBagLayout();
        roomPanel.setLayout(listLayout);
        roomPanel.setBackground(Color.LIGHT_GRAY);

        /* Contraintes GridBag */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        /* Déclaration Boutons */
        returnButton = new JButton("Deconnexion");
        returnButton.setBackground(ColorPerso.rouge);
        returnButton.setForeground(Color.white);
        returnButton.setPreferredSize(new Dimension(140,30));
        returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        rankingButton = new JButton("Classements");
        rankingButton.setBackground(ColorPerso.vert);
        rankingButton.setForeground(ColorPerso.white);
        rankingButton.setPreferredSize(new Dimension(140,30));
        rankingButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

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
        listPanel.setBackground(ColorPerso.gray);


        //roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        /* Setup Titre */
        JLabel titre = new JLabel("Joueur - Liste des salles");
        titre.setFont(FontPerso.ArialBold);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */

        decoPanel.add(returnButton);
        decoPanel.setBackground(ColorPerso.DARK_GRAY);
        panelRanking.setOpaque(false);
        panelRanking.add(rankingButton);


        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(decoPanel,BorderLayout.WEST);
        buttonPanel.add(panelRanking,BorderLayout.EAST);
        buttonPanel.setOpaque(false);

        rankingButton.addActionListener(this);
        rankingButton.addMouseListener(this);

        returnButton.addActionListener(this);
        returnButton.addMouseListener(this);

        /* Setup Fenêtre gestion des salles */

        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.grisClair);
        this.add(listPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.setBackground(ColorPerso.darkGray);
        this.setVisible(true);

    }

    public static RoomAccess getInstance(GlobalFrame frame, RoomList roomList,User user) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new RoomAccess(frame,roomList,user);
                }
            }
        }
        else {
            INSTANCE.frame=frame;
            INSTANCE.ListRoom=roomList;
            INSTANCE.createList();
            INSTANCE.user=user;
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
        JPanel joinPanel = new JPanel();
        joinPanel.setLayout(new BorderLayout());

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

        boutonJoin.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        if(salle.getUserInside()==-1) {
            boutonJoin.setText("Rejoindre la salle");
            boutonJoin.setBackground(ColorPerso.azur);
        }

        else{
            boutonJoin.setText("Occupé. . .");
            boutonJoin.setEnabled(false);
            boutonJoin.setBackground(ColorPerso.darkGray);
        }

        joinPanel.add(boutonJoin,BorderLayout.CENTER);
        joinPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        boutonJoin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(salle.getUserInside()==-1){
                    JOptionPane.showMessageDialog(null, "Le MJ va valider votre connection", "Connexion en cours", JOptionPane.INFORMATION_MESSAGE,null);
                    if(Client.connectToServer(user.getId(),salle)){
                        frame.currentGameDisplay(frame,salle.getGame(),salle.getId());
                        salle.setUserInside(user.getId());
                        //DBRoom.majRoom(salle.getId(),salle.getGame().getId(),salle.getCompetitive(),salle.getUserInside());
                    }
                    else {
                        frame.roomAccessDisplay(frame,Main.ListRoom,user);
                    }
                }

                else{

                    frame.roomAccessDisplay(frame,ListRoom,user);
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
        panelSalle.add(joinPanel);
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

        if (e.getSource() == rankingButton){
            ChooseRanking chooseRanking = new ChooseRanking(ListRoom,user);
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
            returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
        if (e.getSource()==rankingButton){
            rankingButton.setBackground(ColorPerso.vertHoover);
            rankingButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==returnButton) {
            returnButton.setBackground(ColorPerso.rouge);
            returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
        if (e.getSource()==rankingButton){
            rankingButton.setBackground(ColorPerso.vert);
            rankingButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }

    }

}
