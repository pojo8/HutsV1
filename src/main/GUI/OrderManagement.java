package main.GUI;

import main.QuerySet.QueryUtilities;
import main.QuerySet.Query_OrderList;
import main.QuerySet.Query_ProductList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OrderManagement {
    private JPanel OrdeMangementPanel;
    private JButton menuButton;
    private JButton helpButton;
    private JPanel orderWindow;
    private JButton commitButton;
    private JTextField amountSoldTextField;
    private JTable orderTable;
    private JButton refreshButton;

    //private JTable table1;



    public OrderManagement() throws IOException, SQLException {

        String[] columns = {"Product name","amount remaining", "Product ID"};

        //FIXME change inital data to fetch databse values
     //  Object [][] initData = {{"","" ,""}};


        ArrayList vals = Query_ProductList.selectProductAndLot();

        List  AllSqlResults =QueryUtilities.threeResultssplit(vals);


        Object [][] initData = new Object[0][];

        DefaultTableModel model = new DefaultTableModel(initData, columns);

        for( int i= 0; i< AllSqlResults.size(); i++){
            List  val = (List) AllSqlResults.get(i);


            Object[] prodValues = new Object[3];
                prodValues[0] = (Object) val.get(0);

                prodValues[1] = (Object) val.get(1);
                prodValues[2] = (Object) val.get(2);
                model.addRow(prodValues);


        }


        orderTable.setModel(model);

                //orderList.setModel(listModel);

        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //  int va = orderTable.getSelectedRow();
               int selctedRow = orderTable.getSelectedRow();
               int prod_id = Integer.valueOf((String)orderTable.getModel().getValueAt(selctedRow,2));
               int decreaseBy = Integer.parseInt(amountSoldTextField.getText());
               //FixMe make table un editable
                try {
                    Query_OrderList.decreaseCurrentLotBy(prod_id, decreaseBy);

                }


                catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally{

                    // To update the table values

                    ArrayList vals = null;
                    try {
                        vals = Query_ProductList.selectProductAndLot();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    List  AllSqlResults =QueryUtilities.threeResultssplit(vals);



                    Object [][] initData = new Object[0][];

                    DefaultTableModel model = new DefaultTableModel(initData, columns);

                    for( int i= 0; i< AllSqlResults.size(); i++){
                        List  val = (List) AllSqlResults.get(i);


                        Object[] prodValues = new Object[3];
                        prodValues[0] = (Object) val.get(0);

                        prodValues[1] = (Object) val.get(1);
                        prodValues[2] = (Object) val.get(2);
                        model.addRow(prodValues);


                    }

                    orderTable.setModel(model);

                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

    }
    public static void createAndShowGui() {
        JFrame frame = new JFrame("Order Gate ");

        try {
            UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
            // Is your UI already created? So you will have to update the component-tree
            // of your current frame (or actually all of them...)
            SwingUtilities.updateComponentTreeUI(frame);
        } catch(Exception e) { /* Most of the time you're just going to ignore it */ }

        frame.setSize(10000, 10000);

        Vector<String> temp = new Vector<String>();

         ListModel listModel = new DefaultListModel();



        frame.setResizable(true);
        try {
            frame.setContentPane(new OrderManagement().OrdeMangementPanel);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {

                    new OrderManagement().createAndShowGui();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
