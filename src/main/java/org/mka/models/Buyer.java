package org.mka.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "buyers")
@NamedQueries({
        @NamedQuery(name = "Buyer.findAll", query = "select b from Buyer b"),
        @NamedQuery(name = "Buyer.deleteBuyerById", query = "delete from Buyer b where b.id = :id")
})
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String first_name;
    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Buyer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                '}';
    }
}
