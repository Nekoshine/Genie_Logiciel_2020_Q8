//Codé par alan

package view;

import sun.nio.cs.ext.GB18030;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class SignupMenu extends JPanel implements ActionListener {

    private JPanel login;
    private JPanel mdp;
    private JPanel conteneurinscription;

    private JButton confirmation;

    private JTextField saisieidentifiant;
    private JTextField saisiemotdepasse;

    private JLabel identifiant;
    private JLabel motdepasse;
    private JLabel inscription;

    private GlobalFrame frame;

    public SignupMenu(GlobalFrame frame) {

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
        saisiemotdepasse = new JTextField();
        saisiemotdepasse.setColumns(30);

        mdp.add(motdepasse);
        mdp.add(saisiemotdepasse);

        //creation du bouton de connexion

        confirmation= new JButton("Confirmation");
        confirmation.setBackground(Color.GREEN);


        this.add(Box.createRigidArea(new Dimension(0, 150)));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        login.setMaximumSize(new Dimension(800,50));
        this.add(login);
        mdp.setMaximumSize(new Dimension(800,30));
        this.add(mdp);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(confirmation);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
    }
}