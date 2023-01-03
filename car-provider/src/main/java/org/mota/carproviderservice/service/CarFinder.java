package org.mota.carproviderservice.service;

import java.util.ArrayList;
import java.util.List;
import org.mota.grpc.Car;
import org.mota.grpc.CarFilter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CarFinder {

  private static final List<Car> AVAILABLE_CARS = new ArrayList<Car>() {{
    add(Car.newBuilder()
        .setMake("Nissan")
        .setModel("350Z")
        .setYear(2006)
        .setPrice(10500F)
        .setColor("black")
        .build());
    add(Car.newBuilder()
        .setMake("Ford")
        .setModel("Focus RS")
        .setYear(2016)
        .setPrice(25500F)
        .setColor("grey")
        .build());
    add(Car.newBuilder()
        .setMake("Volkswagen")
        .setModel("Golf GTI")
        .setYear(2010)
        .setPrice(11200F)
        .setColor("black").build());
  }};

  public Flux<Car> findCarsByFilter(CarFilter carFilter) {
    return Flux.fromStream(AVAILABLE_CARS.stream());
  }

}
