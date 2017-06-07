package ru.dolgov.tourservice.firm;

import java.io.Serializable;

/**
 * @author M. Dolgov
 *         06.06.2017.
 */
public class Firm implements Serializable{

    private String name;
    private String address;
    private Float rating;

    public Firm() {
    }

    public Firm(String name, String address, Float rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Firm: " + name + ", address: " + address + ", rating: " + rating;
    }
}
