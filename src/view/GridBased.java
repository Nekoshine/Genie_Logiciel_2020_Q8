package src.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class GridBased extends JFrame {
    private static final long serialVersionUID = -680006681909001923L;

    private JPanel contentPane;

    private JButton[][] grid = new JButton[6][5];

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GridBased frame = new GridBased();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GridBased() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                doSize();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        setupGrid();
    }


    /**
     * This method is called only once on the creation of the
     * panel.  It goes through all of the cells in the grid and
     * adds the JButton.
     */
    private void setupGrid(){
        for(int i=0;i<grid.length;i++){
            for(int k=0;k<grid[i].length;k++){
                grid[i][k] = new JButton("["+i+"]["+k+"]");
                getContentPane().add(grid[i][k]);
            }
        }

        doSize();
    }

    /**
     * This method is called once after the creation of the grid, and every
     * time that the window is resized;
     */
    private void doSize(){
        for(int i=0;i<grid.length;i++){
            for(int k=0;k<grid[i].length;k++){

                int width = contentPane.getWidth()/grid.length;
                int height = contentPane.getHeight()/grid[0].length;

                int x = width*i;
                int y = height*k;

                grid[i][k].setBounds(x, y, width, height);
            }
        }
    }

}