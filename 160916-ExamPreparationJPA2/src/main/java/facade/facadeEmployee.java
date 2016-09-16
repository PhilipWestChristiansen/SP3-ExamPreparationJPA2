package facade;

import entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class facadeEmployee {
    
       EntityManagerFactory emf;

    public facadeEmployee(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Employee addPerson(Employee p) {
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
    
        public Employee getPerson(int id) {
        EntityManager em = emf.createEntityManager();

        Employee p = null;

        try {
            em.getTransaction().begin();
            p = em.find(Employee.class, id);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public List<Employee> getPersons() {
        EntityManager em = emf.createEntityManager();

        List<Employee> persons = null;

        try {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p").getResultList();
            em.getTransaction().commit();
            return persons;
        } finally {
            em.close();
        }
    }

    public Employee deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Employee p = em.find(Employee.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    
}
