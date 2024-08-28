package com.ericsson.gladiators.teammanagerservice.Model;

@lombok.Generated
public class Team {

    private int teamId;
    private String teamName;
    private String teamMembers;
    private int retrospectiveId;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }

    public int getRetrospectiveId() {
        return retrospectiveId;
    }

    public void setRetrospectiveId(int retrospectiveId) {
        this.retrospectiveId = retrospectiveId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamMembers='" + teamMembers + '\'' +
                ", retrospectiveId=" + retrospectiveId +
                '}';
    }
}
