package View;

import Model.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomSlangWordTodayView extends JFrame implements ActionListener {
    private SlangWords slangWords;
    private JLabel header;
    private JLabel swLabelOutput;
    private JLabel defiLabelOutput;
    private String[] swRandom;

    public RandomSlangWordTodayView(SlangWords sw) {
        this.slangWords = sw;
        swRandom = this.slangWords.randomSlangWord();

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Danh sách slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);

        header = new JLabel("On this day slang word", JLabel.CENTER);
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 24);
        Font fontBody = new Font("Arial", Font.BOLD, 18);
        Font fontBodyOutput = new Font("Arial", Font.PLAIN, 18);
        header.setFont(fontHeaderAndFooter);
        header.setForeground(new Color(17, 45, 78));

        JPanel jPanelTop = new JPanel();
        jPanelTop.add(header);

        JPanel jPanelBody = new JPanel(new BorderLayout());

        JPanel swPanelOutput = new JPanel(new FlowLayout());
        JPanel defiPanelOutput = new JPanel(new FlowLayout());

        JLabel swLabel = new JLabel("Slang word:");
        swLabel.setFont(fontBody);

        swPanelOutput.add(swLabel);

        swLabelOutput = new JLabel("");
        swLabelOutput.setText(swRandom[0]);
        swLabelOutput.setFont(fontBodyOutput);
        swLabelOutput.setForeground(new Color(63, 114, 175));
        swPanelOutput.add(swLabelOutput);

        JLabel defiLabel = new JLabel("Definition:");
        defiLabel.setFont(fontBody);

        defiPanelOutput.add(defiLabel);
        defiLabelOutput = new JLabel("");
        defiLabelOutput.setFont(fontBodyOutput);
        defiLabelOutput.setText(swRandom[1]);
        defiLabelOutput.setForeground(new Color(63, 114, 175));
        defiPanelOutput.add(defiLabelOutput);

        jPanelBody.add(swPanelOutput, BorderLayout.PAGE_START);
        jPanelBody.add(defiPanelOutput, BorderLayout.CENTER);

        ImageIcon backImg = new ImageIcon("./images/Go-back-icon.png");
        JButton btnBack = new JButton("Quay lại", backImg);
        btnBack.addActionListener(this);
        btnBack.setPreferredSize(new Dimension(100, 50));
        btnBack.setBackground(new Color(63, 114, 175));
        btnBack.setForeground(Color.white);

        ImageIcon resetImg = new ImageIcon("./images/Restore-Window-icon.png");
        JButton btnReset = new JButton("Reset", resetImg);
        btnReset.addActionListener(this);
        btnReset.setPreferredSize(new Dimension(100, 50));
        btnReset.setBackground(new Color(63, 114, 175));
        btnReset.setForeground(Color.white);

        JPanel jPanelBot = new JPanel(new FlowLayout());
        jPanelBot.add(btnBack);
        jPanelBot.add(btnReset);

        JPanel jPanelBot1 = new JPanel();
        jPanelBot1.add(jPanelBot);

        this.setLayout(new BorderLayout());

        this.add(jPanelTop, BorderLayout.PAGE_START);
        this.add(jPanelBody, BorderLayout.CENTER);
        this.add(jPanelBot1, BorderLayout.PAGE_END);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        if (strAction.equals("Quay lại")) {
            this.dispose();
            new MenuView();
        } else if (strAction.equals("Reset")) {
            swRandom = this.slangWords.randomSlangWord();
            swLabelOutput.setText(swRandom[0]);
            defiLabelOutput.setText(swRandom[1]);
        }
    }
}
