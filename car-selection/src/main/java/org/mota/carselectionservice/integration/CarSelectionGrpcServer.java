package org.mota.carselectionservice.integration;

import annotation.GrpcServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@GrpcServer
public class CarSelectionGrpcServer {

  @Value("${grpc.server.port}")
  private Integer port;

  private final Server server;

  public CarSelectionGrpcServer(ServerBuilder<?> serverBuilder, GrpcCarSelection grpcCarSelection) {
    server = serverBuilder
        .addService(grpcCarSelection)
        .build();
  }

  @PostConstruct
  public void start() throws IOException {
    server.start();
    log.info("gRPC Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      // Use stderr here since the logger may have been reset by its JVM shutdown hook.
      System.err.println("*** shutting down gRPC server since JVM is shutting down");
      try {
        CarSelectionGrpcServer.this.stop();
      } catch (InterruptedException e) {
        e.printStackTrace(System.err);
      }
      System.err.println("*** server shut down");
    }));
  }

  public void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

}
