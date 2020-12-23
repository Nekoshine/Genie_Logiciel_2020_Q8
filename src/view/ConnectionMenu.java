//Codé par alan

package view;

import database.DBUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConnectionMenu extends JPanel implements ActionListener {

    private JPanel login;
    private JPanel mdp;
    private JPanel conteneurboutons;

    private JButton connection;
    private JButton inscription;

    private JTextField saisieidentifiant;
    private JPasswordField saisiemotdepasse;

    private JLabel identifiant;
    private JLabel motdepasse;

    private GlobalFrame frame;

    public ConnectionMenu(GlobalFrame frame) {

        this.frame = frame;

        //creation de la partie login


        login = new JPanel();
        login.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));

        identifiant = new JLabel("Identifiant :");
        saisieidentifiant = new JTextField();
        saisieidentifiant.setColumns(30);

        login.add(identifiant);
        login.add(saisieidentifiant);

        //creation de la partie motdepasse

        mdp = new JPanel();
        mdp.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        motdepasse = new JLabel("Mot de passe :");
        saisiemotdepasse = new JPasswordField();
        saisiemotdepasse.setColumns(30);

        mdp.add(motdepasse);
        mdp.add(saisiemotdepasse);

        //creation du bouton de connexion

        connection = new JButton("Connexion");
        connection.addActionListener(this);
        connection.setBackground(ColorPerso.vert);

        inscription = new JButton("S'inscrire");
        inscription.addActionListener(this);
        inscription.setBackground(ColorPerso.jaune);

        //création du lien vers l'inscription
        conteneurboutons = new JPanel();
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

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == connection){
            String idinput = saisieidentifiant.getText();
            String mdpinput = saisiemotdepasse.getText();
            if (DBUser.connectUser(idinput,mdpinput)){
                frame.mainMenuDisplay(frame);
            }
            else{
                JOptionPane.showMessageDialog(frame,"Merci de vérifiez l'identifiant et/ou le mot de passe\n","Informations invalides", JOptionPane.WARNING_MESSAGE);
            }

        }
        else if (event.getSource() == inscription){
            frame.signupMenuDisplay(frame);
        }
    }
}


