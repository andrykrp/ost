package org.octocode.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task extends Eid implements Serializable {

    public final static Task BLANK = new Task();

    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer rating = 3;

    private Customer author;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tasks")
    @JsonManagedReference
    private Set<Tag> tags = new HashSet<>();

    protected Task() {
    }

    public Task(String description) {
        Assert.hasText(description, "Description must not be null or empty!");
        this.description = description;
    }

    public Task(String name, String description, Integer rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
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

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return (name == null ? "" : String.format("%s - ", name)) + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        if (!o.equals(o)) return false;

        Task task = (Task) o;

        if (id != null && id.equals(task.id)) return true;

        if (!description.equals(task.description)) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (rating != null ? !rating.equals(task.rating) : task.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + description.hashCode();
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }
}
