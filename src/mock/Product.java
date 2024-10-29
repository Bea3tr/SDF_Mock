package mock;

import java.util.Date;

public class Product {

    // Attributes
    private long id;
    private String prodName;
    private String prodDesc;
    private String prodCat;
    private float price;
    private Date createdDate;

    // Generate constructors, getters / setters and toString()
    public Product(long id, String prodName, String prodDesc, String prodCat, float price, Date createdDate) {
        this.id = id;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodCat = prodCat;
        this.price = price;
        this.createdDate = createdDate;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getProdName() {return prodName;}
    public void setProdName(String prodName) {this.prodName = prodName;}

    public String getProdDesc() {return prodDesc;}
    public void setProdDesc(String prodDesc) {this.prodDesc = prodDesc;}

    public String getProdCat() {return prodCat;}
    public void setProdCat(String prodCat) {this.prodCat = prodCat;}

    public float getPrice() {return price;}
    public void setPrice(float price) {this.price = price;}

    public Date getCreatedDate() {return createdDate;}
    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate;}

    @Override
    public String toString() {
        return "Product{id: %d, product name: %s, product description: %s, product category: %s, price: $%.2f, date: %tc}"
                .formatted(id, prodName, prodDesc, prodCat, price, createdDate);
    }
    


    
}