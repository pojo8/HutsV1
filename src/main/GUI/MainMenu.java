package main.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JPanel MenuPanel;
    private JButton reportsButton;
    private JButton editOrderButton;
    private JButton orderManagementButton;
    private JButton orderEntryButton;
    private JSplitPane DashBoard;
    private JButton helpButton;
    private JPanel title_image;
    private JSplitPane buttonPane;

    public MainMenu() {
        orderEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OrderEntry.createAndShowGui();
            }
        });


        orderManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OrderManagement.createAndShowGui();
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ReportGenration.createAndShowGui();
            }
        });
    }

    public static  void createAndShowGui(){
        JFrame frame = new JFrame("Order Gate ");

        frame.setSize(10000, 10000);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        frame.setResizable(true);
        frame.setContentPane(new MainMenu().MenuPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {

                    new MainMenu().createAndShowGui();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
