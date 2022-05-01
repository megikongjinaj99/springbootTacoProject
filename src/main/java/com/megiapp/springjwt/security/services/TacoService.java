package com.megiapp.springjwt.security.services;


import com.megiapp.springjwt.dto.TacoDto;
import com.megiapp.springjwt.models.Taco;
import com.megiapp.springjwt.repository.TacoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TacoService {
    @Autowired
    private TacoRepository tacoRepository;

    @Autowired
    public TacoService(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    public Taco addTaco(Taco taco){
        return tacoRepository.save(taco);
    }

    public List<TacoDto> findAllTacos(){

        List<Taco> tacoList = tacoRepository.findAll();

        List<TacoDto> tacoDtoList = tacoList.stream()
                .map(taco -> new TacoDto(taco))
                .collect(Collectors.toList());

        return tacoDtoList;
    }

    public Taco updateTaco(Taco taco){
        return tacoRepository.save(taco);
    }

    public TacoDto findTacoById(Integer id) {
        Taco taco = tacoRepository.findTacoById(id);
        TacoDto tacoDto = new TacoDto(taco);
        return tacoDto;
    }

    public void deleteTaco(Integer id){
        tacoRepository.deleteById(id);
    }

}