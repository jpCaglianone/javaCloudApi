package com.example.apiexemplo.controller;

import com.example.apiexemplo.model.Clientes;
import com.example.apiexemplo.model.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ClientesController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Clientes> listarClientes(){
        return clienteService.listaClientes();
    }

    
    @GetMapping("/clientes/{id}")
    public Optional<Clientes> listarClientes(@PathVariable int id){
        try {
            return clienteService.listaClientePorId(id);
        }
        catch (Exception e){
            System.out.println("Usuario nao encontrado");
            return null;
        }
    }
    @PostMapping ("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Clientes incluiCliente(@RequestBody  Clientes cliente){
        return clienteService.incluiCliente(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaCliente(@PathVariable int id){
        clienteService.excluiCliente(id);
    }

    @PutMapping("/clientes/{id}")
    public Clientes atualizarCliente(@PathVariable int id, @RequestBody Clientes clienteAlterado){
        Clientes clienteAtual = clienteService.listaClientePorId(id).get();
        BeanUtils.copyProperties(clienteAlterado,clienteAtual,"id");

        return clienteService.incluiCliente(clienteAtual);
    }
}