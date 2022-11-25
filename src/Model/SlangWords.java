package Model;

import java.io.*;
import java.util.*;

public class SlangWords {
    private HashMap<String, List<String>> sw = new HashMap<>();
    private final String fileSlangWord = "slang.txt";
    private final String fileSlangWordOrigin = "slangOrigin.txt";
    private final String fileSlangWordHistory = "slangHistory.txt";
    static Scanner sc = new Scanner(System.in);

    public HashMap<String, List<String>> getListSw() {
        return sw;
    }
    public SlangWords() {
        this.sw = readFile(fileSlangWord);
    }

    public String getFileSW() {
        return fileSlangWord;
    }

    public HashMap<String, List<String>> readFile(String inputFile) {
        HashMap<String, List<String>> swAndDefi = new HashMap<>();
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

                if (swAndDefi.containsKey(slAndDefi[0])) { //Nếu đã từng tồn tại Slang word trong danh sách
                    for (String defi : arrDefi) { //Duyệt vòng lặp defi
                        if (!swAndDefi.get(slAndDefi[0]).contains(defi.trim())) { //Nếu chưa từng tồn tại defi này trong slang đó
                            swAndDefi.get(slAndDefi[0]).add(defi);
                        }
                    }
                } else {
                    for (String defi : arrDefi) {
                        listDefi.add(defi.trim());
                    }

                    swAndDefi.put(slAndDefi[0], listDefi);
                }
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return swAndDefi;
    }

    public void resetSlangWord() {
        try {
            this.saveFile(fileSlangWord, this.readFile(fileSlangWordOrigin), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String outputFile, HashMap<String, List<String>> swAndDefi, boolean append) {
        try {
            BufferedWriter fout = new BufferedWriter(new FileWriter(outputFile, append));
            Set<String> keySetSw = swAndDefi.keySet();

            for (String key : keySetSw) {
                fout.write(key + "`");
                fout.write(swAndDefi.get(key).get(0));

                for (int j = 1; j < swAndDefi.get(key).size(); j++) {
                    fout.write("| " + swAndDefi.get(key).get(j));
                }
                fout.newLine();
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, List<String>> findSlangWord(String slangWord) {
        HashMap<String, List<String>> swAndDefi = new HashMap<>();

        if (sw.containsKey(slangWord)) {
            List<String> arrDefi = new ArrayList<>();
            for (String defi : sw.get(slangWord)) {
                arrDefi.add(defi);
            }
            swAndDefi.put(slangWord, arrDefi);
            this.saveHistory(swAndDefi);
        }
        return swAndDefi;
    }

    public HashMap<String, List<String>> findDefinition(String defiKeyword) {
        HashMap<String, List<String>> swAndDefi = new HashMap<>();
        Set<String> keySetSw = sw.keySet();
        boolean flag = false;

        for (String key : keySetSw) {
            flag = false;
            List<String> arrDefi = new ArrayList<>();
            for (String defi : sw.get(key)) {
                if (defi.toLowerCase().contains(defiKeyword.toLowerCase())) {
                    arrDefi.add(defi);
                    flag = true;
                }
            }
            if (flag == true) {
                swAndDefi.put(key, arrDefi);
            }
        }
        return swAndDefi;
    }

    public void saveHistory(HashMap<String, List<String>> swAndDefi) {
        this.saveFile(fileSlangWordHistory, swAndDefi, true);
    }

    public HashMap<String, List<String>> readHistory() {
        return this.readFile(fileSlangWordHistory);
    }

    public void addSlangWord(String slangWord, String defi, int choice) {
        if (choice == 1) {
            sw.get(slangWord).set(0, defi);
            this.saveFile(fileSlangWord, this.sw, false);
        } else if (choice == 2) {
            sw.get(slangWord).add(defi);
            this.saveFile(fileSlangWord, this.sw, false);
        }
         else if (choice == 3) {
            List<String> listDefi = new ArrayList<String>();
            listDefi.add(defi);
            sw.put(slangWord, listDefi);
            this.saveFile(fileSlangWord, this.sw, false);
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
            this.saveFile(fileSlangWord, this.sw, false);
        } else {
            System.out.println("Không tồn tại slang word trong danh sách để chỉnh sửa!!!");
        }
    }

    public void deleteSlangWord(String slangWord) {
        sw.remove(slangWord);
        this.saveFile(fileSlangWord, this.sw, false);

    }

    public static int randomMinMax(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public String[] randomSlangWord() {
        int indexRandSw = randomMinMax(0, sw.size() - 1);
        int index = 0;
        Set<String> keySetSw = sw.keySet();
        String[] swAndDefi = new String[2];
        for (String key : keySetSw) {
            if (index == indexRandSw) {
                int indexRandDefi = randomMinMax(0, sw.get(key).size() - 1);
                swAndDefi[0] = key;
                swAndDefi[1] = sw.get(key).get(indexRandDefi);
                return swAndDefi;
            }
            index++;
        }
        return swAndDefi;
    }

    public boolean quiz(int option) {
        if (option == 1) {
            String[] SwAndDefiRand = randomSlangWord();
            Set<String> fourDefiTemp = new HashSet<>();
            int numberDefi = 1;
            fourDefiTemp.add(SwAndDefiRand[1]);

            while (numberDefi < 4) {
                String[] SwAndDefiRandWrongAns = randomSlangWord();
                if (!SwAndDefiRandWrongAns[0].equals(SwAndDefiRand[0])) {
                    if (fourDefiTemp.add(SwAndDefiRandWrongAns[1])) {
                        numberDefi++;
                    }
                }
            }
            List<String> fourDefi = new ArrayList<>(fourDefiTemp);
            Collections.shuffle(fourDefi);
            System.out.println("Slang word:" + SwAndDefiRand[0]);

            for (int i = 0; i < 4; i++) {
                System.out.println(i + 1 + "." + fourDefi.get(i));
            }

            System.out.print("Nhập lựa chọn của bạn:");
            int choice = sc.nextInt();
            sc.nextLine();

            if (SwAndDefiRand[1].equals(fourDefi.get(choice - 1))) {
                System.out.println("Chúc mừng bạn đã chọn đáp án đúng");
                return true;
            } else {
                System.out.println("Thật đáng tiếc bạn đã chọn sai");
                return false;
            }
        } else {
            String[] SwAndDefiRand = randomSlangWord();
            Set<String> fourSwTemp = new HashSet<>();
            int numberSw = 1;
            fourSwTemp.add(SwAndDefiRand[0]);

            while (numberSw < 4) {
                String[] SwAndDefiRandWrongAns = randomSlangWord();
                if (!SwAndDefiRandWrongAns[0].equals(SwAndDefiRand[0])) {
                    if (fourSwTemp.add(SwAndDefiRandWrongAns[0])) {
                        numberSw++;
                    }
                }
            }
            List<String> fourSw = new ArrayList<>(fourSwTemp);
            Collections.shuffle(fourSw);

            System.out.println("Definition:" + SwAndDefiRand[1]);

            for (int i = 0; i < 4; i++) {
                System.out.println(i + 1 + "." + fourSw.get(i));
            }

            System.out.print("Nhập lựa chọn của bạn:");
            int choice = sc.nextInt();
            sc.nextLine();

            if (SwAndDefiRand[0].equals(fourSw.get(choice - 1))) {
                System.out.println("Chúc mừng bạn đã chọn đáp án đúng");
                return true;
            } else {
                System.out.println("Thật đáng tiếc bạn đã chọn sai");
                return false;
            }
        }
    }
}
