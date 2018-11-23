package ua.com.bpgdev.movieland.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private double rating;
    private BigDecimal price;
    private String picturePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getNameNative() {
        return nameNative;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Movie movie = (Movie) obj;
        return id == movie.id &&
                nameRussian != null && nameRussian.equals(movie.nameRussian) &&
                nameNative != null && nameNative.equals(movie.nameNative) &&
                yearOfRelease == movie.yearOfRelease &&
                rating == movie.rating &&
                price != null && price.equals(movie.price) &&
                picturePath != null && picturePath.equals(movie.picturePath)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRussian, nameNative, yearOfRelease, rating, price, picturePath);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", rating=" + rating +
                ", price=" + price +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
