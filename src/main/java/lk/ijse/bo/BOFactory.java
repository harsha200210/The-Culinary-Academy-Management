package lk.ijse.bo;

import lk.ijse.bo.custom.impl.ProgramBOImpl;
import lk.ijse.bo.custom.impl.StudentBOImpl;

public class BOFactory {

    public enum BOType{
        PROGRAM, STUDENT
    }

    public static SuperBO getBO(BOType boType){
        return switch (boType) {
            case PROGRAM -> new ProgramBOImpl();
            case STUDENT -> new StudentBOImpl();
            default -> null;
        };
    }
}
