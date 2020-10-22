package model;

public class PurchaseInfo{

    private int purchaseId;
    private String userId;
    private Double amount;
    private String purchaseDate;
    private String productId;
    private int quantity;

    public PurchaseInfo() {
    }

    public PurchaseInfo(int purchaseId, String userId, Double amount, String purchaseDate, String productId, int quantity) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

    

    
}
