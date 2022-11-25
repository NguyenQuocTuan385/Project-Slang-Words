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
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnReset;
    private JTextField swField;
    private JTextField defiField;

    public ListSwView(SlangWords sw) {
        this.slangWords = sw;

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Danh sách slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);

        jTextSearch = new JTextField(40);
        btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(this);
        btnSearch.setBackground(new Color(63, 114, 175));
        btnSearch.setForeground(Color.white);

        JPanel jPanelTopBot = new JPanel();
        jPanelTopBot.setLayout(new FlowLayout());
        jPanelTopBot.add(jTextSearch);
        jPanelTopBot.add(btnSearch);

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

        header = new JLabel("Danh sách " + dataSw.size() + " slang word khác nhau trong hệ thống", JLabel.CENTER);
        Font fontHeaderAndFooter = new Font("Arial", Font.BOLD, 24);
        header.setFont(fontHeaderAndFooter);
        header.setForeground(new Color(63, 114, 175));

        JPanel jPanelTop = new JPanel();
        jPanelTop.setLayout(new BorderLayout());
        jPanelTop.add(header, BorderLayout.PAGE_START);
        jPanelTop.add(jPanelTopBot, BorderLayout.CENTER);

        loadDataSw(dataSw);

        jTableSw = new JTable(dtmSw);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTableSw.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTableSw.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableSw.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableSw.getColumnModel().getColumn(2).setPreferredWidth(300);

        JScrollPane sc = new JScrollPane(jTableSw, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel jPanelBody = new JPanel();
        jPanelBody.add(sc);

        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(this);
        btnBack.setPreferredSize(new Dimension(100,50));
        btnBack.setBackground(new Color(63, 114, 175));
        btnBack.setForeground(Color.white);

        btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(this);
        btnAdd.setPreferredSize(new Dimension(100,50));
        btnAdd.setBackground(new Color(63, 114, 175));
        btnAdd.setForeground(Color.white);

        btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(this);
        btnDelete.setPreferredSize(new Dimension(100,50));
        btnDelete.setBackground(new Color(63, 114, 175));
        btnDelete.setForeground(Color.white);

        btnUpdate = new JButton("Cập nhật");
        btnUpdate.addActionListener(this);
        btnUpdate.setPreferredSize(new Dimension(100,50));
        btnUpdate.setBackground(new Color(63, 114, 175));
        btnUpdate.setForeground(Color.white);

        btnReset= new JButton("Khôi phục");
        btnReset.addActionListener(this);
        btnReset.setPreferredSize(new Dimension(100,50));
        btnReset.setBackground(new Color(63, 114, 175));
        btnReset.setForeground(Color.white);

        JPanel jPanelBot = new JPanel(new FlowLayout());
        jPanelBot.add(btnBack);
        jPanelBot.add(btnAdd);
        jPanelBot.add(btnDelete);
        jPanelBot.add(btnUpdate);
        jPanelBot.add(btnReset);

        JPanel jPanelBot1 = new JPanel();
        jPanelBot1.add(jPanelBot);

        this.setLayout(new BorderLayout());
        this.add(jPanelTop, BorderLayout.PAGE_START);
        this.add(jPanelBody, BorderLayout.CENTER);
        this.add(jPanelBot1, BorderLayout.PAGE_END);

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
//    private void jTableMouseClicked(MouseEvent evt) {
//        DefaultTableModel tbModel = (DefaultTableModel) jTableSw.getModel();
//
//        String sw = tbModel.getValueAt(jTableSw.getSelectedRow(), 1).toString();
//        String defi = tbModel.getValueAt(jTableSw.getSelectedRow(), 2).toString();
//
//        swField.setText(sw);
//        defiField.setText(defi);
//    }
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
        else if (strAction.equals("Thêm")) {
            swField = new JTextField(20);
            defiField = new JTextField(20);

            JPanel addPanelTop = new JPanel(new BorderLayout());
            JLabel headerAddPanel = new JLabel("Vui lòng nhập Slang word và Definition muốn thêm");
            headerAddPanel.setForeground(new Color(63, 114, 175));

            addPanelTop.add(headerAddPanel);
            JPanel addPanelBody = new JPanel(new BorderLayout());
            JPanel swPanelInput = new JPanel(new FlowLayout());
            JPanel defiPanelInput = new JPanel(new FlowLayout());

            swPanelInput.add(new JLabel("Slang word:"));
            swPanelInput.add(swField);

            defiPanelInput.add(new JLabel("Definition:"));
            defiPanelInput.add(defiField);

            addPanelBody.add(swPanelInput, BorderLayout.PAGE_START);
            addPanelBody.add(defiPanelInput, BorderLayout.CENTER);

            JPanel addPanel = new JPanel(new BorderLayout());
            addPanel.add(addPanelTop, BorderLayout.PAGE_START);
            addPanel.add(addPanelBody, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(null, addPanel,
                    "Thêm Slang Word", JOptionPane.OK_CANCEL_OPTION);
            String swInput = swField.getText();
            String defiInput = defiField.getText();

            if (result == JOptionPane.OK_OPTION) {
                if(swInput.equals("") || defiInput.equals("")) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa nhập slang word hoặc defi!!!", "Cảnh báo"
                            , JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if(dataSw.containsKey(swInput)) {
                        Object[] options = {"Overwrite", "Duplicate"};
                        int click = JOptionPane.showOptionDialog(null, "Lựa chọn ghi đè"
                                , "Đã tồn tại slang word", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (click == JOptionPane.YES_OPTION) {
                            this.slangWords.addSlangWord(swInput, defiInput, 1);
                            dataSw = slangWords.getListSw();
                            loadDataSw(dataSw);
                        }
                        else if (click == JOptionPane.NO_OPTION) {
                            this.slangWords.addSlangWord(swInput, defiInput, 2);
                            dataSw = slangWords.getListSw();
                            loadDataSw(dataSw);
                        }
                    }
                    else {
                        this.slangWords.addSlangWord(swInput, defiInput, 3);
                        dataSw = slangWords.getListSw();
                        loadDataSw(dataSw);
                        JOptionPane.showMessageDialog(this, "Thêm thành công slang word vào hệ thống"
                                , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        else if (strAction.equals("Xóa")) {
            JTextField swField = new JTextField(20);

            JLabel headerDeletePanel = new JLabel("Vui lòng nhập Slang word muốn xóa");
            headerDeletePanel.setForeground(Color.BLUE);
            headerDeletePanel.setForeground(new Color(63, 114, 175));

            JPanel addPanelTop = new JPanel(new BorderLayout());
            addPanelTop.add(headerDeletePanel);

            JPanel addPanelBody = new JPanel(new BorderLayout());
            JPanel swPanelInput = new JPanel(new FlowLayout());

            swPanelInput.add(new JLabel("Slang word:"));
            swPanelInput.add(swField);

            addPanelBody.add(swPanelInput, BorderLayout.CENTER);

            JPanel addPanel = new JPanel(new BorderLayout());
            addPanel.add(addPanelTop, BorderLayout.PAGE_START);
            addPanel.add(addPanelBody, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(null, addPanel,
                    "Xóa Slang Word", JOptionPane.OK_CANCEL_OPTION);

            String swInput = swField.getText();
            if (result == JOptionPane.OK_OPTION) {
                if (swInput.equals("")) {
                    JOptionPane.showMessageDialog(this, "Bạn chưa nhập slang word!!!", "Cảnh báo"
                            , JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if(dataSw.containsKey(swInput)) {
                        Object[] options = {"Ok", "Cancel"};
                        int click = JOptionPane.showOptionDialog(null, "Bạn có thật sự muốn xóa Slang word này không?"
                                , "Xác nhận xóa Slang word", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (click == JOptionPane.YES_OPTION) {
                            this.slangWords.deleteSlangWord(swInput);
                            dataSw = slangWords.getListSw();
                            loadDataSw(dataSw);
                            JOptionPane.showMessageDialog(this, "Xóa slang word thành công"
                                    , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tồn tại definition muốn xóa!!!"
                                , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        else if(strAction.equals("Cập nhật")) {
            if (!jTableSw.getSelectionModel().isSelectionEmpty()) {
                swField = new JTextField(20);
                defiField = new JTextField(20);

                DefaultTableModel model = (DefaultTableModel) jTableSw.getModel();

                int selectedRowIndex = jTableSw.getSelectedRow();
                String sw = model.getValueAt(selectedRowIndex,1).toString();
                String defi = model.getValueAt(selectedRowIndex,2).toString();

                swField.setText(sw);
                defiField.setText(defi);

                JPanel updatePanelTop = new JPanel(new BorderLayout());
                JLabel headerUpdatePanel = new JLabel("Vui lòng nhập Slang word và Definition muốn chỉnh sửa");
                headerUpdatePanel.setForeground(new Color(63, 114, 175));

                updatePanelTop.add(headerUpdatePanel);
                JPanel updatePanelBody = new JPanel(new BorderLayout());
                JPanel swPanelInput = new JPanel(new FlowLayout());
                JPanel defiPanelInput = new JPanel(new FlowLayout());

                swPanelInput.add(new JLabel("Slang word:"));
                swPanelInput.add(swField);

                defiPanelInput.add(new JLabel("Definition:"));
                defiPanelInput.add(defiField);

                updatePanelBody.add(swPanelInput, BorderLayout.PAGE_START);
                updatePanelBody.add(defiPanelInput, BorderLayout.CENTER);

                JPanel addPanel = new JPanel(new BorderLayout());
                addPanel.add(updatePanelTop, BorderLayout.PAGE_START);
                addPanel.add(updatePanelBody, BorderLayout.CENTER);

                int result = JOptionPane.showConfirmDialog(null, addPanel,
                        "Chỉnh sửa Slang Word", JOptionPane.OK_CANCEL_OPTION);
                String swInput = swField.getText();
                String defiInput = defiField.getText();

                if (result == JOptionPane.OK_OPTION) {
                    if(swInput.equals("") || defiInput.equals("")) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa nhập slang word hoặc defi!!!", "Cảnh báo"
                                , JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        if (swInput.equals(sw)) {  //Không sửa tên slang word
                            int index = 0;
                            for(String defiCheck : dataSw.get(sw)) { //Duyệt vòng lặp để sửa defi đó
                                if  (defiCheck.equals(defi)) {
                                    dataSw.get(sw).set(index, defiInput);
                                    break;
                                }
                                index++;
                            }
                        }
                        else { //Có sửa tên slang word
                            if (dataSw.containsKey(swInput)) { //Kiểm tra xem có slang word nào trùng vs tên đã sửa chưa
                                if (dataSw.get(sw).size() == 1) //Trong slang word bị sửa chỉ có một definition
                                {
                                    dataSw.remove(sw); //Xóa slang word đó
                                }
                                else { //TRong slang word bị sửa có nhiều hơn một definition
                                    for (String defiCheck : dataSw.get(sw)) { //Duyệt vòng lặp để sửa defi đó
                                        if (defiCheck.equals(defi)) {
                                            dataSw.get(sw).remove(defi);
                                            break;
                                        }
                                    }
                                }
                                dataSw.get(swInput).add(defiInput);
                            }
                            else { //Chưa có slang word nào trùng tên muốn sửa
                                if (dataSw.get(sw).size() == 1) //Trong slang word bị sửa chỉ có một definition
                                {
                                    dataSw.remove(sw); //Xóa slang word đó
                                }
                                else { //TRong slang word bị sửa có nhiều hơn một definition
                                    for (String defiCheck : dataSw.get(sw)) { //Duyệt vòng lặp để sửa defi đó
                                        if (defiCheck.equals(defi)) {
                                            dataSw.get(sw).remove(defi);
                                            break;
                                        }
                                    }
                                }
                                dataSw.put(swInput, Arrays.asList(defiInput));
                            }
                        }
                        this.slangWords.setListSw(dataSw);
                        this.slangWords.saveFile(this.slangWords.getFileSW(), dataSw, false);
                        loadDataSw(dataSw);
                        JOptionPane.showMessageDialog(this, "Cập nhật slang word thành công"
                                , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        else if(strAction.equals("Khôi phục")) {
            Object[] options = {"Ok", "Cancel"};
            int click = JOptionPane.showOptionDialog(null, "Bạn có thật sự muốn khôi phục danh sách Slang word không?"
                    , "Xác nhận khôi phục Slang word", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (click == JOptionPane.YES_OPTION) {
                this.slangWords.resetSlangWord();
                dataSw = slangWords.getListSw();
                loadDataSw(dataSw);
                JOptionPane.showMessageDialog(this, "Khôi phục danh sách thành công!!!"
                        , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
