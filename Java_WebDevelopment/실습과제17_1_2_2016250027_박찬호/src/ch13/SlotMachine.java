package ch13_3;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SlotMachine implements ActionListener{
    JLabel[] labels= new JLabel[3];
    int[] numbers = new int[3];
    JLabel label = new JLabel("000");
    JLabel label2 = new JLabel("2016250027 박찬호");
    public static void main(String[] args) {
        SlotMachine gui = new SlotMachine();
        gui.go();
    }
    public void go() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        JButton button = new JButton("spin");

        panel.setLayout(null);
        Font bigfont = new Font("serif", Font.BOLD, 100);
        for(int i = 0 ; i<3 ; i++) {

            labels[i] = new JLabel(""+numbers[i]);

            labels[i].setFont(bigfont);
            labels[i].setSize(100, 100);
            labels[i].setLocation(100+100*i, 20);
            panel.add(labels[i]);
        }
        label2.setFont(new Font("serif", Font.BOLD, 30));
        label2.setSize(350,80);
        label2.setLocation(100, 300);
        panel.add(label2);

        label.setFont(new Font("serif", Font.BOLD, 30));
        label.setSize(350,80);
        label.setLocation(100, 250);
        panel.add(label);

        button.setSize(250, 50);
        button.setLocation(100, 150);
        panel.add(button);
        button.addActionListener(this);

        frame.setSize(500,400);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    public void actionPerformed(ActionEvent ev) {
        for(int i = 0 ; i <3 ; i++) {
            numbers[i] = (int)(Math.random()*10);
            labels[i].setText(""+numbers[i]);

        }
        if (numbers[0] == numbers[1] && numbers[0] ==numbers[2] ){
            label.setText("100");
        }else{
            label.setText("0");
        }
    }
}