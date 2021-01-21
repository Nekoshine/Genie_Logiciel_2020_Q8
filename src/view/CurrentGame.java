package view;

import database.DBEnigma;
import database.DBGame;
import launcher.Main;
import database.DBRoom;
import model.*;
import view.style.ColorPerso;
import view.style.FontPerso;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;


public class CurrentGame extends JPanel implements ActionListener, WindowListener{

    private JPanel firstRawPanel;
    private JPanel secondRawPanel;
    private JPanel secondRawPanelIn1;
    private JPanel secondRawPanelIn2;
    private JPanel currentEnigmaPanel;
    private JPanel currentEnigmaPanelOUT;
    private JPanel fourthRawPanel;
    private JPanel oldEnigmaPanel;
    private JPanel oldEnigmaPanelOUT;
    private JPanel hintRawPanel;
    private JPanel hintContainer1;
    private JPanel hintContainer2;
    private JPanel hintContainer3;
    private JPanel hintMJContainer;
    private JPanelImage componentPanel;

    private JScrollPane currentEnigmaScroll;
    private JScrollPane oldEnigmaScroll;
    private JScrollPane hint1Scroll;
    private JScrollPane hint2Scroll;
    private JScrollPane hint3Scroll;

    private JTextField answerTextField;


    private JButton confirmButton;
    private JButton hint1Button;
    private JButton hint2Button;
    private JButton hint3Button;

    private JTextArea currentEnigmaTextArea;
    private JTextArea oldEnigmaTextArea;
    private JTextArea hint1TextArea;
    private JTextArea hint2TextArea;
    private JTextArea hint3TextArea;
    private JTextArea hintMJTextArea;


    private JLabel titleLabel;
    private JLabel countdownLabel;
    private JLabel window;

    private GridBagConstraints gbcfirstraw;
    private GridBagConstraints gbcglobal;

    private int enigmalistflag = 0;
    private int timerclue1;
    private int timerclue2;
    private int timerclue3;
    private boolean ishint2present;
    private boolean ishint3present;
    private boolean isused1 = false;
    private boolean isused2 = false;
    private boolean isused3 = false;

    private EnigmaList allEnigmas;

    public ImageIcon imageIconValide;
    public  ImageIcon imageIconRefus;
    private Game game;

    private Timer countdowngame;
    private Timer timeonenigma;

    private ActionListener countdowngameTask;
    private ActionListener timeonenigmaTask;

    private int countdownvalue = 3600;
    private int enigmatimevalue = 0;

    private Room room;

    private GlobalFrame frame;

    Dimension windowSize;


    public CurrentGame (GlobalFrame frame, Game partiechoisie,int idRoom){

        room = Main.ListRoom.findByID(idRoom);

        imageIconValide = new ImageIcon(new ImageIcon("./src/view/image/valide.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        imageIconRefus = new ImageIcon(new ImageIcon("./src/view/image/refus.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        windowSize = frame.getSize();
        this.frame = frame;

        // yann commente stp

        if(partiechoisie.getId()!=-1) {
            allEnigmas = DBEnigma.getEnigmas(partiechoisie.getId());
            game = DBGame.getGame(partiechoisie.getId());
        }
        else {
            game=new Game(-1,"",0,0,0,true,"");
            allEnigmas = new EnigmaList();
            allEnigmas.addEnigma(new Enigma(1,1,"","","",1,"",1,"",3));
        }

        //recuperaion du timer de l'indice 1 et des autres si indices présents

        timerclue1 = allEnigmas.getEnigma(enigmalistflag).getTimer1();

        ishint2present = !(allEnigmas.getEnigma(enigmalistflag).getClue2().isEmpty());
        if(ishint2present){timerclue2 = allEnigmas.getEnigma(enigmalistflag).getTimer2();}

        ishint3present = !(allEnigmas.getEnigma(enigmalistflag).getClue3().isEmpty());
        if(ishint3present){timerclue3 = allEnigmas.getEnigma(enigmalistflag).getTimer3();};

        System.out.println(ishint2present);
        System.out.println(allEnigmas.getEnigma(enigmalistflag).getClue2());

        System.out.println(ishint3present);
        System.out.println(allEnigmas.getEnigma(enigmalistflag).getClue3());
        // nom de la fenetre

        firstRawPanel = new JPanel();
        firstRawPanel.setLayout(new FlowLayout());

        window = new JLabel("Joueur - Fenêtre de jeu");

        firstRawPanel.add(window);
        firstRawPanel.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));



        //titre + timer

        secondRawPanel = new JPanel();
        secondRawPanel.setLayout(new GridBagLayout());

        gbcfirstraw = new GridBagConstraints();
        gbcfirstraw.weightx = 1;
        gbcfirstraw.fill = GridBagConstraints.HORIZONTAL;


        secondRawPanelIn1 = new JPanel();
        secondRawPanelIn1.setLayout(new FlowLayout(FlowLayout.LEFT));
        secondRawPanelIn1.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.95),30));
        secondRawPanelIn1.setOpaque(false);

        titleLabel = new JLabel(partiechoisie.getTitre());
        titleLabel.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.60),20));
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));



        countdownLabel = new JLabel("Timer",SwingConstants.CENTER);
        countdownLabel.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.10),20));
        countdownLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        secondRawPanelIn2 = new JPanel();
        secondRawPanelIn2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        secondRawPanelIn2.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.20),30));
        secondRawPanelIn2.setOpaque(false);


        secondRawPanelIn1.add(titleLabel);
        secondRawPanelIn2.add(countdownLabel);
        secondRawPanel.add(secondRawPanelIn1,gbcfirstraw);
        secondRawPanel.add(secondRawPanelIn2,gbcfirstraw);
        secondRawPanel.setBorder(BorderFactory.createEmptyBorder(5,50,5,50));
        secondRawPanel.setOpaque(false);

        //Enigme en cours

        currentEnigmaTextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getText());
        currentEnigmaTextArea.setLineWrap(true);
        currentEnigmaTextArea.setWrapStyleWord(true);
        currentEnigmaTextArea.setEditable(false);
        currentEnigmaTextArea.setFont(FontPerso.Oxanimum);
        currentEnigmaScroll = new JScrollPane(currentEnigmaTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        currentEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        currentEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());


        currentEnigmaPanel = new JPanel();
        currentEnigmaPanel.setLayout(new GridLayout(1,1));
        currentEnigmaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentEnigmaPanel.add(currentEnigmaScroll);

        currentEnigmaPanelOUT = new JPanel();
        currentEnigmaPanelOUT.setLayout(new GridLayout(1,1));
        currentEnigmaPanelOUT.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.95),(int)((float) windowSize.getHeight()*0.25)));
        currentEnigmaPanelOUT.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        currentEnigmaPanelOUT.add(currentEnigmaPanel);
        currentEnigmaPanelOUT.setOpaque(false);

        // Champs de reponse + bouton de confirmation

        answerTextField = new JTextField("your answer");
        answerTextField.setColumns(30);
        answerTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        answerTextField.setFont(FontPerso.Oxanimum);
        //answerTextField.setMaximumSize(new Dimension(60,15));

        confirmButton = new JButton("Confirmer");
        confirmButton.setBackground(ColorPerso.vert);
        //confirmButton.setPreferredSize(new Dimension(30,15));
        confirmButton.addActionListener(this);

        fourthRawPanel = new JPanel();
        fourthRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        fourthRawPanel.setSize(new Dimension((int)((float) windowSize.getWidth()*0.95),10));
        fourthRawPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        fourthRawPanel.add(answerTextField);
        fourthRawPanel.add(confirmButton);
        fourthRawPanel.setOpaque(false);

        //anciennes enigmes

        oldEnigmaTextArea = new JTextArea();
        oldEnigmaTextArea.setLineWrap(true);
        oldEnigmaTextArea.setWrapStyleWord(true);
        oldEnigmaTextArea.setEditable(false);
        oldEnigmaTextArea.setFont(FontPerso.Oxanimum);
        oldEnigmaScroll = new JScrollPane(oldEnigmaTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        oldEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        oldEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());

        oldEnigmaPanel = new JPanel();
        oldEnigmaPanel.setLayout(new GridLayout(1,1));
        oldEnigmaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        oldEnigmaPanel.add(oldEnigmaScroll);

        oldEnigmaPanelOUT = new JPanel();
        oldEnigmaPanelOUT.setLayout(new GridLayout(1,1));
        oldEnigmaPanelOUT.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.95),(int)((float) windowSize.getHeight()*0.25)));
        oldEnigmaPanelOUT.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        oldEnigmaPanelOUT.add(oldEnigmaPanel);
        oldEnigmaPanelOUT.setOpaque(false);

        // indices

        hint1Button = new JButton("Indice n°1");
        hint1Button.setEnabled(false);
        hint1Button.setBackground(Color.GRAY);
        hint1Button.addActionListener(this);
        hint1Button.setFont(FontPerso.Oxanimum);

        hintContainer1 = new JPanel();
        hintContainer1.setLayout(new GridLayout(1,1));
        hintContainer1.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.25),(int)((float) windowSize.getHeight()*0.15)));
        hintContainer1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hintContainer1.add(hint1Button);

        hint2Button = new JButton("Indice n°2");
        hint2Button.setEnabled(false);
        hint2Button.setBackground(ColorPerso.GRAY);
        hint2Button.addActionListener(this);
        hint2Button.setFont(FontPerso.Oxanimum);

        hintContainer2 = new JPanel();
        hintContainer2.setLayout(new GridLayout(1,1));
        hintContainer2.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.25),(int)((float) windowSize.getHeight()*0.15)));
        hintContainer2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hintContainer2.add(hint2Button);

        hint3Button = new JButton("Indice n°3");
        hint3Button.setEnabled(false);
        hint3Button.setBackground(ColorPerso.GRAY);
        hint3Button.addActionListener(this);
        hint3Button.setFont(FontPerso.Oxanimum);

        hintContainer3 = new JPanel();
        hintContainer3.setLayout(new GridLayout(1,1));
        hintContainer3.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.25),(int)((float) windowSize.getHeight()*0.15)));
        hintContainer3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hintContainer3.add(hint3Button);

        hintMJTextArea = new JTextArea("aide envoyée par le MJ");
        hintMJTextArea.setLineWrap(true);
        hintMJTextArea.setWrapStyleWord(true);
        hintMJTextArea.setEditable(false);
        hintMJTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintMJContainer = new JPanel();
        hintMJContainer.setLayout(new GridLayout(1,1));
        hintMJContainer.setPreferredSize(new Dimension((int)((float)windowSize.getWidth()*0.35),(int)((float) windowSize.getHeight()*0.15)));
        hintMJContainer.add(hintMJTextArea);

        hintRawPanel = new JPanel();
        hintRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
        hintRawPanel.setOpaque(false);

        hintRawPanel.setPreferredSize(new Dimension((int)((float) windowSize.getWidth()*0.95),(int)((float) windowSize.getHeight()*0.18)));
        hintRawPanel.add(hintContainer1);
        hintRawPanel.add(hintContainer2);
        hintRawPanel.add(hintContainer3);
        hintRawPanel.add(hintMJContainer);

        // intégration dans la fenetre principale

        componentPanel = new JPanelImage("./src/view/image/FondPrincipal.png",windowSize);
        componentPanel.setLayout(new GridBagLayout());
        componentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        gbcglobal = new GridBagConstraints();
        gbcglobal.weighty = 1;
        gbcglobal.weightx = 1;
        gbcglobal.gridy = 0;
        gbcglobal.fill = GridBagConstraints.BOTH;

        componentPanel.add(secondRawPanel,gbcglobal);

        gbcglobal.weighty = 5;
        gbcglobal.gridy = 1;
        gbcglobal.insets = new Insets(0,10,0,10);

        componentPanel.add(currentEnigmaPanelOUT,gbcglobal);

        gbcglobal.weighty = 1;
        gbcglobal.gridy = 2;
        gbcglobal.insets = new Insets(10,0,0,0);
        gbcglobal.fill = GridBagConstraints.HORIZONTAL;

        componentPanel.add(fourthRawPanel,gbcglobal);

        gbcglobal.weighty = 5;
        gbcglobal.gridy = 3;
        gbcglobal.insets = new Insets(10,10,0,10);
        gbcglobal.fill = GridBagConstraints.BOTH;

        componentPanel.add(oldEnigmaPanelOUT,gbcglobal);

        gbcglobal.weighty = 4;
        gbcglobal.gridy = 4;
        gbcglobal.insets = new Insets(20,0,0,0);

        componentPanel.add(hintRawPanel,gbcglobal);
        componentPanel.setMaximumSize(new Dimension((int)((float) windowSize.getWidth()*0.95),(int)((float) windowSize.getHeight()*0.75)));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                windowSize = Main.frame.getSize();
                frame.revalidate();
                frame.repaint();
            }
        });


        //implementation des timers

        //timer global

        countdowngameTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                int seconde = 0;
                int minute = 0;
                countdownvalue--;
                seconde = countdownvalue%60;
                minute = (countdownvalue - seconde)/60;
                if (seconde < 10){countdownLabel.setText(minute+":0"+seconde);/* rafraichir le label */
                    countdownLabel.setForeground(Color.RED);
                }
                else {
                    countdownLabel.setText(minute + ":" + seconde);/* rafraichir le label */
                    countdownLabel.setForeground(Color.RED);
                }
                frame.revalidate();
                frame.repaint();

                if (countdownvalue == 0){countdowngame.stop();}
            }
        };

        countdowngame = new Timer(1000,countdowngameTask);

        //timer indice

        timeonenigmaTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {

                if (enigmatimevalue == timerclue1) {
                    hint1Button.setEnabled(true);
                }

                if (ishint2present) {
                    if (enigmatimevalue == timerclue2) {
                        hint2Button.setEnabled(true);
                    }
                }

                if (ishint3present) {
                    if (enigmatimevalue == timerclue3) {
                        hint3Button.setEnabled(true);
                    }
                }

                enigmatimevalue ++;
                System.out.println(enigmatimevalue);
                }
        };

        timeonenigma = new Timer(1000, timeonenigmaTask);




        Main.frame.addWindowListener(this);
        this.setLayout(new BorderLayout(10,20));
        this.setBorder(BorderFactory.createEmptyBorder(20,20,40,20));
        this.add(firstRawPanel,BorderLayout.NORTH);
        this.add(componentPanel,BorderLayout.CENTER);
        this.setBackground(ColorPerso.darkGray);
        countdowngame.start();
        timeonenigma.start();

    }



    //listener des boutons

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == hint1Button){
            hint1TextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getClue1());
            hint1TextArea.setLineWrap(true);
            hint1TextArea.setWrapStyleWord(true);
            hint1TextArea.setEditable(false);
            hint1TextArea.setFont(FontPerso.Oxanimum);
            hint1Scroll = new JScrollPane(hint1TextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            hint1Scroll.getVerticalScrollBar().setUnitIncrement(20);
            hint1Scroll.setBorder(BorderFactory.createEmptyBorder());
            hintContainer1.remove(hint1Button);
            hintContainer1.add(hint1Scroll);
            isused1 = true;
            frame.revalidate();
            frame.repaint();
        }

        if (event.getSource() == hint2Button){
            if(ishint2present){
                hint2TextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getClue2());
                hint2TextArea.setLineWrap(true);
                hint2TextArea.setWrapStyleWord(true);
                hint2TextArea.setEditable(false);
                hint2TextArea.setFont(FontPerso.Oxanimum);
                hint2Scroll = new JScrollPane(hint2TextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                hint2Scroll.getVerticalScrollBar().setUnitIncrement(20);
                hint2Scroll.setBorder(BorderFactory.createEmptyBorder());
                hintContainer2.remove(hint2Button);
                hintContainer2.add(hint2Scroll);
                isused2 = true;

                frame.revalidate();
                frame.repaint();
            }
        }

        if (event.getSource() == hint3Button){
            if(ishint3present) {
                hint3TextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getClue3());
                hint3TextArea.setLineWrap(true);
                hint3TextArea.setWrapStyleWord(true);
                hint3TextArea.setEditable(false);
                hint3TextArea.setFont(FontPerso.Oxanimum);
                hint3Scroll = new JScrollPane(hint3TextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                hint3Scroll.getVerticalScrollBar().setUnitIncrement(20);
                hint3Scroll.setBorder(BorderFactory.createEmptyBorder());
                hintContainer3.remove(hint3Button);
                hintContainer3.add(hint3Scroll);
                isused3 = true;
                frame.revalidate();
                frame.repaint();
            }
        }

        if (event.getSource() == confirmButton){
            String answer = answerTextField.getText().toLowerCase();
            String[] possibility = allEnigmas.getEnigma(enigmalistflag).getAnswers();
            boolean find = false;
            for(int i=0;i<possibility.length;i++){
                if(answer.equals(possibility[i].toLowerCase())){
                    find = true;
                }
            }
            //si bonne reponse
            if (find) {
                //si ce n'etait pas la derniere
                if (enigmalistflag < allEnigmas.getSize() - 1) {

                    //maj des champs relatifs aux enigmes

                    oldEnigmaTextArea.append(allEnigmas.getEnigma(enigmalistflag).getText());
                    oldEnigmaTextArea.append("\n");
                    oldEnigmaTextArea.append(answerTextField.getText());
                    oldEnigmaTextArea.append("\n");
                    answerTextField.setText("");
                    enigmalistflag++;
                    currentEnigmaPanel.remove(currentEnigmaScroll);
                    currentEnigmaTextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getText());
                    currentEnigmaTextArea.setLineWrap(true);
                    currentEnigmaTextArea.setWrapStyleWord(true);
                    currentEnigmaTextArea.setEditable(false);

                    currentEnigmaScroll = new JScrollPane(currentEnigmaTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    currentEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
                    currentEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());

                    currentEnigmaPanel.add(currentEnigmaScroll);

                    //maj hint 1
                    if(isused1){
                    hintContainer1.remove(hint1Scroll);
                    hintContainer1.add(hint1Button);}
                    isused1 = false;
                    timerclue1 = allEnigmas.getEnigma(enigmalistflag).getTimer1();
                    hint1Button.setEnabled(false);


                    //maj hint 2

                    if (ishint2present) {
                        if(isused2) {
                            hintContainer2.remove(hint2Scroll);
                            hintContainer2.add(hint2Button);
                        }
                    }
                    isused2 = false;
                    ishint2present = !(allEnigmas.getEnigma(enigmalistflag).getClue2().isEmpty());
                    if (ishint2present) {
                        hint2Button.setEnabled(false);
                        timerclue2 = allEnigmas.getEnigma(enigmalistflag).getTimer2();
                    } else {
                        hint3Button.setEnabled(false);
                    }

                    //maj hint 3

                    if (ishint3present) {
                        if (isused3) {
                            hintContainer3.remove(hint3Scroll);
                            hintContainer3.add(hint3Button);
                        }
                    }
                    isused3 = false;
                    ishint3present = !(allEnigmas.getEnigma(enigmalistflag).getClue3().isEmpty());
                    System.out.print(ishint3present);
                    if (ishint3present) {
                        hint3Button.setEnabled(false);
                        timerclue3 = allEnigmas.getEnigma(enigmalistflag).getTimer3();
                    } else {
                        hint3Button.setEnabled(false);
                    }

                    timeonenigma.stop();
                    enigmatimevalue = 0;
                    timeonenigma.start();

                    frame.revalidate();
                    frame.repaint();
                }
                //si derniere enigme
                else{JOptionPane.showMessageDialog(frame, "Vous avez réussi !!!!", "Bravo !", JOptionPane.WARNING_MESSAGE,imageIconValide);
                    frame.insideRoom = false;
                    room.setUserInside(-1);
                    DBRoom.majRoom(room.getId(),room.getGame().getId(),room.getCompetitive(),room.getUserInside());
                    frame.connectionMenuDisplay(frame);
                }

            }
            //si mauvaise reponse
            else{JOptionPane.showMessageDialog(frame, "Ce n'est pas la bonne reponse", "Raté !", JOptionPane.WARNING_MESSAGE,imageIconRefus);
            }
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        room.setUserInside(-1);
        DBRoom.majRoom(room.getId(),room.getGame().getId(),room.getCompetitive(),room.getUserInside());

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
