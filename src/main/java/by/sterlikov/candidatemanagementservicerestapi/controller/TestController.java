package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.dto.TestDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.TestMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.Test;
import by.sterlikov.candidatemanagementservicerestapi.service.TestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
@Tag(name = "Test resource", description = "description from test resource")
public class TestController {

    private final TestService testService;
    private final TestMapper testMapper;

    @PostMapping
    public ResponseEntity<Test> addTest(@RequestBody TestDto dto) {
        Test test = testService.create(testMapper.createTestDtoToTest(dto));
        return ResponseEntity.ok(test);
    }

    @GetMapping("/all")
    public Page<Test> getAllTests(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(required = false) String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return testService.findTestByName(pageable);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Test> updateTestById(@PathVariable("id") Long id,
                                               @RequestBody TestDto testDto) {
        Test test = testService.findTestById(id);
        test.setDescription(testMapper.createTestDtoToTest(testDto).getDescription());
        test.setName(testMapper.createTestDtoToTest(testDto).getName());
        return ResponseEntity.ok(test);
    }


    @PostMapping("/delete/{id}")
    ResponseEntity<Test> deleteTestById(@PathVariable("id") Long id) {
        Test test = testService.findTestById(id);
        testService.deleteTestById(test);
        return ResponseEntity.ok(test);
    }
}