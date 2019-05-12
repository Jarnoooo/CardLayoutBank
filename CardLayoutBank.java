package cardlayout;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.util.logging.Level;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
import javax.swing.*;

public class CardLayoutBank {
static String serial;
        
//int geldOpnemen = 0; 
    public static void main(String[] args) {
        AccountsConnection acc = new AccountsConnection("jdbc:mysql://localhost:8306/mybank?useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam", "bank", "hunter2");
        Credit c = new Credit(acc);
        int credit;
        int saldoDatabase;
        int geldOpnemen;
        String SaldoPersoon;
        String user = "1";
        String ownerId;
        
        final boolean rfid = false;
        /* hier functie maken/aanroepen dat het alleen mag doorgaan als de pas active op 1 is*/
        
        

        final String card1Text = "Card 1";
        final String card2Text = "Card 2";
        final String card3Text = "Card 3";
        final String card4Text = "Card 4";
        final String card5Text = "Card 5";
        final String card6Text = "Card 6";
        final String card7Text = "Card 7";
        final String card8Text = "Card 8";

        final String cardBon = "cardBox";
        final String saldo = "Saldo";
        final String geblokkeerd = "Geblokkeerd";

        final JPanel cards;

        final String Exit = "Exit";
        final String SnelMenu = "Snelmenu";
        final String Last = "Last";
        final String bon = "Bon";
        final String Sald = "Saldo";

        /*
		 * Change "COM4" to your USB port connected to the Arduino
		 * You can find the right port using the ArduinIDE
		 *
		 * PS: Unix based operating systems use "/dev/ttyUSB"
         */
        SerialPort comPort = SerialPort.getCommPort("COM5");

        //set the baud rate to 9600 (same as the Arduino)
        comPort.setBaudRate(9600);

        //open the port
        comPort.openPort();

        //create a listener and start listening
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return; //wait until we receive data
                }
                byte[] newData = new byte[comPort.bytesAvailable()]; //receive incoming bytes
                comPort.readBytes(newData, newData.length); //read incoming bytes
                String serialData = new String(newData); //convert bytes to string
//                setArduino(serialData);
                //print string received from the Arduino
                setArduino(serialData);
                System.out.println(serialData);
             
            }
        });
        
        saldoDatabase = acc.getCredit();
        ownerId = acc.getOwnerId();
        
//        acc.queryDatabase("SELECT * FROM accounts WHERE accountid =" + user);
//        
        JButton a = new JButton("Welkom bij KU Bank \n Scan uw pas om verder te gaan, en klik op A");
        JButton info = new JButton("Kies hier wat u wil gaan doen...");
        JButton op = new JButton("Opnemen");
        // JButton st = new JButton("Storten");
        JButton sne = new JButton("Snelmenu");
        JButton saldocheck = new JButton("Saldo Check");

//        JButton saldoo = new JButton("Dus te weinig saldo");
        JButton opn = new JButton("£20");
        JButton opne = new JButton("£50");
        JButton opnee = new JButton("£100");

        JButton saldoP = new JButton("Uw saldo bij ons = " + saldoDatabase);

        JButton opge = new JButton("U kunt uw geld uit de automaat nemen!");

        JButton bonnetje = new JButton("Wilt u een bonnetje?");
        JButton ja = new JButton("Ja");
        JButton nee = new JButton("Nee");

        JButton bo = new JButton("Bon printen");

        JButton geblokt = new JButton("Uw pas is geblokt");
        /*!!!!!!!!!!!!!!!!PANELS!!!!!!!!!!!!!*/

        JPanel card1 = new JPanel();
        JPanel infoo = new JPanel();
        JPanel opnemen = new JPanel();
        JPanel SnelMenuu = new JPanel();
        JPanel automaat = new JPanel();
        JPanel saldooo = new JPanel();
        JPanel vraag = new JPanel();
        JPanel bonPrinten = new JPanel();
        JPanel pasBlok = new JPanel();

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

        automaat.add(opge);

        saldooo.add(saldoP);

        card1.setBackground(new Color(255, 0, 0));

        /*!!!!!!!!!!!!!!!!!!Create the panel that contains the "cards"!!!!!!!!!!!!*/
        cards = new JPanel(new java.awt.CardLayout());

        cards.add(card1, card1Text);
        cards.add(infoo, card2Text);
        cards.add(opnemen, card3Text);
        cards.add(SnelMenuu, card4Text);
        cards.add(automaat, card5Text);
        cards.add(saldooo, card6Text);
        cards.add(pasBlok, geblokkeerd);

        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());

        if (!acc.getActive()) {

        }
        if(serial == "a"){
            
        }else {
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
                    c.setCredit(geldOpnemen);
                    int x = c.getCredit();
//                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
                    acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
                    System.out.println(geldOpnemen);
                }
            });

            opne.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent a) {
                    cl.next(cards);

                    int geldOpnemen = 50;
                    c.setCredit(geldOpnemen);
                    int x = c.getCredit();
//                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
                    acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
                    System.out.println(geldOpnemen);
                }
            });
            opnee.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent a) {
                    cl.next(cards);

                    int geldOpnemen = 100;
                    c.setCredit(geldOpnemen);
                    int x = c.getCredit();
//                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
                    acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");

                    System.out.println(geldOpnemen);
                }
            });
            saldocheck.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    cl.next(cards);
                    cl.next(cards);
                    cl.next(cards);
                    cl.next(cards);
                }
            });
            automaat.addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(ComponentEvent e) {
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                }

                @Override
                public void componentShown(ComponentEvent e) {
                    try {
//                        System.out.println(geldOpnemen);
//                        int credit = Credit(acc, geldOpnemen);
//                        acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");

                        Thread.sleep(3000);
                        cl.first(cards);

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
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
//                        saldoDatabase = acc.getCredit();
//                        ownerId = acc.getOwnerId();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        cl.next(cards);// om naar eerste scherm tegaan eventueel nog extra line toevoegen
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

//    public static int newCredit(AccountsConnection a, int b) {
//        int x = a.getCredit();
//        int y = x - b; //nieuw credit na opnemen
//        return y;
//    }
//    
    public String getArduino(){
        return serial;
    }
    public static void setArduino(String x){
        serial = x; 
        System.out.print("JOEJKEO"+serial);
    }
}
