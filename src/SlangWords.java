import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class SlangWords {
    private HashMap<String, List<String>> sw = new HashMap<String, List<String>>();
    private String File_SlangWord = "slang.txt";
    private String File_SlangWordOrigin = "slangOrigin.txt";
    private String File_SlangWordHistory = "slangHistory.txt";
    static Scanner sc = new Scanner(System.in);
    public SlangWords() {
        ReadFile(File_SlangWord);
    }

    public void ReadFile(String inputFile) {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(inputFile));
            String line = "";

            while (true) {
                List<String> listDefi = new ArrayList<String>();
                line = fin.readLine();

                if (line == null)
                    break;

                String slAndDefi[] = line.split("`");
                String[] arrDefi = slAndDefi[1].split("\\|");

                for(String defi : arrDefi) {
                    listDefi.add(defi.trim());
                }

                sw.put(slAndDefi[0], listDefi);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void display() {
        Set<String> keySet = sw.keySet();
        for (String key : keySet) {
            for (String defi : sw.get(key)) {
                System.out.println("slang:" + key + " defi:" + defi);
            }
        }
    }
    public void findSlangWord(String slangWord) {
        if (sw.containsKey(slangWord)) {
            for (String defi : sw.get(slangWord)) {
                System.out.println("slang:" + slangWord + " defi:" + defi);
            }
        }
        else {
            System.out.println("Không tồn tại slang word!!");
        }
    }
    public void findDefinition(String defiKeyword) {
        Set<String> keySet = sw.keySet();

        for(String key : keySet) {
            for (String defi : sw.get(key)) {
                if (defi.toLowerCase().contains(defiKeyword.toLowerCase())) {
                    System.out.println("slang:" + key + " defi:" + defi);
                }
            }
        }
    }

    public void addSlangWord(String slangWord, String defi) {
        if (sw.containsKey(slangWord)) {
            int choice;

            System.out.println("Đã tồn tại slang word!!");
            System.out.println("1. Overwrite");
            System.out.println("2. Duplicate");
            System.out.print("Nhập lựa chọn của bạn:");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                sw.get(slangWord).set(0, defi);
            }
            else if(choice == 2) {
                sw.get(slangWord).add(defi);
            }
        }
        else {
            List<String> listDefi = new ArrayList<String>();
            listDefi.add(defi);
            sw.put(slangWord, listDefi);
        }
    }


}
