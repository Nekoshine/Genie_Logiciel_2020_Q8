//Codé par alan

package view;


import database.DBUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SignupMenu extends JPanel implements ActionListener {

    private JPanel login;
    private JPanel mdp;
    private JPanel key;
    private JPanel conteneurretour;

    private JButton confirmation;
    private JButton retour;

    private JTextField saisieidentifiant;
    private JTextField saisiecle;
    private JPasswordField saisiemotdepasse;

    private JLabel identifiant;
    private JLabel motdepasse;
    private JLabel cleadmin;

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
        saisiemotdepasse = new JPasswordField();
        saisiemotdepasse.setColumns(30);

        mdp.add(motdepasse);
        mdp.add(saisiemotdepasse);

        //creation de la partie clé admin

        key = new JPanel();
        key.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        cleadmin = new JLabel("Clé (compte admin) :");
        saisiecle = new JTextField();
        saisiecle.setColumns(27);

        key.add(cleadmin);
        key.add(saisiecle);

        //creation du bouton de confirmation

        confirmation= new JButton("Confirmation");
        confirmation.addActionListener(this);
        confirmation.setBackground(ColorPerso.vert);

        //creation du bouton de retour

        retour = new JButton("Retour");
        retour.addActionListener(this);
        retour.setBackground(ColorPerso.rouge);
        retour.setForeground(Color.WHITE);

        conteneurretour = new JPanel();
        conteneurretour.setLayout(new FlowLayout(FlowLayout.LEFT));
        conteneurretour.add(retour);

        this.add(Box.createRigidArea(new Dimension(0, 150)));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        login.setMaximumSize(new Dimension(800,50));
        this.add(login);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        mdp.setMaximumSize(new Dimension(800,30));
        this.add(mdp);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        //key.setMaximumSize(new Dimension(1000,30));
        this.add(key);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(confirmation);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(conteneurretour);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == confirmation){
            String idinput = saisieidentifiant.getText();
            String mdpinput = saisiemotdepasse.getText();
            String cleinmput = saisiecle.getText();

            if (idinput.isEmpty() || mdpinput.isEmpty() ){
                JOptionPane.showMessageDialog(frame,"Un ou plusieurs champs n'ont pas été remplis","Informations incomplètes", JOptionPane.WARNING_MESSAGE);
            }
            else if (cleinmput.isEmpty()) {
                if (DBUser.insertUser(idinput, mdpinput)) {
                    frame.connectionMenuDisplay(frame);
                } else {
                    JOptionPane.showMessageDialog(frame, "L'identifiant demandé n'est pas disponible", "Attention", JOptionPane.WARNING_MESSAGE);
                }
            }
            else if (!(cleinmput.isEmpty())){}
        }
        else if (event.getSource() == retour){
            frame.connectionMenuDisplay(frame);
        }
    }
}