import java.io.*;
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

    public String getFileSW() {
        return File_SlangWord;
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

                if (sw.containsKey(slAndDefi[0])) { //Nếu đã từng tồn tại Slang word trong danh sách
                    for(String defi : arrDefi) { //Duyệt vòng lặp defi
                        if (sw.get(slAndDefi[0]).contains(defi.trim()) == false) { //Nếu chưa từng tồn tại defi này trong slang đó
                            sw.get(slAndDefi[0]).add(defi);
                        }
                    }
                }
                else {
                    for(String defi : arrDefi) {
                        listDefi.add(defi.trim());
                    }

                    sw.put(slAndDefi[0], listDefi);
                }
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetSlangWord() {
        try {
            this.ReadFile(File_SlangWordOrigin);
            this.saveFile(File_SlangWord);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveFile(String outputFile) {
        try {
            BufferedWriter fout = new BufferedWriter(new FileWriter(outputFile));
            Set<String> keySetSw = this.sw.keySet();

            for(String key: keySetSw) {
                fout.write(key + "`");
                fout.write(sw.get(key).get(0));

                for(int j = 1; j < sw.get(key).size(); j++) {
                    fout.write("| " + sw.get(key).get(j));
                }
                fout.newLine();
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findSlangWord(String slangWord) {
        if (sw.containsKey(slangWord)) {
            for (String defi : sw.get(slangWord)) {
                System.out.println("slang:" + slangWord + " defi:" + defi);
            }
        }
        else {
            System.out.println("Không tồn tại slang trong danh sách!!");
        }
        this.saveHistory(slangWord);
    }

    public void findDefinition(String defiKeyword) {
        Set<String> keySetSw = sw.keySet();

        for(String key : keySetSw) {
            for (String defi : sw.get(key)) {
                if (defi.toLowerCase().contains(defiKeyword.toLowerCase())) {
                    System.out.println("slang:" + key + " defi:" + defi);
                }
            }
        }
    }

    public void saveHistory(String slangWord) {
        try {
            BufferedWriter fout = new BufferedWriter (new FileWriter(File_SlangWordHistory, true));
            fout.write(slangWord);
            fout.newLine();
            fout.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readHistory() {
        try {
            BufferedReader fin = new BufferedReader (new FileReader(File_SlangWordHistory));
            String line = "";
            System.out.println("Danh sách các slang word đã tìm kiếm:");
            int index = 1;
            while(true) {
                line = fin.readLine();
                if (line == null)
                    break;
                System.out.println(index + ". " + line);
                index++;
            }
            fin.close();
        }catch (Exception e) {
            e.printStackTrace();
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
                this.saveFile(File_SlangWord);
            }
            else if(choice == 2) {
                sw.get(slangWord).add(defi);
                this.saveFile(File_SlangWord);
            }
        }
        else {
            List<String> listDefi = new ArrayList<String>();
            listDefi.add(defi);
            sw.put(slangWord, listDefi);
            this.saveFile(File_SlangWord);
        }
    }

    public void editSlangWord(String slangWord) {
        if (sw.containsKey(slangWord)) {
            int choice;
            int index = 1;
            System.out.println("Bạn muốn chỉnh sửa defi nào?");
            for(String defi : sw.get(slangWord)) {
                System.out.println(String.valueOf(index) + ". " + defi);
                index++;
            }
            System.out.print("Nhập lựa chọn của bạn:");
            choice = sc.nextInt();
            sc.nextLine();

            System.out.print("Nhập defition bạn muốn sau khi sửa:");
            String defiEdit = sc.nextLine();

            sw.get(slangWord).set(choice-1,defiEdit);
            System.out.println("Chỉnh sửa thành công");
            this.saveFile(File_SlangWord);
        }
        else {
            System.out.println("Không tồn tại slang word trong danh sách để chỉnh sửa!!!");
        }
    }
    public void deleteSlangWord(String slangWord) {
        if (sw.containsKey(slangWord)) {
            int choice;
            System.out.println("Bạn chắc chắn muốn xóa slang word này ra khỏi danh sách chứ ?");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.print("Nhập lựa chọn của bạn:");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                sw.remove(slangWord);
                this.saveFile(File_SlangWord);
            }
        }
        else {
            System.out.println("Không tồn tại slang word trong danh sách để xóa!!!");
        }
    }
}
