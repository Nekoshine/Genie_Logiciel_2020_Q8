package view;

import Sockets.Admin;
import Sockets.Client;
import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import launcher.Main;
import model.Enigma;
import model.EnigmaList;
import model.Room;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.Normalizer;
import java.util.ArrayList;

public class PlayerManagement extends JPanel implements ActionListener{

    private JPanel currentStoryPanIn = new JPanel();
    private JPanel answersPanIn = new JPanel();

    private JButton helpButtonGM;
    private JButton buttonReturn;
    private JButton buttonHint1;
    private JButton buttonHint2;
    private JButton buttonHint3;

    public GlobalFrame frame;
    private boolean boolHint1;
    private boolean boolHint2;
    private boolean boolHint3;
    private final int riddleNb;

    private JTextArea currentStory;
    private JTextArea proposition;
    private JTextArea helpMessageGM;
    private EnigmaList currentRiddles;
    private JLabel title;
    private JLabel answers;

    private Room room;

    public PlayerManagement(GlobalFrame frame, Room room, int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed, boolean boolHint3Revealed){
        this.frame = frame;
        this.boolHint1 = boolHint1Revealed;
        this.boolHint2 = boolHint2Revealed;
        this.boolHint3 = boolHint3Revealed;
        this.room = DBRoom.getRooms(Main.idAdmin).findByID(room.getId());
        this.riddleNb=riddleNb;

        int width = (int) frame.windowSize.getWidth();
        int height = (int) frame.windowSize.getHeight();


        currentRiddles = DBEnigma.getEnigmas(gameNb); // la liste des énigmes du jeu

        helpButtonGM = new JButton("Envoyer");
        helpButtonGM.addActionListener(this);
        helpButtonGM.setBackground(Color.white);
        helpButtonGM.setForeground(Color.black);
        helpButtonGM.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        helpButtonGM.setOpaque(true);

        buttonReturn = new JButton("Retour");
        buttonReturn.addActionListener(this);
        buttonReturn.setBackground(ColorPerso.rouge);
        buttonReturn.setForeground(Color.white);
        buttonReturn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        buttonReturn.setOpaque(true);

        buttonHint1 = this.hintButton(1);
        buttonHint2 = this.hintButton(2);
        buttonHint3 = this.hintButton(3);

        title = new JLabel();
        title.setText(DBGame.getTitleGame(gameNb));

        JPanel titlePanIn = new JPanel();
        titlePanIn.setPreferredSize(new Dimension((int) ((width-40)*0.7),(int) ((height-90)*0.06)));
        titlePanIn.setBackground(Color.LIGHT_GRAY);
        titlePanIn.add(title, CENTER_ALIGNMENT);
        titlePanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel titlePan = new JPanel();
        titlePan.setBackground(ColorPerso.darkGray);
        titlePan.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
        titlePan.add(titlePanIn);
        JLabel timer = new JLabel();
        JPanel timerPanIn = new JPanel();
        timerPanIn.setPreferredSize(new Dimension((int)((width-40)*0.3),(int) ((height-90)*0.06)));
        timerPanIn.setBackground(Color.LIGHT_GRAY);
        timerPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        timerPanIn.add(timer);
        JPanel timerPan = new JPanel();
        timerPan.setBackground(ColorPerso.darkGray);
        timerPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        timerPan.add(timerPanIn);
        JPanel topPan = new JPanel();
        topPan.setBackground(ColorPerso.darkGray);
        topPan.setLayout(new BoxLayout(topPan, BoxLayout.LINE_AXIS));
        topPan.add(titlePan);
        topPan.add(timerPan);

        currentStory = new JTextArea();
        currentStory.setLineWrap(true);
        currentStory.setWrapStyleWord(true);
        currentStory.setBackground(Color.LIGHT_GRAY);
        currentStory.setFont(FontPerso.courierNew);
        currentStory.setEditable(false);
        currentStory.setPreferredSize(new Dimension(width-20,(height-90)*50/100-10));
        currentStory.setText((currentRiddles.getEnigma(riddleNb - 1)).getText());


        JScrollPane scrollCurrentStoryPanIn = new JScrollPane(currentStoryPanIn,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollCurrentStoryPanIn.setPreferredSize(new Dimension(width-20,(height-90)*50/100));
        scrollCurrentStoryPanIn.setBackground(Color.LIGHT_GRAY);
        currentStoryPanIn.setBackground(Color.LIGHT_GRAY);
        currentStoryPanIn.add(currentStory);
        scrollCurrentStoryPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel currentStoryPan = new JPanel();
        currentStoryPan.setBackground(ColorPerso.darkGray);
        currentStoryPan.setLayout(new FlowLayout(1));
        currentStoryPan.add(scrollCurrentStoryPanIn);
        //currentStoryPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        answers = new JLabel();
        answers.setText((currentRiddles.getEnigma(riddleNb -1)).getAnswer());

        proposition = new JTextArea("Réponses tentées jusqu'ici : \n");
        proposition.setBackground(Color.lightGray);
        proposition.setFont(FontPerso.Oxanimum);
        proposition.setEditable(false);

        afficheReponse();


        JScrollPane scrollAnswersPanIn = new JScrollPane(answersPanIn,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollAnswersPanIn.setPreferredSize(new Dimension((int) (width-20),(int) ((height-90)*0.10)));
        scrollAnswersPanIn.setBackground(Color.LIGHT_GRAY);
        answersPanIn.setBackground(Color.LIGHT_GRAY);
        answersPanIn.setLayout(new BoxLayout(answersPanIn, BoxLayout.PAGE_AXIS));
        answersPanIn.add(answers);
        answersPanIn.add(proposition);
        scrollAnswersPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel answersPan = new JPanel();
        answersPan.setBackground(ColorPerso.darkGray);
        answersPan.add(scrollAnswersPanIn);
        //answersPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        helpMessageGM = new JTextArea();
        helpMessageGM.setLineWrap(true);
        helpMessageGM.setWrapStyleWord(true);
        helpMessageGM.setFont(FontPerso.Oxanimum);
        helpMessageGM.setPreferredSize(new Dimension((int) (width-200-helpButtonGM.getWidth()), (int) ((height-150)*0.40)));
        JPanel helpMessageGMPan = new JPanel();
        helpMessageGMPan.setBackground(Color.LIGHT_GRAY);
        helpMessageGMPan.setLayout(new FlowLayout(1));
        helpMessageGMPan.add(helpMessageGM);
        JPanel helpButtonGMPanIn = new JPanel();
        helpButtonGMPanIn.setBackground(Color.LIGHT_GRAY);
        helpButtonGMPanIn.add(helpButtonGM);
        JPanel helpButtonGMPan = new JPanel();
        helpButtonGMPan.setBackground(Color.LIGHT_GRAY);
        helpButtonGMPan.add(helpButtonGMPanIn);
        helpButtonGMPan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JPanel helpGMPanIn = new JPanel();
        helpGMPanIn.setBackground(Color.LIGHT_GRAY);
        //helpGMPanIn.setPreferredSize(new Dimension((int) (width-20), (int) ((height-90)*0.40)));
        helpGMPanIn.setLayout(new BoxLayout(helpGMPanIn, BoxLayout.LINE_AXIS));
        helpGMPanIn.add(helpMessageGMPan);
        helpGMPanIn.add(helpButtonGMPan);
        helpGMPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel helpGMPan = new JPanel();
        helpGMPan.setBackground(ColorPerso.darkGray);
        helpGMPan.setLayout(new FlowLayout(1));
        helpGMPan.add(helpGMPanIn);
        //helpGMPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        //helpGMPan.setPreferredSize(new Dimension(width-20,(height-90)*40/100));

        JPanel buttonReturnPanIn = new JPanel();
        buttonReturnPanIn.setBackground(Color.LIGHT_GRAY);
        buttonReturnPanIn.add(buttonReturn);
        JPanel buttonReturnPan = new JPanel();
        buttonReturnPan.setBackground(Color.LIGHT_GRAY);
        buttonReturnPan.add(buttonReturnPanIn);
        buttonReturnPan.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
        JPanel buttonHint1PanIn = new JPanel();
        buttonHint1PanIn.setBackground(Color.LIGHT_GRAY);
        JTextArea hint1Text = new JTextArea();
        hint1Text.setLineWrap(true);
        hint1Text.setWrapStyleWord(true);
        hint1Text.setBackground(Color.LIGHT_GRAY);
        hint1Text.setFont(FontPerso.Oxanimum);
        hint1Text.setEditable(false);
        hint1Text.setText(currentRiddles.getEnigma(riddleNb-1).getClue1());
        JPanel hint1TextPan = new JPanel();
        hint1TextPan.setLayout(new FlowLayout(1));
        hint1TextPan.setBackground(Color.lightGray);
        hint1TextPan.add(hint1Text);
        JPanel hint1ButtonPan = new JPanel();
        hint1ButtonPan.setLayout(new FlowLayout(1));
        hint1ButtonPan.add(buttonHint1);
        hint1ButtonPan.setBackground(Color.lightGray);
        buttonHint1PanIn.setLayout(new BoxLayout(buttonHint1PanIn, BoxLayout.PAGE_AXIS));
        buttonHint1PanIn.add(hint1TextPan);
        buttonHint1PanIn.add(hint1ButtonPan);
        JPanel buttonHint1Pan = new JPanel();
        buttonHint1Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint1Pan.add(buttonHint1PanIn);
        buttonHint1Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        JPanel buttonHint2PanIn = new JPanel();
        buttonHint2PanIn.setBackground(Color.LIGHT_GRAY);
        JTextArea hint2Text = new JTextArea();
        hint2Text.setLineWrap(true);
        hint2Text.setWrapStyleWord(true);
        hint2Text.setBackground(Color.LIGHT_GRAY);
        hint2Text.setFont(FontPerso.Oxanimum);
        hint2Text.setEditable(false);
        hint2Text.setText(currentRiddles.getEnigma(riddleNb-1).getClue2());
        JPanel hint2TextPan = new JPanel();
        hint2TextPan.setLayout(new FlowLayout(1));
        hint2TextPan.setBackground(Color.lightGray);
        hint2TextPan.add(hint2Text);
        JPanel hint2ButtonPan = new JPanel();
        hint2ButtonPan.setLayout(new FlowLayout(1));
        hint2ButtonPan.add(buttonHint2);
        hint2ButtonPan.setBackground(Color.lightGray);
        buttonHint2PanIn.setLayout(new BoxLayout(buttonHint2PanIn, BoxLayout.PAGE_AXIS));
        buttonHint2PanIn.add(hint2TextPan);
        buttonHint2PanIn.add(hint2ButtonPan);
        JPanel buttonHint2Pan = new JPanel();
        buttonHint2Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint2Pan.add(buttonHint2PanIn);
        buttonHint2Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        JPanel buttonHint3PanIn = new JPanel();
        buttonHint3PanIn.setBackground(Color.LIGHT_GRAY);
        JTextArea hint3Text = new JTextArea();
        hint3Text.setLineWrap(true);
        hint3Text.setWrapStyleWord(true);
        hint3Text.setBackground(Color.LIGHT_GRAY);
        hint3Text.setFont(FontPerso.Oxanimum);
        hint3Text.setEditable(false);
        hint3Text.setText(currentRiddles.getEnigma(riddleNb-1).getClue3());
        JPanel hint3TextPan = new JPanel();
        hint3TextPan.setLayout(new FlowLayout(1));
        hint3TextPan.setBackground(Color.lightGray);
        hint3TextPan.add(hint3Text);
        JPanel hint3ButtonPan = new JPanel();
        hint3ButtonPan.setLayout(new FlowLayout(1));
        hint3ButtonPan.add(buttonHint3);
        hint3ButtonPan.setBackground(Color.lightGray);
        buttonHint3PanIn.setLayout(new BoxLayout(buttonHint3PanIn, BoxLayout.PAGE_AXIS));
        buttonHint3PanIn.add(hint3TextPan);
        buttonHint3PanIn.add(hint3ButtonPan);
        JPanel buttonHint3Pan = new JPanel();
        buttonHint3Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint3Pan.add(buttonHint3PanIn);
        buttonHint3Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        JPanel bottomPan = new JPanel();
        bottomPan.setBackground(ColorPerso.darkGray);
        //bottomPan.setPreferredSize(new Dimension((int) (width-20),(int) ((height)*0.40)));
        JPanel bottomPanIn = new JPanel();
        bottomPanIn.setBackground(Color.LIGHT_GRAY);
        bottomPanIn.setLayout(new BoxLayout(bottomPanIn, BoxLayout.LINE_AXIS));
        bottomPanIn.add(buttonReturnPan);
        bottomPanIn.add(buttonHint1Pan);
        bottomPanIn.add(buttonHint2Pan);
        bottomPanIn.add(buttonHint3Pan);
        bottomPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        bottomPan.add(bottomPanIn);


        Border mainEdge = BorderFactory.createEmptyBorder(10,10,10,10);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.revalidate();
                frame.repaint();
            }
        });

        this.setBorder(mainEdge);
        this.setBackground(ColorPerso.darkGray);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbcglobal = new GridBagConstraints();

        gbcglobal.weighty = 1;
        gbcglobal.weightx = 2;
        gbcglobal.gridy = 0;
        gbcglobal.fill = GridBagConstraints.BOTH;
        this.add(topPan, gbcglobal);

        gbcglobal.weighty = 3;
        gbcglobal.gridy = 1;
        this.add(scrollCurrentStoryPanIn, gbcglobal);

        gbcglobal.weighty = 3;
        gbcglobal.gridy = 2;
        gbcglobal.insets = new Insets(20, 0,0,0);
        this.add(scrollAnswersPanIn, gbcglobal);

        gbcglobal.weighty = 3;
        gbcglobal.gridy = 3;
        this.add(helpGMPanIn, gbcglobal);

        gbcglobal.weighty = 1;
        gbcglobal.weightx = 1;
        gbcglobal.gridy = 4;
        gbcglobal.fill = GridBagConstraints.VERTICAL;
        this.add(bottomPanIn, gbcglobal);

        Runnable runnable = () -> {
            while (true) {
                String reponse = Admin.recepAnswerJoueur(room.getUserInside());
                if(frame.getContentPane() instanceof PlayerManagement){
                    if(removeAccents(reponse).equals(removeAccents(answers.getText().toLowerCase()))) {
                        frame.playerManagementDisplay(frame, room, gameNb, riddleNb + 1, false, false, false);
                    }
                }
                proposition.append("\n"+reponse);
            }
        };
        Thread ta = new Thread(runnable);
        ta.start();
    }

  /*  public static PlayerManagement getInstance(GlobalFrame frame,Room room,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed,
                                               boolean boolHint3Revealed){
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new PlayerManagement(frame,room,gameNb, riddleNb, boolHint1Revealed, boolHint2Revealed,
                    boolHint3Revealed);
                }
            }
        }
        else {
            INSTANCE.frame=frame;
            INSTANCE.boolHint1 = boolHint1Revealed;
            INSTANCE.boolHint2 = boolHint2Revealed;
            INSTANCE.boolHint3 = boolHint3Revealed;

            INSTANCE.title.setText(DBGame.getTitleGame(gameNb));
            if(room!=null) {
                System.out.println("id de ladmin "+Main.idAdmin);
                System.out.println("room id"+room.getId());
                INSTANCE.room = DBRoom.getRooms(Main.idAdmin).findByID(room.getId());
            }
            INSTANCE.currentRiddles = DBEnigma.getEnigmas(gameNb);
            INSTANCE.currentStory.setText((INSTANCE.currentRiddles.getEnigma(riddleNb - 1)).getText());
            INSTANCE.answers.setText((INSTANCE.currentRiddles.getEnigma(riddleNb -1)).getAnswer());

            if(INSTANCE.boolHint1){
                INSTANCE.buttonHint1.setText("Indice 1 déjà affiché");
                INSTANCE.buttonHint1.setBackground(Color.lightGray);
            }DBEnigma.getEnigmas(room.getGame().getId()).getEnigma(enigmalistflag).getClue1()
            else{
                INSTANCE.buttonHint1.setText("Afficher l'indice 1");
                INSTANCE.buttonHint1.setBackground(Color.white);
            }
            if(INSTANCE.boolHint2){
                INSTANCE.buttonHint2.setText("Indice 2 déjà affiché");
                INSTANCE.buttonHint2.setBackground(Color.lightGray);
            }
            else{
                INSTANCE.buttonHint2.setText("Afficher l'indice 2");
                INSTANCE.buttonHint2.setBackground(Color.white);
            }
            if(INSTANCE.boolHint3){
                INSTANCE.buttonHint3.setText("Indice 3 déjà affiché");
                INSTANCE.buttonHint3.setBackground(Color.lightGray);
            }
            else{
                INSTANCE.buttonHint3.setText("Afficher l'indice 3");
                INSTANCE.buttonHint3.setBackground(Color.white);
            }
            INSTANCE.helpMessageGM.setText("");
        }



        return INSTANCE;
    }*/

    private JButton hintButton(int i){
        JButton button = null;
        boolean revealed = false;
        if(i==1){
            revealed=boolHint1;
        }
        else if (i==2){
            revealed=boolHint2;
        }
        else{
            revealed=boolHint3;
        }
        if(!revealed){
            if(room == null){
                button = new JButton("Envoyer l'indice " + i);
            }else{
                if(i==1) {
                    button = new JButton("Afficher indice 1 ");
                }else if(i==2){
                    button = new JButton("Afficher indice 2 ");
                }else if(i==3){
                    button = new JButton("Afficher indice 3 ");
                }
            }
            button.addActionListener(this);
            button.setBackground(Color.white);
        }else{
            button.setBackground(Color.lightGray);
        }
        button.setForeground(Color.black);
        button.setOpaque(true);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==helpButtonGM){
            //Baptiste fonction envoyer le message aux joueurs
            String messageFromMJ = helpMessageGM.getText();
            System.out.println(messageFromMJ);
            Admin.envoiAideJoueur(messageFromMJ,0,room.getUserInside());
            System.out.println("send");
        }else if(e.getSource() == buttonReturn){
            frame.roomManagementDisplay(frame);
        }else if(e.getSource()==buttonHint1){
            boolHint1=true;
            buttonHint1.setText("Afficher indice 1");
            Admin.envoiAideJoueur(null,1,room.getUserInside());
            buttonHint1.setBackground(Color.DARK_GRAY);
        }else if(e.getSource()==buttonHint2){
            boolHint2=true;
            buttonHint2.setText("Afficher indice 2 ");
            Admin.envoiAideJoueur(null,2,room.getUserInside());
            buttonHint2.setBackground(Color.DARK_GRAY);
        }else if(e.getSource()==buttonHint3){
            boolHint3=true;
            buttonHint3.setText("Afficher indice 3 ");
            Admin.envoiAideJoueur(null,3,room.getUserInside());
            buttonHint3.setBackground(Color.DARK_GRAY);
        }
    }

    public static String removeAccents(String text) {
        return text == null ? null :
                Normalizer.normalize(text, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public void afficheReponse(){
        for (int i=0;i<Main.answers.get(room.getUserInside()).size();i++){
            proposition.append("\n"+Main.answers.get(room.getUserInside()).get(i));
        }
    }
}