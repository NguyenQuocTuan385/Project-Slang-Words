package View;

import Model.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame implements ActionListener {
    private SlangWords sw;
    private JLabel headerSlangWord;
    private JLabel footerSlangWord;

    public MenuView() {
        sw = new SlangWords();

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(249, 247, 247));

        headerSlangWord = new JLabel("Slang Word");
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 24);
        Font fontBody = new Font("Arial", Font.BOLD, 20);
        headerSlangWord.setFont(fontHeaderAndFooter);
        headerSlangWord.setForeground(new Color(17, 45, 78));

        JPanel jPanelTop = new JPanel();
        jPanelTop.add(headerSlangWord);

        ImageIcon listImg = new ImageIcon("./images/list-icon.png");
        JButton btnListSw = new JButton("Danh sách các slang word", listImg);
        btnListSw.addActionListener(this);
        btnListSw.setBackground(new Color(63, 114, 175));
        btnListSw.setForeground(Color.white);
        btnListSw.setFont(fontBody);

        ImageIcon historyImg = new ImageIcon("./images/history-icon.png");
        JButton btnShowHistory = new JButton("Lịch sử các slang word đã tìm kiếm", historyImg);
        btnShowHistory.addActionListener(this);
        btnShowHistory.setBackground(new Color(63, 114, 175));
        btnShowHistory.setForeground(Color.white);
        btnShowHistory.setFont(fontBody);

        ImageIcon quizImg = new ImageIcon("./images/quiz-icon.png");
        JButton btnRandomSW = new JButton("Random slang word hôm nay", quizImg);
        btnRandomSW.addActionListener(this);
        btnRandomSW.setBackground(new Color(63, 114, 175));
        btnRandomSW.setForeground(Color.white);
        btnRandomSW.setFont(fontBody);

        JButton btnQuizAboutSW = new JButton("Quiz về Slang Word", quizImg);
        btnQuizAboutSW.addActionListener(this);
        btnQuizAboutSW.setBackground(new Color(63, 114, 175));
        btnQuizAboutSW.setForeground(Color.white);
        btnQuizAboutSW.setFont(fontBody);

        JButton btnQuizAboutDefi = new JButton("Quiz về Definition", quizImg);
        btnQuizAboutDefi.addActionListener(this);
        btnQuizAboutDefi.setBackground(new Color(63, 114, 175));
        btnQuizAboutDefi.setForeground(Color.white);
        btnQuizAboutDefi.setFont(fontBody);

        ImageIcon exitImg = new ImageIcon("./images/Exit-icon.png");
        JButton btnExit = new JButton("Thoát", exitImg);
        btnExit.addActionListener(this);
        btnExit.setBackground(new Color(63, 114, 175));
        btnExit.setForeground(Color.white);
        btnExit.setFont(fontBody);

        JPanel jPanelBody = new JPanel();
        jPanelBody.setLayout(new GridLayout(3, 5, 20, 20));
        jPanelBody.add(btnListSw);
        jPanelBody.add(btnShowHistory);
        jPanelBody.add(btnRandomSW);
        jPanelBody.add(btnQuizAboutSW);
        jPanelBody.add(btnQuizAboutDefi);
        jPanelBody.add(btnExit);

        footerSlangWord = new JLabel("20127659 - Nguyễn Quốc Tuấn");
        footerSlangWord.setFont(fontHeaderAndFooter);
        footerSlangWord.setForeground(new Color(17, 45, 78));

        JPanel jPanelBot = new JPanel();
        jPanelBot.add(footerSlangWord);

        this.setLayout(new BorderLayout());
        this.add(jPanelTop, BorderLayout.PAGE_START);
        this.add(new JPanel(), BorderLayout.LINE_START);
        this.add(jPanelBody, BorderLayout.CENTER);
        this.add(new JPanel(), BorderLayout.LINE_END);
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
        } else if (strAction.equals("Thoát")) {
            this.dispose();
        }
    }
}
