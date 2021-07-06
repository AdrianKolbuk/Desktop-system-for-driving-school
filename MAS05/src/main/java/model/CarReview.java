package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "CarReview")
public class CarReview {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private LocalDate date;
    @Basic
    private String reviewRaport;

    @ManyToOne
    private CarMechanic carMechanic;
    @ManyToOne
    private Car car;

    public CarReview(){}

    public CarReview(LocalDate date, String reviewRaport){
        this.date = date;
        this.reviewRaport = reviewRaport;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setReviewRaport(String reviewRaport) {
        this.reviewRaport = reviewRaport;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getReviewRaport() {
        return reviewRaport;
    }

    public CarMechanic getCarMechanic() {
        return carMechanic;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if(this.car != null) {
            if(this.car != car) {
                this.car.removeCarReview(this);
                this.car = car;
                car.addCarReview(this);
            }
        } else {
            this.car = car;
            //event.addEmployee(this);
        }
    }

    public void removeCar() {
        if(car.getCarReviews().contains(this)) {
            car.removeCarReview(this);
        }

        if(car != null) {
            car = null;
        }

    }

    public void setCarMechanic(CarMechanic carMechanic) {
        if(this.carMechanic != null) {
            if(this.carMechanic != carMechanic) {
                this.carMechanic.removeCarReview(this);
                this.carMechanic = carMechanic;
                carMechanic.addCarReview(this);
            }
        } else {
            this.carMechanic = carMechanic;
            //event.addEmployee(this);
        }
    }

    public void removeCarMechanic() {
        if(carMechanic.getCarReviews().contains(this)) {
            carMechanic.removeCarReview(this);
        }

        if(carMechanic != null) {
            carMechanic = null;
        }

    }

    @Override
    public String toString() {
        return "CarReview{" +
                "id=" + id +
                ", date=" + date +
                ", reviewRaport='" + reviewRaport + '\'' +
                ", carMechanic=" + carMechanic +
                ", car=" + car +
                '}';
    }
}
