package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GenreCatalogGameItem {
    public String name;
    public Double originalPrice;
    public Double finalPrice;
    public Double discount;
    public List<Platform> platforms;


    public enum Platform{
        WINDOWS,
        MAC,
        LINUX
    }
}
