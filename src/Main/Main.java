package Main;
import Model.SlangWords;
import View.MenuView;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new MenuView();
//        SlangWords sw = new SlangWords();
//        int choice;
//        do {
//            System.out.println("-------------------------------Menu-------------------------------");
//            System.out.println("0. Thoát");
//            System.out.println("1. Tìm kiếm theo slang word");
//            System.out.println("2. Tìm kiếm theo definition");
//            System.out.println("3. Hiển thị lịch sử slang word đã tìm kiếm");
//            System.out.println("4. Thêm slang word mới");
//            System.out.println("5. Chỉnh sửa một slang word");
//            System.out.println("6. Xóa một slang word");
//            System.out.println("7. Reset danh sách slang words gốc");
//            System.out.println("8. Random 1 slang word");
//            System.out.println("9. Đố vui theo slang word");
//            System.out.println("10. Đố vui theo definition");
//            System.out.print("Nhập lựa chọn của bạn:");
//            choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//                case 1: {
//                    System.out.println("----------------------------------------");
//                    System.out.print("Nhập slang word bạn muốn tìm:");
//                    String slangWord = sc.nextLine();
//                    sw.findSlangWord(slangWord);
//                    break;
//                }
//                case 2: {
//                    System.out.println("----------------------------------------");
//                    System.out.print("Nhập definition bạn muốn tìm:");
//                    String defi = sc.nextLine();
//                    sw.findDefinition(defi);
//                    break;
//                }
//                case 3: {
//                    sw.readHistory();
//                    break;
//                }
//                case 4: {
//                    System.out.println("----------------------------------------");
//                    System.out.print("Nhập slang word bạn muốn thêm:");
//                    String slangWord = sc.nextLine();
//                    System.out.print("Nhập definition bạn muốn thêm:");
//                    String defi = sc.nextLine();
//                    sw.addSlangWord(slangWord, defi);
//                    break;
//                }
//                case 5: {
//                    System.out.println("----------------------------------------");
//                    System.out.print("Nhập slang word bạn muốn chỉnh sửa:");
//                    String slangWord = sc.nextLine();
//                    sw.editSlangWord(slangWord);
//                    break;
//                }
//                case 6: {
//                    System.out.println("----------------------------------------");
//                    System.out.print("Nhập slang word bạn muốn xóa:");
//                    String slangWord = sc.nextLine();
//                    sw.deleteSlangWord(slangWord);
//                    break;
//                }
//                case 7: {
//                    sw.resetSlangWord();
//                    sw.readFile(sw.getFileSW());
//                    break;
//                }
//                case 8: {
//                    System.out.println("----------------------------------------");
//                    System.out.println("On this day slang word");
//                    String[] swAndDefi = sw.randomSlangWord();
//                    System.out.println("Slang word:" + swAndDefi[0]);
//                    break;
//                }
//                case 9: {
//                    sw.quiz(1);
//                    break;
//                }
//                case 10: {
//                    sw.quiz(2);
//                    break;
//                }
//                default:
//                    break;
//            }
//        }while (choice != 0);
    }
}