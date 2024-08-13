package com.app.server.meeting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long meetingId;
    @Column(name = "dependentid", nullable = false)
    private String dependentId;
    @Column(name = "meetingname", unique = false)
    private String meetingName;
    @Column(name = "starttime", nullable = true)
    private Long startTime;
    @Column(name = "endtime", nullable = true)
    private Long endTime;
    @Column(name = "status", nullable = true)
    private String status;

    public Meeting() {

    }

    public Meeting(String meetingName, Long startTime, Long endTime, String status, String dependentId, String meetingUId) {
        this.meetingName = meetingName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.dependentId = dependentId;
    }

    public String getmeetingName() {
        return meetingName;
    }

    public void setMeetingname(String meetingName) {
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
