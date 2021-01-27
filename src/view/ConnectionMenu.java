//Codé par alan

package view;

import Sockets.Admin;
import Sockets.Client;
import database.DBRoom;
import database.DBUser;
import launcher.Main;
import view.SwingWorkers.ImageLoaderConnection;
import view.style.ColorPerso;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class ConnectionMenu extends JPanel implements ActionListener, MouseListener, KeyListener, FocusListener {

  private final JButton connection;
  private final JButton inscription;
  private BufferedImage backgroundConnexion;

  private final JTextField saisieidentifiant;
  private final JPasswordField saisiemotdepasse;

  private GlobalFrame frame;

  private static volatile ConnectionMenu INSTANCE = new ConnectionMenu(Main.frame);

  /**
   * Interface de connexion au lançement du logiciel
   * @param frame Fenêtre d'affichage
   */
  private ConnectionMenu(GlobalFrame frame) {

    this.frame = frame;
    new ImageLoaderConnection(this).execute();

    //creation de la partie login


    JPanel login = new JPanel();
    login.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));

    JLabel identifiant = new JLabel("Identifiant :");
    identifiant.setForeground(Color.white);
    saisieidentifiant = new JTextField("identifiant");
    saisieidentifiant.setColumns(30);
    saisieidentifiant.setPreferredSize(new Dimension(200,20));
    saisieidentifiant.addKeyListener(this);
    saisieidentifiant.addFocusListener(this);

    login.add(identifiant);
    login.add(saisieidentifiant);

    //creation de la partie motdepasse

    JPanel mdp = new JPanel();
    mdp.setLayout(new FlowLayout(FlowLayout.CENTER,9,0));
    JLabel motdepasse = new JLabel("Mot de passe :");
    motdepasse.setForeground(Color.white);
    JPanel panelSaisie = new JPanel(new FlowLayout(1));
    saisiemotdepasse = new JPasswordField("");
    saisiemotdepasse.setColumns(38);
    panelSaisie.add(saisiemotdepasse);
    panelSaisie.setOpaque(false);
    saisiemotdepasse.setPreferredSize(new Dimension(500,20));
    saisiemotdepasse.addKeyListener(this);
    saisiemotdepasse.addFocusListener(this);

    mdp.add(motdepasse);
    mdp.add(panelSaisie);

    //creation du bouton de connexion

    connection = new JButton("Connexion");
    connection.addActionListener(this);
    connection.addMouseListener(this);
    connection.setBackground(ColorPerso.vert);
    connection.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    connection.setPreferredSize(new Dimension(100,35));

    inscription = new JButton("S'inscrire");
    inscription.addActionListener(this);
    inscription.addMouseListener(this);
    inscription.setBackground(ColorPerso.jaune);
    inscription.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    inscription.setPreferredSize(new Dimension(100,35));

    //création du lien vers l'inscription
    JPanel conteneurboutons = new JPanel();
    conteneurboutons.setLayout(new FlowLayout(FlowLayout.CENTER));

    conteneurboutons.add(connection);
    conteneurboutons.add(inscription);

    conteneurboutons.setOpaque(false);
    login.setOpaque(false);
    mdp.setOpaque(false);

    this.add(Box.createRigidArea(new Dimension(0, 150)));
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
    login.setMaximumSize(new Dimension(800,50));
    this.add(login);
    this.add(Box.createRigidArea(new Dimension(0, 20)));
    mdp.setMaximumSize(new Dimension(800,50));
    this.add(mdp);
    this.add(Box.createRigidArea(new Dimension(0, 10)));
    this.add(conteneurboutons);
    this.setVisible(true);


  }

  /**
   * Permet de recupérer l'instance de GameCreation
   * @param frame Fenêtre d'affichage
   * @return Retourne une instance de ConnectionMenu
   */

  public static ConnectionMenu getInstance(GlobalFrame frame) {
    //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
    //d'éviter un appel coûteux à synchronized,
    //une fois que l'instanciation est faite.
    if (INSTANCE == null) {
      // Le mot-clé synchronized sur ce bloc empêche toute instanciation
      // multiple même par différents "threads".
      // Il est TRES important.
      synchronized(INSTANCE) {
        if (INSTANCE == null) {
          INSTANCE = new ConnectionMenu(frame);
        }
      }
    }
    else {
      INSTANCE.frame=frame;

      /*indication pour les champs*/
      INSTANCE.saisieidentifiant.setText("Identifiant");
      INSTANCE.saisiemotdepasse.setText("mot de passe");
      INSTANCE.saisiemotdepasse.setForeground(Color.gray);
      INSTANCE.saisiemotdepasse.setFont(INSTANCE.saisiemotdepasse.getFont().deriveFont(Font.ITALIC));
      INSTANCE.saisieidentifiant.setForeground(Color.gray);
      INSTANCE.saisieidentifiant.setFont(INSTANCE.saisieidentifiant.getFont().deriveFont(Font.ITALIC));

      /*remettre les boutons*/
      INSTANCE.connection.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
      INSTANCE.connection.setBackground(ColorPerso.vert);
      INSTANCE.inscription.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
      INSTANCE.inscription.setBackground(ColorPerso.jaune);

    }
    return INSTANCE;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if(backgroundConnexion != null){
      g.drawImage(backgroundConnexion,0,0,this);

    }


  }

  private void connect(String idinput, String mdpinput) {
    int isAdmin = DBUser.connectUser(idinput,mdpinput);
    if(isAdmin==1){
      Main.idAdmin= DBUser.getidUser(idinput);
    }
    int idUser = DBUser.getidUser(idinput);
    if (isAdmin==1){

      ExecutorService service = Executors.newFixedThreadPool(4);
      service.submit((Runnable) () -> {
        while (true){
          Admin.setServerAdmin(Main.idAdmin);
        }
      });

      ExecutorService serviceFin = Executors.newFixedThreadPool(4);
      serviceFin.submit((Runnable) () -> {
        while (true){
          Admin.acceptFin();
        }
      });

      Main.frame.mainMenuDisplay(Main.frame);
      Main.ListRoom = DBRoom.getRooms(Main.idAdmin); // recherche des salles dans la BDD apres la connection

    }
    else if( isAdmin==0){
      int idAdmin=Client.recepIpIdAdmin(idUser);
      System.out.println("admin " + idAdmin);
      System.out.println("id du Joueur " + idUser);
      Main.ListRoom = DBRoom.getRooms(idAdmin); //si le joueur est le numero
      Main.frame.roomAccessDisplay(Main.frame,Main.ListRoom,DBUser.getUser(idUser));

    }
    else {
      Toolkit.getDefaultToolkit().beep();
      JOptionPane.showMessageDialog(Main.frame,"l'identifiant ou le mot de passe ne correspond pas");
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == connection){
      String idinput = saisieidentifiant.getText();
      String mdpinput = String.valueOf(saisiemotdepasse.getPassword());
      connect(idinput,mdpinput);

    }
    else if (e.getSource() == inscription){
      Main.frame.signupMenuDisplay(frame);
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
    if(e.getSource()==inscription){
      inscription.setBackground(ColorPerso.jauneHoover);
      inscription.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }
    else if (e.getSource()==connection){
      connection.setBackground(ColorPerso.vertHoover);
      connection.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if(e.getSource()==inscription){
      inscription.setBackground(ColorPerso.jaune);
      inscription.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
    else if (e.getSource()==connection){
      connection.setBackground(ColorPerso.vert);
      connection.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key=e.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
      String idinput = saisieidentifiant.getText();
      String mdpinput = String.valueOf(saisiemotdepasse.getPassword());
      connect(idinput,mdpinput);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  public void imageLoaded(BufferedImage backgroundConnexion) {
    this.backgroundConnexion = backgroundConnexion;
    repaint();
  }

  @Override
  public void focusGained(FocusEvent e) {
    if(e.getSource()==saisieidentifiant){
      if(saisieidentifiant.getText().equals("Identifiant")){
        saisieidentifiant.setText("");
        saisieidentifiant.setForeground(Color.black);
        saisieidentifiant.setFont(saisieidentifiant.getFont().deriveFont(Font.PLAIN));
      }
    }
    else if(e.getSource()==saisiemotdepasse){
      if(String.valueOf(saisiemotdepasse.getPassword()).equals("mot de passe")){
        saisiemotdepasse.setText("");
        saisiemotdepasse.setForeground(Color.black);
        saisiemotdepasse.setFont(saisiemotdepasse.getFont().deriveFont(Font.PLAIN));
      }
    }
  }

  @Override
  public void focusLost(FocusEvent e) {
    if(e.getSource()==saisieidentifiant){
      if(saisieidentifiant.getText().equals("")){
        saisieidentifiant.setText("Identifiant");
        saisieidentifiant.setForeground(Color.gray);
        saisieidentifiant.setFont(saisieidentifiant.getFont().deriveFont(Font.ITALIC));
      }
    }
    else if(e.getSource()==saisiemotdepasse){
      if(String.valueOf(saisiemotdepasse.getPassword()).equals("")){
        saisiemotdepasse.setText("mot de passe");
        saisiemotdepasse.setForeground(Color.gray);
        saisiemotdepasse.setFont(saisiemotdepasse.getFont().deriveFont(Font.ITALIC));
      }
    }
  }
}