import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        SlangWords sw = new SlangWords();
        System.out.print("Nhập slang word muốn tìm:");
        String  slang = sc.nextLine();
        sw.findDefinition(slang);
    }
}