/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionManager {
    
    private static DataSource ds;

    static {
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/operationsDB");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener el DataSource", e);
        }
    }

    private ConnectionManager() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
}
