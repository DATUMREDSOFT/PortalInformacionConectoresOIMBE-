/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.data;

import java.util.List;

/**
 *
 * @author Datum-Redsoft
 */
public class InstanceInfoJson {
    
    public String description;
    
    public String descriptionRecon;
    
    public String descriptionScript;
    
    public InstanceTemplate infoTemplate;
    
    public String connectorDeploymentPath;
    
    public String transformationScript;
    
    public List<AttributeMapping> mappings;
    
    public List<CorrelationRule> identityCorrelationRules;

    public InstanceTemplate getInfoTemplate() {
        return infoTemplate;
    }

    public void setInfoTemplate(InstanceTemplate infoTemplate) {
        this.infoTemplate = infoTemplate;
    }

    public String getConnectorDeploymentPath() {
        return connectorDeploymentPath;
    }

    public void setConnectorDeploymentPath(String connectorDeploymentPath) {
        this.connectorDeploymentPath = connectorDeploymentPath;
    }

    public String getTransformationScript() {
        return transformationScript;
    }

    public void setTransformationScript(String transformationScript) {
        this.transformationScript = transformationScript;
    }

    public List<AttributeMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<AttributeMapping> mappings) {
        this.mappings = mappings;
    }

    public List<CorrelationRule> getIdentityCorrelationRules() {
        return identityCorrelationRules;
    }

    public void setIdentityCorrelationRules(List<CorrelationRule> identityCorrelationRules) {
        this.identityCorrelationRules = identityCorrelationRules;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionRecon() {
        return descriptionRecon;
    }

    public void setDescriptionRecon(String descriptionRecon) {
        this.descriptionRecon = descriptionRecon;
    }

    public String getDescriptionScript() {
        return descriptionScript;
    }

    public void setDescriptionScript(String descriptionScript) {
        this.descriptionScript = descriptionScript;
    }
    
}
