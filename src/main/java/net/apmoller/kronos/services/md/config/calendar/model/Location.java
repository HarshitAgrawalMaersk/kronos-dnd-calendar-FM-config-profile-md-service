package net.apmoller.kronos.services.md.config.calendar.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.AssertTrue;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Location
{
    @JsonProperty("facilityCode")
    private String facility;

    @JsonProperty("facilityGroupCode")
    private String facilityGroup;

    @JsonProperty("cityCode")
    private String city;

    @JsonProperty("cityGroupCode")
    private String cityGroup;

    @JsonProperty("iso2CountryCode")
    private String country;

    @JsonProperty("countryGroupCode")
    private String countryGroup;

    @AssertTrue(message = "Please provide any one of facilityCode, facilityGroupCode, cityCode, cityGroup, iso2CountryCode and countryGroupCode")
    @JsonIgnore
    public boolean isPropertyValid() {
        int count = 0;
        count += facility != null  ? 1 : 0;
        count += facilityGroup != null  ? 1 : 0;
        count += city != null ? 1 : 0;
        count += cityGroup != null ? 1 : 0;
        count += country != null ? 1 : 0;
        count += countryGroup != null ? 1 : 0;
        return count == 1;
    }

    @AssertTrue(message = "Can't be empty or null")
    @JsonIgnore
    public boolean isValueValid() {
        int count = 0;
        count += facility != null && !facility.isEmpty() ? 1 : 0;
        count += facilityGroup != null && !facilityGroup.isEmpty() ? 1 : 0;
        count += city != null && !city.isEmpty() ? 1 : 0;
        count += cityGroup != null && !cityGroup.isEmpty() ? 1 : 0;
        count += country != null && !country.isEmpty() ? 1 : 0;
        count += countryGroup != null && !countryGroup.isEmpty() ? 1 : 0;
        return count == 1;
    }
}

