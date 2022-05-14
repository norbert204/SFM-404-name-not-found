package hu.unideb.inf.notfound.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Column(name = "product_code")
    @Id
    private String product_code;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private int unit_price;

    @Column(name = "total_price")
    private int total_price;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity>-1 ? quantity:0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUnit_price(int unit_price) {

        this.unit_price = unit_price >-1? unit_price:0;
    }

    public int getUnit_price() {
        return unit_price;
    }


    public void setTotal_price() {
        this.total_price = this.unit_price * this.quantity;
    }

    public int getTotal_price() {
        return this.total_price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
