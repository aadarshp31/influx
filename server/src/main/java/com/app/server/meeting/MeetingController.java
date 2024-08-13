package com.app.server.meeting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.server.dyte.DyteMeetingDTO;
import com.app.server.dyte.DyteMeetingDTO.DyteMeetingResponseDTO;
import com.app.server.dyte.DyteService;

import static com.app.server.common.CONSTANT.*;

@RestController
@RequestMapping({ "/api/meetings", "/api/meetings/" })
public class MeetingController {
    @Autowired
    private DyteService dyteService;

    @GetMapping({ "" })
    public ResponseEntity<Object> getAllMeetings() throws Exception {
        Map<String, Object> body = new HashMap<String, Object>();

        ResponseEntity<?> result = dyteService.getAllDyteMeetings();

        body.put("result", result);
        return new ResponseEntity<Object>(body, HttpStatus.OK);

    }

    @GetMapping({ "/{meetingId}", "/{meetingId}/" })
    public ResponseEntity<Object> getUserMeetings(@PathVariable String meetingId) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("success", true);

        try {

        } catch (Exception e) {
            // TODO: handle exception
        }

        return new ResponseEntity<Object>(body, HttpStatus.OK);

    }

    @PostMapping({ "" })
    public ResponseEntity<Object> postMeeting(@RequestBody DyteMeetingDTO DyteMeetingDTO)throws Exception{
        Map<String, Object> body = new HashMap<String, Object>();
        ResponseEntity<?> result = dyteService.postDyteMeeting(DyteMeetingDTO);
        body.put("success", true);
        body.put("data", result);
        DyteMeetingResponseDTO responseDTO = (DyteMeetingResponseDTO)result.getBody();
        // return ResponseEntity.ok(responseDTO);
        return new ResponseEntity<Object>(responseDTO, HttpStatus.OK);
    }

}
