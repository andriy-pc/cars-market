package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
  private Integer id;
  private String make;
  private String model;
  private Integer year;
  private Float price;
  private String color;
}
