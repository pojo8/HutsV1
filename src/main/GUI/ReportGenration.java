package main.GUI;

import main.SMTP.Email_Utilities;
import main.reports.ReportGenerator;

import javax.mail.Message;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import static main.SMTP.SendReport.createQREmail;

public class ReportGenration {
    private JPanel ReportGui;
    private JButton menuButton;
    private JButton helpButton;
    private JButton generateButton;
    private JComboBox orders;
    private JTextPane reportContents;
    private JRadioButton emailReportRadioButton;
    private JButton addToReportButton;
    private JButton clearReportButton;
    private JFileChooser chooser;

    public ReportGenration() {
        addToReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        clearReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Message message = null;
                try {
                    message = createQREmail(ReportGenerator.writeToFile());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    Email_Utilities.transportEmail(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        emailReportRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    public static void createAndShowGui() {
        JFrame frame = new JFrame("Order Gate ");



        frame.setSize(10000, 10000);



        frame.setResizable(true);
        frame.setContentPane(new ReportGenration().ReportGui);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {

                    new ReportGenration().createAndShowGui();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

