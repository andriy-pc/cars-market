package org.mota.carselectionservice.integration;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.mota.carselectionservice.service.CarService;
import org.mota.grpc.CarClientGrpc.CarClientImplBase;
import org.mota.grpc.CarFilter;
import org.mota.grpc.GetAvailableCarsResponse;
import org.mota.grpc.ReserveCarRequest;
import org.mota.grpc.ReserveCarResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcCarSelection extends CarClientImplBase {

  private final CarService carService;

  @Override
  public void getListOfAvailableCars(CarFilter request,
      StreamObserver<GetAvailableCarsResponse> responseObserver) {
    responseObserver.onNext(GetAvailableCarsResponse.newBuilder()
        .addAllCar(carService.getAvailableCars(request).keySet())
        .build());
    responseObserver.onCompleted();
  }

  @Override
  public void reserveCar(ReserveCarRequest request,
      StreamObserver<ReserveCarResponse> responseObserver) {
    responseObserver.onNext(carService.reserveCar(request));
    responseObserver.onCompleted();
  }

}
