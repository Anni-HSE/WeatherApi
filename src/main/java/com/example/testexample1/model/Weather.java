package com.example.testexample1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
//@Table(name = "")
public class Weather implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private int cityId;
    private String city;
    private String country;
    private String description;
    private String image;
    private double temp;
    private double temp_min;
    private double temp_max;
    private double wind_speed;

    public Weather() {
    }

    public double getWindSpeed() {
        return wind_speed;
    }

    public int getId() {
        return this.id;
    }

    public int getCityId() {
        return this.cityId;
    }

    public String getDescription() {
        return this.description;
    }

    public double getTemp() {
        return this.temp;
    }

    public double getTemp_min() {
        return this.temp_min;
    }

    public double getTemp_max() {
        return this.temp_max;
    }

    public double getWind_speed() {
        return this.wind_speed;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public String getImage() {
        return this.image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Weather)) return false;
        final Weather other = (Weather) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (this.getCityId() != other.getCityId()) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (Double.compare(this.getTemp(), other.getTemp()) != 0) return false;
        if (Double.compare(this.getTemp_min(), other.getTemp_min()) != 0) return false;
        if (Double.compare(this.getTemp_max(), other.getTemp_max()) != 0) return false;
        if (Double.compare(this.getWind_speed(), other.getWind_speed()) != 0) return false;
        final Object this$country = this.getCountry();
        final Object other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
        final Object this$city = this.getCity();
        final Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Weather;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getCityId();
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final long $temp = Double.doubleToLongBits(this.getTemp());
        result = result * PRIME + (int) ($temp >>> 32 ^ $temp);
        final long $temp_min = Double.doubleToLongBits(this.getTemp_min());
        result = result * PRIME + (int) ($temp_min >>> 32 ^ $temp_min);
        final long $temp_max = Double.doubleToLongBits(this.getTemp_max());
        result = result * PRIME + (int) ($temp_max >>> 32 ^ $temp_max);
        final long $wind_speed = Double.doubleToLongBits(this.getWind_speed());
        result = result * PRIME + (int) ($wind_speed >>> 32 ^ $wind_speed);
        final Object $country = this.getCountry();
        result = result * PRIME + ($country == null ? 43 : $country.hashCode());
        final Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        return result;
    }

    public String toString() {
        return "Weather(id=" + this.getId() + ", cityId=" + this.getCityId() + ", description=" + this.getDescription() + ", temp=" + this.getTemp() + ", temp_min=" + this.getTemp_min() + ", temp_max=" + this.getTemp_max() + ", wind_speed=" + this.getWind_speed() + ", country=" + this.getCountry() + ", city=" + this.getCity() + ", image=" + this.getImage() + ")";
    }
}
