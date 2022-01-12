package apiAutomation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@ToString
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
    private int id;
    private Rating rating;

}
