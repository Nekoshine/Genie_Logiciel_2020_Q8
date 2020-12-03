package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class RoomManagement extends JPanel{


    private JPanel listPanel;
    private JPanel titlePanel;
    private JPanel decoPanel;
    private JPanel newButtonPanel;
    private JPanel roomPanel;

    private GridBagLayout listLayout;
    private JScrollPane scrollPane;
    private FlowLayout decoLayout;
    private BorderLayout mainLayout;
    private BorderLayout centerLayout;

    private JButton returnButton;
    private JButton newButton;

    private JLabel titre;

    private Dimension windowSize;


    public RoomManagement(){

        /* Déclaration JPanel / JScrollPane */

        listPanel = new JPanel();
        roomPanel = new JPanel();
        titlePanel = new JPanel();
        decoPanel = new JPanel();
        newButtonPanel = new JPanel();
        scrollPane = new JScrollPane(listPanel);

        /* Déclaration Layouts */

        windowSize = new Dimension();
        mainLayout = new BorderLayout(10,10);
        centerLayout = new BorderLayout(4,4);
        decoLayout = new FlowLayout(FlowLayout.LEFT);
        listLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;



        /* Déclaration Boutons */

        returnButton = new JButton("Retour");
        returnButton.setBackground(Color.red);
        returnButton.setForeground(Color.black);



        newButton = new JButton();
        newButton.setBackground(Color.GRAY);
        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        /* Ajout d'une nouvelle salle */

        newButton.setAction(new AbstractAction("Nouvelle Salle") {

            @Override
            public void actionPerformed(ActionEvent e) {

                listPanel.remove(newButtonPanel);
                JPanel panelSalle = new JPanel();
                panelSalle.setSize(new Dimension(roomPanel.getWidth(),roomPanel.getHeight()/4));
                panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                roomPanel.add(panelSalle);
                listPanel.add(newButtonPanel,BorderLayout.SOUTH);
                listPanel.revalidate();
                listPanel.repaint();

            }
        });



        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border listPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(mainPadding);
        listPanel.setBorder(listPadding);

        newButtonPanel.add(newButton);

        this.setLayout(mainLayout);
        listPanel.setLayout(centerLayout);
        listPanel.add(roomPanel,BorderLayout.CENTER);
        listPanel.add(newButtonPanel,BorderLayout.SOUTH);


        newButtonPanel.add(newButton);


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
