package org.octocode.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
public class Task extends Entity implements Serializable {

    public final static Task BLANK = new Task();

    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer rating = 3;

    private Customer author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tasks")
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

    @JsonProperty ("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty ("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    @JsonProperty ("rating")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return (name == null ? "" : String.format("%s - ", name)) + description;
    }

//    public String getJSON() throws JSONException {
//        JSONObject json = new JSONObject();
//        json.put("name", name);
//        json.put("description", description);
//        json.put("rating", rating);
//        json.put("tags", tags);
//
//        return json.toString();
//    }
}
