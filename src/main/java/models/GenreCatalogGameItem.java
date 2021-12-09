package models;

import java.util.List;

public class GenreCatalogGameItem {
    public String name;
    public Double originalPrice;
    public Double finalPrice;
    public Double discount;
    public List<Platform> platforms;

    public GenreCatalogGameItem(String name, Double originalPrice, Double finalPrice, Double discount, List<Platform> platforms) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.discount = discount;
        this.platforms = platforms;
    }

    public enum Platform {
        WINDOWS,
        MAC,
        LINUX
    }

    public String getName() {
        return name;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    @Override
    public String toString() {
        return "GenreCatalogGameItem{" +
                "name='" + name + '\'' +
                ", originalPrice=" + originalPrice +
                ", finalPrice=" + finalPrice +
                ", discount=" + discount +
                ", platforms=" + platforms +
                '}';
    }
}
