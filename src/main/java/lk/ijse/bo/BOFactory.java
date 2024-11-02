package lk.ijse.bo;

import lk.ijse.bo.custom.impl.ProgramBOImpl;

public class BOFactory {

    public enum BOType{
        PROGRAM
    }

    public static SuperBO getBO(BOType boType){
        return switch (boType) {
            case PROGRAM -> new ProgramBOImpl();
            default -> null;
        };
    }
}
