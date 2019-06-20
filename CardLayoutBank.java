package cardlayout;

import static cardlayout.NewMain.serial;
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
    static int opnemen;
//int geldOpnemen = 0;

    public static void main(String[] args) {
        AccountsConnection acc = new AccountsConnection("jdbc:mysql://localhost:8306/mybank?useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam", "bank", "hunter2");
        Credit c = new Credit(acc);
        int credit;
        int saldoDatabase;
        int geldOpnemen;
        Integer check;

        String SaldoPersoon;
        String user = "1";
        String ownerId;
        char y[] = new char[4];
        int x;
        boolean rfid = false;
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

        JButton a = new JButton("Welkom bij KU Bank" + "\n" + "Scan uw pas om verder te gaan en voer uw pincode in");
        JButton info = new JButton("Kies hier wat u wil gaan doen...");
        JButton op = new JButton("(A) Opnemen");
        // JButton st = new JButton("Storten");
        JButton sne = new JButton("(*) Snelmenu");
        JButton saldocheck = new JButton("(B) Saldo Check");

//        JButton saldoo = new JButton("Dus te weinig saldo");
        JButton opn = new JButton("(A) £500");
        JButton opne = new JButton("(B) £1000");
        JButton opnee = new JButton("(C) £2000");

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

        card1.setBackground(new Color(255, 0, 0));

        /*!!!!!!!!!!!!!!!!!!Create the panel that contains the "cards"!!!!!!!!!!!!*/
        cards = new JPanel(new java.awt.CardLayout());

        JFrame frame = new JFrame("Bank");

        cards.add(card1, card1Text);
        cards.add(infoo, card2Text);
        cards.add(opnemen, card3Text);
        cards.add(SnelMenuu, card4Text);
        cards.add(automaat, card5Text);
        cards.add(bonPrinten, card7Text);
        cards.add(saldooo, card6Text);
        cards.add(pasBlok, geblokkeerd);

        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());

        class ControlActionListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {

                String cmd = e.getActionCommand();

                if (cmd.equals(Exit) || serial.trim() == "#") {
                    cl.first(cards);
                } else if (cmd.equals(SnelMenu) || serial.trim() == "*") {
                    cl.next(cards);
                    cl.next(cards);

                }
            }
        }
        ControlActionListener cal = new ControlActionListener();

        JButton saldoP = new JButton("Uw saldo bij ons = " + saldoDatabase);
        JButton opge = new JButton("U kunt uw geld uit de automaat nemen!");

        automaat.add(opge);

        bonPrinten.add(bonnetje);
        bonPrinten.add(ja);
        bonPrinten.add(nee);

        saldooo.add(saldoP);
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
//        while(3< !serial.length()){
//            Thread.yield();
//        }
//        if (!acc.getActive()) {
//
//        }

        if (serial.length() > 4) {
            serial.trim();
            if (acc.getCardNumber("SELECT accountid FROM cards WHERE rfidid =" + serial) == -1) {
                return;
            }

            acc.queryDatabase("SELECT * FROM accounts WHERE accountid =" + user);
            saldoDatabase = acc.getCredit();
            ownerId = acc.getOwnerId();
            System.out.println(acc.getCardNumber("SELECT accountid FROM cards WHERE rfidid =" + serial));
            rfid = true;
            acc.getActive();
            serial = "";
        }

        if ((serial.trim() == "AA" || serial.trim() == "A") && rfid == true) {
            char[] d = new char[4];

            for (int i = 0; i < 4; i++) { // fill array of input
                if (d[i] == ' ') {
                    i = -1;
                }
                y[i] = d[i];
            }
            check = Integer.parseInt(new String(d)); // parse char array to an int number
            cl.next(cards);
            serial = "";
            int pin = acc.getPin();
            if (check == pin) { //check if pin isn't correct 
                cl.next(cards);
            } else {
                
                return;
            }
        }
//            a.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
////                setId(acc.getCardNumber("SELECT accountid FROM cards WHERE rfidid =" + serial));
//
//                    cl.next(cards);
//                }
//
//            });
        if (serial.trim() == "A") {
            cl.next(cards);
            cl.next(cards);
            serial = "";
        } else if (serial.trim() == "B" || serial.trim() == "BB") {
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);

        }
//        op.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent a) {
//
//                cl.next(cards);
//                cl.next(cards);
//
//            }
//        });
        if (serial.trim() == "A" || serial.trim() == "AA") {
            cl.next(cards);

            geldOpnemen = 500;
            c.setCredit(geldOpnemen);
            x = c.getCredit();
//                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
            acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
            acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
            System.out.println(geldOpnemen);
            serial = "";
        } else if (serial.trim() == "B" || serial.trim() == "BB") {
            cl.next(cards);
            geldOpnemen = 1000;
            c.setCredit(geldOpnemen);
            x = c.getCredit();
//                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
            acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
            acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
            System.out.println(geldOpnemen);
            serial = "";
        } else if (serial.trim() == "C" || serial.trim() == "CC") {
            cl.next(cards);

            geldOpnemen = 2000;
            c.setCredit(geldOpnemen);
            x = c.getCredit();
//                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
            acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
            acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");

            System.out.println(geldOpnemen);
        }
//        } else if (serial.trim() == "D" || serial.trim() == "DD") {
//            cl.next(cards);
//
//            geldOpnemen = 2000;
//            c.setCredit(geldOpnemen);
//            x = c.getCredit();
////                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//            acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
//            acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//
//            System.out.println(geldOpnemen);
//
//        }

//        opn.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent a) {
//                cl.next(cards);
//
//                int geldOpnemen = 20;
//                c.setCredit(geldOpnemen);
//                int x = c.getCredit();
////                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//                acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
//                acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//                System.out.println(geldOpnemen);
//            }
//        });
//        opne.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent a) {
//                cl.next(cards);
//
//                int geldOpnemen = 50;
//                c.setCredit(geldOpnemen);
//                int x = c.getCredit();
////                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//                acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
//                acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//                System.out.println(geldOpnemen);
//            }
//        });
//        opnee.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent a) {
//                cl.next(cards);
//
//                int geldOpnemen = 100;
//                c.setCredit(geldOpnemen);
//                int x = c.getCredit();
////                    acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//                acc.updateTable("UPDATE accounts SET credit =" + x + ", active = 1 WHERE accountid =" + user + ";");
//                acc.updateTable("INSERT INTO transactions (home, foreignacct, amt) VALUES(1, 0," + geldOpnemen + ");");
//
//                System.out.println(geldOpnemen);
//            }
//        });
//        saldocheck.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent a) {
//                cl.next(cards);
//                cl.next(cards);
//                cl.next(cards);
//                cl.next(cards);
//                cl.next(cards);
//            }
//        });
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
                    // close connection

                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }

        });
        serial = "";
        rfid = false;

        if (serial.trim() == "A" || serial.trim() == "AA") {
            geldOpnemen = c.getCredit();
            Printen print = new Printen(geldOpnemen);
            cl.first(cards);

        } else if (serial.trim() == "B" || serial.trim() == "BB") {
            cl.first(cards);
        }
//        ja.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent a) {
//                int geldOpnemen = c.getCredit();
//                Printen print = new Printen(geldOpnemen);
//                cl.first(cards);
//            }
//        });
//        nee.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent a) {
//                cl.first(cards);
//
//            }
//        });

        //    public static int newCredit(AccountsConnection a, int b) {
        //        int x = a.getCredit();
        //        int y = x - b; //nieuw credit na opnemen
        //        return y;
        //    }
        //
    }

    public static String getArduino() {
        return serial;
    }

    public static void setArduino(String x) {
        if (!x.equals(null)) {
            serial = x;
        }

        System.out.print(serial);
    }

    public static int getOpnemen() {
        return opnemen;
    }

    public static void setId(int x) {
        opnemen = x;

    }
}
