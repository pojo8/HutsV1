package main.GUI;

import javax.swing.*;

public class ErrorPanes extends JFrame {

    public static void showNumberFieldError(){
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        JOptionPane.showMessageDialog(parent,
                "The values for 'Order size' and 'notify at' must be numbers","Number fields Error" ,JOptionPane.ERROR_MESSAGE);


    }

    public static void showDoubleField(){
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        JOptionPane.showMessageDialog(parent,
                "The values for 'The value for price must have a decimal place e.g 13.00","Price field Error" ,JOptionPane.ERROR_MESSAGE);


    }

    public static void showTextFieldError(){
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        JOptionPane.showMessageDialog(parent,
                "The values for fields other than 'Price', 'Order size' and 'notify at' must be text ","Text fields Error" ,JOptionPane.ERROR_MESSAGE);


    }

    public static void noRowSelectedError(){
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        JOptionPane.showMessageDialog(parent,
                "Unable to clear the rows as none have been selected","No row selected" ,JOptionPane.ERROR_MESSAGE);


    }


    public static void main (String [] args){
        showNumberFieldError();
        showTextFieldError();
    }


}
