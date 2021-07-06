package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Training")
public class Training {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private String name;
    @Basic
    private int difficulty;
    @Basic
    private int duration;

    private static final int maxDifficulty = 3;
    private static final int minDifficulty = 1;

    @OneToMany(
            mappedBy = "training",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TrainingReservation> trainingReservations = new ArrayList<>();

    public Training(){}

    public Training(String name, int difficulty, int duration){
        this.name = name;
        this.difficulty = difficulty;
        this.duration = duration;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDifficulty(int difficulty) throws Exception {
        if(difficulty > maxDifficulty){
            throw new Exception(String.format("The difficulty (%s) cannot be higher then %s", difficulty, maxDifficulty));
        }
        if(difficulty < minDifficulty){
            throw new Exception(String.format("The difficulty (%s) has to be at least %s", difficulty, minDifficulty));
        }

        this.difficulty = difficulty;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
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
            trainingReservation.setTraining(this);
        }
    }

    public void removeTrainingReservation(TrainingReservation trainingReservation) {
        if(trainingReservations.contains(trainingReservation)) {
            trainingReservations.remove(trainingReservation);
            trainingReservation.removeTraining();
        }
    }



}
