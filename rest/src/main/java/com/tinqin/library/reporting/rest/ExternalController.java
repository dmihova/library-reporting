package com.tinqin.library.reporting.rest;

import com.tinqin.library.book.api.operations.book.getbooksidlist.GetBooksIdListInput;
import com.tinqin.library.reporting.api.ApiRoutes;
import com.tinqin.library.reporting.domain.clients.BookClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ExternalController extends BaseController {

    private final BookClient bookClient;


    @GetMapping(ApiRoutes.BOOK)
    public ResponseEntity<?> getBook(
            @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @Valid @RequestParam(name = "authorId", required = false, defaultValue = "") String authorId,
            @RequestParam(name = "createDateMin", required = false ) LocalDate createDateMin,
            @RequestParam(name = "createDateMax", required = false ) LocalDate createDateMax,
            @RequestParam(name = "pageMin", required = false ) Integer pageMin,
            @RequestParam(name = "pageMax", required = false ) Integer pageMax
    ) {


        return   bookClient.getBookIDList(title, authorId, createDateMin, createDateMax, pageMin, pageMax);


    }

}
