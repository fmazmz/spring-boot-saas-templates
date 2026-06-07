package org.fmazmz.jwtservice.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/protected")
public class ProtectedController {

    @GetMapping
    public ResponseEntity<String> demo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("PROTECTED");
    }
}
