package com.example.jobfinderapp;

public class Job {
    private String jobName, jobCompany, jobSalary, jobLocation, jobType;

    public Job(String jobName, String jobCompany, String jobSalary, String jobLocation, String jobType) {
        this.jobName = jobName;
        this.jobCompany = jobCompany;
        this.jobSalary = jobSalary;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
