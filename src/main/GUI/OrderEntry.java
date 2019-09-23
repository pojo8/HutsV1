package main.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import main.GUI.ErrorPanes.*;

import main.QuerySet.*;

public class OrderEntry extends JFrame {
    private JPanel UiPanel;
    private JButton submitListButton;
    private JTextField productNameTextField;
    private JButton mainMenu;
    private JButton helpButton;
    private JTextField unitCurrency;
    private JTextField unitPrice;
    private JTextArea dateOrderedTextArea;
    private JTextArea OrderSize;
    private JTextArea vendorEmail;
    private JTextArea vendorTriggerLevel;
    private JTextField notifyAtTextField;
    private JTextArea secondaryEmailTextArea;
    private JTextArea primaryEmailTextArea;
    private JSplitPane companyEmailPane;
    private JButton clearListButton;
    private JButton addToListButton;
    private JButton clearInputButton;
    private JLabel product_label;
    private JPanel tablePanel;
    private JTable inputList;

  //  public static int prod_if;


//    static final String[] columns = {"Product name", "Primary email", "Secondary email", "Notify users", "Price",
//            "Currency", "Order size", "Date Ordered", "Vendor Email", "Notify Vendor", "Product ID", "Order ID"};
//
//    static final Object[][] initialData = {{"", "", "", "", "", "", "", "", "", "", "", ""}};


    public OrderEntry() {

        String[] columns = {"Product name", "Primary email", "Secondary email", "Notify users", "Price",
                "Currency", "Order size", "Date Ordered", "Vendor Email", "Notify Vendor",
                "Product ID", "Order ID", "QR code"};

        Object[][] initialData = {{"", "", "", "", "",
                "", "", "", "", "",
                "", "", ""}};


        DefaultTableModel model = new DefaultTableModel(initialData, columns);

        inputList.setModel(model);

        for (int i = 0; i < 12; i++) {
            inputList.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        UiPanel.addComponentListener(new ComponentAdapter() {
        });
        UiPanel.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent containerEvent) {
                super.componentAdded(containerEvent);
            }
        });

        // Sets clears the variables from the text Areas
        clearInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                productNameTextField.setText("");
                primaryEmailTextArea.setText("");
                secondaryEmailTextArea.setText("");
                notifyAtTextField.setText("");
                dateOrderedTextArea.setText("");
                OrderSize.setText("");
                vendorEmail.setText("");
                vendorTriggerLevel.setText("");
                unitCurrency.setText("");
                unitPrice.setText("");

            }
        });
        addToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


//                int prod_id = QueryUtilities.createUniqueID();
//
//                int order_id= QueryUtilities.createUniqueID();
//
//
//
//                // in the event same number is achieved randomly
//                if(prod_id == order_id){
//                   order_id =  order_id + 42;
//                }

                // Gets the texts fields and converwts too the db values

                //FIXME add text field type restrictions
                // FixMe in the event that some fields are null
                try {

                    int prod_id = QueryUtilities.createUniqueID();

                    int order_id = QueryUtilities.createUniqueID();


                    // in the event same number is achieved randomly
                    if (prod_id == order_id) {
                        order_id = order_id + 42;
                    }


                    String productName = productNameTextField.getText();
                    String primaryEmail = primaryEmailTextArea.getText();
                    String secondaryEmail = secondaryEmailTextArea.getText();
                    int notifyUser = Integer.parseInt(notifyAtTextField.getText());
                    double price = Double.parseDouble(unitPrice.getText());
                    String currency = unitCurrency.getText();
                    int orderSize = Integer.parseInt(OrderSize.getText());
                    String dateOrdered = dateOrderedTextArea.getText();
                    String vendorMail = vendorEmail.getText();
                    String qrBlock = productNameTextField.getText() + prod_id + dateOrderedTextArea.getText();

                    //FixME need a check that price is a double type

                    System.out.println("The rpod id is:" + prod_id);

                    int notifyVendor = Integer.parseInt(vendorTriggerLevel.getText());

                    // int currentRows = inputList.getRowCount();


                    // Checks if the first row is not empty
                    if (inputList.getModel().getValueAt(0, 0).equals("")) {
                        Object[][] firstRow = {{productName, primaryEmail, secondaryEmail, notifyUser, price,
                                currency, orderSize, dateOrdered, vendorMail, notifyUser,
                                prod_id, order_id, qrBlock}};

                        inputList.setModel(new DefaultTableModel(firstRow, columns));

                        //Sets default column width
                        for (int i = 0; i < 12; i++) {
                            inputList.getColumnModel().getColumn(i).setPreferredWidth(100);
                        }

                        // clearInputButton.doClick();
                    } else if (!inputList.getModel().getValueAt(0, 0).equals("")) {


                        DefaultTableModel populatedModel = (DefaultTableModel) inputList.getModel();

                        populatedModel.addRow(new Object[]{productName, primaryEmail, secondaryEmail, notifyUser, price,
                                currency, orderSize, dateOrdered, vendorMail, notifyUser,
                                prod_id, order_id, qrBlock});


                    }

                } catch (NumberFormatException e) {
                    ErrorPanes.showNumberFieldError();


                }


            }
        });
        clearListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                DefaultTableModel populatedModel = (DefaultTableModel) inputList.getModel();


                int[] selectedRows = inputList.getSelectedRows();

                if (selectedRows.length > 0) {

                    for (int k = 0; k < selectedRows.length - 1; k++) {
                        populatedModel.removeRow(selectedRows[k]);
                    }
                } else {
                    // Throws the error message
                    ErrorPanes.noRowSelectedError();

                }
            }

        });
        submitListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                DefaultTableModel populatedModel = (DefaultTableModel) inputList.getModel();


                //{  list of rows:
                // [p nmae, p email, s mial, notify at, price, currency, lot size, date ordered, v email, notify at, prod_id, order_id]
                //[[Product name, Primary email, Secondary email , 5, 5, Currency, 5, date Ordered, Email address, 5, 429355, 13680]]


                int totalSize = populatedModel.getRowCount();

                for (int i = 0; i < totalSize; i++) {

                    Vector<Object> rowData = (Vector) populatedModel.getDataVector().elementAt(i);


                    //FixME Need a check for the inital order date
                    // FIxme helper method form date to sql
                    ProductList newProdList = new ProductList((Integer) rowData.get(10), (String) rowData.get(0), (String) rowData.get(7)
                            , (String) rowData.get(7), (double) rowData.get(4), (String) rowData.get(5));

                  //  System.out.println(newProdList);
                    try {
                        Query_ProductList.AddNewProduct(newProdList);

                        System.out.println("Contact has been added");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    //Fixme Need a notify vendor column
                    ContactList newContactList = new ContactList((Integer) rowData.get(10), (String) rowData.get(1),
                            (String) rowData.get(2), (String) rowData.get(8), (Integer) rowData.get(3));


                    // The Contact submit query

                    try {
                        Query_ContactList.AddNewcontacts(newContactList);

                        System.out.println("Contact has been added");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    // FixME need option for partial order entry
                    OrderList newOrder = new OrderList((Integer) rowData.get(11), (String) rowData.get(7), (Integer) rowData.get(10),
                            (Integer) rowData.get(6), (Integer) rowData.get(6), (String) rowData.get(12));


                    //The order List submit query
                    try {
                        Query_OrderList.AddNewOrder(newOrder);

                        System.out.println("Contact has been added");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        //FIXME add support for main menu and help pages
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }


        public static void createAndShowGui () {

//            String[] columns = {"Product name", "Primary email", "Secondary email", "Notify users", "Price",
//                    "Currency", "Order size", "Date Ordered", "Vendor Email", "Notify Vendor",
//                    "Product ID", "Order ID"};
//
//            Object[][] initialData = {{"", "", "", "", "",
//                    "", "", "", "", "",
//                    "", ""}};

            JFrame frame = new JFrame("Order Gate ");


//            DefaultTableModel model = new DefaultTableModel(initialData, columns);
            try {
                UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");

                // Is your UI already created? So you will have to update the component-tree
                // of your current frame (or actually all of them...)
                SwingUtilities.updateComponentTreeUI(frame);
            } catch(Exception e) { /* Most of the time you're just going to ignore it */ }

            // inputList.setModel(model);


            frame.setSize(10000, 10000);


            frame.setResizable(true);
            frame.setContentPane(new OrderEntry().UiPanel);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }


        public static void main (String[]args){

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                      //  DefaultTableModel model = new DefaultTableModel(initialData, columns);


                        new OrderEntry().createAndShowGui();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });



        }




}
