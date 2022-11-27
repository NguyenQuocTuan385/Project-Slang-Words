package View;

import Model.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class QuizForSlangView extends JFrame implements ActionListener {
    private int option;
    private SlangWords slangWords;
    private JLabel header;
    private JLabel swQuestionLabel;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;
    private JButton btnD;
    private String swQuestion;
    private String defiCorrect;
    private int numberClick = 1;

    public QuizForSlangView(SlangWords sw, int option) {
        this.slangWords = sw;
        this.option = option;

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Quiz for slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(249, 247, 247));

        if (this.option == 1) {
            header = new JLabel("Hãy chọn đáp án đúng với slang word sau đây", JLabel.CENTER);
        } else {
            header = new JLabel("Hãy chọn đáp án đúng với definition sau đây", JLabel.CENTER);
        }

        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 24);
        Font fontBody = new Font("Arial", Font.BOLD, 20);
        header.setFont(fontHeaderAndFooter);
        header.setForeground(new Color(17, 45, 78));

        swQuestionLabel = new JLabel("", JLabel.CENTER);
        swQuestionLabel.setFont(fontHeaderAndFooter);
        swQuestionLabel.setForeground(new Color(63, 114, 175));

        JPanel jPanelTop = new JPanel(new BorderLayout());
        jPanelTop.add(header, BorderLayout.PAGE_START);
        jPanelTop.add(swQuestionLabel, BorderLayout.CENTER);

        JPanel jPanelBody = new JPanel(new GridLayout(2, 2, 10, 10));

        btnA = new JButton("A");
        btnA.addActionListener(this);
        btnA.setBackground(new Color(219, 226, 239));
        btnA.setFont(fontBody);
        btnA.setActionCommand("A");

        btnB = new JButton("B");
        btnB.addActionListener(this);
        btnB.setBackground(new Color(219, 226, 239));
        btnB.setFont(fontBody);
        btnB.setActionCommand("B");

        btnC = new JButton("C");
        btnC.addActionListener(this);
        btnC.setBackground(new Color(219, 226, 239));
        btnC.setFont(fontBody);
        btnC.setActionCommand("C");

        btnD = new JButton("D");
        btnD.addActionListener(this);
        btnD.setBackground(new Color(219, 226, 239));
        btnD.setFont(fontBody);
        btnD.setActionCommand("D");

        this.loadDataQuiz(this.option);

        jPanelBody.add(btnA);
        jPanelBody.add(btnB);
        jPanelBody.add(btnC);
        jPanelBody.add(btnD);

        ImageIcon backImg = new ImageIcon("Go-back-icon.png");
        JButton btnBack = new JButton("Quay lại", backImg);
        btnBack.addActionListener(this);
        btnBack.setPreferredSize(new Dimension(100, 50));
        btnBack.setBackground(new Color(63, 114, 175));
        btnBack.setForeground(Color.white);

        ImageIcon resetImg = new ImageIcon("Restore-Window-icon.png");
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

    public void loadDataQuiz(int option) {
        HashMap<String, Integer> swAndDefiQuiz = new HashMap<>();
        if (option == 1) {
            swAndDefiQuiz = slangWords.quiz(1);
        } else {
            swAndDefiQuiz = slangWords.quiz(2);
        }

        List<String> defiAnswer = new ArrayList<>();
        Set<String> swAndDefi = swAndDefiQuiz.keySet();
        for (String swDefi : swAndDefi) {
            if (swAndDefiQuiz.get(swDefi) == 2) {
                swQuestion = swDefi;
            } else {
                defiAnswer.add(swDefi);
            }
            if (swAndDefiQuiz.get(swDefi) == 1) {
                defiCorrect = swDefi;
            }
        }
        Collections.shuffle(defiAnswer);

        swQuestionLabel.setText(swQuestion);
        btnA.setText(defiAnswer.get(0));
        btnB.setText(defiAnswer.get(1));
        btnC.setText(defiAnswer.get(2));
        btnD.setText(defiAnswer.get(3));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();

        if (strAction.equals("Quay lại")) {
            this.dispose();
            new MenuView();
        } else if (strAction.equals("Reset")) {
            this.loadDataQuiz(this.option);
            numberClick = 1;
            btnA.setBackground(new Color(219, 226, 239));
            btnB.setBackground(new Color(219, 226, 239));
            btnC.setBackground(new Color(219, 226, 239));
            btnD.setBackground(new Color(219, 226, 239));
        } else if (strAction.equals("A") && numberClick == 1) {
            if (btnA.getText().equals(defiCorrect)) {
                btnA.setBackground(Color.GREEN);
                JOptionPane.showMessageDialog(this, "Chúc mừng bạn đã chọn đáp án đúng!!!", "Thông báo"
                        , JOptionPane.INFORMATION_MESSAGE);
            } else {
                btnA.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Rất tiếc bạn đã chọn đáp án sai!!!", "Thông báo"
                        , JOptionPane.ERROR_MESSAGE);

                if (btnB.getText().equals(defiCorrect)) {
                    btnB.setBackground(Color.GREEN);
                } else if (btnC.getText().equals(defiCorrect)) {
                    btnC.setBackground(Color.GREEN);
                } else if (btnD.getText().equals(defiCorrect)) {
                    btnD.setBackground(Color.GREEN);
                }
            }
            numberClick++;
        } else if (strAction.equals("B") && numberClick == 1) {
            if (btnB.getText().equals(defiCorrect)) {
                btnB.setBackground(Color.GREEN);
                JOptionPane.showMessageDialog(this, "Chúc mừng bạn đã chọn đáp án đúng!!!", "Thông báo"
                        , JOptionPane.INFORMATION_MESSAGE);
            } else {
                btnB.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Rất tiếc bạn đã chọn đáp án sai!!!", "Thông báo"
                        , JOptionPane.ERROR_MESSAGE);

                if (btnA.getText().equals(defiCorrect)) {
                    btnA.setBackground(Color.GREEN);
                } else if (btnC.getText().equals(defiCorrect)) {
                    btnC.setBackground(Color.GREEN);
                } else if (btnD.getText().equals(defiCorrect)) {
                    btnD.setBackground(Color.GREEN);
                }
            }
            numberClick++;
        } else if (strAction.equals("C") && numberClick == 1) {
            if (btnC.getText().equals(defiCorrect)) {
                btnC.setBackground(Color.GREEN);
                JOptionPane.showMessageDialog(this, "Chúc mừng bạn đã chọn đáp án đúng!!!", "Thông báo"
                        , JOptionPane.INFORMATION_MESSAGE);
            } else {
                btnC.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Rất tiếc bạn đã chọn đáp án sai!!!", "Thông báo"
                        , JOptionPane.ERROR_MESSAGE);

                if (btnA.getText().equals(defiCorrect)) {
                    btnA.setBackground(Color.GREEN);
                } else if (btnB.getText().equals(defiCorrect)) {
                    btnB.setBackground(Color.GREEN);
                } else if (btnD.getText().equals(defiCorrect)) {
                    btnD.setBackground(Color.GREEN);
                }
            }
            numberClick++;
        } else if (strAction.equals("D") && numberClick == 1) {
            if (btnD.getText().equals(defiCorrect)) {
                btnD.setBackground(Color.GREEN);
                JOptionPane.showMessageDialog(this, "Chúc mừng bạn đã chọn đáp án đúng!!!", "Thông báo"
                        , JOptionPane.INFORMATION_MESSAGE);
            } else {
                btnD.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Rất tiếc bạn đã chọn đáp án sai!!!", "Thông báo"
                        , JOptionPane.ERROR_MESSAGE);

                if (btnA.getText().equals(defiCorrect)) {
                    btnA.setBackground(Color.GREEN);
                } else if (btnB.getText().equals(defiCorrect)) {
                    btnB.setBackground(Color.GREEN);
                } else if (btnC.getText().equals(defiCorrect)) {
                    btnC.setBackground(Color.GREEN);
                }
            }
            numberClick++;
        }
    }
}
