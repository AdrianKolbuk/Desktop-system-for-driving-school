package model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//W bazie nie utworzy się tabela person, każda klasa dziedzicząca po person dostanie swoją własną tabelę
//nie mamy overlappingu, ani dynamic, więc nie potrzebne są nam instancje klasy Person
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Basic
    private String firstName;
    @Basic
    private String lastName;
    @Basic
    private String email;

    public Person(){}

    public Person(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }



}
