package View;

import Model.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class RandomSlangWordTodayView extends JFrame implements ActionListener {
    private SlangWords slangWords;
    private JLabel header;
    private JButton btnBack;
    private JButton btnAdd;
    private JLabel swLabelOutput;
    private JLabel defiLabelOutput;
    private String[] swRandom;
    private JButton btnRandom;

    public RandomSlangWordTodayView(SlangWords sw) {
        this.slangWords = sw;
        swRandom = this.slangWords.randomSlangWord();

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Danh sách slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);

        header = new JLabel("On this day slang word", JLabel.CENTER);
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 24);
        Font fontBody = new Font("Arial", Font.BOLD, 18);
        Font fontBodyOutput = new Font("Arial", Font.PLAIN, 18);
        header.setFont(fontHeaderAndFooter);
        header.setForeground(new Color(63, 114, 175));

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

        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(this);
        btnBack.setPreferredSize(new Dimension(100,50));
        btnBack.setBackground(new Color(63, 114, 175));
        btnBack.setForeground(Color.white);

        btnRandom = new JButton("Random");
        btnRandom.addActionListener(this);
        btnRandom.setPreferredSize(new Dimension(100,50));
        btnRandom.setBackground(new Color(63, 114, 175));
        btnRandom.setForeground(Color.white);

        JPanel jPanelBot = new JPanel(new FlowLayout());
        jPanelBot.add(btnBack);
        jPanelBot.add(btnRandom);

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
        }
        else if(strAction.equals("Random")) {
            swRandom = this.slangWords.randomSlangWord();
            swLabelOutput.setText(swRandom[0]);
            defiLabelOutput.setText(swRandom[1]);
        }
    }
}
