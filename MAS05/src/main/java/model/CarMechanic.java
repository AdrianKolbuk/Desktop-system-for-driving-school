package model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CarMechanic")
public class CarMechanic extends Person{

    @Basic
    private double salary;
    @Basic
    private int bonus;

    public final static int maxBonus = 30;
    public final static int minBonus = 0;

    @OneToMany(
            mappedBy = "carMechanic",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CarReview> carReviews = new ArrayList<>();


    public CarMechanic(){super();}

    public CarMechanic(String firstName, String lastName, String email, double salary, int bonus){
        super(firstName, lastName, email);
        this.salary = salary;
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

    public double getSalary() {
        return salary;
    }

    public int getBonus() {
        return bonus;
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
            carReview.setCarMechanic(this);
        }
    }

    public void removeCarReview(CarReview carReview) {
        if(carReviews.contains(carReview)) {
            carReviews.remove(carReview);
            carReview.removeCarMechanic();
        }
    }

}
