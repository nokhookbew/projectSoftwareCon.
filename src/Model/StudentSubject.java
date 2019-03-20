package Model;

import java.io.IOException;
import java.io.Serializable;

public class StudentSubject implements Serializable {
    private ListOfSubject listOfSubject;
    private PassSubject passSubject;
    private StudyingSubject studyingSubject;


    public StudentSubject() throws IOException {
       listOfSubject = new ListOfSubject();
       passSubject = PassSubject.getInstance() ;
       studyingSubject =StudyingSubject.getInstance();
    }

    public ListOfSubject getListOfSubject() {
        return listOfSubject;
    }

    public PassSubject getPassSubject() {
        return passSubject;
    }

    public StudyingSubject getStudyingSubject() {
        return studyingSubject;
    }
}
