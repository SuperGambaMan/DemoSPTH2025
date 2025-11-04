package com.iesvdm.demospth2025;

import com.iesvdm.demospth2025.dao.ClienteDAO;
import com.iesvdm.demospth2025.dao.ComercialDAO;
import com.iesvdm.demospth2025.modelo.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoSpTh2025ApplicationTests {

    //Spring es un framework de Inyeccion de Dependencias e Inversion de Control

    @Autowired
    ClienteDAO  clienteDAO;
    @Autowired
    ComercialDAO comercialDAO;

    @Test
    void contextLoads() {

    }

    @Test
    void testGetAll(){
        List <Cliente> list = clienteDAO.getAll();
            list.forEach(System.out::println);
    }

    @Test
    void testFindOk(){
        Optional<Cliente> optionalCliente = clienteDAO.find(3);
        if (optionalCliente.isPresent()){
            System.out.println(optionalCliente.get());
        } else {
            //optionalCliente.isEmpty();
            System.out.println("VACIO!!");
        }
    }

    @Test
    void testFindFail(){
        Optional<Cliente> optionalCliente = clienteDAO.find(100);
        if (optionalCliente.isPresent()){
            System.out.println(optionalCliente.get());
        } else {
            //optionalCliente.isEmpty();
            System.out.println("VACIO!!");
        }
    }

    @Test
    void testCreate(){
        Cliente cliente = Cliente.builder().nombre("José")
                        .apellido1("Martín")
                        .apellido2("Tejero")
                        .ciudad("Málaga")
                        .categoria(1)
                        .build();

        System.out.println("Antes de crear id "+cliente.getId());

        clienteDAO.create(cliente);

        cliente.setNombre("José Manuel");

        System.out.println("Despues de crear id "+cliente.getId());

        clienteDAO.update(cliente);



        Optional<Cliente> optionalClienteReal = clienteDAO.find(cliente.getId());

        if(optionalClienteReal.isPresent()) {
            Assertions.assertEquals("José Manuel", optionalClienteReal.get().getNombre());
        } else{
            Assertions.fail();
        }

    }

    @Test
    void testDelete(){

        Cliente cliente = Cliente.builder().nombre("José")
                .apellido1("Martín")
                .apellido2("Tejero")
                .ciudad("Málaga")
                .categoria(1)
                .build();

        System.out.println("Antes de crear id "+cliente.getId());

        clienteDAO.create(cliente);

        clienteDAO.delete(cliente.getId());

        Optional<Cliente> optionalClienteReal = clienteDAO.find(cliente.getId());

        Assertions.assertTrue(optionalClienteReal.isEmpty());


    }

}
