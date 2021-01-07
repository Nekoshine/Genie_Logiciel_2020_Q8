//Codé par alan

package view;

import database.DBRoom;
import database.DBUser;
import launcher.Main;
import view.style.ColorPerso;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class ConnectionMenu extends JPanel implements ActionListener, MouseListener {

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
        saisieidentifiant = new JTextField();
        saisieidentifiant.setColumns(30);

        login.add(identifiant);
        login.add(saisieidentifiant);

        //creation de la partie motdepasse

        JPanel mdp = new JPanel();
        mdp.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        JLabel motdepasse = new JLabel("Mot de passe :");
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connection){
            String idinput = saisieidentifiant.getText();
            String mdpinput = String.valueOf(saisiemotdepasse.getPassword());
            int isAdmin = DBUser.connectUser(idinput,mdpinput);
            if (isAdmin==1){
                frame.mainMenuDisplay(frame);
                Main.ListRoom = DBRoom.getRooms(Main.idUser); // recherche des salles dans la BDD apres la connection
            }
            else if( isAdmin==0){
                Main.ListRoom = DBRoom.getRooms(3); //si le joueur est le numero
                frame.roomAccessDisplay(frame,Main.ListRoom);
            }
            else {
                JOptionPane.showMessageDialog(frame,"l'identifiant ou le mot de passe ne correspond pas");
            }

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
}


