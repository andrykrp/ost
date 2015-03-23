package org.octocode.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
public class Part extends Eid implements Serializable {

    public final static Part BLANK = new Part();

    private String name;
    private String title;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "parts")
    private Set<Tag> tags = new HashSet<>();

    protected Part() {
    }

    public Part(String title) {
        Assert.hasText(title, "Title must not be null or empty!");
        this.name = title;
        this.title = title;
    }

    public Part(String name, String title) {
        Assert.hasText(title, "Title must not be null or empty!");
        Assert.hasText(name, "Name must not be null or empty!");
        this.name = name;
        this.title = title;
    }

    public void add(Tag tag) {
        Assert.notNull(tag);
        this.tags.add(tag);
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

    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s[%d]", name, (tags == null ? 0 : tags.size()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;
        if (!o.equals(o)) return false;

        Part part = (Part) o;

        if (id != null && id.equals(part.id)) return true;
        if (!name.equals(part.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + name.hashCode();
        return result;
    }
}
