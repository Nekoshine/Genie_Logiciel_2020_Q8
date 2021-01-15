//Codé par alan

package view;


import database.DBUser;
import view.style.ColorPerso;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SignupMenu extends JPanel implements ActionListener, MouseListener, KeyListener {
  
  private JButton confirm;
  private JButton back;
  
  private JTextField idtextfiled;
  private JTextField keytextfield;
  private JPasswordField passwordtextfield;
  
  private GlobalFrame frame;
  
  private String cleAdmin = "JeSuisLaCleSecurisee";
  SignupMenu(GlobalFrame frame) {
    
    this.frame = frame;
    
    //creation de la partie login
    
    
    JPanel logincontainer = new JPanel();
    logincontainer.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
    
    JLabel id = new JLabel("Identifiant :");
    idtextfiled = new JTextField();
    idtextfiled.setColumns(30);
    idtextfiled.addKeyListener(this);
    
    logincontainer.add(id);
    logincontainer.add(idtextfiled);
    
    //creation de la partie motdepasse
    
    JPanel passwordcontainer = new JPanel();
    passwordcontainer.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
    JLabel password = new JLabel("Mot de passe :");
    passwordtextfield = new JPasswordField();
    passwordtextfield.setColumns(30);
    passwordtextfield.addKeyListener(this);
    
    passwordcontainer.add(password);
    passwordcontainer.add(passwordtextfield);
    
    //creation de la partie clé admin
    
    JPanel keycontainer = new JPanel();
    keycontainer.setLayout(new FlowLayout(FlowLayout.CENTER,25,0));
    JLabel key = new JLabel("Clé (compte admin) :");
    keytextfield = new JTextField();
    keytextfield.setColumns(25);
    keytextfield.addKeyListener(this);
    
    keycontainer.add(key);
    keycontainer.add(keytextfield);
    
    //creation du bouton de confirmation
    
    confirm = new JButton("Confirmation");
    confirm.addActionListener(this);
    confirm.addMouseListener(this);
    confirm.setBackground(ColorPerso.vert);
    
    //creation du bouton de retour
    
    back = new JButton("Retour");
    back.addActionListener(this);
    back.addMouseListener(this);
    back.setBackground(ColorPerso.rouge);
    back.setForeground(Color.WHITE);
    
    JPanel backcontainer = new JPanel();
    backcontainer.setLayout(new FlowLayout(FlowLayout.LEADING));
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

  private void signUp(String idinput, String mdpinput, String cleinmput) {
    if (idinput.isEmpty() || mdpinput.isEmpty() ){
      Toolkit.getDefaultToolkit().beep();
      JOptionPane.showMessageDialog(frame,"Un ou plusieurs champs n'ont pas été remplis","Informations incomplètes", JOptionPane.WARNING_MESSAGE);
    }
    if(cleinmput.isEmpty()){
      if (DBUser.insertUser(idinput, mdpinput,false)) {
        frame.connectionMenuDisplay(frame);
      } else {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(frame, "L'identifiant demandé n'est pas disponible", "Attention", JOptionPane.WARNING_MESSAGE);
      }
    } else if(cleinmput.equals(cleAdmin)){
      if (DBUser.insertUser(idinput, mdpinput,true)) {
        frame.connectionMenuDisplay(frame);
      } else {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(frame, "L'identifiant demandé n'est pas disponible", "Attention", JOptionPane.WARNING_MESSAGE);
      }
    } else {
      Toolkit.getDefaultToolkit().beep();
      JOptionPane.showMessageDialog(frame, "Soit l'identifiant demandé n'est pas disponible , soit la clé fournie est incorrecte", "Attention", JOptionPane.WARNING_MESSAGE);
    }
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == confirm){
      String idinput = idtextfiled.getText();
      String mdpinput = String.valueOf(passwordtextfield.getPassword());
      String cleinmput = keytextfield.getText();
      signUp(idinput,mdpinput,cleinmput);
    }
    else if (e.getSource() == back){
      frame.connectionMenuDisplay(frame);
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
          if(e.getSource()==back){
            back.setBackground(ColorPerso.rougeHoover);
          }
          else if(e.getSource()==confirm){
            confirm.setBackground(ColorPerso.vertHoover);
          }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
          if(e.getSource()==back){
            back.setBackground(ColorPerso.rouge);
          }
          else if(e.getSource()==confirm){
            confirm.setBackground(ColorPerso.vert);
          }
        }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key=e.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
      String idinput = idtextfiled.getText();
      String mdpinput = String.valueOf(passwordtextfield.getPassword());
      String cleinmput = keytextfield.getText();
      signUp(idinput,mdpinput,cleinmput);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
      