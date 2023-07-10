package net.apmoller.kronos.services.md.config.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ConfigurationProfilesRequestPayload
{


    @JsonProperty("configurationProfileType")
    @NotEmpty(message = "Configuration profile type must not be empty")
    private String configurationProfileType;
    @JsonProperty("location")
    private Location location;



    @NotEmpty
    @JsonProperty("configurationProfileIdentifiers")
    private List<ConfigurationProfileIdentifier> configurationProfileIdentifiers;

}