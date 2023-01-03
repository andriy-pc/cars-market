package org.mota.carselectionservice.integration.streamobserver;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mota.carselectionservice.service.CarService;
import org.mota.grpc.Car;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CarsStreamObserver implements StreamObserver<Car> {

  private final CarService carService;

  @Override
  public void onNext(Car car) {
    carService.addAvailableCar(car);
  }

  @Override
  public void onError(Throwable throwable) {
    log.error("Exception occurred during receiving available cars. Exception: ", throwable);
  }

  @Override
  public void onCompleted() {
    log.info("Receiving of available cars completed");
  }
}
