package com.smadex.services;

import com.smadex.domain.Model.ProfileInfo;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.FailedException;
import java.util.concurrent.StructuredTaskScope.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProfileService {

  private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);

  private final RestClient restClient;

  public ProfileService(RestClient restClient) {
    this.restClient = restClient;
  }

  public ProfileInfo getFirstProfileAvailable(String ip) {
    try (var taskScope = StructuredTaskScope.open(
        Joiner.<ProfileInfo>anySuccessfulResultOrThrow())) {
      var startTime = Instant.now();
      taskScope.fork(() -> getProfile(ip, "profile1"));
      taskScope.fork(() -> getProfile(ip, "profile2"));

      var result = taskScope.join();
      logger.info("{} ProfileService.getFirstProfileAvailable: Joined in {} s", ip,
          Duration.between(startTime, Instant.now()).toSeconds());

      return result;

    } catch (FailedException failedException) {
      logger.error("{} ProfileService.getFirstProfileAvailable: All profile requests failed", ip,
          failedException);
      throw new RuntimeException(
          "Failed to retrieve profile information from all available endpoints", failedException);

    } catch (InterruptedException e) {
      logger.info("{} ProfileService.getFirstProfileAvailable: Interrupted", ip);
      throw new RuntimeException(e);
    }
  }

  private ProfileInfo getProfile(String ip, String endpoint) {
    logger.info("{} ProfileService.getFirstProfileAvailable {} : Start", ip, endpoint);
    var profile = restClient.get().uri("/{ip}/{endpoint}", ip, endpoint).retrieve()
        .body(ProfileInfo.class);
    logger.info("{} ProfileService.getFirstProfileAvailable {} : Done", ip, endpoint);
    return profile;
  }

}
