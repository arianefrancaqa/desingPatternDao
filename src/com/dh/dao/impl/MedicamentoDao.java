package com.dh.dao.impl;

import com.dh.config.ConfiguracaoJDBC;
import com.dh.dao.IDao;
import com.dh.model.Medicamentos;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicamentoDao implements IDao<Medicamentos> {

    private ConfiguracaoJDBC configuracaoJDBC;
    private final static Logger log = Logger.getLogger(MedicamentoDao.class);

    public MedicamentoDao(ConfiguracaoJDBC configuracaoJDBC) {
        this.configuracaoJDBC = configuracaoJDBC;
    }

    @Override
    public Medicamentos salvar(Medicamentos medicamentos) {
        log.debug("Registrando medicamento novo: " + medicamentos.toString());

        Connection connection = configuracaoJDBC.conectarComBancoDeDados();

        Statement statement = null;
        String query = String.format("INSERT INTO medicamentos(nome, laboratorio, quantidade, preco)VALUES('%s', '%s', '%s', '%s')",
                medicamentos.getNome(), medicamentos.getLaboratorio(), medicamentos.getQuantidade(), medicamentos.getPreco());

        try{

            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if(generatedKeys.next()){
                medicamentos.setId(generatedKeys.getInt(1));
                statement.close();
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
