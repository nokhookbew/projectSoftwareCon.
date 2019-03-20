package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class StudyingSubject implements Serializable {
    private static StudyingSubject studyingSubject = new StudyingSubject();
    private ArrayList<String> studyingId = new ArrayList<>();

    StudyingSubject() {}

    public static StudyingSubject getInstance() {
        return studyingSubject;
    }

    public void addId(String id){
        System.out.println("Add:" +id);
        studyingId.add(id);
    }

    public void removeId(String id){
        studyingId.remove(id);
    }

    public String getStatus(){
        return "Studying";
    }
    public int getSize(){
        return studyingId.size();
    }

    public String getId(String id){
        System.out.println(studyingId);
        for (String s : studyingId) {
            if (s.equals(id))
                return id;
        }
        throw new IllegalArgumentException("This id has not in ArrayList");
    }

}
