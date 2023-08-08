package com.christ.security.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 史偕成
 * @date 2023/08/07 22:36
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {


    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}
