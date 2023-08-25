package br.com.neo.token.util;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    private static final String SALESFORCE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String SALESFORCE_API_PARAM_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public OffsetDateTime parse(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SALESFORCE_DATE_FORMAT);
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);
        return offsetDateTime;
    }
    
    public String format(OffsetDateTime offsetDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SALESFORCE_DATE_FORMAT);
        String formatted = offsetDateTime.format(formatter);
        return formatted;
    }

    public OffsetDateTime parseApiDateParam(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SALESFORCE_API_PARAM_DATE_FORMAT).withZone(ZoneOffset.of("Z"));
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);
        return offsetDateTime;
    }
    
    public OffsetDateTime parseDateUrlParam(String dateString) throws ParseException {
    	LocalDateTime localDateTime = LocalDateTime.parse(dateString);  
    	OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime,ZoneOffset.UTC);
        return offsetDateTime;
    }

    public String formatApiDateParam(OffsetDateTime offsetDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SALESFORCE_API_PARAM_DATE_FORMAT).withZone(ZoneOffset.of("Z"));
        String formatted = offsetDateTime.format(formatter);
        return formatted;
    }
}
