package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {
    private String name;
    private int credit;
    private String preSubject;
    private ArrayList preSubArray;
    private String status;

    public Subject(String name, int credit, String preSubject, String status){
        preSubArray = new ArrayList();
        String[] pre = preSubject.split(",");
        for(int i = 0; i < pre.length; i++){
            preSubArray.add(pre[i]);
        }
        this.name = name;
        this.credit = credit;
        this.status = status;
    }

    public int getSizeOfPre(){
        return preSubArray.size();
    }

    public String getPreInArray(int i){
        return (String) preSubArray.get(i);
    }

//    public void setPreSubject(String preSubject){
//        this.preSubject = preSubject;
//    }


    public String getName() {
        return name;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){ return status; }

//    public String getPreSubject(){
//        return preSubject;
//    }

    public int getCredit() {
        return credit;
    }
}

