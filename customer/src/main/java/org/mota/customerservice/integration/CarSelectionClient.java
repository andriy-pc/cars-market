package org.mota.customerservice.integration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mota.grpc.Car;
import org.mota.grpc.CarClientGrpc.CarClientBlockingStub;
import org.mota.grpc.CarFilter;
import org.mota.grpc.ReserveCarRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarSelectionClient {

  private final CarClientBlockingStub carClientStub;

  public List<Car> getAvailableCars(CarFilter carFilter) {
    return carClientStub.getListOfAvailableCars(carFilter).getCarList();
  }

  public LocalDateTime reserveCar(ReserveCarRequest request) {
    return Instant.ofEpochMilli(carClientStub.reserveCar(request).getReservationEndDateUnix())
        .atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

}
