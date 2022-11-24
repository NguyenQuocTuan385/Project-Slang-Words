package View;

import Controller.MenuController;
import Model.SlangWords;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {
    SlangWords sw;
    JLabel headerSlangWord;
    JButton btnListSw;
    JButton btnSearchSw;

    JButton btnSearchDefi;
    JButton btnShowHistory;
    JButton btnAddSw;
    JButton btnEditSw;
    JButton btnDeleteSw;
    JButton btnResetListSw;
    JButton btnRandomSW;
    JButton btnQuizAboutSW;
    JButton btnQuizAboutDefi;
    JLabel footerSlangWord;

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

        MenuController mc = new MenuController(this, sw);
        btnListSw = new JButton("Danh sách các slang word");
        btnListSw.addActionListener(mc);

        btnSearchSw = new JButton("Tìm kiếm slang word");
        btnSearchSw.addActionListener(mc);

        btnSearchDefi = new JButton("Tìm kiếm definition");
        btnSearchDefi.addActionListener(mc);

        btnShowHistory = new JButton("Lịch sử các slang word đã tìm kiếm");
        btnShowHistory.addActionListener(mc);

        btnAddSw = new JButton("Thêm slang word");
        btnAddSw.addActionListener(mc);

        btnEditSw = new JButton("Chỉnh sửa slang word");
        btnEditSw.addActionListener(mc);

        btnDeleteSw = new JButton("Xóa slang word");
        btnDeleteSw.addActionListener(mc);

        btnResetListSw = new JButton("Khôi phục danh sách slang word");
        btnResetListSw.addActionListener(mc);

        btnRandomSW = new JButton("Random slang word hôm nay");
        btnRandomSW.addActionListener(mc);

        btnQuizAboutSW = new JButton("Quiz về Slang Word");
        btnQuizAboutSW.addActionListener(mc);

        btnQuizAboutDefi = new JButton("Quiz về Definition");
        btnQuizAboutDefi.addActionListener(mc);

        JPanel jPanelBody = new JPanel();
        jPanelBody.setLayout(new GridLayout(3,5, 10, 10));
        jPanelBody.add(btnListSw);
        jPanelBody.add(btnSearchSw);
        jPanelBody.add(btnSearchDefi);
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
}
