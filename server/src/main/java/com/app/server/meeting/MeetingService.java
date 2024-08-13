package com.app.server.meeting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting addMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = new Meeting();
        
        meeting.setDependentID(meetingDTO.getDependentID());
        meeting.setMeetingname(meetingDTO.getMeetingName());
        meeting.setStartTime(meetingDTO.getStartTime());
        meeting.setEndTime(meetingDTO.getEndTime());
        meeting.setStatus(meetingDTO.getStatus());
        Meeting MeetingStore = meetingRepository.save(meeting);

        return MeetingStore;

    }

     public List<Meeting> addMeetings(List<MeetingDTO> meetingDTOList) {
        List<Meeting> meetings = new ArrayList<>();
        for (MeetingDTO meetingDTO : meetingDTOList) {
            Meeting meeting = addMeeting(meetingDTO);
            meetings.add(meeting);
        }
        return meetings;
    }

}
