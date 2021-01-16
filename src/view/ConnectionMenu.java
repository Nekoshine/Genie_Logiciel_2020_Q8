//Codé par alan

package view;

import Sockets.Client;
import Sockets.ThreadAdmin;
import database.DBRoom;
import database.DBUser;
import launcher.Main;
import view.style.ColorPerso;
import view.style.ImagePerso;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ConnectionMenu extends JPanel implements ActionListener, MouseListener, KeyListener {

    private JButton connection;
    private JButton inscription;

    private JTextField saisieidentifiant;
    private JPasswordField saisiemotdepasse;

    private GlobalFrame frame;

    ConnectionMenu(GlobalFrame frame) {

        this.frame = frame;

        //creation de la partie login


        JPanel login = new JPanel();
        login.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));

        JLabel identifiant = new JLabel("Identifiant :");
        identifiant.setForeground(Color.white);
        saisieidentifiant = new JTextField();
        saisieidentifiant.setColumns(30);
        saisieidentifiant.addKeyListener(this);

        login.add(identifiant);
        login.add(saisieidentifiant);

        //creation de la partie motdepasse

        JPanel mdp = new JPanel();
        mdp.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        JLabel motdepasse = new JLabel("Mot de passe :");
        motdepasse.setForeground(Color.white);
        saisiemotdepasse = new JPasswordField();
        saisiemotdepasse.setColumns(30);

        mdp.add(motdepasse);
        mdp.add(saisiemotdepasse);

        //creation du bouton de connexion

        connection = new JButton("Connexion");
        connection.addActionListener(this);
        connection.addMouseListener(this);
        connection.setBackground(ColorPerso.vert);

        inscription = new JButton("S'inscrire");
        inscription.addActionListener(this);
        inscription.addMouseListener(this);
        inscription.setBackground(ColorPerso.jaune);

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
        login.setMaximumSize(new Dimension(800,50));
        this.add(login);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        mdp.setMaximumSize(new Dimension(800,30));
        this.add(mdp);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(conteneurboutons);
        this.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImagePerso.backgroundMenu,0,0,this);
    }

    private void connect(String idinput, String mdpinput) {
        int isAdmin = DBUser.connectUser(idinput,mdpinput);
        if (isAdmin==1){
            frame.mainMenuDisplay(frame);
            Main.ListRoom = DBRoom.getRooms(Main.idUser); // recherche des salles dans la BDD apres la connection
        }
        else if( isAdmin==0){
            //Client.connectToServer();
            Main.ListRoom = DBRoom.getRooms(3); //si le joueur est le numero
            frame.roomAccessDisplay(frame,Main.ListRoom);
            //Client.recepAdminInfo()
        }
        else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(frame,"l'identifiant ou le mot de passe ne correspond pas");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connection){
            String idinput = saisieidentifiant.getText();
            String mdpinput = String.valueOf(saisiemotdepasse.getPassword());
            connect(idinput,mdpinput);

        }
        else if (e.getSource() == inscription){
            frame.signupMenuDisplay(frame);
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
        }
        else if (e.getSource()==connection){
            connection.setBackground(ColorPerso.vertHoover);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==inscription){
            inscription.setBackground(ColorPerso.jaune);
        }
        else if (e.getSource()==connection){
            connection.setBackground(ColorPerso.vert);
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
}


