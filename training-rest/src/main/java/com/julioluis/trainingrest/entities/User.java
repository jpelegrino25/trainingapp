package com.julioluis.trainingrest.entities;

import com.fasterxml.jackson.annotation.*;
import com.julioluis.trainingrest.utils.prototypes.TrainingProptotype;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@ApiModel(description = "User details")
//@JsonFilter("UserFilter")
public class User implements TrainingProptotype {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "email_address")
    private String emailAddress;
    @NotNull(message = "Username should not be null")
    @Size(min = 5,message = "At least Username should have 5 characters")
    @ApiModelProperty(notes = "At least Username should have 5 characters")
    private String username;
//    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "status")
    private Status status;


    @JoinColumn(name = "roles_id")
    @ManyToOne
    @NotNull(message = "Rol should not be null")
    private Rol rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public TrainingProptotype clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
