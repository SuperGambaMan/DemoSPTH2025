package com.iesvdm.demospth2025;

import com.iesvdm.demospth2025.dao.ClienteDAO;
import com.iesvdm.demospth2025.dao.ComercialDAO;
import com.iesvdm.demospth2025.modelo.Cliente;
import com.iesvdm.demospth2025.modelo.Comercial;
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

            List <Comercial> listComercial = comercialDAO.getAll();
            listComercial.forEach(System.out::println);
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

        Optional<Comercial> optionalComercial = comercialDAO.find(4);
        if (optionalComercial.isPresent()){
            System.out.println(optionalComercial.get());
        }  else {
            //optionalComercial.isEmpty();
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

        Optional<Comercial> optionalComercial = comercialDAO.find(1);
        if (optionalComercial.isPresent()){
            System.out.println(optionalComercial.get());
        } else  {
            //optionalComercial.isEmpty();
            System.out.println("VACIO!!");
        }
    }

    @Test
    void testCreateCliente(){

        //Creaamos un Objeto de Cliente con toda la información
        Cliente cliente = Cliente.builder()
                        .nombre("José")
                        .apellido1("Martín")
                        .apellido2("Tejero")
                        .ciudad("Málaga")
                        .categoria(1)
                        .build();

        System.out.println("Antes de crear id "+cliente.getId());

        //Introducimos la fila en la tabla con la informacion de Cliente
        clienteDAO.create(cliente);

        System.out.println("Despues de crear id "+cliente.getId());

        Optional<Cliente> optionalClienteReal = clienteDAO.find(cliente.getId());
        // Comprobamos hemos rellenado con un Optional la casilla que acabamos de crear
        if(optionalClienteReal.isPresent()) {
            Assertions.assertEquals("José Manuel", optionalClienteReal.get().getNombre());
        } else{
            Assertions.fail();
        }

    }

    @Test
    void testUpdateCliente(){

        //Creaamos un Objeto de Cliente con toda la información
        Cliente cliente = Cliente.builder()
                .nombre("José")
                .apellido1("Martín")
                .apellido2("Tejero")
                .ciudad("Málaga")
                .categoria(1)
                .build();

        System.out.println("Antes de crear id "+cliente.getId());

        //Introducimos la fila en la tabla con la informacion de Cliente
        clienteDAO.create(cliente);

        cliente.setNombre("José Manuel");

        System.out.println("Despues de crear id "+cliente.getId());

        //Cambiamos el Nombre y actualizamos la fila del cliente que acabamos de crear
        clienteDAO.update(cliente);


        Optional<Cliente> optionalClienteReal = clienteDAO.find(cliente.getId());
        // Comprobamos hemos rellenado con un Optional la casilla que acabamos de crear y actualizar
        if(optionalClienteReal.isPresent()) {
            Assertions.assertEquals("José Manuel", optionalClienteReal.get().getNombre());
        } else{
            Assertions.fail();
        }

    }

    @Test
    void testDeleteCliente(){

        //Creaamos un Objeto de Cliente con toda la información
        Cliente cliente = Cliente.builder().nombre("José")
                .apellido1("Martín")
                .apellido2("Tejero")
                .ciudad("Málaga")
                .categoria(1)
                .build();

        System.out.println("Antes de crear id "+cliente.getId());

        //Introducimos la fila en la tabla con la informacion de Cliente
        clienteDAO.create(cliente);

        //Eliminamos la fila del cliente que acabamos de crear
        clienteDAO.delete(cliente.getId());

        Optional<Cliente> optionalClienteReal = clienteDAO.find(cliente.getId());

        // Comprobamos que esta vacia con un Optional la casilla que hemos eliminado
        Assertions.assertTrue(optionalClienteReal.isEmpty());
    }

    @Test
    void testCreateComercial(){

        //Creaamos un Objeto de Comercial con toda la información
        Comercial comercial = Comercial.builder()
                .nombre("Pepe")
                .apellido1("Muñoz")
                .apellido2("Perez")
                .comision(0.25)
                .build();

        System.out.println("Antes de crear id "+comercial.getId());

        //Introducimos la fila en la tabla con la informacion de Comercial
        comercialDAO.create(comercial);

        System.out.println("Despues de crear id "+comercial.getId());

        Optional<Comercial> optionalComercialReal = comercialDAO.find(comercial.getId());
        // Comprobamos hemos rellenado con un Optional la casilla que acabamos de crear
        if(optionalComercialReal.isPresent()) {
            Assertions.assertEquals("Pepe", optionalComercialReal.get().getNombre());
        } else{
            Assertions.fail();
        }

    }

    @Test
    void testUpdateComercial() {

        //Creaamos un Objeto de Comercial con toda la información
        Comercial comercial = Comercial.builder()
                .nombre("Pepe")
                .apellido1("Muñoz")
                .apellido2("Perez")
                .comision(0.25)
                .build();

        //Introducimos la fila en la tabla con la informacion de Comercial
        comercialDAO.create(comercial);

        //Cambiamos el Nombre y actualizamos la fila del comercial que acabamos de crear
        comercial.setNombre("Jose Andres");
        comercialDAO.update(comercial);

        Optional<Comercial> optionalComercialReal = comercialDAO.find(comercial.getId());
        // Comprobamos hemos rellenado con un Optional la casilla que acabamos de crear y actualizar
        if(optionalComercialReal.isPresent()) {
            Assertions.assertEquals("Jose Andres", optionalComercialReal.get().getNombre());
        } else{
            Assertions.fail();
        }
    }

    @Test
    void testDeleteComercial(){

        //Creaamos un Objeto de Comercial con toda la información
        Comercial comercial = Comercial.builder()
                .nombre("Marcos")
                .apellido1("Bernal")
                .apellido2("Urban")
                .comision(0.30)
                .build();

        //Introducimos la fila en la tabla con la informacion de Comercial
        comercialDAO.create(comercial);

        //Eliminamos la fila del comercial que acabamos de crear
        comercialDAO.delete(comercial.getId());

        Optional<Comercial> optionalComercialReal = comercialDAO.find(comercial.getId());
        // Comprobamos que esta vacia con un Optional la casilla que hemos eliminado
        Assertions.assertTrue(optionalComercialReal.isEmpty());
    }

}
