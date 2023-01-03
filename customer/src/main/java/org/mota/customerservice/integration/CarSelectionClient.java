package org.mota.customerservice.integration;

import dto.CarDTO;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.mota.grpc.Car;
import org.mota.grpc.CarClientGrpc.CarClientBlockingStub;
import org.mota.grpc.CarFilter;
import org.mota.grpc.ReserveCarRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarSelectionClient {

  private final CarClientBlockingStub carClientStub;
  private final ModelMapper modelMapper;

  public List<CarDTO> getAvailableCars(CarFilter carFilter) {
    List<Car> availableCars = carClientStub.getListOfAvailableCars(carFilter).getCarList();
    return availableCars.stream()
        .map(car -> modelMapper.map(car, CarDTO.class))
        .collect(Collectors.toList());
  }

  public LocalDateTime reserveCar(ReserveCarRequest request) {
    return Instant.ofEpochMilli(carClientStub.reserveCar(request).getReservationEndDateUnix())
        .atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

}
