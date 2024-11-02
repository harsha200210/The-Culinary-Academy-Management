package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CulinaryProgramDAO;
import lk.ijse.db.FactoryConfiguration;
import lk.ijse.entity.CulinaryProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CulinaryProgramDAOImpl implements CulinaryProgramDAO {

    @Override
    public void saveCulinaryProgram(CulinaryProgram culinaryProgram) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(culinaryProgram);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteCulinaryProgram(CulinaryProgram culinaryProgram) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(culinaryProgram);

        transaction.commit();
        session.close();
    }

    @Override
    public void updateCulinaryProgram(CulinaryProgram culinaryProgram) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(culinaryProgram);

        transaction.commit();
        session.close();
    }

    @Override
    public List<CulinaryProgram> getAllCulinaryProgram() {
        List<CulinaryProgram> culinaryPrograms;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        culinaryPrograms = session.createQuery("from CulinaryProgram", CulinaryProgram.class).list();

        transaction.commit();
        session.close();

        return culinaryPrograms;
    }
}
