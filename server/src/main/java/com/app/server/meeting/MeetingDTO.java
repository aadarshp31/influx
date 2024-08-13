package com.app.server.meeting;

public class MeetingDTO {
    private Long meetingId;
    private String dependentId;
    private String meetingName;
    private Long startTime;
    private Long endTime;
    private String status;


    // public MeetingDTO(Long meetingId, String dependentId, String meetingName, Long startTime, Long endTime, String status) {
    //     this.meetingId = meetingId;
    //     this.dependentId = dependentId;
    //     this.meetingName = meetingName;
    //     this.startTime = startTime;
    //     this.endTime = endTime;
    //     this.status = status;
    // }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDependentID() {
        return dependentId;
    }

    public void setDependentID(String dependentID) {
        this.dependentId = dependentID;
    }


}
