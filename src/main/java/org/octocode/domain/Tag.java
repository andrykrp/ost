package org.octocode.domain;

public class Tag extends AbstractEntity {
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
}
