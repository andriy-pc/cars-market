package org.mota.carselectionservice.config;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;
import io.grpc.ServerCredentials;
import org.mota.grpc.CarProviderGrpc;
import org.mota.grpc.CarProviderGrpc.CarProviderStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

  @Value("${grpc.server.port}")
  private Integer port;

  @Value("${car-provider.host}")
  private String carProviderHost;

  @Value("${car-provider.port}")
  private Integer carProviderPort;
  @Bean
  ServerCredentials insecureServerCredentials() {
    return InsecureServerCredentials.create();
  }

  @Bean
  ServerBuilder<?> serverBuilder(ServerCredentials serverCredentials) {
    return Grpc.newServerBuilderForPort(port, serverCredentials);
  }

  @Bean
  public ManagedChannel carProviderChannel() {
    return ManagedChannelBuilder
        .forAddress(carProviderHost, carProviderPort)
        .usePlaintext()
        .build();
  }

  @Bean
  CarProviderStub carProviderStub(ManagedChannel carProviderChannel) {
    return CarProviderGrpc.newStub(carProviderChannel);
  }

}
