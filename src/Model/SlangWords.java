package Model;
import java.io.*;
import java.util.*;

public class SlangWords {
    private HashMap<String, List<String>> sw = new HashMap<>();
    private final String fileSlangWord = "slang.txt";
    private final String fileSlangWordOrigin = "slangOrigin.txt";
    private final String fileSlangWordHistory = "slangHistory.txt";
    public HashMap<String, List<String>> getListSw() {
        return sw;
    }
    public SlangWords() {
        this.sw = readFile(fileSlangWord);
    }
    public HashMap<String, List<String>> readFile(String inputFile) { //Đọc file slang word
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

    public void resetSlangWord() { //Khôi phục danh sách slang word về bản gốc
        try {
            this.sw = this.readFile(fileSlangWordOrigin);
            this.saveFile(fileSlangWord, this.sw, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String outputFile, HashMap<String, List<String>> swAndDefi, boolean append) { //Lưu file slang word
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

    public HashMap<String, List<String>> findSlangWord(String slangWord) { //Tìm kiếm slang word trong danh sách
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

    public void saveHistory(HashMap<String, List<String>> swAndDefi) { //Lưu lại lịch sử tìm kiếm slang word thành công
        this.saveFile(fileSlangWordHistory, swAndDefi, true);
    }

    public HashMap<String, List<String>> readHistory() {
        return this.readFile(fileSlangWordHistory);
    }

    public void addSlangWord(String slangWord, String defi, int choice) { //Thêm slang word vào trong danh sách
        if (choice == 1) { //Overwrite
            sw.get(slangWord).set(0, defi);
            this.saveFile(fileSlangWord, this.sw, false);
        } else if (choice == 2) { //Duplicate
            sw.get(slangWord).add(defi);
            this.saveFile(fileSlangWord, this.sw, false);
        }
         else if (choice == 3) { //Thêm slang word vào danh sách nếu chưa từng tồn tại
            List<String> listDefi = new ArrayList<String>();
            listDefi.add(defi);
            sw.put(slangWord, listDefi);
            this.saveFile(fileSlangWord, this.sw, false);
        }
    }

    public void deleteSlangWord(String slangWord, String defi, int option) { //Xóa slang word ra khỏi hệ thống
        if(option == 1) { //Xóa lun slang word đó
            sw.remove(slangWord);
        }
        else { //Nếu chỉ muốn xóa defi trong slang word đó
            if (sw.get(slangWord).size() == 1) //Trong slang word bị sửa chỉ có một definition
            {
                sw.remove(slangWord); //Xóa slang word đó
            }
            else { //TRong slang word bị sửa có nhiều hơn một definition
                for (String defiCheck : sw.get(slangWord)) { //Duyệt vòng lặp để sửa defi đó
                    if (defiCheck.equals(defi)) {
                        sw.get(slangWord).remove(defi);
                        break;
                    }
                }
            }
        }
        this.saveFile(fileSlangWord, this.sw, false);
    }

    //Chỉnh sửa slang  word
    public void editSlangWord(String slangWordOld, String defiOld, String slangWordNew, String defiNew) {
        if (slangWordNew.equals(slangWordOld)) {  //Không sửa tên slang word
            int index = 0;
            for(String defiCheck : sw.get(slangWordOld)) { //Duyệt vòng lặp để sửa defi đó
                if  (defiCheck.equals(defiOld)) {
                    sw.get(slangWordOld).set(index, defiNew);
                    break;
                }
                index++;
            }
        }
        else { //Có sửa tên slang word
            this.deleteSlangWord(slangWordOld, defiOld, 2);
            if (sw.containsKey(slangWordNew)) { //Kiểm tra xem có slang word nào trùng vs tên đã sửa chưa
                sw.get(slangWordNew).add(defiNew);
            }
            else { //Chưa có slang word nào trùng tên muốn sửa
                sw.put(slangWordNew, Arrays.asList(defiNew));
            }
        }
        this.saveFile(fileSlangWord, this.sw, false);
    }

    //Hàm giả về giá trị số ngẫu nhiên từ [min,max]
    public static int randomMinMax(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    //Hàm trả về slang word ngẫu nhiên và defi ngẫu nhiên của slang word đó
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

    //Hàm trả về slang word vs 4 đáp án defi hoặc defi vs 4 đáp án slang word
    public HashMap<String, Integer> quiz(int option) {
        HashMap<String, Integer> swAndDefiQuiz = new HashMap<>();
        String[] SwAndDefiRand = randomSlangWord();

        if (option == 1) { //Quiz về slang word
            Set<String> threeDefiTemp = new HashSet<>();
            int numberDefi = 0;
            swAndDefiQuiz.put(SwAndDefiRand[0],2); //2 là slang word
            swAndDefiQuiz.put(SwAndDefiRand[1],1); //1 là definition đúng

            while (numberDefi < 3) {
                String[] SwAndDefiRandWrongAns = randomSlangWord();

                if (!SwAndDefiRandWrongAns[0].equals(SwAndDefiRand[0])) {
                    if (threeDefiTemp.add(SwAndDefiRandWrongAns[1])) {
                        numberDefi++;
                    }
                }
            }
            for(String defi : threeDefiTemp) {
                swAndDefiQuiz.put(defi, 0); //0 có nghĩa l definition sai
            }
        } else { //Quiz về defi
            Set<String> threeSwTemp = new HashSet<>();
            int numberSw = 0;
            swAndDefiQuiz.put(SwAndDefiRand[1],2); //2 là defintion
            swAndDefiQuiz.put(SwAndDefiRand[0],1); //1 là sw đúng

            while (numberSw < 3) {
                String[] SwAndDefiRandWrongAns = randomSlangWord();
                if (!SwAndDefiRandWrongAns[0].equals(SwAndDefiRand[0])) {
                    if (threeSwTemp.add(SwAndDefiRandWrongAns[0])) {
                        numberSw++;
                    }
                }
            }
            for(String swWrong : threeSwTemp) {
                swAndDefiQuiz.put(swWrong, 0); //0 có nghĩa là sw sai
            }
        }
        return swAndDefiQuiz;
    }
}
