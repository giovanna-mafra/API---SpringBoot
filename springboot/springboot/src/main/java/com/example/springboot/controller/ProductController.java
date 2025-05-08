package com.example.springboot.controller;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // ou @Controller, mas no caso do Rest serve para específicar para o spring que esse vai ser um bean gerenciado por ele q vai usar uma api REST
public class ProductController {

    @Autowired //Ponto de injeção, da pra fazer via construtor também
    ProductRepository productRepository;

    @PostMapping("/products")

    //@RequestBody -->recebe como "corpo" o dto (name e price)
    //@Valid para validar o NotBlank e NotNull funcionarem
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){

        // dto é apenas para receber, ams vamos converter o dto para o model, por que vamos salvar um tipo model
        var productModel = new ProductModel();

        //BeanUtils.copyProperties --> conversor de dto para model, ModelMapper tbm faz essa conversão
        BeanUtils.copyProperties(productRecordDto, productModel);

        //HttpStatus --> criar status (404,201,...)
        //body --> .save(productModel) --> jpa
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
        public ResponseEntity<List<ProductModel>> getAllProducts(){
            return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());

    }

}
