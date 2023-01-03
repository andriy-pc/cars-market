package org.mota.carselectionservice.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.mota.grpc.Car;
import org.mota.grpc.CarFilter;
import org.mota.grpc.ReserveCarRequest;
import org.mota.grpc.ReserveCarResponse;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  private static final Map<Car, Integer> AVAILABLE_CARS_PER_COUNT = new HashMap<>() {{
    put(Car.newBuilder()
        .setId(1)
        .setMake("Honda")
        .setModel("Civic")
        .setYear(2000)
        .setPrice(1000.0F)
        .setColor("Yellow")
        .build(), 2
    );
  }};

  public Map<Car, Integer> getAvailableCars(CarFilter carFilter) {
    //TODO: to be implemented
    return AVAILABLE_CARS_PER_COUNT;
  }

  public ReserveCarResponse reserveCar(ReserveCarRequest request) {
    //TODO: to be implemented
    return ReserveCarResponse.newBuilder()
        .setReservationEndDateUnix(LocalDate.now().plusWeeks(1).toEpochDay()).build();
  }

  public void addAvailableCar(Car car) {
    AVAILABLE_CARS_PER_COUNT.compute(car, (availableCar, amount) -> {
      if(Objects.isNull(amount)) {
        return 1;
      } else {
        return ++amount;
      }
    });
  }

}
