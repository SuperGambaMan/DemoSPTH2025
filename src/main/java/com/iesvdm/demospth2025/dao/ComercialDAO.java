package com.iesvdm.demospth2025.dao;

import com.iesvdm.demospth2025.modelo.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {

    //Mapear CRUD a modelo
    void create(Comercial comercial);

    List<Comercial> getAll();
    Optional<Comercial> find(int id);

    void update(Comercial comercial);

    void delete(int id);

}

