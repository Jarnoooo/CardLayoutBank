package cardlayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class CardLayout {
 
    public static void main(String[] args) {
 
        final String card1Text = "Card 1";
        final String card2Text = "Card 2";
        final String card3Text = "Card 3";
        final String card4Text = "Card 4";
        final String card5Text = "Card 5";
        final String card6Text = "Card 6";
        
        final JPanel cards; //a panel that uses CardLayout
        
        // button commands
        final String Exit = "Exit";
        final String SnelMenu = "SnelMenu";
        final String LAST = "LAST";
        
        JButton a = new JButton("Welkom bij UW bank Klik hier om verder te gaan");
        
        JButton info = new JButton("Kies hier wat u wil gaan doen");
        JButton op = new JButton("Opnemen");
        JButton st = new JButton("Storten");
        JButton sne = new JButton("Snelmenu"); // vraag aan CMD
        
        JButton opn = new JButton("$20");
        JButton opne = new JButton("$50");
        JButton opnee = new JButton("100");
        
        JButton sto = new JButton("Stop het geld in de automaat");
        
        JButton stor = new JButton("U heeft xbedrag gestort");
        
        JButton opge = new JButton("Neemt u uw geld uit de automaat");   
        
        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        JPanel card3 = new JPanel(); 
        JPanel card4 = new JPanel();
        JPanel card5 = new JPanel();
        JPanel card6 = new JPanel();
                
        card1.add(a);
        
        card2.add(info);
        card2.add(st);
        card2.add(op);
        card2.add(sne);
        
        card3.add(opn);
        card3.add(opne);
        card3.add(opnee);
        
        card4.add(sto);
        
        card5.add(stor);
        
        card6.add(opge);
        
        card1.setBackground(new Color(255,0,0));
 
        /*!!!!!!!!!!!!!!!!!!Create the panel that contains the "cards"!!!!!!!!!!!!*/
        
        cards = new JPanel(new java.awt.CardLayout());
        cards.add(card1, card1Text);
        cards.add(card2, card2Text); 
        cards.add(card3, card3Text);
        cards.add(card4, card4Text);
        cards.add(card5, card5Text);
        cards.add(card6, card6Text);
        
        
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());

        a.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               cl.next(cards);
            }
        });
        op.addActionListener(new ActionListener(){
       
        public void actionPerformed(ActionEvent a){
            cl.next(cards);
        }
    });
        st.addActionListener(new ActionListener(){
       
        public void actionPerformed(ActionEvent a){
            cl.next(cards);
            cl.next(cards);
        }
    });
       sto.addActionListener(new ActionListener(){
       
        public void actionPerformed(ActionEvent a){
            cl.next(cards);
        }
    });
       opn.addActionListener(new ActionListener(){
       
        public void actionPerformed(ActionEvent a){
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
        }
    });
       
       opne.addActionListener(new ActionListener(){
       
        public void actionPerformed(ActionEvent a){
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
        }
    });   
       opnee.addActionListener(new ActionListener(){
       
        public void actionPerformed(ActionEvent a){
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
        }
    });
        
        JFrame frame = new JFrame("Bank");
 
        /*!!!!!!!!!!!!!!!!!!!Create the "cards"!!!!!!!!!!!!!!!!!*/

        

        class ControlActionListener implements ActionListener {
            
                public void actionPerformed(ActionEvent e) {
                
                String cmd = e.getActionCommand();
                
                if (cmd.equals(Exit)) {
                    cl.first(cards);
                } else if (cmd.equals(SnelMenu)) {
                    cl.next(cards);
                    cl.next(cards);

                } else if (cmd.equals(LAST)) {
                    cl.last(cards);
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
 