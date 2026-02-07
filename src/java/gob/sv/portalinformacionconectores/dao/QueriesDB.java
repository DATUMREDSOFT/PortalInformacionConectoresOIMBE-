/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.dao;

/**
 *
 * @author Datum-Redsoft
 */
import gob.sv.portalinformacionconectores.data.AttributesPlainOIM;
import gob.sv.portalinformacionconectores.data.ConnectorDescription;
import gob.sv.portalinformacionconectores.data.InstancePlainOIM;
import gob.sv.portalinformacionconectores.data.InstanceTemplate;
import gob.sv.portalinformacionconectores.data.InstancesInformation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueriesDB {

    public List<AttributesPlainOIM> attributesOIMList(Connection conn) throws SQLException{
       List<AttributesPlainOIM> attrs = new ArrayList<>();
       
       String sqlAttrs = "select attr_id,attr_name, attr_desc from cat_attributes_oim";
       
       try (PreparedStatement ps = conn.prepareStatement(sqlAttrs);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AttributesPlainOIM attr = new AttributesPlainOIM();
                
                attr.setAttrId(rs.getInt(1));
                attr.setAttrName(rs.getString(2));
                attr.setAttrDesc(rs.getString(3));
                
                attrs.add(attr);
            }
        }
       
       return attrs;
    }
    
    
    public List<InstancesInformation> instanciasDBRecords(Connection conn) throws SQLException {

        List<InstancesInformation> instances = new ArrayList<>();

        String sql = "select app_instance_name, app_instance_display_name, app_instance_description from app_instance";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                InstancesInformation instance = new InstancesInformation();

                instance.setInstanceName(rs.getString("app_instance_name"));
                instance.setInstanceDisplayName(rs.getString("app_instance_display_name"));
                instance.setInstanceDescription(rs.getString("app_instance_description"));

                instances.add(instance);
            }
        }

        return instances;
    }

    public ConnectorDescription descriptionInfo(Connection conn, String connectorName) throws SQLException {

        ConnectorDescription infoRs = new ConnectorDescription();

        String sqlDesc = "select CAT_CON_DESC_INFO, CAT_CON_DESC_INFO_SCRIPT, CAT_CON_DESC_RECON from CAT_CONECTORES_DESC where CAT_CON_DESC_INSTANCENAME = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlDesc)) {
            ps.setString(1, connectorName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    infoRs.setDescriptionGen(rs.getString(1));
                    infoRs.setDescriptionScript(rs.getString(2));
                    infoRs.setDescriptionRecon(rs.getString(3));
                }

            }

        }

        return infoRs;
    }

    public InstanceTemplate instanceTemplate(Connection conn, String connectorName) throws SQLException {

        InstanceTemplate instTmp = new InstanceTemplate();

        String sqlTemp = "select at.connector_name, at.connector_version, at.data, to_char(at.create_date,'dd/mm/yyyy') as create_date, (select usr_login from usr where usr_key = at.createby) as createby, to_char(at.update_date,'dd/mm/yyyy') as update_date, (select usr_login from usr where usr_key = at.updateby) as updateby from app_template at WHERE at.name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlTemp)) {
            ps.setString(1, connectorName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    instTmp.setConnectorName(rs.getString("connector_name"));
                    instTmp.setConnectorVersion(rs.getString("connector_version"));
                    instTmp.setData(rs.getString("data"));
                    instTmp.setCreateBy(rs.getString("createby"));
                    instTmp.setCreateDate(rs.getString("create_date"));
                    instTmp.setUpdateBy(rs.getString("updateby"));
                    instTmp.setUpdateDate(rs.getString("update_date"));
                }
            }
        }

        return instTmp;
    }

    public List<InstancePlainOIM> instancesPlain(Connection conn, String attributeOIM) throws SQLException {
        List<InstancePlainOIM> instanceList = new ArrayList<>();

        // Usamos una variable interna en el XPath ($attr) y la pasamos en la cláusula PASSING
        String sqlInstancesFound = "SELECT "
                + " t.name AS instancia, "
                + " x.target_field AS campo_en_target, "
                + " x.display_name AS etiqueta_visible, "
                + " x.es_provisionable AS aprovisionamiento, "
                + " x.es_reconciliable AS reconciliacion "
                + "FROM "
                + " app_template t, "
                + " XMLTABLE('/application/objectClass/form/schemaAttributes/schemaAttribute[@identityAttribute=$attr]' "
                + "   PASSING XMLTYPE(t.data), ? AS \"attr\" " // Aquí inyectamos el parámetro de forma segura
                + "   COLUMNS "
                + "     target_field     VARCHAR2(100) PATH '@name', "
                + "     display_name     VARCHAR2(100) PATH '@displayName', "
                + "     es_provisionable VARCHAR2(10)  PATH '@provisionable', "
                + "     es_reconciliable VARCHAR2(10)  PATH '@reconcileable' "
                + " ) x";

        try (PreparedStatement ps = conn.prepareStatement(sqlInstancesFound)) {
            // Ahora el driver encontrará el índice 1 correctamente fuera del string XPath
            ps.setString(1, attributeOIM);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InstancePlainOIM instanceFound = new InstancePlainOIM();
                    instanceFound.setInstance(rs.getString("instancia"));
                    instanceFound.setAttributeTarget(rs.getString("campo_en_target"));
                    instanceFound.setLabelForm(rs.getString("etiqueta_visible"));
                    instanceFound.setAprov(Boolean.parseBoolean(rs.getString("aprovisionamiento")));
                    instanceFound.setRecon(Boolean.parseBoolean(rs.getString("reconciliacion")));
                    instanceList.add(instanceFound);
                }
            }
        }
        return instanceList;
    }

}
