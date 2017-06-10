package ru.dolgov.tourservice.firm;

import java.io.Serializable;

/**
 * @author M. Dolgov
 *         06.06.2017.
 */
public class Firm implements Serializable, Comparable<Firm>{

    private long id;
    private String name;
    private String address;
    private double rating;

    public Firm() {
    }

    public Firm(long id, String name, String address, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Firm: " + name + ", address: " + address + ", rating: " + rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (this == obj) return true;
        Firm firm = (Firm) obj;
        if (this.id == firm.id) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Firm o) {
        if (this.getRating() > o.getRating()) return 1;
        if (this.getRating() < o.getRating()) return -1;
        return 0;
    }
}
