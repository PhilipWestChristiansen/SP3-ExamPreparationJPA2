package facade;

import entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class facadeStudents {

    EntityManagerFactory emf;

    public facadeStudents(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Student addPerson(Student p) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Student getPerson(int id) {
        EntityManager em = emf.createEntityManager();

        Student p = null;

        try {
            em.getTransaction().begin();
            p = em.find(Student.class, id);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public List<Student> getPersons() {
        EntityManager em = emf.createEntityManager();

        List<Student> persons = null;

        try {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p").getResultList();
            em.getTransaction().commit();
            return persons;
        } finally {
            em.close();
        }
    }

    public Student deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Student p = em.find(Student.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

}
