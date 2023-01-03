package org.mota.customerservice.controller;

import dto.CarDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mota.customerservice.integration.CarSelectionClient;
import org.mota.grpc.CarFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {

  private final CarSelectionClient carSelectionClient;

  @PostMapping("/available")
  public List<CarDTO> getAvailableCars(@RequestBody(required = false) CarFilter carsFilter) {
    return carSelectionClient.getAvailableCars(carsFilter);
  }

}
