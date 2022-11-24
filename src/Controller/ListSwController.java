package Controller;

import View.ListSwView;
import View.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListSwController implements ActionListener {
    private ListSwView listSwView;
    public ListSwController(ListSwView lsv) {
        this.listSwView = lsv;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        if (strAction.equals("Quay lại")) {
            this.listSwView.dispose();
            new MenuView();
        }
    }
}
