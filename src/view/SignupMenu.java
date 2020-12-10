//Codé par alan

package view;


import model.DBUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class SignupMenu extends JPanel implements ActionListener {

    private JPanel login;
    private JPanel mdp;
    private JPanel conteneurretour;

    private JButton confirmation;
    private JButton retour;

    private JTextField saisieidentifiant;
    private JPasswordField saisiemotdepasse;

    private JLabel identifiant;
    private JLabel motdepasse;


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

        //creation du bouton de confirmation

        confirmation= new JButton("Confirmation");
        confirmation.addActionListener(this);
        confirmation.setBackground(Color.GREEN);

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
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(confirmation);
        this.add(Box.createRigidArea(new Dimension(0, 150)));
        this.add(conteneurretour);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == confirmation){
            String idinput = saisieidentifiant.getText();
            String mdpinput = saisiemotdepasse.getText();

            if (idinput.isEmpty() || mdpinput.isEmpty() ){
                JOptionPane.showMessageDialog(frame,"Un ou plusieurs champs n'ont pas été remplis","Informations incomplètes", JOptionPane.WARNING_MESSAGE);
            }
            else if (DBUser.insertUser(idinput,mdpinput)){
                frame.connectionMenuDisplay(frame);
            }
            else{
                JOptionPane.showMessageDialog(frame,"L'identifiant demandé n'est pas disponible","Attention", JOptionPane.WARNING_MESSAGE);
            }

        }
        else if (event.getSource() == retour){
            frame.connectionMenuDisplay(frame);
        }
    }
}