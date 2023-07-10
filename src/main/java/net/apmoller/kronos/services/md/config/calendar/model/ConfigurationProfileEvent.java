package net.apmoller.kronos.services.md.config.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ConfigurationProfileEvent
{

    @JsonProperty("eventType")
    private String eventType;


    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("eventStartDate")
    @NotEmpty(message = "It will show event start date")
    private String eventStartDate;

    @JsonProperty("eventEndDate")
    @NotEmpty(message = "It will show event end date")
    private String eventEndDate;

    @JsonProperty("eventStartHour")
    @NotEmpty(message = "It will show event start hour")
    private String eventStartHour;

    @JsonProperty("eventEndHour")
    @NotEmpty(message = "It will show event end hour")
    private String eventEndHour;

    @JsonProperty("eventId")
    private String eventId;

}
