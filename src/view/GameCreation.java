// Interface dévelopée par Cédric Plantet

package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class GameCreation extends JPanel{

    private BorderLayout mainLayout;
    private BorderLayout titleLayout;

    private GridBagLayout buttonLayout;

    private GridBagLayout gridInfo;
    private GridLayout grid;
    private GridLayout gridHint;

    private JScrollPane scrollStory;
    private JScrollPane scrollEnigmas;

    private JPanel centerPanel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JPanel titleNamePanel;
    private JPanel defaultScorePanel;
    private JPanel pointsPanel;

    private JPanel newPanel;


    private JButton exitButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton newButton;

    private JLabel windowName;

    private JTextField initialScore;
    private JTextField points;
    private JTextField title;

    private JTextField story;
    private JTextField hint1;
    private JTextField hint2;
    private JTextField hint3;
    private JTextField answer;
    private JTextField time1;
    private JTextField time2;
    private JTextField time3;

    private GlobalFrame frame;


    public GameCreation(GlobalFrame frame){

        this.frame = frame;

        mainLayout = new BorderLayout(10,10);
        titleLayout= new BorderLayout(10,10);
        buttonLayout = new GridBagLayout();

        gridInfo = new GridBagLayout();
        GridBagConstraints gbcTitle = new GridBagConstraints();
        GridBagConstraints gbcScores = new GridBagConstraints();

        grid = new GridLayout(1,0);
        gridHint = new GridLayout(2,0);

        centerPanel = new JPanel();
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        infoPanel = new JPanel();
        titleNamePanel = new JPanel();
        defaultScorePanel = new JPanel();
        pointsPanel = new JPanel();

        saveButton = new JButton("Enregistrer");
        exitButton = new JButton("Quitter");
        deleteButton = new JButton("Supprimer");

        title = new JTextField("Titre");
        initialScore = new JTextField("Score Initial");
        points = new JTextField("Points (Si désiré)");

        story = new JTextField("Enigme");
        hint1 = new JTextField("Indice 1");
        hint2 = new JTextField("Indice 2");
        hint3 = new JTextField("Indice 3");
        time1 = new JTextField("Timer");
        time2 = new JTextField("Timer");
        time3 = new JTextField("Timer");
        answer = new JTextField("Réponse");

        windowName = new JLabel("MJ - Création/Modification de Jeux",JLabel.CENTER);

        scrollStory = new JScrollPane();
        scrollEnigmas = new JScrollPane();


        //Center






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
}
