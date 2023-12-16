package by.sterlikov.candidatemanagementservicerestapi.service;

import by.sterlikov.candidatemanagementservicerestapi.model.Test;
import by.sterlikov.candidatemanagementservicerestapi.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public Page<Test> findTestByName(Pageable pageable){
        return testRepository.findAll(pageable);
    }

    public Test create(Test test){
        return testRepository.save(test);
    }

    public Test findTestById(Long id){
        return testRepository.findAllById(id);
    }

    public void deleteTestById(Test test){
         testRepository.delete(test);
    }



}
