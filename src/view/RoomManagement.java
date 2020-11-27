package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class RoomManagement extends JPanel{

    private BorderLayout mainLayout;
    private JPanel listPanel;
    private JPanel titlePanel;
    private JPanel decoPanel;
    private BoxLayout listLayout;
    private JScrollPane scrollPane;
    private FlowLayout decoLayout;

    private JButton returnButton;
    private JButton newButton;

    private JLabel titre;

    private Dimension windowSize;


    public RoomManagement(){

        /* Déclaration JPanel / JScrollPane */

        listPanel = new JPanel();
        titlePanel = new JPanel();
        decoPanel = new JPanel();
        scrollPane = new JScrollPane(listPanel);

        /* Déclaration Layouts */

        windowSize = new Dimension();
        mainLayout = new BorderLayout(10,10);
        decoLayout = new FlowLayout(FlowLayout.LEFT);
        listLayout = new BoxLayout(listPanel,BoxLayout.Y_AXIS);

        /* Déclaration Boutons */

        returnButton = new JButton("Retour");
        returnButton.setBackground(Color.red);
        returnButton.setForeground(Color.black);



        newButton = new JButton();
        newButton.setBackground(Color.GRAY);
        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.setAction(new AbstractAction("Nouvelle Salle") {

            @Override
            public void actionPerformed(ActionEvent e) {

                listPanel.remove(newButton);
                JPanel panelSalle = new JPanel();
                panelSalle.setSize(new Dimension(700,40));
                panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                listPanel.add(panelSalle);
                listPanel.add(newButton);
                listPanel.revalidate();
                listPanel.repaint();

            }
        });

        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border listPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(mainPadding);
        listPanel.setBorder(listPadding);

        this.setLayout(mainLayout);
        listPanel.setLayout(listLayout);
        listPanel.add(Box.createRigidArea(new Dimension(0,10)));
        listPanel.add(newButton);


        titre = new JLabel("MJ - Gestion des salles");
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        listPanel.setBorder(BorderFactory.createLineBorder(Color.black));


        titlePanel.add(titre);

        decoPanel.setLayout(decoLayout);
        decoPanel.add(returnButton);
        //listPanel.add(scrollPane);

        this.add(listPanel,mainLayout.CENTER);
        this.add(titlePanel,mainLayout.NORTH);
        this.add(decoPanel,mainLayout.SOUTH);

        this.setVisible(true);


    }



}
