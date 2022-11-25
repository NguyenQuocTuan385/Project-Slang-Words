package View;

import Model.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame implements ActionListener {
    private SlangWords sw;
    private JLabel headerSlangWord;
    private JButton btnListSw;
    private JButton btnShowHistory;
    private JButton btnEditSw;
    private JButton btnRandomSW;
    private JButton btnQuizAboutSW;
    private JButton btnQuizAboutDefi;
    private JLabel footerSlangWord;

    public MenuView() {
        sw = new SlangWords();

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(249, 247, 247));

        headerSlangWord = new JLabel("Slang Word");
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 24);
        Font fontBody = new Font("Arial", Font.BOLD, 20);
        headerSlangWord.setFont(fontHeaderAndFooter);
        headerSlangWord.setForeground(new Color(63, 114, 175));

        JPanel jPanelTop = new JPanel();
        jPanelTop.add(headerSlangWord);

        btnListSw = new JButton("Danh sách các slang word");
        btnListSw.addActionListener(this);
        btnListSw.setBackground(new Color(63, 114, 175));
        btnListSw.setForeground(Color.white);
        btnListSw.setFont(fontBody);

        btnShowHistory = new JButton("Lịch sử các slang word đã tìm kiếm");
        btnShowHistory.addActionListener(this);
        btnShowHistory.setBackground(new Color(63, 114, 175));
        btnShowHistory.setForeground(Color.white);
        btnShowHistory.setFont(fontBody);

        btnRandomSW = new JButton("Random slang word hôm nay");
        btnRandomSW.addActionListener(this);
        btnRandomSW.setBackground(new Color(63, 114, 175));
        btnRandomSW.setForeground(Color.white);
        btnRandomSW.setFont(fontBody);

        btnQuizAboutSW = new JButton("Quiz về Slang Word");
        btnQuizAboutSW.addActionListener(this);
        btnQuizAboutSW.setBackground(new Color(63, 114, 175));
        btnQuizAboutSW.setForeground(Color.white);
        btnQuizAboutSW.setFont(fontBody);

        btnQuizAboutDefi = new JButton("Quiz về Definition");
        btnQuizAboutDefi.addActionListener(this);
        btnQuizAboutDefi.setBackground(new Color(63, 114, 175));
        btnQuizAboutDefi.setForeground(Color.white);
        btnQuizAboutDefi.setFont(fontBody);

        JPanel jPanelBody = new JPanel();
        jPanelBody.setLayout(new GridLayout(3,5, 10, 10));
        jPanelBody.add(btnListSw);
        jPanelBody.add(btnShowHistory);
        jPanelBody.add(btnRandomSW);
        jPanelBody.add(btnQuizAboutSW);
        jPanelBody.add(btnQuizAboutDefi);

        footerSlangWord = new JLabel("20127659 - Nguyễn Quốc Tuấn");
        footerSlangWord.setFont(fontHeaderAndFooter);
        footerSlangWord.setForeground(new Color(63, 114, 175));

        JPanel jPanelBot = new JPanel();
        jPanelBot.add(footerSlangWord);

        this.setLayout(new BorderLayout());
        this.add(jPanelTop, BorderLayout.PAGE_START);
        this.add(jPanelBody, BorderLayout.CENTER);
        this.add(jPanelBot, BorderLayout.PAGE_END);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        if (strAction.equals("Danh sách các slang word")) {
            this.dispose();
            new ListSwView(this.sw);
        } else if (strAction.equals("Lịch sử các slang word đã tìm kiếm")) {
            this.dispose();
            new HistorySwView(this.sw);
        } else if (strAction.equals("Random slang word hôm nay")) {
            this.dispose();
            new RandomSlangWordTodayView(this.sw);
        } else if (strAction.equals("Quiz về Slang Word")) {
            this.dispose();
            new QuizForSlangView(this.sw, 1);
        } else if (strAction.equals("Quiz về Definition")) {
            this.dispose();
            new QuizForSlangView(this.sw, 2);
        }
    }
}
