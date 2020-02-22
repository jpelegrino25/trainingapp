package com.julioluis.trainingrest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rolname")
    private String description;

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference
    @OneToMany(mappedBy = "rol")
    private List<User> users;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "rol_auth",
    joinColumns = @JoinColumn(name = "roles_id",
            referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "authorities_id"
            ,referencedColumnName = "id"))
    private List<Authority> authorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
