package fr.troisIl.evaluation;

/**
 * Représente un produit en BDD
 */
public class Product {
    private Integer id;
    private String label;
    private Integer quantity;

   

    public Product(Integer id, String label, Integer quantity) {
        this.id = id;
        this.label = label;
        this.quantity = quantity;
    }

    public Product() {
    }

    public Product(String label, Integer quantity) {
        this.label = label;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
