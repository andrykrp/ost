package org.octocode.domain;

import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task extends AbstractEntity {
    private String name, description;
    private Integer rating;
    private Customer author;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Set<Tag> tags = new HashSet<Tag>();

    public Task(String description) {
        Assert.hasText(description, "Street must not be null or empty!");
        this.description = description;
    }

    protected Task() {
    }

    public void add(Tag tag) {
        Assert.notNull(tag);
        this.tags.add(tag);
    }

    public Customer getAuthor() {
        return author;
    }

    public void setAuthor(Customer author) {
        Assert.notNull(author);
        this.author = author;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
