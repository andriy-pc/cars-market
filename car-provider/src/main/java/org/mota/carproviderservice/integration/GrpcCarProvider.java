package org.mota.carproviderservice.integration;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.mota.carproviderservice.service.CarFinder;
import org.mota.grpc.Car;
import org.mota.grpc.CarFilter;
import org.mota.grpc.CarProviderClientGrpc.CarProviderClientImplBase;

@RequiredArgsConstructor
public class GrpcCarProvider extends CarProviderClientImplBase {

  private final CarFinder carFinder;

  @Override
    public void findCarsByFilter(CarFilter request, StreamObserver<Car> responseObserver) {
    carFinder.findCarsByFilter(request)
        .doOnNext(responseObserver::onNext)
        .doFinally(car -> responseObserver.onCompleted());
  }
}
