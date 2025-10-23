package com.iesvdm.demospth2025.dao;

import com.iesvdm.demospth2025.modelo.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring
//que forma parte de la 'capa de persistencia'.
@Repository

public class ClienteDAOImpl implements ClienteDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Cliente cliente) {

    }

    @Override
    public List<Cliente> getAll() {
        return List.of();
    }

    @Override
    public Optional<Cliente> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(int id) {

    }
}
