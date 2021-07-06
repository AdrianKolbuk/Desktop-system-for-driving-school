import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import views.*;
import model.*;
import org.hibernate.Session;

public class Main{

    public static void main(String[] args) {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

            Employee emp1 = new Employee("Jan", "Kowalski", "employee@gmail.com", 3000, 20);
            Client client1 = new Client("Adam", "Kowalski", "user@gmail.com", "600300400");
            CarMechanic carMechanic = new CarMechanic("Kamil", "Kowal", "carMechanic@gmail.com", 3000, 20);
            Car car = new Car("Nissan", "Primera", 3000, 300, 200, LocalDate.now(), Car.CarStatus.Efficient);
            Event event1 = new Event(LocalDateTime.now(), LocalDateTime.now().plusHours(3), "Zlot fanów JDM", "Lorem ipsum");
            Event event2 = new Event(LocalDateTime.now(), LocalDateTime.now().plusHours(3), "Zlot fanów VAG", "Lorem ipsum");
            Event event3 = new Event(LocalDateTime.now(), LocalDateTime.now().plusHours(5), "W³oska motoryzacja", "Lorem ipsum");
            EventReservation eventR1 = new EventReservation(3000, EventReservation.EventReservationStatus.Pending, 4, false, false);
            EventReservation eventR2 = new EventReservation(4000, EventReservation.EventReservationStatus.Pending, 6, false, false);
            EventReservation eventR3 = new EventReservation(5000, EventReservation.EventReservationStatus.Pending, 7, true, false);
            EventReservation eventR4 = new EventReservation(6000, EventReservation.EventReservationStatus.Pending, 2, false, true);
            Training training1 = new Training("Nauka driftu", 2, 2);
            TrainingReservation trainingR1 = new TrainingReservation(5000, LocalDateTime.now(), TrainingReservation.TrainingReservationStatus.Pending);
            CarReview carReview = new CarReview(LocalDate.now(), "Auto sprawne");
            CustomerServiceDepartment csd = new CustomerServiceDepartment("Warsaw", "csd@gmail.com");
            EventOrganizationDepartment eod = new EventOrganizationDepartment("Warsaw", "eod@gmail.com");
            
            event1.addEventReservation(eventR1);
            event1.addEventReservation(eventR2);
            event3.addEventReservation(eventR3);
            event3.addEventReservation(eventR4);
            client1.addEventReservation(eventR1);
            client1.addEventReservation(eventR2); 
            training1.addTrainingReservation(trainingR1);
            carMechanic.addCarReview(carReview);
            car.addCarReview(carReview);
            emp1.addCar(car);
            car.addTrainingReservation(trainingR1);
            emp1.setDepartment(csd);
  
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(emp1);
            session.save(client1);
            session.save(event1);
            session.save(event2);
            session.save(event3);
            session.save(eventR1);
            session.save(eventR2);
            session.save(eventR3);
            session.save(eventR4);
            session.save(carMechanic);
            session.save(car);
            session.save(training1);
            session.save(trainingR1);
            session.save(carReview);
            session.save(csd);
            session.save(eod);
            

            session.getTransaction().commit();
            session.close();

//
//            //---------- asocjacje ------------
//
            session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
            Root<Event> root = criteria.from(Event.class);
            criteria.select(root);
            
            Shared.eventsFromDb = session.createQuery(criteria).getResultList();
    
            session.getTransaction().commit();
            session.close();
            
           

//            //---------- asocjacje-usuwanie ------------
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            System.out.println("\033[0;34m" + "Deleting associations test" + "\033[0m");
            //System.out.println(car.toString());

            car.removeCarReview(carReview);
            session.update(car);

            session.getTransaction().commit();
            session.close();
            
            System.out.println(car.getCarReviews().toString());


        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
        
        new LoginFrame().setVisible(true);
    }
    
    
    
}
