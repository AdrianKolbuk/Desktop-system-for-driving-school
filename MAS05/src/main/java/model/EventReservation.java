package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "EventReservation")
public class EventReservation {
    public enum EventReservationStatus { Pending, Accepted, Rejected;}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private int code;
    @Enumerated
    private EventReservationStatus status;
    @Basic
    private int numberOfPeople;
    @Basic
    private boolean ifVip;
    @Basic
    private boolean ifParking;
    @Basic
    private double price;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Event event;
    @ManyToOne
    private Employee employee;

    public EventReservation(){}

    public EventReservation(int code, EventReservationStatus status, int numberOfPeople, boolean ifVip, boolean ifParking){
        this.code = code;
        this.status = status;
        this.numberOfPeople = numberOfPeople;
        this.ifVip = ifVip;
        this.ifParking = ifParking;
        setPrice();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(EventReservationStatus status) {
        this.status = status;
    }

    public void setPrice(){
        double pricetmp = 0;
        if(this.ifVip == true){
            pricetmp += this.numberOfPeople*30;
        }
        if(this.ifParking == true){
            pricetmp += 15;
        }

        this.price = (this.numberOfPeople*30) + pricetmp;
    }


    public void setIfParking(boolean ifParking) {
        this.ifParking = ifParking;
    }

    public void setIfVip(boolean ifVip) {
        this.ifVip = ifVip;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getCode() {
        return code;
    }

    public EventReservationStatus getStatus() {
        return status;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public boolean isIfParking() {
        return ifParking;
    }

    public boolean isIfVip() {
        return ifVip;
    }

    public Client getClient() {
        return client;
    }

    public Event getEvent() {
        return event;
    }

    public void setClient(Client client) {
        if(this.client != null) {
            if(this.client != client) {
                this.client.removeEventReservation(this);
                this.client = client;
                client.addEventReservation(this);
            }
        } else {
            this.client = client;
            //event.addEmployee(this);
        }
    }

    public void removeClient() {
        if(client.getEventReservations().contains(this)) {
            client.removeEventReservation(this);
        }

        if(client != null) {
            client = null;
        }
    }

    public void setEvent(Event event) {
        if(this.event != null) {
            if(this.event != event) {
                this.event.removeEventReservation(this);
                this.event = event;
                event.addEventReservation(this);
            }
        } else {
            this.event = event;
            //event.addEmployee(this);
        }
    }

    public void removeEvent() {
        if(event.getEventReservations().contains(this)) {
            event.removeEventReservation(this);
        }

        if(event != null) {
            event = null;
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if(this.employee != null) {
            if(this.employee != employee) {
                this.employee.removeEventReservation(this);
                this.employee = employee;
                employee.addEventReservation(this);
            }
        } else {
            this.employee = employee;
            //event.addEmployee(this);
        }
    }

    public void removeEmployee() {
        if(employee.getEventReservations().contains(this)) {
            employee.removeEventReservation(this);
        }

        if(employee != null) {
            employee = null;
        }
    }

    @Override
    public String toString() {
        return "EventReservation{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
