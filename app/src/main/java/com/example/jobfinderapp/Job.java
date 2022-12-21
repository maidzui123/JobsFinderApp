package com.example.jobfinderapp;

public class Job {
    private String jobId, jobName, jobCompany, jobSalary, jobLocation, jobType, jobField, jobRq1, jobRq2, jobRq3;

    public Job() {
    }

    public Job(String jobId, String jobName, String jobCompany, String jobSalary, String jobLocation, String jobType, String jobField, String jobRq1, String jobRq2, String jobRq3) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobCompany = jobCompany;
        this.jobSalary = jobSalary;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.jobField = jobField;
        this.jobRq1 = jobRq1;
        this.jobRq2 = jobRq2;
        this.jobRq3 = jobRq3;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getJobField() {
        return jobField;
    }

    public void setJobField(String jobField) {
        this.jobField = jobField;
    }

    public String getJobRq1() {
        return jobRq1;
    }

    public void setJobRq1(String jobRq1) {
        this.jobRq1 = jobRq1;
    }

    public String getJobRq2() {
        return jobRq2;
    }

    public void setJobRq2(String jobRq2) {
        this.jobRq2 = jobRq2;
    }
    public String getJobRq3() {
        return jobRq3;
    }

    public void setJobRq3(String jobRq3) {
        this.jobRq3 = jobRq3;
    }
}
