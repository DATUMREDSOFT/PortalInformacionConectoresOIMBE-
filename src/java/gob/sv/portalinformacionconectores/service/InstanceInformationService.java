/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.service;

import gob.sv.portalinformacionconectores.dao.QueriesDB;
import gob.sv.portalinformacionconectores.data.AttributesPlainOIM;
import gob.sv.portalinformacionconectores.data.ConnectorDescription;
import gob.sv.portalinformacionconectores.data.InstanceInfoJson;
import gob.sv.portalinformacionconectores.data.InstancePlainOIM;
import gob.sv.portalinformacionconectores.data.InstanceTemplate;
import gob.sv.portalinformacionconectores.data.InstancesInformation;
import gob.sv.portalinformacionconectores.db.ConnectionManager;
import gob.sv.portalinformacionconectores.parse.OIMMetadataParser;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import oracle.iam.platform.OIMClient;
import oracle.iam.platform.Platform;
import oracle.iam.platform.utils.crypto.CryptoUtil;

/**
 *
 * @author Datum-Redsoft
 */
public class InstanceInformationService {

    public List<InstancesInformation> instancesList() throws SQLException {

        try (Connection con = ConnectionManager.getConnection()) {
            QueriesDB sqlTools = new QueriesDB();
            return sqlTools.instanciasDBRecords(con);
        }
    }

    public List<InstancePlainOIM> instancesPlain(String attributeOIM) throws SQLException, Exception {
        
        try (Connection con = ConnectionManager.getConnection()) {
            QueriesDB sqlTools = new QueriesDB();
            
            return sqlTools.instancesPlain(con, attributeOIM);
        }
        
    }
    
    public List<AttributesPlainOIM> attrsPlain() throws SQLException{
    
        try (Connection con = ConnectionManager.getConnection()) {
            QueriesDB sqlTools = new QueriesDB();
            
            return sqlTools.attributesOIMList(con); 
        
        }
        
    }
    
    public List<InstanceInfoJson> instanceInfoAll(String connectorName) throws SQLException, Exception {
        List<InstanceInfoJson> resp = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {
            QueriesDB sqlTools = new QueriesDB();

            InstanceTemplate insTempDB = sqlTools.instanceTemplate(con, connectorName);

            //Interpretacion de xml
            OIMMetadataParser pr = new OIMMetadataParser(insTempDB.getData());
            
            ConnectorDescription desc = sqlTools.descriptionInfo(con, connectorName);

            InstanceInfoJson dataJson = new InstanceInfoJson();

            dataJson.setInfoTemplate(insTempDB);
            dataJson.setConnectorDeploymentPath(pr.getConnectorDeploymentPath());
            dataJson.setTransformationScript(pr.getTransformationScript());
            dataJson.setMappings(pr.getMappings());
            dataJson.setIdentityCorrelationRules(pr.getIdentityCorrelationRules());
            dataJson.setDescription(desc.getDescriptionGen());
            dataJson.setDescriptionScript(desc.getDescriptionScript());
            dataJson.setDescriptionRecon(desc.getDescriptionRecon());
            
            resp.add(dataJson);

            return resp;
        }

    }

}
