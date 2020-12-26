// Interface dévelopée par Cédric Plantet

package view;

import model.Enigma;
import model.Room;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameCreation extends JPanel implements ActionListener {

    private BorderLayout mainLayout;
    private BorderLayout titleLayout;
    private BorderLayout centerLayout;
    private BorderLayout enigmaInfoLayout;

    private GridBagLayout buttonLayout;

    private GridBagLayout gridInfo;
    private GridBagLayout gridEnigma;

    private GridBagConstraints gbcEnigma;

    private GridLayout grid;
    private GridLayout gridHint;


    private JScrollPane scrollStory;
    private JScrollPane scrollEnigmas;


    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JPanel titleNamePanel;
    private JPanel defaultScorePanel;
    private JPanel pointsPanel;

    private JPanel centerPanel;
    private JPanel storyPanel;
    private JPanel enigmasPanel;
    private JPanel newPanel;
    private JPanel answerPanel;
    private JPanel hint1Panel;
    private JPanel hint2Panel;
    private JPanel hint3Panel;



    private JButton exitButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton newButton;

    private JLabel windowName;

    private JTextField initialScore;
    private JTextField points;
    private JTextField title;

    private GlobalFrame frame;

    public ArrayList<Enigma> getEnigma;



    public GameCreation(GlobalFrame frame,int roomNumber){

        this.frame = frame;

        getEnigma = new ArrayList<Enigma>();
        getEnigma.add(new Enigma(1,1,"Enigme1","","",0,"",0,"",0));
        getEnigma.add(new Enigma(2,1,"Enig2","","",0,"",0,"",0));

        mainLayout = new BorderLayout(10,10);
        titleLayout= new BorderLayout(10,10);
        enigmaInfoLayout = new BorderLayout(10,10);
        centerLayout = new BorderLayout(30,30);

        GridBagConstraints gbcTitle = new GridBagConstraints();
        GridBagConstraints gbcScores = new GridBagConstraints();
        gbcEnigma = new GridBagConstraints();

        gridEnigma = new GridBagLayout();
        buttonLayout = new GridBagLayout();
        gridInfo = new GridBagLayout();

        grid = new GridLayout(1,0);
        gridHint = new GridLayout(2,0,10,10);

        centerPanel = new JPanel();
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        infoPanel = new JPanel();
        titleNamePanel = new JPanel();
        defaultScorePanel = new JPanel();
        pointsPanel = new JPanel();
        newPanel = new JPanel();
        enigmasPanel = new JPanel();

        saveButton = new JButton("Enregistrer");
        exitButton = new JButton("Quitter");
        deleteButton = new JButton("Supprimer");
        newButton = new JButton();

        title = new JTextField("Titre",45);
        initialScore = new JTextField("Score Initial",7);
        points = new JTextField("Points (Si désiré)",7);

        windowName = new JLabel("MJ - Création/Modification de Jeux",JLabel.CENTER);


        //Center

        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newPanel.add(newButton);

        centerPanel.setLayout(centerLayout);

        enigmasPanel.setLayout(gridEnigma);

        scrollEnigmas = new JScrollPane(enigmasPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollEnigmas.setBorder(BorderFactory.createLineBorder(Color.red));
        scrollEnigmas.getVerticalScrollBar().setUnitIncrement(20);
        scrollEnigmas.setBorder(BorderFactory.createEmptyBorder());


        centerPanel.add(newPanel,BorderLayout.SOUTH);



        /* Chargement des énigmes */

        this.createList();


        newButton.setAction(new AbstractAction("Nouvelle Enigme") {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(getEnigma.size());

                Enigma enigme = new Enigma(getEnigma.size()+1,1,"Enigme","Réponse","Indice 1",-1,"Indice 2",-1,"Indice 3",-1);

                ajoutListeEnigma(enigme,getEnigma);
                createList();


            }
        });

        /* Setup Marges */

        Border listPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);


        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        centerPanel.add(scrollEnigmas,BorderLayout.CENTER);


        title.setBorder(BorderFactory.createEmptyBorder());
        title.setHorizontalAlignment(JTextField.CENTER);
        initialScore.setBorder(BorderFactory.createEmptyBorder());
        initialScore.setHorizontalAlignment(JTextField.CENTER);
        points.setBorder(BorderFactory.createEmptyBorder());
        points.setHorizontalAlignment(JTextField.CENTER);

        gbcTitle.weightx = 2;
        gbcTitle.insets = new Insets(0,0,0,20);
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;


        gbcScores.weightx =1;
        gbcScores.insets = new Insets(0,10,0,0);
        gbcScores.fill = GridBagConstraints.HORIZONTAL;

        titleNamePanel.setLayout(grid);
        defaultScorePanel.setLayout(grid);
        pointsPanel.setLayout(grid);

        titleNamePanel.add(title);
        defaultScorePanel.add(initialScore);
        pointsPanel.add(points);

        infoPanel.setLayout(gridInfo);
        infoPanel.add(titleNamePanel,gbcTitle);
        infoPanel.add(defaultScorePanel,gbcScores);
        infoPanel.add(pointsPanel,gbcScores);

        titleNamePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        defaultScorePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        pointsPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));

        // Boutons

        exitButton.setBackground(ColorPerso.azur);
        exitButton.setForeground(Color.white);
        exitButton.addActionListener(this);

        saveButton.setBackground(ColorPerso.vert);
        saveButton.setForeground(Color.white);

        deleteButton.setBackground(ColorPerso.rouge);
        deleteButton.setForeground(Color.white);
        deleteButton.addActionListener(this);

        buttonPanel.setLayout(buttonLayout);
        buttonPanel.add(exitButton,gbcScores);
        buttonPanel.add(saveButton,gbcScores);
        buttonPanel.add(deleteButton,gbcScores);

        windowName.setBorder(BorderFactory.createLineBorder(Color.black,2));

        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 0, 10);
        Border buttonsPadding = BorderFactory.createEmptyBorder(5,0,10,0);
        this.setBorder(mainPadding);
        buttonPanel.setBorder(buttonsPadding);

        titlePanel.setLayout(titleLayout);
        titlePanel.add(windowName,BorderLayout.NORTH);
        titlePanel.add(infoPanel,BorderLayout.SOUTH);


        this.setLayout(mainLayout);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(titlePanel,BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);


    }


    JPanel ajoutEnigme(Enigma enigme,GridBagConstraints gbcEnigma){

        /* Gridbag Constraints */

        gbcEnigma.gridy = GridBagConstraints.RELATIVE;
        System.out.println(gbcEnigma.gridy);
        gbcEnigma.fill = GridBagConstraints.HORIZONTAL;
        gbcEnigma.weightx = 1;
        gbcEnigma.gridx = 0;


        /* Ajout Panel */

        JPanel enigmaPan = new JPanel();

        JPanel storyPanel = new JPanel();
        JPanel infoEngimaPanel = new JPanel();
        JPanel answerPanel = new JPanel();
        JPanel hint1Panel = new JPanel();
        JPanel hint2Panel = new JPanel();
        JPanel hint3Panel = new JPanel();

        JTextArea story = new JTextArea(enigme.getText());
        story.setLineWrap(true);
        story.setWrapStyleWord(true);
        story.setMargin(new Insets(5,5,5,5));


        // Clue Components

        JTextField hint1 = new JTextField(enigme.getClue1());
        hint1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        JTextField hint2 = new JTextField(enigme.getClue2());
        hint2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        JTextField hint3 = new JTextField(enigme.getClue3());
        hint3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        // Answer Components

        JTextField answer = new JTextField(enigme.getAnswer());
        answer.setBorder(BorderFactory.createLineBorder(Color.black,2));

       // Timer Components


        JTextField time1;

        if (enigme.getTimer1()==-1){
            time1 = new JTextField("Timer 1");
        }
        else{
            time1 = new JTextField(enigme.getTimer1());
        }

        JTextField time2;

        if (enigme.getTimer2()==-1){
            time2 = new JTextField("Timer 2");
        }
        else{
            time2 = new JTextField(enigme.getTimer2());
        }

        JTextField time3;

        if (enigme.getTimer3()==-1){
            time3 = new JTextField("Timer 3");
        }
        else{
            time3 = new JTextField(enigme.getTimer3());
        }

        /* Layout PanelEnigme */


        GridLayout grille = new GridLayout(1,4,20,0);
        /* Construction Panel */



        storyPanel.add(story);
        storyPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));


        answerPanel.add(answer);

        answerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        hint1Panel.add(hint1);
        hint1Panel.add(time1);
        hint1Panel.setLayout(gridHint);
        time1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        hint2Panel.add(hint2);
        hint2Panel.add(time2);
        hint2Panel.setLayout(gridHint);
        time2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        hint3Panel.add(hint3);
        hint3Panel.add(time3);
        hint3Panel.setLayout(gridHint);
        time3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint3Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        infoEngimaPanel.add(answer);
        infoEngimaPanel.add(hint1Panel);
        infoEngimaPanel.add(hint2Panel);
        infoEngimaPanel.add(hint3Panel);
        infoEngimaPanel.setLayout(grille);

        scrollStory = new JScrollPane(story,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollStory.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        enigmaPan.setLayout(enigmaInfoLayout);
        enigmaPan.add(scrollStory,BorderLayout.CENTER);
        enigmaPan.add(infoEngimaPanel,BorderLayout.SOUTH);
        enigmaPan.setBorder(BorderFactory.createLineBorder(Color.green));


        return enigmaPan;


    }

    void ajoutListeEnigma(Enigma enigma,ArrayList<Enigma> getEnigma){

        getEnigma.add(enigma);
    }

    public void createList(){

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        for (int i = 0; i < getEnigma.size(); i++) {
            centerPanel.remove(newPanel);
            JPanel panelEnigme = ajoutEnigme(getEnigma.get(i), gbc);
            panelEnigme.setPreferredSize(new Dimension(centerPanel.getWidth()-45, 300));
            enigmasPanel.add(panelEnigme, gbc);
            centerPanel.add(newPanel, BorderLayout.SOUTH);
            centerPanel.revalidate();
            centerPanel.repaint();
        }

        centerPanel.revalidate();
        centerPanel.repaint();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== exitButton){
            frame.gameManagementDisplay(frame,frame.roomNumber);
        }
        if (e.getSource()==deleteButton){
            getEnigma.remove(getEnigma.size()-1);
            frame.revalidate();
            frame.repaint();
        }
    }
}
