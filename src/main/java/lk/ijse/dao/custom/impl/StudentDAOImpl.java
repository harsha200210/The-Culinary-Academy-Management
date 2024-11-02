package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.db.FactoryConfiguration;
import lk.ijse.entity.CulinaryProgram;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void saveStudent(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteStudent(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(student);

        transaction.commit();
        session.close();
    }

    @Override
    public void updateStudent(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> students;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        students = session.createQuery("from Student", Student.class).list();

        transaction.commit();
        session.close();

        return students;
    }

    @Override
    public Student getStudent(String studentId){
        Student student = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        student = session.get(Student.class, studentId);

        transaction.commit();
        session.close();

        return student;
    }
}
