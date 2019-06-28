package yess;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.util.logging.Level;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.*;

public class Yess {

    static String serial;
    static int opnemen;
//int geldOpnemen = 0; 
    public static void main(String[] args) {
        
        AccountsConnection acc = new AccountsConnection("jdbc:mysql://localhost:8306/mybank?useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam", "bank", "hunter2");
        Credit c = new Credit(acc);
        Random r = new Random(); 
        Integer random = r.nextInt(2);
        int credit;
        int saldoDatabase;
        int geldOpnemen;
            
        String SaldoPersoon;
        String user = random.toString();
        String ownerId;
        String x;
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


        acc.queryDatabase("SELECT * FROM accounts WHERE accountid =" + user);
        saldoDatabase = acc.getCredit();
        ownerId = acc.getOwnerId();
        JButton a = new JButton("Welkom bij KU Bank" + "\n" + "Scan uw pas om verder te gaan "+"\n"+" en voer daarna uw pincode in");
        JButton info = new JButton("Kies hier wat u wil gaan doen...");
        JButton op = new JButton("(A) Opnemen");
        // JButton st = new JButton("Storten");
        JButton sne = new JButton("(*) Snelmenu");
        JButton saldocheck = new JButton("(B) Saldo Check");

//        JButton saldoo = new JButton("Dus te weinig saldo");
        JButton opn = new JButton("(A) £2000");
        JButton opne = new JButton("(B)£500");
        JButton opnee = new JButton("(C)£1000");

        JButton saldoP = new JButton("Uw saldo bij ons = " + saldoDatabase);
        JButton opge = new JButton("U kunt uw geld uit de automaat nemen!");

        JButton bonnetje = new JButton("Wilt u een bonnetje?");
        JButton ja = new JButton("(A) Ja");
        JButton nee = new JButton("(B) Nee");

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
        
        bonPrinten.add(bonnetje);
        bonPrinten.add(ja);
        bonPrinten.add(nee);
        
        saldooo.add(saldoP);
        card1.setBackground(new Color(255, 0, 0));

        /*!!!!!!!!!!!!!!!!!!Create the panel that contains the "cards"!!!!!!!!!!!!*/
        cards = new JPanel(new java.awt.CardLayout());

        cards.add(card1, card1Text);
        cards.add(infoo, card2Text);
        cards.add(opnemen, card3Text);
        cards.add(SnelMenuu, card4Text);
        cards.add(automaat, card5Text);
        cards.add(bonPrinten, card7Text);
        cards.add(saldooo, card6Text);
        cards.add(pasBlok, geblokkeerd);

        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());

//        while(3< !serial.length()){
//            Thread.yield();
//        }
//        if (!acc.getActive()) {
//
//        }
//        try {
//            if (serial.length() > 3) {
//                acc.getCardNumber("SELECT accountid FROM cards WHERE rfidid =" + serial);
////           System.out.println(acc.getCardNumber("SELECT accountid FROM cards WHERE rfidid =" + serial));
//////                    rfid = true;
////            acc.getActive();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Thread.yield();
//        }
//        else if (serial == "A") {
//            cl.next(cards);
//        } else {
//        while(getArduino() == null){
//        if (getArduino() == "AA"){
//            System.out.println("da");
//                    cl.next(cards);
//                }
//        }
           
        a.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                setId(acc.getCardNumber("SELECT accountid FROM cards WHERE rfidid =" + serial));
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
                cl.next(cards);
                int geldOpnemen = 2000;
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

                int geldOpnemen = 500;
                c.setCredit(geldOpnemen);
                int x = c.getCredit();
//                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
                acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
                acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
                System.out.println(geldOpnemen);
                geefGeld(geldOpnemen);
            }
        });
        opnee.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                cl.next(cards);

                int geldOpnemen = 1000;
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
                    cl.next(cards);

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
                    cl.first(cards);
                    // close connection 
                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
             

        });
        ja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                int u = c.getCredit();
                
                Printen print = new Printen(u);
                cl.first(cards);
                                SerialPort comPort2 = SerialPort.getCommPort("COM5");

        //set the baud rate to 9600 (same as the Arduino)
        comPort2.setBaudRate(9600);

        //open the port
        comPort2.openPort();

        //create a listener and start listening
        comPort2.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return; //wait until we receive data
                }
                byte[] newData = new byte[comPort2.bytesAvailable()]; //receive incoming bytes
                comPort2.readBytes(newData, newData.length); //read incoming bytes
                String serialData = new String(newData); //convert bytes to string
//                setArduino(serialData);
                //print string received from the Arduino
                setArduino(serialData);
                System.out.println(serialData);
                byte [] a = user.getBytes();
                comPort2.writeBytes(a, a.length);
            }
        });
                
            }
        });
        nee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                cl.first(cards);
                
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
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
    //    public static int newCredit(AccountsConnection a, int b) {
    //        int x = a.getCredit();
    //        int y = x - b; //nieuw credit na opnemen
    //        return y;
    //    }
    //    

    public static String getArduino() {
        return serial;
    }

    public static void setArduino(String x) {
        serial = x;
        System.out.print(serial);
    }
    public static int getOpnemen(){
        return opnemen;
    }
    public static void setId(int x){
        opnemen = x;
        
    }
    public static void geefGeld(int x){
        Integer i = x;
        String o = i.toString();
        SerialPort comPort3 = SerialPort.getCommPort("COM5");

        //set the baud rate to 9600 (same as the Arduino)
        comPort3.setBaudRate(9600);

        //open the port
        comPort3.openPort();

        //create a listener and start listening
        comPort3.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return; //wait until we receive data
                }
                byte[] newData = new byte[comPort3.bytesAvailable()]; //receive incoming bytes
                comPort3.readBytes(newData, newData.length); //read incoming bytes
                String serialData = new String(newData); //convert bytes to string
//                setArduino(serialData);
                //print string received from the Arduino
                setArduino(serialData);
                System.out.println(serialData);
                byte [] a = o.getBytes();
                comPort3.writeBytes(a, a.length);
            }
        });
    }
    
}