package Controller;

import Model.SlangWords;
import View.ListSwView;
import View.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    SlangWords sw;
    MenuView menuView;

    public MenuController(MenuView mv, SlangWords sw) {
        menuView = mv;
        this.sw = sw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        if (strAction.equals("Danh sách các slang word")) {
            menuView.dispose();
            new ListSwView(this.sw);
        } else if (strAction.equals("Tìm kiếm slang word")) {
            menuView.dispose();
        } else if (strAction.equals("Tìm kiếm definition")) {
            menuView.dispose();
        } else if (strAction.equals("Lịch sử các slang word đã tìm kiếm")) {
            menuView.dispose();
        } else if (strAction.equals("Thêm slang word")) {
            menuView.dispose();
        } else if (strAction.equals("Chỉnh sửa slang word")) {
            menuView.dispose();
        } else if (strAction.equals("Xóa slang word")) {
            menuView.dispose();
        } else if (strAction.equals("Khôi phục danh sách slang word")) {
            menuView.dispose();
        } else if (strAction.equals("Random slang word hôm nay")) {
            menuView.dispose();
        } else if (strAction.equals("Quiz về Slang Word")) {
            menuView.dispose();
        } else if (strAction.equals("Quiz về Definition")) {
            menuView.dispose();
        }

    }
}
