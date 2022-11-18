import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SlangWords {
    private TreeMap<String, List<String>> sw = new TreeMap<String, List<String>>();
    public SlangWords() {
        List<String> test = new ArrayList<String>();
        test.add("Test");
        sw.put("J", test);
    }
    public void display() {
        System.out.println(this.sw);
    }
}
