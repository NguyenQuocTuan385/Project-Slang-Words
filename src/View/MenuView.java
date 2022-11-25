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
    private JButton btnAddSw;
    private JButton btnEditSw;
    private JButton btnDeleteSw;
    private JButton btnResetListSw;
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

        headerSlangWord = new JLabel("Slang Word");
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 18);
        headerSlangWord.setFont(fontHeaderAndFooter);
        headerSlangWord.setForeground(Color.BLUE);

        JPanel jPanelTop = new JPanel();
        jPanelTop.add(headerSlangWord);

        btnListSw = new JButton("Danh sách các slang word");
        btnListSw.addActionListener(this);

        btnShowHistory = new JButton("Lịch sử các slang word đã tìm kiếm");
        btnShowHistory.addActionListener(this);

        btnAddSw = new JButton("Thêm slang word");
        btnAddSw.addActionListener(this);

        btnEditSw = new JButton("Chỉnh sửa slang word");
        btnEditSw.addActionListener(this);

        btnDeleteSw = new JButton("Xóa slang word");
        btnDeleteSw.addActionListener(this);

        btnResetListSw = new JButton("Khôi phục danh sách slang word");
        btnResetListSw.addActionListener(this);

        btnRandomSW = new JButton("Random slang word hôm nay");
        btnRandomSW.addActionListener(this);

        btnQuizAboutSW = new JButton("Quiz về Slang Word");
        btnQuizAboutSW.addActionListener(this);

        btnQuizAboutDefi = new JButton("Quiz về Definition");
        btnQuizAboutDefi.addActionListener(this);

        JPanel jPanelBody = new JPanel();
        jPanelBody.setLayout(new GridLayout(3,5, 10, 10));
        jPanelBody.add(btnListSw);
        jPanelBody.add(btnShowHistory);
        jPanelBody.add(btnAddSw);
        jPanelBody.add(btnEditSw);
        jPanelBody.add(btnDeleteSw);
        jPanelBody.add(btnResetListSw);
        jPanelBody.add(btnRandomSW);
        jPanelBody.add(btnQuizAboutSW);
        jPanelBody.add(btnQuizAboutDefi);

        footerSlangWord = new JLabel("20127659 - Nguyễn Quốc Tuấn");
        footerSlangWord.setFont(fontHeaderAndFooter);
        footerSlangWord.setForeground(Color.BLUE);

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
        } else if (strAction.equals("Thêm slang word")) {
            this.dispose();
        } else if (strAction.equals("Chỉnh sửa slang word")) {
            this.dispose();
        } else if (strAction.equals("Xóa slang word")) {
            this.dispose();
        } else if (strAction.equals("Khôi phục danh sách slang word")) {
            this.dispose();
        } else if (strAction.equals("Random slang word hôm nay")) {
            this.dispose();
        } else if (strAction.equals("Quiz về Slang Word")) {
            this.dispose();
        } else if (strAction.equals("Quiz về Definition")) {
            this.dispose();
        }
    }
}
