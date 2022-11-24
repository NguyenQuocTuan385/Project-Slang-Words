package View;

import Controller.ListSwController;
import Model.SlangWords;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ListSwView extends JFrame {
    SlangWords sw;
    private HashMap<String, List<String>> data;
    DefaultTableModel dtmSw;
    JTable jTableSw;
    JLabel header;
    JButton btnBack;

    public ListSwView(SlangWords sw) {
        this.sw = sw;

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Danh sách slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);

        header = new JLabel("Danh sách các slang word");
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 18);
        header.setFont(fontHeaderAndFooter);
        header.setForeground(Color.BLUE);

        JPanel jPanelTop = new JPanel();
        jPanelTop.add(header);

        dtmSw = new DefaultTableModel();
        dtmSw.setRowCount(0);
        dtmSw.addColumn("Slang word");
        dtmSw.addColumn("Definition");

        data = this.sw.getListSw();

        Set<String> keySetSw = data.keySet();
        for(String key : keySetSw) {
            for(String defi : data.get(key)) {
                Vector<String> vec = new Vector<>();
                vec.add(key);
                vec.add(defi);
                dtmSw.addRow(vec);
            }
        }

        jTableSw = new JTable(dtmSw);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTableSw.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        JScrollPane sc = new JScrollPane(jTableSw, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel jPanelBody = new JPanel();
        jPanelBody.add(sc);

        ListSwController lsc = new ListSwController(this);
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(lsc);
        btnBack.setPreferredSize(new Dimension(100,50));

        JPanel jPanelBot = new JPanel();
        jPanelBot.setLayout(new BorderLayout());
        jPanelBot.add(Box.createRigidArea(new Dimension(0,5)));
        jPanelBot.add(btnBack, BorderLayout.PAGE_START);

        JPanel jPanelBot1 = new JPanel();
        jPanelBot1.add(jPanelBot);

        this.setLayout(new BorderLayout());
        this.add(jPanelTop, BorderLayout.PAGE_START);
        this.add(jPanelBody, BorderLayout.CENTER);
        this.add(jPanelBot1, BorderLayout.PAGE_END);

        this.setVisible(true);
    }
}
