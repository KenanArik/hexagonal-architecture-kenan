package io.kenan.hexagonalarchitecture.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler
    ResponseEntity<CustomProblemDetail> handle(Exception e) {
        CustomProblemDetail problemDetail = new CustomProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getClass().getSimpleName() + ":" + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

    static class CustomProblemDetail {
        private final HttpStatus status;
        private final String detail;

        public CustomProblemDetail(HttpStatus status, String detail) {
            this.status = status;
            this.detail = detail;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getDetail() {
            return detail;
        }
    }
}