package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TrainingReservation")
public class TrainingReservation {
    public enum TrainingReservationStatus { Pending, Accepted, Rejected;}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private int code;
    @Basic
    private LocalDateTime dateTimeFrom;
    @Enumerated
    private TrainingReservationStatus status;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Training training;
    @ManyToOne
    private Car car;

    public TrainingReservation(){ }

    public TrainingReservation(int code, LocalDateTime dateTimeFrom, TrainingReservationStatus status){
        this.code = code;
        this.dateTimeFrom = dateTimeFrom;
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDateTimeFrom(LocalDateTime dateTimeFrom) {
        this.dateTimeFrom = dateTimeFrom;
    }

    public void setStatus(TrainingReservationStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public LocalDateTime getDateTimeFrom() {
        return dateTimeFrom;
    }

    public TrainingReservationStatus getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if(this.client != null) {
            if(this.client != client) {
                this.client.removeTrainingReservation(this);
                this.client = client;
                client.addTrainingReservation(this);
            }
        } else {
            this.client = client;
            //event.addEmployee(this);
        }
    }

    public void removeClient() {
        if(client.getTrainingReservations().contains(this)) {
            client.removeTrainingReservation(this);
        }

        if(client != null) {
            client = null;
        }
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        if(this.training != null) {
            if(this.training != training) {
                this.training.removeTrainingReservation(this);
                this.training = training;
                training.addTrainingReservation(this);
            }
        } else {
            this.training = training;
            //event.addEmployee(this);
        }
    }

    public void removeTraining() {
        if(training.getTrainingReservations().contains(this)) {
            training.removeTrainingReservation(this);
        }

        if(training != null) {
            training = null;
        }
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if(this.car != null) {
            if(this.car != car) {
                this.car.removeTrainingReservation(this);
                this.car = car;
                car.addTrainingReservation(this);
            }
        } else {
            this.car = car;
            //event.addEmployee(this);
        }
    }

    public void removeCar() {
        if(car.getTrainingReservations().contains(this)) {
            car.removeTrainingReservation(this);
        }

        if(car != null) {
            car = null;
        }
    }


}
