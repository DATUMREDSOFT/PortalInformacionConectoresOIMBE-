/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.dao;

import gob.sv.portalinformacionconectores.data.AttributesPlainOIM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudTablesConnectorsDesc {

    public void insertAttribute(Connection conn, AttributesPlainOIM attr) throws SQLException {
        // 1. Usamos COALESCE para que si la tabla está vacía, el primer ID sea 1
        String sqlMaxId = "SELECT COALESCE(MAX(attr_id), 0) + 1 FROM cat_attributes_oim";

        // 2. Usamos SYSDATE directamente en el SQL para la columna date_create
        String sqlInsert = "INSERT INTO cat_attributes_oim (attr_id, attr_name, attr_desc, date_create, user_create) "
                + "VALUES (?, ?, ?, SYSDATE, ?)";

        int nextId = 1;

        // Obtención del siguiente ID
        try (PreparedStatement psId = conn.prepareStatement(sqlMaxId);
                ResultSet rs = psId.executeQuery()) {
            if (rs.next()) {
                nextId = rs.getInt(1);
            }
        }

        // Ejecución de la inserción
        try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
            psInsert.setInt(1, nextId);
            psInsert.setString(2, attr.getAttrName());
            psInsert.setString(3, attr.getAttrDesc());
            psInsert.setString(4, "RED.DATUM"); // Este es el parámetro 5 del esquema original

            psInsert.executeUpdate();
            attr.setAttrId(nextId); // Actualizamos el objeto con el ID generado
        }
    }

    public void updateAttribute(Connection conn, AttributesPlainOIM attr) throws SQLException {
        String sql = "UPDATE cat_attributes_oim SET attr_name = ?, attr_desc = ? WHERE attr_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, attr.getAttrName());
            ps.setString(2, attr.getAttrDesc());
            ps.setInt(3, attr.getAttrId());
            ps.executeUpdate();
        }
    }

    public void deleteAttribute(Connection conn, int attrId) throws SQLException {
        String sql = "DELETE FROM cat_attributes_oim WHERE attr_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, attrId);
            ps.executeUpdate();
        }
    }

}
