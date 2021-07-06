package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Car")
public class Car {
    public enum CarStatus { Damaged, Efficient, InRepair}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private String brand;
    @Basic
    private String model;
    @Basic
    private double engineCapacity;
    @Basic
    private double power;
    @Basic
    private double torque;
    @Basic
    private LocalDate lastReviewDate;
    @Enumerated
    private CarStatus status;

    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CarReview> carReviews = new ArrayList<>();

    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TrainingReservation> trainingReservations = new ArrayList<>();

    @ManyToOne
    private Employee employee;

    public Car(){}

    public Car(String brand, String model, double engineCapacity, double power, double torque, LocalDate lastReviewDate, CarStatus status){
        this.brand = brand;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.power = power;
        this.torque = torque;
        this.lastReviewDate = lastReviewDate;
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setTorque(double torque) {
        this.torque = torque;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLastReviewDate(LocalDate lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public CarStatus getStatus() {
        return status;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public double getPower() {
        return power;
    }

    public double getTorque() {
        return torque;
    }

    public LocalDate getLastReviewDate() {
        return lastReviewDate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public void setCarReviews(List<CarReview> carReviews) {
        this.carReviews = carReviews;
    }

    public List<CarReview> getCarReviews() {
        return carReviews;
    }

    public void addCarReview(CarReview carReview) {
        if(!carReviews.contains(carReview)) {
            carReviews.add(carReview);
            carReview.setCar(this);
        }
    }

    public void removeCarReview(CarReview carReview) {
        if(carReviews.contains(carReview)) {
            carReviews.remove(carReview);
            carReview.removeCar();
        }
    }

    public void setTrainingReservations(List<TrainingReservation> trainingReservations) {
        this.trainingReservations = trainingReservations;
    }

    public List<TrainingReservation> getTrainingReservations() {
        return trainingReservations;
    }

    public void addTrainingReservation(TrainingReservation trainingReservation) {
        if(!trainingReservations.contains(trainingReservation)) {
            trainingReservations.add(trainingReservation);
            trainingReservation.setCar(this);
        }
    }

    public void removeTrainingReservation(TrainingReservation trainingReservation) {
        if(trainingReservations.contains(trainingReservation)) {
            trainingReservations.remove(trainingReservation);
            trainingReservation.removeCar();
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if(this.employee != null) {
            if(this.employee != employee) {
                this.employee.removeCar(this);
                this.employee = employee;
                employee.addCar(this);
            }
        } else {
            this.employee = employee;
            //event.addEmployee(this);
        }
    }

    public void removeEmployee() {
        if(employee.getCars().contains(this)) {
            employee.removeCar(this);
        }

        if(employee != null) {
            employee = null;
        }
    }

    
}
