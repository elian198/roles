package com.roles.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@SQLDelete(sql = "UPDATE users SET soft_delete=true WHERE id = ?")
@Where(clause = "soft_delete = false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String passowrd;

    @Column(name = "SOFT_DELETE")
    private Boolean sorfDelete;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;


    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Boolean getSorfDelete() {
        return sorfDelete;
    }

    public void setSorfDelete(Boolean sorfDelete) {
        this.sorfDelete = sorfDelete;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
