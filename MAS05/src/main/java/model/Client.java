package model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Client")
public class Client extends Person {

    @Basic
    private String phoneNumber;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<EventReservation> eventReservations = new ArrayList<>();

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TrainingReservation> trainingReservations = new ArrayList<>();

    public Client(){super();}

    public Client(String firstName, String lastName, String email, String phoneNumber){
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
            eventReservation.setClient(this);
        }
    }

    public void removeEventReservation(EventReservation eventReservation) {
        if(eventReservations.contains(eventReservation)) {
            eventReservations.remove(eventReservation);
            eventReservation.removeClient();
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
            trainingReservation.setClient(this);
        }
    }

    public void removeTrainingReservation(TrainingReservation trainingReservation) {
        if(trainingReservations.contains(trainingReservation)) {
            trainingReservations.remove(trainingReservation);
            trainingReservation.removeClient();
        }
    }


}
