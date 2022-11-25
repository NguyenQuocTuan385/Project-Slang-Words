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

public class HistorySwView extends JFrame implements ActionListener {
    private SlangWords slangWords;
    private HashMap<String, List<String>> dataSw;
    private DefaultTableModel dtmSw;
    private JTable jTableSw;
    private JLabel header;
    private JButton btnBack;

    public HistorySwView(SlangWords sw) {
        this.slangWords = sw;

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Lịch sử slang word đã tìm kiếm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);

        header = new JLabel("Lịch sử slang word đã tìm kiếm", JLabel.CENTER);
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 18);
        header.setFont(fontHeaderAndFooter);
        header.setForeground(Color.BLUE);

        JPanel jPanelTop = new JPanel();
        jPanelTop.add(header);

        dtmSw = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtmSw.addColumn("Number");
        dtmSw.addColumn("Slang word đã tìm");
        dtmSw.addColumn("Definition");

        loadDataSwSearch();

        jTableSw = new JTable(dtmSw);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTableSw.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

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

    public void loadDataSwSearch() {
        dtmSw.setRowCount(0);
        HashMap<String, List<String>> swAndDefiSearched = this.slangWords.readHistory();
        Set<String> keySetSw = swAndDefiSearched.keySet();
        int index = 1;
        for(String key : keySetSw) {
            for(String defi : swAndDefiSearched.get(key)) {
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
    }
}