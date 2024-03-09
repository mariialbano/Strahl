package br.com.thomasmariana.apireststhral.controller;

import org.springframework.http.HttpStatus;

public @interface RequestStatus {

    HttpStatus code();

}
