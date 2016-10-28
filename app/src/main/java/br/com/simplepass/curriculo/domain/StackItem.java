package br.com.simplepass.curriculo.domain;

/**
 * Created by leandro on 6/3/16.
 */
public class StackItem {
    String name;
    String description;
    int level;

    public StackItem(String name, String description, int level) {
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
