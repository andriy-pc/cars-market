package org.mota.carproviderservice.service;

import org.mota.grpc.Car;
import org.mota.grpc.CarFilter;
import reactor.core.publisher.Flux;

public interface CarFinder {

  Flux<Car> findCarsByFilter(CarFilter carFilter);

}
