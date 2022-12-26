package org.mota.carselectionservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mota.grpc.Car;
import org.mota.grpc.CarFilter;
import org.mota.grpc.ReserveCarRequest;
import org.mota.grpc.ReserveCarResponse;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  private static final List<Car> AVAILABLE_CARS = new ArrayList<>() {{
    add(Car.newBuilder()
        .setId(1)
        .setMake("Honda")
        .setModel("Civic")
        .setYear(2000)
        .setPrice(1000.0F)
        .setColor("Yellow")
        .build()
    );
  }};

  public List<Car> getAvailableCars(CarFilter carFilter) {
    //TODO: to be implemented
    return AVAILABLE_CARS;
  }

  public ReserveCarResponse reserveCar(ReserveCarRequest request) {
    //TODO: to be implemented
    return ReserveCarResponse.newBuilder()
        .setReservationEndDateUnix(LocalDate.now().plusWeeks(1).toEpochDay()).build();
  }

}
