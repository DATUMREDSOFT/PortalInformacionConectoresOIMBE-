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
public class GenericResponse<T> {
    
    private String state;
	private List<T> data;
	private List<String> messages;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
    
}
