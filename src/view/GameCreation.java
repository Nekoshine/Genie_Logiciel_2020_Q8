// Interface dévelopée par Cédric Plantet

package view;

import model.Enigma;
import model.Room;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GameCreation extends JPanel{

    private BorderLayout mainLayout;
    private BorderLayout titleLayout;
    private BorderLayout centerLayout;
    private BorderLayout enigmaInfoLayout;

    private GridBagLayout buttonLayout;

    private GridBagLayout gridInfo;
    private GridBagLayout gridEnigma;


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



    public GameCreation(GlobalFrame frame){

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
        GridBagConstraints gbcEnigma = new GridBagConstraints();



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


        gbcEnigma.insets = new Insets(7,15,30,30);

        centerPanel.add(newPanel,BorderLayout.SOUTH);



        /* Chargement des énigmes */

        for(int i = 0;i<getEnigma.size();i++) {
            ajoutEnigme(getEnigma.get(i), Color.black, gbcEnigma);
        }



        newButton.setAction(new AbstractAction("Nouvelle Enigme") {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(getEnigma.size());

                Enigma enigme = new Enigma(getEnigma.size()+1,1,"","","",0,"",0,"",0);

                /*

                // Gridbag Constraints

                gbcEnigma.gridy = enigme.getId()-1;
                gbcEnigma.fill = GridBagConstraints.HORIZONTAL;
                gbcEnigma.weightx = 1;
                gbcEnigma.gridx = 0;


                // Ajout Panel

                JPanel enigmaPan = new JPanel();

                JPanel storyPanel = new JPanel();
                JPanel infoEngimaPanel = new JPanel();
                JPanel answerPanel = new JPanel();
                JPanel hint1Panel = new JPanel();
                JPanel hint2Panel = new JPanel();
                JPanel hint3Panel = new JPanel();

                JTextArea story = new JTextArea("Enigme");
                JTextField hint1 = new JTextField("Indice 1");
                JTextField hint2 = new JTextField("Indice 2");
                JTextField hint3 = new JTextField("Indice 3");
                JTextField answer = new JTextField("Réponse");
                JTextField time1 = new JTextField("Timer");
                JTextField time2 = new JTextField("Timer");
                JTextField time3 = new JTextField("Timer");

                hint1.setAlignmentX(Component.CENTER_ALIGNMENT);
                hint2.setAlignmentX(Component.CENTER_ALIGNMENT);
                hint3.setAlignmentX(Component.CENTER_ALIGNMENT);
                time1.setAlignmentX(Component.CENTER_ALIGNMENT);
                time2.setAlignmentX(Component.CENTER_ALIGNMENT);
                time3.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Layout PanelEnigme


                GridLayout grille = new GridLayout(1,4,20,0);
                // Construction Panel

                story.setLineWrap(true);
                story.setWrapStyleWord(true);
                story.setMargin(new Insets(5,5,5,5));

                storyPanel.add(story);
                storyPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));


                answerPanel.add(answer);
                answer.setBorder(BorderFactory.createLineBorder(Color.black,2));
                answerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                hint1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                hint1Panel.add(hint1);
                hint1Panel.add(time1);
                hint1Panel.setLayout(gridHint);
                time1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                //hint1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                hint2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                hint2Panel.add(hint2);
                hint2Panel.add(time2);
                hint2Panel.setLayout(gridHint);
                time2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                //hint2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                hint3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
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
                scrollStory.setBorder(BorderFactory.createLineBorder(Color.black,2));

                enigmaPan.setLayout(enigmaInfoLayout);
                enigmaPan.add(scrollStory,BorderLayout.CENTER);
                enigmaPan.add(infoEngimaPanel,BorderLayout.SOUTH);



                enigmaPan.setPreferredSize(new Dimension(centerPanel.getWidth()-45,300));
                enigmaPan.setBounds(0,0+(enigme.getId()*300),centerPanel.getWidth()-45,300);

                enigmasPanel.add(enigmaPan,gbcEnigma);
                ajoutListeEnigma(enigme,getEnigma);
                centerPanel.revalidate();
                centerPanel.repaint();

                */
                ajoutListeEnigma(enigme,getEnigma);
                ajoutEnigme(enigme,Color.BLACK,gbcEnigma);


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
        saveButton.setBackground(ColorPerso.vert);
        saveButton.setForeground(Color.white);
        deleteButton.setBackground(ColorPerso.rouge);
        deleteButton.setForeground(Color.white);

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


    void ajoutEnigme(Enigma enigme,Color color,GridBagConstraints gbcEnigma){

        /* Gridbag Constraints */


        gbcEnigma.insets = new Insets(7,15,30,30);
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

        JTextArea story = new JTextArea("Enigme");
        JTextField hint1 = new JTextField("Indice 1");
        JTextField hint2 = new JTextField("Indice 2");
        JTextField hint3 = new JTextField("Indice 3");
        JTextField answer = new JTextField("Réponse");
        JTextField time1 = new JTextField("Timer");
        JTextField time2 = new JTextField("Timer");
        JTextField time3 = new JTextField("Timer");

        hint1.setAlignmentX(Component.CENTER_ALIGNMENT);
        hint2.setAlignmentX(Component.CENTER_ALIGNMENT);
        hint3.setAlignmentX(Component.CENTER_ALIGNMENT);
        time1.setAlignmentX(Component.CENTER_ALIGNMENT);
        time2.setAlignmentX(Component.CENTER_ALIGNMENT);
        time3.setAlignmentX(Component.CENTER_ALIGNMENT);

        /* Layout PanelEnigme */


        GridLayout grille = new GridLayout(1,4,20,0);
        /* Construction Panel */

        story.setLineWrap(true);
        story.setWrapStyleWord(true);
        story.setMargin(new Insets(5,5,5,5));

        storyPanel.add(story);
        storyPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));


        answerPanel.add(answer);
        answer.setBorder(BorderFactory.createLineBorder(Color.black,2));
        answerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hint1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint1Panel.add(hint1);
        hint1Panel.add(time1);
        hint1Panel.setLayout(gridHint);
        time1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hint2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint2Panel.add(hint2);
        hint2Panel.add(time2);
        hint2Panel.setLayout(gridHint);
        time2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hint3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
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
        scrollStory.setBorder(BorderFactory.createLineBorder(color,2));

        enigmaPan.setLayout(enigmaInfoLayout);
        enigmaPan.add(scrollStory,BorderLayout.CENTER);
        enigmaPan.add(infoEngimaPanel,BorderLayout.SOUTH);



        enigmaPan.setPreferredSize(new Dimension(centerPanel.getWidth()-45,300));
        enigmaPan.setBounds(0,0+(enigme.getId()*300),centerPanel.getWidth()-45,300);

        enigmasPanel.add(enigmaPan,gbcEnigma);
        centerPanel.revalidate();
        centerPanel.repaint();

        System.out.println("yo le rap");


    }

    void ajoutListeEnigma(Enigma enigma,ArrayList<Enigma> getEnigma){

        getEnigma.add(enigma);
    }
}
