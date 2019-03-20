package Model;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListOfSubject implements Serializable {
    private Map<String, Subject> subjects;

    public ListOfSubject() throws IOException {
        subjects = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Subject.txt"));

//        File file = new File("src/FileText/Subject.txt");

        BufferedReader br = new BufferedReader(isr);
        String st;
        while ((st = br.readLine()) != null) {
             String[] inputFile = st.split(",",5);
             subjects.put(inputFile[2], new Subject(inputFile[3], Integer.valueOf(inputFile[1]), inputFile[4], inputFile[0]));
        }
        br.close();
    }

    public Set<String> getKeyset(){
        return subjects.keySet();

    }

    public void addSubject(String id, Subject subject){
        subjects.put(id, subject);
    }

    public String getId(String id){
        return id;
    }

    public Subject getSubject(String id){
        return subjects.get(id);
    }


}
