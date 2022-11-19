import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class SlangWords {
    private TreeMap<String, List<String>> sw = new TreeMap<String, List<String>>();
    private TreeMap<Integer, List<String>> swHashCode = new TreeMap<Integer, List<String>>();
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

                swHashCode.put( slAndDefi[0].hashCode(), listDefi);
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
        Set<String> keySet = sw.keySet();

        for(String key : keySet) {
            if (key.toLowerCase().contains(slangWord.toLowerCase())) {
                for (String defi : sw.get(key)) {
                    System.out.println("slang:" + key + " defi:" + defi);
                }
            }
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
        int hashSlang = slangWord.hashCode();

        if (swHashCode.containsKey(hashSlang)) {
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
            swHashCode.put(slangWord.hashCode(), listDefi);
        }
    }
}
