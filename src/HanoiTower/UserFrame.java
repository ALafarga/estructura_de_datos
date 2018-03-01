package HanoiTower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class UserFrame extends JFrame implements ActionListener{
    static String name = "charles";
    UserFrame(){
        setTitle("UsersFrame");
        setSize(600,550);

    }


    public static void main(String[] args){

        UserFrame f = new UserFrame();
        JPanel pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());
        pan1.setBackground(Color.GREEN);
        JPanel pan2 = new JPanel();
        pan2.setBackground(Color.ORANGE);
        JPanel pan3 = new JPanel();
        pan3.setBackground(Color.RED);
        JPanel pan4 = new JPanel();
        pan4.setBackground(Color.magenta);
        JPanel pan5 = new JPanel();
        pan5.setBackground(Color.BLUE);
        JPanel pan6 = new JPanel();
        pan6.setBackground(Color.WHITE);

        pan6.setLayout(new BorderLayout());

        JButton jb = new JButton("Hello");
        JButton jb6 = new JButton("WEST added");
        JButton jb7 = new JButton("EAST added");
        JLabel lb = new JLabel(" label for panel, this is showing the paneel");
        JLabel lb2 = new JLabel(" East");
        pan6.add(lb,BorderLayout.NORTH);
        pan6.add(lb2,BorderLayout.EAST);
        jb.setSize(50, 50);
        pan1.add(jb,BorderLayout.NORTH);
        pan1.add(pan6,BorderLayout.SOUTH);
        JButton jb2 = new JButton("there");
        jb.setSize(50, 50);
        pan2.add(jb2);
        pan2.add(jb7);
        JButton jb3 = new JButton("North");
        jb.setSize(50, 50);
        pan3.add(jb3);
        JButton jb4 = new JButton("South");
        jb.setSize(50, 50);
        pan4.add(jb4);
        JButton jb5 = new JButton("Center");
        jb.setSize(50, 50);
        pan5.add(jb5);
        f.add(pan1,BorderLayout.WEST);
        f.add(pan2,BorderLayout.EAST);
        f.add(pan3,BorderLayout.NORTH);
        f.add(pan4,BorderLayout.SOUTH);
        f.add(pan5,BorderLayout.CENTER);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("The first button has been clickled");
    }


}
