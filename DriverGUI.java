import java.awt.*;
import java.io.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;

public class DriverGUI implements ActionListener {
    JFrame F1 = new JFrame("Test Case Generator");
    JPanel P1 = new JPanel();
    ButtonGroup G1 = new ButtonGroup();
    ButtonGroup G2 = new ButtonGroup();
    JLabel L1 = new JLabel("Enter Number of");
    JLabel L11 = new JLabel("Test Cases :");
    JLabel L2 = new JLabel("Select Your OS : ");
    JLabel L3 = new JLabel("Select Language : ");
    JTextField T1 = new JTextField(15);
    JTextArea taArea = new JTextArea();
    JScrollPane scroll;
    JButton b = new JButton("Generate");
    JRadioButton Ro1 = new JRadioButton("Windows");
    JRadioButton Ro2 = new JRadioButton("Linux");
    JRadioButton Rl1 = new JRadioButton("C");
    JRadioButton Rl2 = new JRadioButton("C++");
    JRadioButton Rl3 = new JRadioButton("Java");
    JRadioButton Rl4 = new JRadioButton("Python");

    DriverGUI() {
        P1.setBackground(Color.GRAY);
        F1.setBounds(600, 200, 700, 300);
        scroll = new JScrollPane(taArea);
        scroll = new JScrollPane(taArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        addcomponents();
        F1.add(P1);
        // F1.add(P2);
        G1.add(Ro1);
        G1.add(Ro2);
        G2.add(Rl1);
        G2.add(Rl2);
        G2.add(Rl3);
        G2.add(Rl4);
        P1.add(scroll);
        taArea.setFont(new Font("dialog", Font.PLAIN, 14));
        F1.setVisible(true);
        F1.setSize(700, 300);

        F1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addActions();
        if (Rl1.isSelected() == false || Rl2.isSelected() == false || Rl3.isSelected() == false
                || Rl4.isSelected() == false) {
            scroll.setVisible(false);
        }
    }

    public void buttonAction(ActionEvent e) {
        TCCreation tcgen = new TCCreation();
        Compile compiler = new Compile();
        ExecuteTC excuter=new ExecuteTC();
        try
        {
            int tcfiles = Integer.parseInt(T1.getText());
            tcgen.makeTC(Integer.parseInt(T1.getText())); 
            int os=0,lang=0;
			System.out.println("TC Files generated");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Enter 1 for Windows\n2 for Linux");
            if(Ro1.isSelected()){
                os = 1;
            }else if (Ro2.isSelected()) {
                os = 2;
            } 
            System.out.println("Enter 1 for c\n2 for c++\n3 for Java\n4 for python");
            if(Rl1.isSelected()){
                lang = 1;
            }else if (Rl2.isSelected()) {
                lang = 2;
            }else if (Rl3.isSelected()) {
                lang = 3;
            }else if (Rl4.isSelected()) {
                lang = 4;
            } 
            String s = taArea.t
            compiler.compile(lang,os);
            for(int i=0;i<tcfiles;i++)
            {
                String s=excuter.exec(new String[]{"<input/input0"+i+".txt",">output/output0"+i+".txt" },os,lang);
				s="cmd /c start /wait cmd.exe /K \""+s+" && exit\"";
				long st=System.nanoTime();
                Process p=Runtime.getRuntime().exec(s);
                
				p.waitFor();
				long et=System.nanoTime();
                System.out.println("Test Case: "+i+" executed successfully, time taken is : "+(et-st)/Math.pow(10,9));
            }
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
    public void addActions() {
        Rl1.addActionListener(this);
        Rl2.addActionListener(this);
        Rl3.addActionListener(this);
        Rl4.addActionListener(this);
        b.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        F1.setSize(700, 650);
        scroll.setBounds(30, 230, 640, 320);
        scroll.setVisible(true);
        if (e.getSource() == Rl1) {
            taArea.setVisible(true);
            taArea.setText("#include <stdio.h>\n\nint main(void){\n    // your code goes here\n    return 0;\n}");
        } else if (e.getSource() == Rl2) {
            taArea.setVisible(true);
            taArea.setText(
                    "#include <iostream>\nusing namespace std;\n\nint main() {\n    // your code goes here\n    return 0;\n}");
        } else if (e.getSource() == Rl3) {

            taArea.setVisible(true);
            taArea.setText(
                    "import java.util.*;\nimport java.lang.*;\nimport java.io.*;\n\nclass logic\n{\n	public static void main (String[] args) throws java.lang.Exception\n	{\n		// your code goes here\n	}\n}");
        } else if (e.getSource() == Rl4) {
            taArea.setText("# write your code here");
        }
    }

    public void addcomponents() {
        // P1.setBounds(0,0,400,600);
        scroll.setBounds(30, 150, 640, 320);
        L1.setBounds(30, 15, 150, 30);
        L11.setBounds(30, 30, 150, 30);
        T1.setBounds(200, 20, 100, 30);
        L2.setBounds(30, 107, 150, 15);
        L3.setBounds(20, 150, 150, 30);

        Ro1.setBounds(200, 100, 100, 30);
        Ro2.setBounds(310, 100, 100, 30);

        Rl1.setBounds(200, 150, 100, 30);
        Rl2.setBounds(320, 150, 100, 30);
        Rl3.setBounds(440, 150, 100, 30);
        Rl4.setBounds(560, 150, 100, 30);

        b.setBounds(280, 565, 110, 30);

        P1.add(L1);
        F1.add(b);
        P1.add(L11);
        P1.add(T1);
        P1.add(L2);
        P1.add(L3);
        P1.add(Ro1);
        P1.add(Ro2);
        P1.add(Rl1);
        P1.add(Rl2);
        P1.add(Rl3);
        P1.add(Rl4);
        P1.setLayout(null);

    }

    public static void main(String[] args) {
        new DriverGUI();
    }
}
