package View;

import Model.SlangWords;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ListSwView extends JFrame implements ActionListener {
    private SlangWords slangWords;

    private JTextField jTextSearch;
    private JButton btnSearch;
    private HashMap<String, List<String>> dataSw;
    private DefaultTableModel dtmSw;
    private JTable jTableSw;
    private JLabel header;
    private JButton btnBack;

    public ListSwView(SlangWords sw) {
        this.slangWords = sw;

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Danh sách slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);

        header = new JLabel("Danh sách các slang word", JLabel.CENTER);
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 18);
        header.setFont(fontHeaderAndFooter);
        header.setForeground(Color.BLUE);

        jTextSearch = new JTextField(37);
        btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(this);

        JPanel jPanelTopBot = new JPanel();
        jPanelTopBot.setLayout(new FlowLayout());
        jPanelTopBot.add(jTextSearch);
        jPanelTopBot.add(btnSearch);


        JPanel jPanelTop = new JPanel();
        jPanelTop.setLayout(new BorderLayout());
        jPanelTop.add(header, BorderLayout.PAGE_START);
        jPanelTop.add(jPanelTopBot, BorderLayout.CENTER);

        dtmSw = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtmSw.addColumn("Number");
        dtmSw.addColumn("Slang word");
        dtmSw.addColumn("Definition");

        dataSw = this.slangWords.getListSw();

        loadDataSw(dataSw);

        jTableSw = new JTable(dtmSw);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTableSw.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableSw.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableSw.getColumnModel().getColumn(2).setPreferredWidth(300);

        JScrollPane sc = new JScrollPane(jTableSw, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel jPanelBody = new JPanel();
        jPanelBody.add(sc);

        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(this);
        btnBack.setPreferredSize(new Dimension(100,50));

        JPanel jPanelBot = new JPanel();
        jPanelBot.add(btnBack);

        this.setLayout(new BorderLayout());
        this.add(jPanelTop, BorderLayout.PAGE_START);
        this.add(jPanelBody, BorderLayout.CENTER);
        this.add(jPanelBot, BorderLayout.PAGE_END);

        this.setVisible(true);
    }

    public void loadDataSw(HashMap<String, List<String>> data) {
        dtmSw.setRowCount(0);
        Set<String> keySetSw = data.keySet();
        int index = 1;
        for(String key : keySetSw) {
            for(String defi : data.get(key)) {
                Vector<String> vec = new Vector<>();
                vec.add(String.valueOf(index));
                vec.add(key);
                vec.add(defi);
                dtmSw.addRow(vec);
                index++;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        if (strAction.equals("Quay lại")) {
            this.dispose();
            new MenuView();
        }
        else if(strAction.equals("Tìm kiếm")) {
            String swSearch = jTextSearch.getText();
            if (swSearch.equals("")) {
                loadDataSw(dataSw);
            } else {
                Object[] options = {"Tìm kiếm slang word", "Tìm kiếm definition"};
                int click = JOptionPane.showOptionDialog(null, "Lựa chọn tìm kiếm", "Tìm kiếm",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (click == JOptionPane.YES_OPTION) {
                    HashMap<String, List<String>> swAndDefi = slangWords.findSlangWord(swSearch);
                    if (swAndDefi.size() == 0) {
                        JOptionPane.showMessageDialog(this, "Không tồn tại slang word!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        loadDataSw(swAndDefi);
                    }
                }
                else if (click == JOptionPane.NO_OPTION) {
                    HashMap<String, List<String>> swAndDefi = slangWords.findDefinition(swSearch);
                    if (swAndDefi.size() == 0) {
                        JOptionPane.showMessageDialog(this, "Không tồn tại definition!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        loadDataSw(swAndDefi);
                    }
                }
            }
        }
    }
}