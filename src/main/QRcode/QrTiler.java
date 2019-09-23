package main.QRcode;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class QrTiler extends JPanel {
    public JTable table_code;
    private JPanel panel;
    private JButton button;


    Image img;

    {
        try {
            img = ImageIO.read(new File("src/main/QRcode/TmpQr/testdelete/QrTemp0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ImageIcon icon = new ImageIcon(img);


    // creates the Qrimage icon to fill the Jtable
    private ImageIcon qrToImageIcon() throws IOException {
        try{
            Image qrIMage = ImageIO.read(new File("src/main/QRcode/TmpQr/testdelete/QrTemp0.png"));

            ImageIcon icon = new ImageIcon(qrIMage);

            return icon;
        } catch (IOException e) {

            System.out.println(" The Qr image cannot be loaded:\n");
            e.printStackTrace();
        }
        throw  new IOException("Qr code is not found");

    }

//
//    class tableDataModel extends Abstract


    public QrTiler() throws Exception {


        String[] columnNames = {"Qr", "QR"};
        Object[][] data =
                {
                        {icon, icon},
                        {icon, icon},
                        {icon, icon},
                };

//                DefaultTableModel model = new DefaultTableModel(data, columnNames)
//                {
//                    //  Returning the Class of each column will allow different
//                    //  renderers to be used based on Class
//                    public Class getColumnClass(int column)
//                    {
//                        return getValueAt(0, column).getClass();
//                    }
//                };
        JTable table = new JTable( data,columnNames );
        table.setRowHeight(200);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane( table );
        add( scrollPane );


    }




    //TODO add a print button
    public void printTable() throws PrinterException {

        MessageFormat header = new MessageFormat("Code print");

        MessageFormat footer = new MessageFormat("Page(0,number,integer");

        table_code.print(JTable.PrintMode.NORMAL,header, footer);
    }

    public static BufferedImage getScreen( Component c){
        BufferedImage bf = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
        c.paint(bf.getGraphics());
        return bf;
    }

    public static  void savePicture(Component c , String Filenme) throws IOException {
        BufferedImage b = getScreen(c);
        ImageIO.write(b,"png", new File("Filenme"));

    }

    private void makePanelImage(Component panel)
    {
        Dimension size = panel.getSize();
        BufferedImage image = new BufferedImage(
                size.width, size.height
                , BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        try
        {
            ImageIO.write(image, "png", new File("src/main/QRcode/TmpQr/testdelete/qrOutput2.png"));
            System.out.println("Panel saved as Image.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private  void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        main.QRcode.TmpQr.testdelete.QrTilerbackup newContentPane = null;
        try {
            newContentPane = new main.QRcode.TmpQr.testdelete.QrTilerbackup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        makePanelImage(newContentPane);
    }


    //TODO Like to the GUI action listener
    public static void main (String[]args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new main.QRcode.QrTiler().createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });






    }
}

