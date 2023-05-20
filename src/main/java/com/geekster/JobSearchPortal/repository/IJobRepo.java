package com.geekster.JobSearchPortal.repository;

import com.geekster.JobSearchPortal.model.Job;
import com.geekster.JobSearchPortal.model.JobType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobRepo extends CrudRepository<Job,Long>{

// custom finders on data members of Job class
    public List<Job> findByEmployerName(String employerName);
    public List<Job> findBySalaryLessThanEqual(Double salary);
    public List<Job> findByLocation(String location);
    public List<Job> findByCompanyName(String companyName);

//    custom native query

    @Modifying
    @Query(value = "update Job set LOCATION = gurugram where SALARY < :salary",nativeQuery = true)
    public void updateLocationBasedOnSalary(Double salary);

    @Modifying
    @Query(value = "delete from Job where LOCATION = :location",nativeQuery = true)
    public void removeByLocation(String location);

    @Modifying
    @Query(value = "update salary from Job where JOB_TYPE = :jobType",nativeQuery = true)
    public void updateSalaryByJobType(JobType jobType);

    @Modifying
    @Query(value = "update JOB_TYPE = :jobType from Job where description = :description",nativeQuery = true)
    public void updateJobTypeBasedOnDescription(JobType jobType,String description);
}
