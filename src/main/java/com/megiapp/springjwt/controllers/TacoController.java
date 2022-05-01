package com.megiapp.springjwt.controllers;

import com.megiapp.springjwt.dto.TacoDto;
import com.megiapp.springjwt.models.Taco;
import com.megiapp.springjwt.security.services.TacoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tacos")
public class TacoController {

    private TacoService tacoService;

    public TacoController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping
    public ResponseEntity<List<TacoDto>> getAllTacos() {
        List<TacoDto> tacos = tacoService.findAllTacos();
        return new ResponseEntity<>(tacos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoDto>  getTacoById (@PathVariable("id") Integer id) {
        TacoDto taco = tacoService.findTacoById(id);
        return new ResponseEntity<>(taco, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Taco> addTaco(@RequestBody Taco taco) {
        Taco newTaco = tacoService.addTaco(taco);
        return new ResponseEntity<>(newTaco, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Taco> updateTaco(@RequestBody Taco taco) {
        Taco updateTaco = tacoService.updateTaco(taco);
        return new ResponseEntity<>(updateTaco, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Taco> deleteTaco(@PathVariable("id") Integer id) {
        tacoService.deleteTaco(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}