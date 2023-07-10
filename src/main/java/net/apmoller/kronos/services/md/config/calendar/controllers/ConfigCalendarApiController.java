package net.apmoller.kronos.services.md.config.calendar.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.apmoller.kronos.services.md.config.calendar.model.*;
import net.apmoller.kronos.services.md.config.calendar.service.ConfigCalendarApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Slf4j

@RestController
@Validated
public class ConfigCalendarApiController
{
     @Autowired
     ConfigCalendarApiService configCalendarApiService;


    @PostMapping(path = "/configuration-calendar-api")
    public CompletableFuture<ResponseEntity<ConfigurationProfilesResponsePayload>> getConfigProfile(@RequestBody ConfigurationProfilesRequestPayload requestPayload, @RequestHeader HttpHeaders httpHeaders) {
        return configCalendarApiService.getConfigProfile(requestPayload, httpHeaders)
                .thenApply(responsePayload -> ResponseEntity.ok().body(responsePayload));
    }
}
