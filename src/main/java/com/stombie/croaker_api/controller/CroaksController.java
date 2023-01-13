package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.models.CroakGetDto;
import com.stombie.croaker_api.models.Paginator;
import com.stombie.croaker_api.repo.CroakRepository;
import com.stombie.croaker_api.util.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/croaks")
@RestController
public class CroaksController {
    private final CroakRepository croakRepository;

    @Autowired
    public CroaksController(CroakRepository croakRepository) {
        this.croakRepository = croakRepository;
    }

    @GetMapping("/")
    public ResponseEntity<Paginator<CroakGetDto>> index(@RequestParam(required = false, defaultValue = "1") int page,
                                                        @RequestParam(required = false, defaultValue = "10") int limit) {
        if (page <= 0 || limit <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Set<CroakGetDto> croaks = croakRepository.findAll()
                .stream()
                .map(MappingUtils::mapToDto)
                .collect(Collectors.toSet());
        return ResponseEntity.ok()
                .body(Paginator.of(croaks, 1, 2));
    }
}
