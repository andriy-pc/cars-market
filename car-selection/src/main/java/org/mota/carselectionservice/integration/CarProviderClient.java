package org.mota.carselectionservice.integration;


import lombok.RequiredArgsConstructor;
import org.mota.carselectionservice.integration.streamobserver.CarsStreamObserver;
import org.mota.grpc.CarFilter;
import org.mota.grpc.CarProviderGrpc.CarProviderStub;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarProviderClient {

  private final CarProviderStub carProviderStub;
  private final CarsStreamObserver carsStreamObserver;

  public void findCarsByFilter(CarFilter carFilter) {
    carProviderStub.findCarsByFilter(carFilter, carsStreamObserver);
  }
}
