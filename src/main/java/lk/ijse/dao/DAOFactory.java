package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CulinaryProgramDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {

    public enum DAOType{
        PROGRAM,STUDENT
    }

    public static SuperDAO getDAO(DAOType daoType){
        return switch (daoType) {
            case PROGRAM -> new CulinaryProgramDAOImpl();
            case STUDENT -> new StudentDAOImpl();
            default -> null;
        };
    }
}
