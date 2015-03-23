package org.octocode.helpers;

public enum SQLTemplate {
    BY_TAGS("find_order_by_tag.ftl");


    private String name;

    SQLTemplate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
