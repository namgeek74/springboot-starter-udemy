package com.udemy.springbootdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.springbootdemo.entity.Student;
import com.udemy.springbootdemo.entity.StudentOnly;
import com.udemy.springbootdemo.repository.StudentOnlyRepository;
import com.udemy.springbootdemo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private StudentOnlyRepository studentOnlyRepository;

    RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public StudentServiceImpl(StudentOnlyRepository studentOnlyRepository) {
        this.studentOnlyRepository = studentOnlyRepository;
    }

    @Cacheable(value = "Students_1")
    public List<StudentOnly> getListStudent() {
        return studentOnlyRepository.findAll();
    }

    public List<StudentOnly> getDriverByRedis() throws JsonProcessingException {
        String value = this.redisService.getKeyValue("Students_Redis");
        if (value == null || value == "") {
            List<StudentOnly> drivers = this.studentOnlyRepository.findAll();
            this.redisService.saveKeyValue("Students_Redis", objectMapper.writeValueAsString(drivers));
            return drivers;
        }
        return objectMapper.readValue(value, new TypeReference<List<StudentOnly>>() {
        });

    }
}
