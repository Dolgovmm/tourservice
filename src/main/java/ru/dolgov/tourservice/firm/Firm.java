package ru.dolgov.tourservice.firm;

/**
 * @author M. Dolgov
 *         06.06.2017.
 */
public class Firm {

    private String name;
    private String address;
    private float rating;

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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
