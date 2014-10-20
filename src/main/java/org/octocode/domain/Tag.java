package org.octocode.domain;

import java.io.Serializable;

@javax.persistence.Entity
public class Tag extends Entity implements Serializable {
    private String name;
    private Boolean hidden = false, active = true, top = false;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!o.equals(o)) return false;

        Tag tag = (Tag) o;

        if (!name.equals(tag.name)) return false;
        if (top != null ? !top.equals(tag.top) : tag.top != null) return false;
        if (active != null ? !active.equals(tag.active) : tag.active != null) return false;
        if (hidden != null ? !hidden.equals(tag.hidden) : tag.hidden != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (hidden != null ? hidden.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        return result;
    }
}
