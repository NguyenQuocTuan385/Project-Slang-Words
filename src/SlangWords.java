import java.io.*;
import java.util.*;

public class SlangWords {
    private HashMap<String, List<String>> sw = new HashMap<>();
    private final String fileSlangWord = "slang.txt";
    private final String fileSlangWordOrigin = "slangOrigin.txt";
    private final String fileSlangWordHistory = "slangHistory.txt";
    static Scanner sc = new Scanner(System.in);

    public SlangWords() {
        readFile(fileSlangWord);
    }

    public String getFileSW() {
        return fileSlangWord;
    }

    public void readFile(String inputFile) {
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
                    for (String defi : arrDefi) { //Duyệt vòng lặp defi
                        if (!sw.get(slAndDefi[0]).contains(defi.trim())) { //Nếu chưa từng tồn tại defi này trong slang đó
                            sw.get(slAndDefi[0]).add(defi);
                        }
                    }
                } else {
                    for (String defi : arrDefi) {
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
            this.readFile(fileSlangWordOrigin);
            this.saveFile(fileSlangWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String outputFile) {
        try {
            BufferedWriter fout = new BufferedWriter(new FileWriter(outputFile));
            Set<String> keySetSw = this.sw.keySet();

            for (String key : keySetSw) {
                fout.write(key + "`");
                fout.write(sw.get(key).get(0));

                for (int j = 1; j < sw.get(key).size(); j++) {
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
        } else {
            System.out.println("Không tồn tại slang trong danh sách!!");
        }
        this.saveHistory(slangWord);
    }

    public void findDefinition(String defiKeyword) {
        Set<String> keySetSw = sw.keySet();

        for (String key : keySetSw) {
            for (String defi : sw.get(key)) {
                if (defi.toLowerCase().contains(defiKeyword.toLowerCase())) {
                    System.out.println("slang:" + key + " defi:" + defi);
                }
            }
        }
    }

    public void saveHistory(String slangWord) {
        try {
            BufferedWriter fout = new BufferedWriter(new FileWriter(fileSlangWordHistory, true));
            fout.write(slangWord);
            fout.newLine();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readHistory() {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(fileSlangWordHistory));
            String line = "";
            System.out.println("Danh sách các slang word đã tìm kiếm:");
            int index = 1;
            while (true) {
                line = fin.readLine();
                if (line == null)
                    break;
                System.out.println(index + ". " + line);
                index++;
            }
            fin.close();
        } catch (Exception e) {
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
                this.saveFile(fileSlangWord);
            } else if (choice == 2) {
                sw.get(slangWord).add(defi);
                this.saveFile(fileSlangWord);
            }
        } else {
            List<String> listDefi = new ArrayList<String>();
            listDefi.add(defi);
            sw.put(slangWord, listDefi);
            this.saveFile(fileSlangWord);
        }
    }

    public void editSlangWord(String slangWord) {
        if (sw.containsKey(slangWord)) {
            int choice;
            int index = 1;
            System.out.println("Bạn muốn chỉnh sửa defi nào?");
            for (String defi : sw.get(slangWord)) {
                System.out.println(index + ". " + defi);
                index++;
            }
            System.out.print("Nhập lựa chọn của bạn:");
            choice = sc.nextInt();
            sc.nextLine();

            System.out.print("Nhập defition bạn muốn sau khi sửa:");
            String defiEdit = sc.nextLine();

            sw.get(slangWord).set(choice - 1, defiEdit);
            System.out.println("Chỉnh sửa thành công");
            this.saveFile(fileSlangWord);
        } else {
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
                this.saveFile(fileSlangWord);
            }
        } else {
            System.out.println("Không tồn tại slang word trong danh sách để xóa!!!");
        }
    }

    public static int randomMinMax(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public String[] randomSlangWord() {
        int indexRandSw = randomMinMax(0, sw.size() - 1);
        int index = 0;
        Set<String> keySetSw = sw.keySet();
        String[] swAndDefi = new String[2];
        for(String key : keySetSw) {
            if(index == indexRandSw) {
                int indexRandDefi = randomMinMax(0, sw.get(key).size() - 1);
                swAndDefi[0] = key;
                swAndDefi[1] = sw.get(key).get(indexRandDefi);
                return swAndDefi;
            }
            index++;
        }
        return swAndDefi;
    }
}
