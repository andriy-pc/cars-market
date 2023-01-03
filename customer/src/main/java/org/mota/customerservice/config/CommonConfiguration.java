package org.mota.customerservice.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.modelmapper.ModelMapper;
import org.mota.grpc.CarClientGrpc;
import org.mota.grpc.CarClientGrpc.CarClientBlockingStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

  @Value("${car-selection.host}")
  private String carSelectionHost;

  @Value("${car-selection.port}")
  private Integer carSelectionPort;

  @Bean
  public ManagedChannel carSelectionChannel() {
    return ManagedChannelBuilder
        .forAddress(carSelectionHost, carSelectionPort)
        .usePlaintext()
        .build();
  }

  @Bean
  public CarClientBlockingStub carClientBlockingStub(ManagedChannel carSelectionChannel) {
    return CarClientGrpc.newBlockingStub(carSelectionChannel);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
