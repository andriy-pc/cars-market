package org.mota.carproviderservice.config;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.ServerBuilder;
import io.grpc.ServerCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

  @Value("${grpc.server.port}")
  private Integer port;

  @Bean
  ServerCredentials insecureServerCredentials() {
    return InsecureServerCredentials.create();
  }

  @Bean
  ServerBuilder<?> serverBuilder(ServerCredentials serverCredentials) {
    return Grpc.newServerBuilderForPort(port, serverCredentials);
  }

}
