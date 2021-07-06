package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Employee")
public class Employee extends Person{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private double salary;
    @Basic
    private int bonus;

    public final static int maxBonus = 30;
    public final static int minBonus = 0;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<EventReservation> eventReservations = new ArrayList<>();

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    //XOR
    @ManyToOne
    private EventOrganizationDepartment eventOrganizationDepartment;
    @ManyToOne
    private CustomerServiceDepartment customerServiceDepartment;

    public Employee() {super();}

    public Employee(String firstName, String lastName, String email, double salary, int bonus) throws Exception{
        super(firstName, lastName, email);
        this.salary = salary;
        if(bonus > maxBonus){
            throw new Exception(String.format("The bonus (%s) cannot be higher then %s", bonus, maxBonus));
        }
        if(bonus < minBonus){
            throw new Exception(String.format("The bonus (%s) has to be at least %s", bonus, minBonus));
        }

        this.bonus = bonus;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonus(int bonus) throws Exception {
        if(bonus > maxBonus){
            throw new Exception(String.format("The bonus (%s) cannot be higher then %s", bonus, maxBonus));
        }
        if(bonus < minBonus){
            throw new Exception(String.format("The bonus (%s) has to be at least %s", bonus, minBonus));
        }

        this.bonus = bonus;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public int getBonus() {
        return bonus;
    }

    public long getId() {
        return id;
    }

    public void setEventReservations(List<EventReservation> eventReservations) {
        this.eventReservations = eventReservations;
    }

    public List<EventReservation> getEventReservations() {
        return eventReservations;
    }

    public void addEventReservation(EventReservation eventReservation) {
        if(!eventReservations.contains(eventReservation)) {
            eventReservations.add(eventReservation);
            eventReservation.setEmployee(this);
        }
    }

    public void removeEventReservation(EventReservation eventReservation) {
        if(eventReservations.contains(eventReservation)) {
            eventReservations.remove(eventReservation);
            eventReservation.removeEmployee();
        }
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        if(!cars.contains(car)) {
            cars.add(car);
            car.setEmployee(this);
        }
    }

    public void removeCar(Car car) {
        if(cars.contains(car)) {
            cars.remove(car);
            car.removeEmployee();
        }
    }

    //XOR implementacja

    public EventOrganizationDepartment getEventOrganizationDepartment() {
        return eventOrganizationDepartment;
    }

    public CustomerServiceDepartment getCustomerServiceDepartment() {
        return customerServiceDepartment;
    }

    public void setDepartment(Object department) {
        if(this.eventOrganizationDepartment != null || this.customerServiceDepartment != null) {
            if(this.eventOrganizationDepartment != department || this.customerServiceDepartment != department) {
                if(department instanceof EventOrganizationDepartment) {
                    this.eventOrganizationDepartment.removeEmployee(this);
                    this.eventOrganizationDepartment = (EventOrganizationDepartment) department;
                    ((EventOrganizationDepartment) department).addEmployee(this);
                }
                else if(department instanceof CustomerServiceDepartment) {
                    this.customerServiceDepartment.removeEmployee(this);
                    this.customerServiceDepartment = (CustomerServiceDepartment) department;
                    ((CustomerServiceDepartment) department).addEmployee(this);
                }
            }
        } else {
            if(department instanceof EventOrganizationDepartment) {
                this.eventOrganizationDepartment = (EventOrganizationDepartment) department;
            }
            else if(department instanceof CustomerServiceDepartment) {
                this.customerServiceDepartment = (CustomerServiceDepartment) department;
            }
        }
    }

    public void removeDepartment() {
        if(eventOrganizationDepartment.getEmployees().contains(this)) {
            eventOrganizationDepartment.removeEmployee(this);
        } else if(customerServiceDepartment.getEmployees().contains(this)){
            customerServiceDepartment.removeEmployee(this);
        }

        if(eventOrganizationDepartment != null) {
            eventOrganizationDepartment = null;
        } else if(customerServiceDepartment != null){
            customerServiceDepartment = null;
        }
    }



}
