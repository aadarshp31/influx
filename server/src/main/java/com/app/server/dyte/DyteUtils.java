package com.app.server.dyte;

import com.app.server.meeting.MeetingDTO;
import com.google.gson.Gson;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;


class Result {
    boolean success;
    List<Data> data;
    Paging paging;
}

class Data {
    String id;
    String created_at;
    String updated_at;
    String title;
    String status;
}

class Paging {
    int total_count;
    int start_offset;
    int end_offset;
}

public class DyteUtils {

    public static List<MeetingDTO> parseMeetingResponse(ResponseEntity<String> responseEntity) throws Exception {
        
        List<MeetingDTO> MettingList = new ArrayList<>();
        Gson gson = new Gson();
        Result res = gson.fromJson(responseEntity.getBody(), Result.class);
        // Now you can access the parsed data
        System.out.println("Total count: " + res.paging.total_count);
        System.out.println("Start offset: " + res.paging.start_offset);
        System.out.println("End offset: " + res.paging.end_offset);

        // Accessing individual data elements
        for (Data data : res.data) {
            // MeetingDTO meetingDTO = new MeetingDTO(null,data.id,data.title,convertToTimestamp(data.created_at),convertToTimestamp(data.updated_at),data.status);
            MeetingDTO meetingDTO = new MeetingDTO();
            meetingDTO.setDependentID(data.id);
            meetingDTO.setMeetingName(data.title);
            meetingDTO.setStatus(data.status);
            meetingDTO.setStartTime(convertToTimestamp(data.created_at));
            meetingDTO.setEndTime(convertToTimestamp(data.updated_at));
            MettingList.add(meetingDTO);
        }

        return MettingList;
    }

    public static long convertToTimestamp(String dateString) {
        // Define the formatter with the expected date-time pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        // Parse the date string to an Instant
        Instant instant = Instant.from(formatter.parse(dateString));

        // Convert Instant to timestamp in milliseconds
        return instant.toEpochMilli();
    }

    /**
     * @param timestamp
     * @return
     */
    public static String convertToDateString(long timestamp) {
        // Convert timestamp to Instant
        Instant instant = Instant.ofEpochMilli(timestamp);

        // Define the formatter with the desired date-time pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX").withZone(ZoneOffset.UTC);

        // Format the Instant to the date string
        return formatter.format(instant);
    }

}
