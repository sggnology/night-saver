package com.sggnology.nightsaver.view

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("")
class LandingViewController {

    @GetMapping("")
    fun landingPage(): ResponseEntity<String> {

        val landingViewText = "Night Saver Sever 에 오신 여러분 여기는 오면 안되는 곳이에요.. 돌아가주세요.."

        return ResponseEntity(landingViewText, HttpStatus.OK)
    }
}