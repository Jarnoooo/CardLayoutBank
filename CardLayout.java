package cardlayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class CardLayout {
 
    public static void main(String[] args) {
 
        final String card1Text = "Card 1";
        final String card2Text = "Card 2";
        final String card3Text = "Card 3";
        final JPanel cards; //a panel that uses CardLayout
        // button commands
        final String Exit = "Exit";
        final String SnelMenu = "SnelMenu";
        final String LAST = "LAST";
        
        JButton a = new JButton("Welkom bij UW bank");
        JButton info = new JButton("Kies hier wat u wil gaan doen");
        JButton op = new JButton("Opnemen");
        JButton st = new JButton("Storten");
        JButton sne = new JButton("Snelmenu"); // vraag aan CMD
        
        a.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // hier naar nieuw card
                if (cmd.equals(Exit)) {
                    cl.first(cards);
            }
        });
        
        JFrame frame = new JFrame("CardLayout Demo");
 
        /*!!!!!!!!!!!!!!!!!!!Create the "cards"!!!!!!!!!!!!!!!!!*/
        
        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        
        
        card1.add(a);
        card2.add(info);
        card2.add(st);
        card2.add(op);
        card2.add(sne);
        
        card1.setBackground(new Color(255,0,0));
 
        /*!!!!!!!!!!!!!!!!!!Create the panel that contains the "cards"!!!!!!!!!!!!*/
        
        cards = new JPanel(new java.awt.CardLayout());
        cards.add(card1, card1Text);
        cards.add(card2, card2Text);
 
        class ControlActionListenter implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                
                java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
                String cmd = e.getActionCommand();
                
                if (cmd.equals(Exit)) {
                    cl.first(cards);
                } else if (cmd.equals(SnelMenu)) {
                    cl.next(cards);

                } else if (cmd.equals(LAST)) {
                    cl.last(cards);
                }
            }
        }
        ControlActionListenter cal = new ControlActionListenter();
 
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