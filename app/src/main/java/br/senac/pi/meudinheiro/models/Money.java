package br.senac.pi.meudinheiro.models;

public class Money {

    private long id;
    private String description;
    private Double value;
    private long category;

    public Money() {}
    public Money(long id, String description, Double value, long category) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
