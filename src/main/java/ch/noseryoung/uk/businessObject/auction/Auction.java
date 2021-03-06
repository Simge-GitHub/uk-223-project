package ch.noseryoung.uk.businessObject.auction;

import javax.persistence.*;

// This is an example class on a domain model could be built
// These annotations show hibernate that this is an entity
@Entity

// This annotation defines this entity as it's own table with it's given name
// We can also not define it's name, but then it'd take the name of the class itself and that wouldn't be best practice
@Table(name = "auction")
public class Auction {

    // Regular attributes
    // The primary key, this annotation defines that this is a primary key:
    @Id

    // This annotation makes sure that our id gets auto-incremented
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // This annotation simply defines that this attribute has it's own column and how that column is called
    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private int price;

    // In this example the name has not been defined, if you do this hibernate will plainly use the name of the variable itself
    // The nullable parameter defines if this attribute can be null in the database
    @Column(nullable = false)
    private String name;

    public Auction(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public Auction() {

    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Auction setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Auction setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Auction setPrice(int price) {
        this.price = price;
        return this;
    }
}
