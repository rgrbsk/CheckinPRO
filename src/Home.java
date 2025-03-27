import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.event.*;

public class Home extends JFrame {

    private JPanel contentPane;
    
    int xx, xy;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UIManager.setLookAndFeel(new FlatDarkLaf());
                    Home frame = new Home();
                    frame.setUndecorated(false);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Home() {
    	
        setResizable(false);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1400, 800);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(19, 19, 19));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(32, 32, 32));
        panel.setForeground(new Color(0, 0, 0));
        panel.setBounds(10, 10, 257, 743);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel logoImg = new JLabel("");
        logoImg.setHorizontalAlignment(SwingConstants.CENTER);
        logoImg.setBounds(0, 0, 257, 233);
        panel.add(logoImg);
        logoImg.setIcon(new ImageIcon(Home.class.getResource("/img/lgoBranco.png")));
        
        JButton checkoutBtn = new JButton("Check-out");
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 18));
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setBackground(new Color(0, 128, 0));
        checkoutBtn.setBounds(10, 355, 237, 45);
        panel.add(checkoutBtn);
        
        checkoutBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkoutBtn.setBackground(new Color(0, 100, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                checkoutBtn.setBackground(new Color(0, 128, 0));
            }
        });

        checkoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        JButton roomsBtn = new JButton("Quartos");
        roomsBtn.setForeground(Color.WHITE);
        roomsBtn.setFont(new Font("Arial", Font.BOLD, 18));
        roomsBtn.setFocusPainted(false);
        roomsBtn.setBackground(new Color(0, 128, 0));
        roomsBtn.setBounds(10, 434, 237, 45);
        panel.add(roomsBtn);
        
        roomsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roomsBtn.setBackground(new Color(0, 100, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roomsBtn.setBackground(new Color(0, 128, 0));
            }
        });

        roomsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        JButton clientsBtn = new JButton("Clientes");
        clientsBtn.setForeground(Color.WHITE);
        clientsBtn.setFont(new Font("Arial", Font.BOLD, 18));
        clientsBtn.setFocusPainted(false);
        clientsBtn.setBackground(new Color(70, 130, 180)); 
        clientsBtn.setBounds(10, 513, 237, 45);
        panel.add(clientsBtn);
        
        clientsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                clientsBtn.setBackground(new Color(50, 120, 160));  
            }

            @Override
            public void mouseExited(MouseEvent e) {
                clientsBtn.setBackground(new Color(70, 130, 180)); 
            }
        });

        clientsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        JButton exitBtn = new JButton("Sair");
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        exitBtn.setFocusPainted(false);
        exitBtn.setBackground(new Color(255, 0, 0));
        exitBtn.setBounds(10, 675, 237, 35);
        panel.add(exitBtn);
        
        JButton btnNewButton = new JButton("Check-in");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton.setIcon(new ImageIcon(Home.class.getResource("/img/icons8-smart-checkin-30.png")));
        btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 23));
        btnNewButton.setBackground(new Color(0, 128, 0));
       
        btnNewButton.setBounds(10, 278, 237, 45);
        panel.add(btnNewButton);
        
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn.setBackground(new Color(200, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setBackground(new Color(255, 0, 0));
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  
            }
        });
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(55, 55, 55));
        panel_1.setBounds(272, 10, 1105, 743);
        contentPane.add(panel_1);
        
        JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
        panel_1.add(tglbtnNewToggleButton);
    }
}
