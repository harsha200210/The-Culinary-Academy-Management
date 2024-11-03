package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {

    public enum BOType{
        PROGRAM, STUDENT, DASHBOARD, ADDPROGRAM, VIEWALL, ADDPAYMENT
    }

    public static SuperBO getBO(BOType boType){
        return switch (boType) {
            case PROGRAM -> new ProgramBOImpl();
            case STUDENT -> new StudentBOImpl();
            case DASHBOARD -> new DashboardBOImpl();
            case ADDPROGRAM -> new AddProgramBOImpl();
            case VIEWALL -> new ViewAllBOImpl();
            case ADDPAYMENT -> new AddPaymentBOImpl();
            default -> null;
        };
    }
}
