package net.apmoller.kronos.services.md.config.calendar.service;


import lombok.extern.slf4j.Slf4j;
import net.apmoller.kronos.services.md.config.calendar.model.ConfigurationProfileEvent;
import net.apmoller.kronos.services.md.config.calendar.model.ConfigurationProfileIdentifier;
import net.apmoller.kronos.services.md.config.calendar.model.ConfigurationProfilesRequestPayload;
import net.apmoller.kronos.services.md.config.calendar.model.ConfigurationProfilesResponsePayload;
import net.apmoller.kronos.services.md.config.calendar.service.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class ConfigCalendarApiService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public CompletableFuture<ConfigurationProfilesResponsePayload> getConfigProfile(ConfigurationProfilesRequestPayload requestPayload, HttpHeaders httpHeaders)
    {

        List<String> configurationProfileIdentifierValues = requestPayload.getConfigurationProfileIdentifiers().stream()
                .filter(identifier -> "HOLIDAY_AND_FORCE_MAEJURE_CONFIG_TYPES".equals(identifier.getConfigurationProfileIdentifierName()))
                .findFirst()
                .map(ConfigurationProfileIdentifier::getConfigurationProfileIdentifierValues)
                .orElseThrow(() -> new RuntimeException("configuration Profile Identifier Values not found"));
        String tableName = null;

        for (String value : configurationProfileIdentifierValues)
        {
            if(value.equals("HOLIDAY"))
            {
                tableName = "calendar_sync.location_to_holiday_mapping";
            }
            else if(value.equals("FORCE_MAEJURE"))
            {
                tableName = "calendar_sync.location_to_force_majeure_mapping";
            }
        }
        String eventStartTimeDate = requestPayload.getConfigurationProfileIdentifiers().get(0).getConfigurationProfileIdentifierValue();
        String eventEndTimeDate = requestPayload.getConfigurationProfileIdentifiers().get(1).getConfigurationProfileIdentifierValue();

        System.out.println("Start Date: " + eventStartTimeDate);
        System.out.println("End Date: " + eventEndTimeDate);
        String eventStartDate = eventStartTimeDate.substring(0, eventStartTimeDate.indexOf("T"));
        eventStartDate= eventStartDate.replace("-","");

        String eventEndDate= eventEndTimeDate.substring(0,eventEndTimeDate.indexOf("T"));
      eventEndDate=eventEndDate.replace("-","");

        System.out.println("Start Date: " + eventStartDate);
        System.out.println("End Date: " + eventEndDate);


        String sql ="SELECT * " +
                "FROM " +tableName+" "+
                "WHERE day_identifier BETWEEN '"+eventStartDate+"' AND '"+eventEndDate+"'";


          System.out.println(sql);

         String finalTableName = tableName;

          List<Holiday> holidays = jdbcTemplate.query(sql,(rs, rowNum) -> {

            Holiday holiday = new Holiday();


            holiday.setFacility(rs.getString("facility"));
            holiday.setFacilityGroup(rs.getString("facility_group"));

            holiday.setCity(rs.getString("city"));
            holiday.setCityGroup(rs.getString("city_group"));
            holiday.setCountry(rs.getString("country"));
            holiday.setCountryGroup(rs.getString("country_group"));



            holiday.setDayIdentifier(rs.getString("day_identifier"));


           if(finalTableName.contains("force_majeure_mapping"))
           {
              holiday.setIsHoliday(rs.getString("is_force_majeure"));
           }
           if (finalTableName.contains("holiday_mapping"))
           {
               holiday.setIsHoliday(rs.getString("is_holiday"));
           }

            holiday.setRemark(rs.getString("remark"));
            return holiday;
        });
        List<ConfigurationProfileEvent> eventList = new ArrayList<>();

        for (Holiday holiday : holidays)
        {

            System.out.println("Day Identifier: " + holiday.getDayIdentifier());
            String result = (holiday.getFacility() != null) ? holiday.getFacility(): (holiday.getCountry() != null) ?
                    holiday.getCountry() : (holiday.getCity() != null) ? holiday.getCity() : (holiday.getCityGroup() != null)
                    ? holiday.getCityGroup() : (holiday.getCountryGroup() != null) ? holiday.getCountryGroup() : (holiday.getCountry() != null) ? holiday.getCountry() : null;


            System.out.println("location :"+result);

            System.out.println("Is Holiday: " + holiday.getIsHoliday());
            System.out.println("Holiday Name: " + holiday.getRemark());
            System.out.println("Hour identifier start: " + holiday.getHourIdentifierStart());
            System.out.println("Hour identifier end: " + holiday.getHourIdentifierEnd());


            System.out.println("Holiday Name: " + holiday.getRemark());


            System.out.println("---------------------");
            eventList.add(ConfigurationProfileEvent.builder()
                    .remarks(holiday.getRemark())
                    .eventStartDate(eventStartDate)
                    .eventEndDate(eventEndDate)
                    .eventStartHour("00")
                    .eventEndHour("23")
                    .build());
        }

        System.out.println(requestPayload.getLocation());



        ConfigurationProfilesResponsePayload responsePayload = ConfigurationProfilesResponsePayload.builder()
                .configurationProfileType(requestPayload.getConfigurationProfileType())
                .location(requestPayload.getLocation())
                .configurationProfileIdentifiers(requestPayload.getConfigurationProfileIdentifiers())
                .configurationProfileEvents(eventList)
                .build();


        return CompletableFuture.completedFuture(responsePayload);
    }

}
