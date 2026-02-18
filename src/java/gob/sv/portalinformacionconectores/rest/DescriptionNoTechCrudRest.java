/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.rest;

import com.google.gson.Gson;
import gob.sv.portalinformacionconectores.data.AttributesPlainOIM;
import gob.sv.portalinformacionconectores.data.GenericResponse;
import gob.sv.portalinformacionconectores.service.InstanceInformationService;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/api/desc")
public class DescriptionNoTechCrudRest {
    
    
    @POST
    @Path("/attr/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AttributesPlainOIM input) throws SQLException{
    
        InstanceInformationService svc = new InstanceInformationService();
        
        GenericResponse<String> rs = svc.AttrRecord(input);
        
        // Convertimos el objeto a un String JSON real
        String jsonOutput = new Gson().toJson(rs);
        
        return Response.ok(jsonOutput).build();
        
    } 
    
    @POST
    @Path("/attr/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(AttributesPlainOIM input) throws SQLException{
    
        InstanceInformationService svc = new InstanceInformationService();
        
        GenericResponse<String> rs = svc.AttrUpdate(input);
        
        // Convertimos el objeto a un String JSON real
        String jsonOutput = new Gson().toJson(rs);
        
        return Response.ok(jsonOutput).build();
        
    } 
    
    @DELETE
    @Path("/attr/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") int id) throws SQLException{
    
        InstanceInformationService svc = new InstanceInformationService();
        
        GenericResponse<String> rs = svc.AttrDelete(id);
        
        // Convertimos el objeto a un String JSON real
        String jsonOutput = new Gson().toJson(rs);
        
        return Response.ok(jsonOutput).build();
        
    }
    
    
    
}
