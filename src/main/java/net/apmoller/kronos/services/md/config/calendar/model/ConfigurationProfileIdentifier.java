package net.apmoller.kronos.services.md.config.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ConfigurationProfileIdentifier
{


    @JsonProperty("configurationProfileIdentifierName")
    @NotEmpty
    private String configurationProfileIdentifierName;

    @JsonProperty("configurationProfileIdentifierValues")
    private List<String> configurationProfileIdentifierValues;
    @JsonProperty("configurationProfileIdentifierValue")
    private String configurationProfileIdentifierValue;
}
