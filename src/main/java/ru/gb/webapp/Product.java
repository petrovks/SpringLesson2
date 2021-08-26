package ru.gb.webapp;

public class Product {
    private Long id;
    private String title;
    private int cost;

    public Product(Long id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + cost + '}';
    }
}
