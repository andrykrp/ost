package org.octocode.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
public class Part extends Entity implements Serializable {

    public final static Part BLANK = new Part();

    private String name;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parts")
//    private Set<Tag> tags = new HashSet<>();

    protected Part() {
    }

    public Part(String name) {
        Assert.hasText(name, "Name must not be null or empty!");
        this.name = name;
    }

    public void add(Tag tag) {
        Assert.notNull(tag);
//        this.tags.add(tag);
    }

//    public Set<Tag> getTags() {
//        return Collections.unmodifiableSet(tags);
//    }

    public String getName() {
        return name;
    }

    @JsonProperty ("name")
    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return String.format("%s[%d]", name, (tags == null ? 0 : tags.size()));
//    }
}
