import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        SlangWords sw = new SlangWords();
        int choice;
        do {
            System.out.println("0. Thoát");
            System.out.println("1. Tìm kiếm theo slang word");
            System.out.println("2. Tìm kiếm theo definition");
            System.out.println("4. Thêm slang words mới");
            System.out.print("Nhập lựa chọn của bạn");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
                    System.out.println("----------------------------------------");
                    System.out.print("Nhập slang word bạn muốn tìm:");
                    String slangWord = sc.nextLine();
                    sw.findSlangWord(slangWord);
                    break;
                }
                case 2: {
                    System.out.println("----------------------------------------");
                    System.out.print("Nhập definition bạn muốn tìm:");
                    String defi = sc.nextLine();
                    sw.findDefinition(defi);
                    break;
                }
                case 4: {
                    System.out.println("----------------------------------------");
                    System.out.print("Nhập slang word bạn muốn thêm:");
                    String slangWord = sc.nextLine();
                    System.out.print("Nhập definition bạn muốn thêm:");
                    String defi = sc.nextLine();
                    sw.addSlangWord(slangWord, defi);
                    break;
                }
            }
        }while (choice != 0);
    }
}