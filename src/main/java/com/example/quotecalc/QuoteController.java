package com.example.quotecalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

@RestController
public class QuoteController {

    @Autowired
    Validator validator;

    @GetMapping("/quote")
    public Quote getQuote(Quote quote, BindingResult result) {

        validator.validate(quote);
        if (result.hasErrors()) {   //on any error we just return null.
            return null;
        }

        quote.setApproxQuote(quote.calculateApproxQuote());

        return quote;
    }

    @PostMapping("/quote")
    public Quote post(@Valid @RequestBody Quote quote, HttpSession session) { //TODO: use Spring Session
        quote.setRealQuote(quote.calculateRealQuote());

        session.setAttribute("quote", quote);

        return quote;
    }

    @GetMapping("/quote/details")
    public Quote getQuoteDetails(HttpSession session) {

        Quote quote = (Quote)session.getAttribute("quote");

        return quote;
    }
}
