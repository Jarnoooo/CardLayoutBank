package cardlayout;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CardLayout {

//int geldOpnemen = 0; 
    public static void main(String[] args) {
        AccountsConnection acc = new AccountsConnection("jdbc:mysql://localhost:8306/mybank?useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam", "bank", "hunter2");
        
        int saldoDatabase;
        int geldOpnemen;
        String SaldoPersoon;
        String user = "1";
        String ownerId ;
        acc.queryDatabase("SELECT * FROM accounts WHERE accountid ="+user);
        
        saldoDatabase = acc.getCredit();
        ownerId = acc.getOwnerId();
        
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

        JButton a = new JButton("Welkom bij KU Bank \n klik hier om verder te gaan");
        JButton info = new JButton("Kies hier wat u wil gaan doen...");
        JButton op = new JButton("Opnemen");
        // JButton st = new JButton("Storten");
        JButton sne = new JButton("Snelmenu");
        JButton saldocheck = new JButton("Saldo Check");

        JButton bo = new JButton("Bon printen");

//        JButton saldoo = new JButton("Dus te weinig saldo");

        JButton opn = new JButton("£20");
        JButton opne = new JButton("£50");
        JButton opnee = new JButton("£100");

        JButton saldoP = new JButton("Uw saldo bij ons = "+saldoDatabase);

        JButton opge = new JButton("U kunt uw geld uit de automaat nemen!");
        /*!!!!!!!!!!!!!!!!PANELS!!!!!!!!!!!!!*/

        JPanel card1 = new JPanel();
        JPanel infoo = new JPanel();
        JPanel opnemen = new JPanel();
        JPanel SnelMenuu = new JPanel();
        JPanel bonn = new JPanel();
        JPanel saldooo = new JPanel();

        card1.add(a);

        infoo.add(info);

        infoo.add(op);
        infoo.add(saldocheck);

        opnemen.add(opn);
        opnemen.add(opne);
        opnemen.add(opnee);

        SnelMenuu.add(opn);
        SnelMenuu.add(opne);
        SnelMenuu.add(opnee);

        bonn.add(opge);

        saldooo.add(saldoP);
     

        card1.setBackground(new Color(255, 0, 0));

        /*!!!!!!!!!!!!!!!!!!Create the panel that contains the "cards"!!!!!!!!!!!!*/
        cards = new JPanel(new java.awt.CardLayout());

        cards.add(card1, card1Text);
        cards.add(infoo, card2Text);
        cards.add(opnemen, card3Text);
        cards.add(SnelMenuu, card4Text);
        cards.add(bonn, card5Text);
        cards.add(saldooo, card6Text);

        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());

        a.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
            }
        });
        op.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                cl.next(cards);
                cl.next(cards);
                
            }
        });
        opn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                cl.next(cards);
//             
                int geldOpnemen = 20;
                acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0,"+geldOpnemen+");");
                System.out.println(geldOpnemen);
            }
        });

        opne.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                cl.next(cards);
                
                int geldOpnemen = 50;
                System.out.println(geldOpnemen);
            }
        });
        opnee.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                cl.next(cards);
                cl.next(cards);
                cl.next(cards);
                
                int geldOpnemen = 100;
                System.out.println(geldOpnemen);
            }
        });
        saldocheck.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
               cl.next(cards);
               cl.next(cards);
               cl.next(cards);
               cl.next(cards);
            }
        });
        bonn.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }finally{
//                cl.next(cards);// om naar eerste scherm tegaan eventueel nog extra line toevoegen
//                cl.next(cards);
                // close connection 
                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }

        });
        saldooo.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }finally{
//                cl.next(cards);// om naar eerste scherm tegaan eventueel nog extra line toevoegen
                cl.next(cards);
                // close connection 
                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }

        });
        JFrame frame = new JFrame("Bank");

        class ControlActionListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {

                String cmd = e.getActionCommand();

                if (cmd.equals(Exit)) {
                    cl.first(cards);
                } else if (cmd.equals(SnelMenu)) {
                    cl.next(cards);
                    cl.next(cards);

                }
            }
        }
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
