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
public class AttributeMapping {

    public String target;
    public String origin;
    public String display;
    public String dataType;
    
    public AttributeMapping(String t, String o, String d, String dataType) {
        this.target = t;
        this.origin = (o == null || o.isEmpty()) ? "N/A (Calculado/Fijo)" : o;
        this.display = d;
        this.dataType =dataType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
}
