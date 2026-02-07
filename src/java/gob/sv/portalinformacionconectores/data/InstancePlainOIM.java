/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.data;

/**
 *
 * @author Datum-Redsoft
 */
public class InstancePlainOIM {
    
    public String instance;
    public String attributeTarget;
    public String labelForm;
    public boolean aprov;
    public boolean recon;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getAttributeTarget() {
        return attributeTarget;
    }

    public void setAttributeTarget(String attributeTarget) {
        this.attributeTarget = attributeTarget;
    }

    public String getLabelForm() {
        return labelForm;
    }

    public void setLabelForm(String labelForm) {
        this.labelForm = labelForm;
    }

    public boolean isAprov() {
        return aprov;
    }

    public void setAprov(boolean aprov) {
        this.aprov = aprov;
    }

    public boolean isRecon() {
        return recon;
    }

    public void setRecon(boolean recon) {
        this.recon = recon;
    }
    
    
    
}
