package net.apmoller.kronos.services.md.config.calendar.service.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
@ToString
public class Holiday {

    String eventId;
    String dayIdentifier;
    String hourIdentifierStart;
    String hourIdentifierEnd;
    String country;
    String countryGroup;
    String city;
    String cityGroup;
    String facility;
    String facilityGroup;

    String isHoliday;

    String remark;

}
