package hu.unideb.inf.notfound.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Column(name = "product_code")
    @Id
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private int productQuantity;

    @Column(name = "unit_price")
    private int productUnitPrice;

    @Column(name = "total_price")
    private int total_price;

    @Column(name = "category")
    private String productCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;



    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(int productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public int getTotal_price() {
        return productUnitPrice * productQuantity;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
