package com.example.apiexemplo.controller;

import com.example.apiexemplo.model.Produtos;
import com.example.apiexemplo.model.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;


    @GetMapping("/produtos")
    public List<Produtos> listaProdutos(){
        return produtoService.listaProdutos();
    }

    @GetMapping("/produtos/{id}")
    public Optional<Produtos> listaProduto(@PathVariable int id){
        try {
            return produtoService.listaProdutoPorId(id);
        }
        catch (Exception e){
            System.out.println("Usuario nao encontrado");
            return null;
        }
    }
    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public Produtos incluiProdutos(@RequestBody  Produtos produto){
        return produtoService.incluiProduto(produto);
    }

    @DeleteMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaProduto(@PathVariable int id){
        produtoService.excluiProduto(id);
    }

    @PutMapping("/produtos/{id}")
    public Produtos atualizarProdutos(@PathVariable int id, @RequestBody Produtos produtoAlterado){
        Produtos produtoAtual = produtoService.listaProdutoPorId(id).get();
        BeanUtils.copyProperties(produtoAlterado,produtoAtual,"id");

        return produtoService.incluiProduto(produtoAtual);
    }

}
