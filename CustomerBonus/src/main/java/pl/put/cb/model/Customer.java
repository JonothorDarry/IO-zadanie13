package pl.put.cb.model;

public class Customer {

    private Integer id;
    private Double spendings;

    public Customer(Integer id,  Double spendings) {
        this.id = id;
        this.spendings = spendings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Double getSpendings() {
        return spendings;
    }

    public void setSpendings(Double spendings) {
        this.spendings = spendings;
    }

}
