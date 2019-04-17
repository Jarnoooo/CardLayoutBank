package javaapplication33;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class JavaApplication33 {

    public static void main(String[] args) {
        
              
        final int psaldo = 0;
        final String SaldoPersoon = "42";

        final String card1Text = "Card 1";
        final String card2Text = "Card 2";
        final String card3Text = "Card 3";
        final String card4Text = "Card 4";
        final String card5Text = "Card 5";
        final String card6Text = "Card 6";
        final String cardBon = "cardBox";
        final String saldo = "Saldo";

        final JPanel cards;

        final String Exit = "Exit";
        final String SnelMenu = "Snelmenu";
        final String Last = "Last";
        final String bon = "Bon";
        final String Sald = "Saldo";

        /*!!!!!!!!!!!!!!!!!!!!!!BUTTONS!!!!!!!!!!!!!!!*/
        JButton a = new JButton("Welkom bij KU Bank \n klik hier om verder te gaan");
        JButton info = new JButton("Kies hier wat u wil gaan doen...");
        JButton op = new JButton("Opnemen");
        // JButton st = new JButton("Storten");
        JButton sne = new JButton("Snelmenu");
        JButton saldocheck = new JButton("Saldo Check");

        JButton bo = new JButton("Bon printen");

        JButton saldoo = new JButton("Te weinig saldo");

        JButton opn = new JButton("£20");
        JButton opne = new JButton("£50");
        JButton opnee = new JButton("£100");

        JButton saldoP = new JButton(SaldoPersoon);

        JButton opge = new JButton("U kunt uw geld uit de automaat nemen!");
        /*!!!!!!!!!!!!!!!!PANELS!!!!!!!!!!!!!*/

        JPanel card1 = new JPanel();
        JPanel infoo = new JPanel();
        JPanel opnemen = new JPanel();
        JPanel SnelMenuu = new JPanel();
        JPanel bonn = new JPanel();
        JPanel saldooo = new JPanel();

        /*!!!!!!!!!!!!!!Buttons toevoegen!!!!!!!!!!!!!!!!!*/
        card1.add(a);

        infoo.add(info);
//        infoo.add(st);
        infoo.add(op);
        infoo.add(sne);
        infoo.add(saldocheck);

        opnemen.add(opn);
        opnemen.add(opne);
        opnemen.add(opnee);

        SnelMenuu.add(opn);
        SnelMenuu.add(opne);
        SnelMenuu.add(opnee);

        bonn.add(opge);

        saldooo.add(saldoP);
        saldooo.add(saldoo);

        /*!!!!!!!!!!!!!!Cards!!!!!!!!!!!!!!*/
        cards = new JPanel(new java.awt.CardLayout());

        cards.add(card1, card1Text);
        cards.add(infoo, card2Text);
        cards.add(opnemen, card3Text);
        cards.add(SnelMenuu, card4Text);
        cards.add(bonn, card5Text);
        cards.add(saldoo, card6Text);
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());

        /*!!!!!!!!!!!!!!ActionListeners!!!!!!!!!!*/
        a.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
            }
        });
        op.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
                cl.next(cards);
            }
        });
        sne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
                cl.next(cards);
            }
        });
        saldocheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
                cl.next(cards);
                cl.next(cards);
                cl.next(cards);
                // if functie om te bepalen of er genoeg geld op de bank staat
            }
        });
        opn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
                
                    
                try {
//                    cl.next(cards);
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Na de try");
                //cl.first(cards);
            }
});
         ComponentListener s = new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
                
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        };
 
        opnee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cl.next(cards);
            }
        });

        JFrame frame = new JFrame("Bank");
        class ControlActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd.equals(Exit)) {
            cl.first(cards);
        } else if (cmd.equals(SnelMenu)) {
//                    cl.next(cards);

//            } else if (cmd.equals(LAST)) {
//                cl.last(cards);
//            }
        }
    }
}
/*!!!!!!!!!!!!!FRAME!!!!!!!!!!!!*/
ControlActionListener cal = new ControlActionListener();

        JButton btn1 = new JButton("Exit");
        btn1.setActionCommand(Exit);
        btn1.addActionListener(cal);

        JButton btn3 = new JButton("SnelMenu");
        btn3.setActionCommand(SnelMenu);
        btn3.addActionListener(cal);

        JPanel controlButtons = new JPanel();
        controlButtons.add(btn1);
        controlButtons.add(btn3);

        Container pane = frame.getContentPane();
        pane.add(cards, BorderLayout.CENTER);
        pane.add(controlButtons, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
