package Model;


import java.io.Serializable;
import java.util.ArrayList;

public class PassSubject implements Serializable {
    private static PassSubject passSubject = new PassSubject();
    private ArrayList<String> passId = new ArrayList<>();

    PassSubject() {}

    public static PassSubject getInstance() {
        return passSubject;
    }

    public void addPassId(String id) {
        System.out.println("Add:" +id);
        passId.add(id);
    }

    public void removePassId(String id){
        passId.remove(id);
    }

    public int getSize(){
        return passId.size();
    }

    public String getStatus(){
        return "Pass";
    }

    public String getId(String id){
        System.out.println(passId);
        for (String s : passId) {
            System.out.println("In pass: " + s);
            System.out.println("sent id: " + id);
            if (s.equals(id))
                return id;
        }
        throw new IllegalArgumentException("This id has not in ArrayList");
    }

}
