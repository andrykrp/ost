package org.octocode.domain;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends AbstractEntity {
    private String firstname, lastname;
    @Column(unique = true)
    private Email email;

    public Customer(String firstname, String lastname) {
        Assert.hasText(firstname);
        Assert.hasText(lastname);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    protected Customer() {
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
