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
        int hashSlang = slangWord.hashCode();

        if (swHashCode.containsKey(hashSlang)) {
            for (String defi : swHashCode.get(hashSlang)) {
                    System.out.println("slang:" + slangWord + " defi:" + defi);
                }
        }
        else {
            System.out.println("Không có slang word này");
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
}
