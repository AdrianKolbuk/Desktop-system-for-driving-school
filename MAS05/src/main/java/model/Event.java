package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Event")
public class Event {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private LocalDateTime dateTimeFrom;
    @Basic
    private LocalDateTime dateTimeTo;
    @Basic
    private String title;
    @Basic
    private String description;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<EventReservation> eventReservations = new ArrayList<>();

    public Event() { }

    public Event(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, String title, String description){
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
        this.title = title;
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTimeFrom(LocalDateTime dateTimeFrom) {
        this.dateTimeFrom = dateTimeFrom;
    }

    public void setDateTimeTo(LocalDateTime dateTimeTo) {
        this.dateTimeTo = dateTimeTo;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTimeFrom() {
        return dateTimeFrom;
    }

    public LocalDateTime getDateTimeTo() {
        return dateTimeTo;
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
            eventReservation.setEvent(this);
        }
    }

    public void removeEventReservation(EventReservation eventReservation) {
        if(eventReservations.contains(eventReservation)) {
            eventReservations.remove(eventReservation);
            eventReservation.removeEvent();
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", dateTimeFrom=" + dateTimeFrom +
                ", dateTimeTo=" + dateTimeTo +
                ", title='" + title + '\'' +
                ", description='" + description +
                '}';
    }
}
