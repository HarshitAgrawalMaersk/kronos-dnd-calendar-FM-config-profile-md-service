package net.apmoller.kronos.services.md.config.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigurationProfilesResponsePayload {

    private String configurationProfileType;
    private Location location;
    private List<ConfigurationProfileIdentifier> configurationProfileIdentifiers;
    private List<ConfigurationProfileEvent> configurationProfileEvents;

    public static ConfigurationProfilesResponsePayloadBuilder builder() {
        return new ConfigurationProfilesResponsePayloadBuilder();
    }

    public static class ConfigurationProfilesResponsePayloadBuilder {
        private String configurationProfileType;
        private Location location;
        private List<ConfigurationProfileIdentifier> configurationProfileIdentifiers;
        private List<ConfigurationProfileEvent> configurationProfileEvents;

        public ConfigurationProfilesResponsePayloadBuilder configurationProfileType(String configurationProfileType) {
            this.configurationProfileType = configurationProfileType;
            return this;
        }

        public ConfigurationProfilesResponsePayloadBuilder location(Location location) {
            this.location = location;
            return this;
        }

        public ConfigurationProfilesResponsePayloadBuilder configurationProfileIdentifiers(List<ConfigurationProfileIdentifier> configurationProfileIdentifiers) {
            this.configurationProfileIdentifiers = configurationProfileIdentifiers;
            return this;
        }

        public ConfigurationProfilesResponsePayloadBuilder configurationProfileEvents(List<ConfigurationProfileEvent> configurationProfileEvents) {
            this.configurationProfileEvents = configurationProfileEvents;
            return this;
        }

        public ConfigurationProfilesResponsePayload build() {
            return new ConfigurationProfilesResponsePayload(configurationProfileType, location, configurationProfileIdentifiers, configurationProfileEvents);
        }
    }

    // Other constructors, getters, and setters
    // ...
}


