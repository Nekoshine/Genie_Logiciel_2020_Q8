/* Interface développée par Cédric Plantet */

package view;

import sockets.Admin;
import database.DBRoom;
import launcher.Main;
import model.Room;
import model.RoomList;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class RoomManagement extends JPanel implements ActionListener,MouseListener {

    /* Liste des salles */
    private RoomList ListRoom;

    /* Panel */
    private final JPanel listPanel;
    private final JPanel newButtonPanel;
    private final JPanel roomPanel;
    /* Boutons */
    private final JButton returnButton;
    private final JButton newButton;

    private GlobalFrame frame;

    Dimension windowSize;

    private static volatile RoomManagement INSTANCE = new RoomManagement(Main.frame);
    private RoomManagement(GlobalFrame frame){

        this.frame = frame;
        windowSize = frame.getSize();

        /* Récuperation des salles */
        ListRoom = Main.ListRoom;

        /* Déclaration JPanel - JScrollPane */
        listPanel = new JPanel();
        roomPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel decoPanel = new JPanel();
        newButtonPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(roomPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        /* Déclaration Layouts */
        BorderLayout mainLayout = new BorderLayout(10, 10);
        BorderLayout centerLayout = new BorderLayout(4, 4);
        GridBagLayout listLayout = new GridBagLayout();
        roomPanel.setLayout(listLayout);
        roomPanel.setBackground(Color.LIGHT_GRAY);



        /* Contraintes GridBag */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        /* Déclaration Boutons */
        returnButton = new JButton("Retour");

        //returnButton.setBackground(ColorPerso.darkGray);
        returnButton.setForeground(Color.white);
        returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        returnButton.setPreferredSize(new Dimension(100,30));



        newButton = new JButton("Nouvelle Salle");
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        newButton.setForeground(Color.white);
        newButton.addActionListener(this);
        newButton.addMouseListener(this);
        newButton.setBackground(ColorPerso.grisFonce);
        newButton.setPreferredSize(new Dimension(210,45));

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


        newButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        newButtonPanel.add(newButton);
        newButtonPanel.setBackground(Color.LIGHT_GRAY);

        //roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        /* Setup Titre */
        JLabel titre = new JLabel("MJ - Gestion des salles");
        titre.setFont(FontPerso.ArialBold);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */
        decoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        decoPanel.setBackground(ColorPerso.DARK_GRAY);
        decoPanel.add(returnButton);
        returnButton.addActionListener(this);
        returnButton.addMouseListener(this);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                GlobalFrame.windowSize = getSize();
                INSTANCE.revalidate();
                INSTANCE.repaint();
            }
        });

        /* Setup Fenêtre gestion des salles */
        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.DARK_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.add(listPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(decoPanel, BorderLayout.PAGE_END);
        this.setVisible(true);


    }

    /**
     * Interface de gestion des salles
     * @param frame Fenêtre d'affichage
     * @return Retourne une instance de RoomManagement
     */

    public static RoomManagement getInstance(GlobalFrame frame) {
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
            Main.ListRoom=DBRoom.getRooms(Main.idAdmin);
            INSTANCE.ListRoom = Main.ListRoom;
            INSTANCE.createList();
            INSTANCE.returnButton.setBackground(ColorPerso.rouge);
            INSTANCE.returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        }
        return INSTANCE;
    }

    /**
     * La méthode ajoutSalle() permet l'ajout d'une salle à l'interface de gestion des salles.
     * Elle renvoie un JPanel contenant les informations de la salle
     *
     * @param salle Salle à ajouter
     * @param gbc Contraintes d'affichage
     * @param i Indice de la salle à ajouter
     * @return Retourne un JPanel avec la salle
     */

    private JPanel ajoutSalle(Room salle, GridBagConstraints gbc, int i) {

        BorderLayout layoutChoose = new BorderLayout();
        BorderLayout layoutManage = new BorderLayout();

        /* Contraintes GridBag */
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = salle.getId() - 1;
        gbc.gridx = 0;

        /* Ajout Panel */
        JPanel panelSalle = new JPanel();
        JPanel panelManage = new JPanel();
        JPanel panelChoose = new JPanel();
        JPanel nomSallePanel = new JPanel();
        JPanel nomJeuPanel = new JPanel();

        /* Construction Panel Salle */
        GridLayout grille = new GridLayout(1,4,20,0);

        JLabel nomSalle = new JLabel("Salle " + i + " :");
        nomSalle.setHorizontalAlignment(SwingConstants.CENTER);
        nomSalle.setVerticalAlignment(SwingConstants.CENTER);
        nomSallePanel.setLayout(new BorderLayout());
        nomSallePanel.add(nomSalle,BorderLayout.CENTER);

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
        nomJeuPanel.setLayout(new BorderLayout());
        nomJeuPanel.add(nomJeu,BorderLayout.CENTER);

        panelChoose.setLayout(layoutChoose);

        JButton boutonJeu = new JButton("Choisir Jeu");
        boutonJeu.setBackground(ColorPerso.jaune);
        boutonJeu.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        panelChoose.add(boutonJeu,BorderLayout.CENTER);
        panelChoose.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));


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
                boutonJeu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boutonJeu.setBackground(ColorPerso.jaune);
                boutonJeu.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
        });

        JButton boutonLancer = new JButton("Suivre Partie");

        panelManage.setLayout(layoutManage);
        boutonLancer.setBackground(ColorPerso.vert);
        boutonLancer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        panelManage.add(boutonLancer,BorderLayout.CENTER);
        panelManage.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));


        if (salle.getGame() == null  || salle.getUserInside() == -1 ) {
            boutonLancer.setText("Salle vide");
            boutonLancer.setEnabled(false);
            boutonLancer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            boutonLancer.setBackground(Color.DARK_GRAY);

        }

        boutonLancer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int riddleNB = Admin.getRiddleNb(salle.getUserInside());
                riddleNB = Admin.getRiddleNb(salle.getUserInside());
                System.out.println("le joueur me dit qu'il est a l'enigme "+riddleNB);
                frame.playerManagementDisplay(frame,salle,salle.getGame().getId(), riddleNB,false,false,false);
            }
        });

        if (boutonLancer.isEnabled()) {
            boutonLancer.setText("Suivre Partie");
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
                    boutonLancer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    boutonLancer.setBackground(ColorPerso.vert);
                    boutonLancer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                }
            });
        }


        /* Ajout Éléments au panel Salle */
        panelSalle.setLayout(grille);
        panelSalle.add(nomSallePanel);
        panelSalle.add(nomJeuPanel);
        panelSalle.add(panelChoose);
        panelSalle.add(panelManage);

        /* Configuration panelSalle */
        panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

       return panelSalle;

    }

    /**
     * Met à jour la liste des salles
     */

    private void majRoom() {
        ListRoom.addRoom(DBRoom.getMax()+1,null,false,-1);
        this.createList();
    }

    /**
     * Méthode affichant les panels des salles à l'interface
     */

    private void createList() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);
        roomPanel.removeAll();

        for (int i = 0; i < ListRoom.getSize(); i++) {
            listPanel.remove(newButtonPanel);
            JPanel panelSalle = this.ajoutSalle(ListRoom.getRoom(i), gbc,i+1);
            panelSalle.setPreferredSize(new Dimension((int) (listPanel.getWidth() - 85), 100));
            roomPanel.add(panelSalle, gbc);
            listPanel.add(newButtonPanel, BorderLayout.PAGE_END);
            listPanel.revalidate();
            listPanel.repaint();
        }

        listPanel.revalidate();
        listPanel.repaint();
        frame.repaint();

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
        else if(e.getSource()==newButton){
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBackground(Color.BLACK);
            newButton.setOpaque(true);
            newButton.setForeground(Color.white);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==returnButton) {
            returnButton.setBackground(ColorPerso.rouge);
            returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
        else if(e.getSource()==newButton){
            newButton.setBackground(ColorPerso.grisFonce);
            newButton.setForeground(Color.white);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
    }
}
