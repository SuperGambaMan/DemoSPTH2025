package com.iesvdm.demospth2025.dao;

import com.iesvdm.demospth2025.modelo.Comercial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ComercialDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {
        String sql = """
                insert into comercial (nombre, apellido1, apellido2, ciudad, categoría)
                values (                  ?,         ?,         ?,      ?,         ?);
                """;

        String[] ids = {"id"};
        // Keyholder para recuperar el id autoincremental
        KeyHolder keyholder = new GeneratedKeyHolder();
        //Con recuperación de id generado
/*
        jdbcTemplate.update(sql,
                comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getCiudad(),
                comercial.getCategoria()

        );
 */
        //Otra manera de hacerlo
        jdbcTemplate.update(con -> {

            PreparedStatement ps = con.prepareStatement(sql,ids);
            ps.setString(1, comercial.getNombre()); //set String por que es una palabra
            ps.setString(2, comercial.getApellido1());
            ps.setString(3, comercial.getApellido2());
            ps.setDouble(5,comercial.getComision()); //set INT por que es un numero

            return ps;
        },keyholder);

        comercial.setId(keyholder.getKey().intValue());

    }

    @Override
    public List<Comercial> getAll() {

        List<Comercial> listComercials = jdbcTemplate.query(""" 
                        select *
                        from comercial
                        """,
                //RowMapper ----v
                (rs, rowNum) ->new Comercial(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getDouble("comisión")
                )
        );

        log.info("Devueltos {} clientes", listComercials.size());

        return listComercials;
    }

    @Override
    public Optional<Comercial> find(int id) {

        try{
            Comercial comercial = jdbcTemplate.queryForObject(""" 
                        select * from comercial
                        where id = ?
                        """,
                    (rs, rowNum)-> Comercial.builder()
                            .id(rs.getInt("id"))
                            .nombre(rs.getString("nombre"))
                            .apellido1(rs.getString("apellido1"))
                            .apellido2(rs.getString("apellido2"))
                            .comision(rs.getDouble("comisión"))
                            .build()
                    ,
                    id
            );

            return Optional.of(comercial);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Comercial comercial) {

        int rowsUpdate = jdbcTemplate.update("""
                UPDATE comercial
                SET nombre = ?, apellido1 = ?, apellido2 = ?, comisión =?
                WHERE id = ?;
                """ ,
                comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision(),
                comercial.getId());

        log.debug ("Filas actualizadas {}", rowsUpdate);

    }

    @Override
    public void delete(int id) {

        int rowsDelete = jdbcTemplate.update("""
                DELETE FROM comercial
                WHERE id = ?;
                """ ,
                id);

        log.debug ("Filas actualizadas {}", rowsDelete);

    }

}
