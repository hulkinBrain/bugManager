package com.bugManager;

public class bugs {
    private String bugId;
    private String bugTitle;
    private String bugDesc;
    private String bugGeneratedById;
    private String bugGeneratedOn;
    private String bugSolvedById;
    private String bugSolvedByName;

    private String bugSolvedOn;
    private String relatedProjectId;
    private String updateLock;

    public String getBugTitle() {
        return bugTitle;
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public String getBugDesc() {
        return bugDesc;
    }

    public void setBugDesc(String bugDesc) {
        this.bugDesc = bugDesc;
    }

    public String getBugGeneratedById() {
        return bugGeneratedById;
    }

    public void setBugGeneratedById(String bugGeneratedById) {
        this.bugGeneratedById = bugGeneratedById;
    }

    public String getBugGeneratedOn() {
        return bugGeneratedOn;
    }

    public void setBugGeneratedOn(String bugGeneratedOn) {
        this.bugGeneratedOn = bugGeneratedOn;
    }

    public String getBugSolvedById() {
        return bugSolvedById;
    }

    public void setBugSolvedById(String bugSolvedById) {
        this.bugSolvedById = bugSolvedById;
    }

    public String getBugSolvedByName() {
        return bugSolvedByName;
    }

    public void setBugSolvedByName(String bugSolvedByName) {
        this.bugSolvedByName = bugSolvedByName;
    }

    public String getBugSolvedOn() {
        return bugSolvedOn;
    }

    public void setBugSolvedOn(String bugSolvedOn) {
        this.bugSolvedOn = bugSolvedOn;
    }

    public String getRelatedProjectId() {
        return relatedProjectId;
    }

    public void setRelatedProjectId(String relatedProjectId) {
        this.relatedProjectId = relatedProjectId;
    }

    public String getUpdateLock() {
        return updateLock;
    }

    public void setUpdateLock(String updateLock) {
        this.updateLock = updateLock;
    }
}
