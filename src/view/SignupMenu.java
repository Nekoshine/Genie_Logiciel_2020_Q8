//Codé par alan

package view;


import database.DBUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SignupMenu extends JPanel implements ActionListener {

    private JPanel logincontainer;
    private JPanel passwordcontainer;
    private JPanel keycontainer;
    private JPanel backcontainer;

    private JButton confirm;
    private JButton back;

    private JTextField idtextfiled;
    private JTextField keytextfield;
    private JPasswordField passwordtextfield;

    private JLabel id;
    private JLabel password;
    private JLabel key;

    private GlobalFrame frame;

    public SignupMenu(GlobalFrame frame) {

        this.frame = frame;

        //creation de la partie login


        logincontainer = new JPanel();
        logincontainer.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));

        id = new JLabel("Identifiant :");
        idtextfiled = new JTextField();
        idtextfiled.setColumns(30);

        logincontainer.add(id);
        logincontainer.add(idtextfiled);

        //creation de la partie motdepasse

        passwordcontainer = new JPanel();
        passwordcontainer.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        password = new JLabel("Mot de passe :");
        passwordtextfield = new JPasswordField();
        passwordtextfield.setColumns(30);

        passwordcontainer.add(password);
        passwordcontainer.add(passwordtextfield);

        //creation de la partie clé admin

        keycontainer = new JPanel();
        keycontainer.setLayout(new FlowLayout(FlowLayout.CENTER,25,0));
        key = new JLabel("Clé (compte admin) :");
        keytextfield = new JTextField();
        keytextfield.setColumns(25);

        keycontainer.add(key);
        keycontainer.add(keytextfield);

        //creation du bouton de confirmation

        confirm = new JButton("Confirmation");
        confirm.addActionListener(this);
        confirm.setBackground(ColorPerso.vert);

        //creation du bouton de retour

        back = new JButton("Retour");
        back.addActionListener(this);
        back.setBackground(ColorPerso.rouge);
        back.setForeground(Color.WHITE);

        backcontainer = new JPanel();
        backcontainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        backcontainer.add(back);

        //ajout des composants

        this.add(Box.createRigidArea(new Dimension(0, 150)));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        logincontainer.setMaximumSize(new Dimension(800,50));
        this.add(logincontainer);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        passwordcontainer.setMaximumSize(new Dimension(800,30));
        this.add(passwordcontainer);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        //key.setMaximumSize(new Dimension(1000,30));
        this.add(keycontainer);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(confirm);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(backcontainer);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == confirm){
            String idinput = idtextfiled.getText();
            String mdpinput = passwordtextfield.getText();
            String cleinmput = keytextfield.getText();

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
        else if (event.getSource() == back){
            frame.connectionMenuDisplay(frame);
        }
    }
}