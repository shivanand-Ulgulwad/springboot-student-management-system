package com.app.exception;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleStudentNotFound(
            StudentNotFoundException ex) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("error/custom-error");

        mav.addObject("errorCode", "404");
        mav.addObject("errorTitle", "Student Not Found");
        mav.addObject("errorMessage", ex.getMessage());

        mav.setStatus(HttpStatus.NOT_FOUND);

        return mav;
    }



    @ExceptionHandler(InvalidFileException.class)
    public ModelAndView handleInvalidFile(
            InvalidFileException ex) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("error/custom-error");

        mav.addObject("errorCode", "400");
        mav.addObject("errorTitle", "Invalid File");
        mav.addObject("errorMessage", ex.getMessage());

        mav.setStatus(HttpStatus.BAD_REQUEST);

        return mav;
    }


    @ExceptionHandler(FileStorageException.class)
    public ModelAndView handleFileStorage(
            FileStorageException ex) {

        log.error("File upload error", ex);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("error/custom-error");

        mav.addObject("errorCode", "500");
        mav.addObject("errorTitle", "Upload Failed");
        mav.addObject("errorMessage", ex.getMessage());

        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return mav;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDatabaseError(
            DataIntegrityViolationException ex) {

        log.error("Database error", ex);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("error/custom-error");

        mav.addObject("errorCode", "500");
        mav.addObject("errorTitle", "Database Error");

        mav.addObject(
                "errorMessage",
                "Database constraint violation occurred."
        );

        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return mav;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgument(
            IllegalArgumentException ex) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("error/custom-error");

        mav.addObject("errorCode", "400");
        mav.addObject("errorTitle", "Invalid Data");
        mav.addObject("errorMessage", ex.getMessage());

        mav.setStatus(HttpStatus.BAD_REQUEST);

        return mav;
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSize(
            MaxUploadSizeExceededException ex) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("error/custom-error");

        mav.addObject("errorCode", "413");
        mav.addObject("errorTitle", "File Too Large");

        mav.addObject(
                "errorMessage",
                "Maximum file size exceeded."
        );

        mav.setStatus(HttpStatus.PAYLOAD_TOO_LARGE);

        return mav;
    }



    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(
            Exception ex,
            HttpServletRequest request) {

        log.error("Unexpected error occurred", ex);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("error/custom-error");

        mav.addObject("errorCode", "500");

        mav.addObject(
                "errorTitle",
                "Unexpected Error"
        );

        mav.addObject(
                "errorMessage",
                "Something went wrong. Please try again."
        );

        mav.addObject(
                "path",
                request.getRequestURI()
        );

        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return mav;
    }
}