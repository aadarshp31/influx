package com.app.server.dyte;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.server.dyte.DyteMeetingDTO.DyteMeetingResponseDTO;
import com.app.server.meeting.Meeting;
import com.app.server.meeting.MeetingDTO;
import com.app.server.meeting.MeetingService;

import static com.app.server.common.CONSTANT.*;


@Service
public class DyteService {

    @Value("${dyte-auth-key}")
    String DYTE_AUTH_KEY;

    private final MeetingService meetingService;

    @Autowired
    public DyteService(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    public ResponseEntity<Object> getAllDyteMeetings() throws Exception {

        Map<String, Object> body = new HashMap<String, Object>();
        RestTemplate restTemplate = new RestTemplate();
        
        String uri = HTTPS + DYTE_API + MEETINGS;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", DYTE_AUTH_KEY);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        List<MeetingDTO> dtoList = DyteUtils.parseMeetingResponse(result);
        List<Meeting> resp = meetingService.addMeetings(dtoList);
        
        body.put("result", resp);
        return new ResponseEntity<Object>(body, HttpStatus.OK);

    }

    public ResponseEntity<Object> postDyteMeeting(DyteMeetingDTO DyteMeetingDTO) throws Exception{

        RestTemplate restTemplate = new RestTemplate();
        String uri = HTTPS + DYTE_API + MEETINGS; 
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", DYTE_AUTH_KEY);
        HttpEntity<Object> entity = new HttpEntity<>(DyteMeetingDTO, headers);
        ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
        return new ResponseEntity<Object>(result, HttpStatus.OK);

    }

}
