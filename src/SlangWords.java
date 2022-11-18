import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class SlangWords {
    private TreeMap<String, List<String>> sw = new TreeMap<String, List<String>>();
    public SlangWords() {
        try {
            BufferedReader fin = new BufferedReader(new FileReader("slang.txt"));
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
            System.out.println(key + "-" + sw.get(key));
        }
    }
}
