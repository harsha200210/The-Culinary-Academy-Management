package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CulinaryProgramDAOImpl;

public class DAOFactory {

    public enum DAOType{
        PROGRAM
    }

    public static SuperDAO getDAO(DAOType daoType){
        return switch (daoType) {
            case PROGRAM -> new CulinaryProgramDAOImpl();
            default -> null;
        };
    }
}
