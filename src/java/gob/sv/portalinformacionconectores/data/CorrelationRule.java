/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.data;

public class CorrelationRule {

    public String targetAttr; // Atributo en el sistema externo (uid, cn, etc)
    public String userAttr;   // Atributo en OIM (User Login, Employee Number, etc)
    public String operator;   // Equals, Contains, etc.
    public String logic;      // OR / AND (el operador global de la regla)

    public CorrelationRule(String target, String user, String op, String log) {
        this.targetAttr = target;
        this.userAttr = user;
        this.operator = op;
        this.logic = log;
    }

    public String getTargetAttr() {
        return targetAttr;
    }

    public void setTargetAttr(String targetAttr) {
        this.targetAttr = targetAttr;
    }

    public String getUserAttr() {
        return userAttr;
    }

    public void setUserAttr(String userAttr) {
        this.userAttr = userAttr;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

}
