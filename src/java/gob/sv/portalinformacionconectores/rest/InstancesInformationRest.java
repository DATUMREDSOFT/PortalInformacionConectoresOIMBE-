/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.rest;

import com.google.gson.Gson;
import gob.sv.portalinformacionconectores.data.AttributesPlainOIM;
import gob.sv.portalinformacionconectores.data.GenericResponse;
import gob.sv.portalinformacionconectores.data.InstanceInfoJson;
import gob.sv.portalinformacionconectores.data.InstancePlainOIM;
import gob.sv.portalinformacionconectores.data.InstancesInformation;
import gob.sv.portalinformacionconectores.service.InstanceInformationService;
import java.sql.SQLException;
import java.util.Arrays;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Datum-Redsoft
 */
@Path("/api/docs/instancesconnectors")
public class InstancesInformationRest {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() throws SQLException {

        GenericResponse<InstancesInformation> rs = new GenericResponse<>();

        InstanceInformationService instSvc = new InstanceInformationService();

        rs.setState("SUCCESS");
        rs.setData(instSvc.instancesList());
        rs.setMessages(Arrays.asList(""));
        
          // Convertimos el objeto a un String JSON real
        String jsonOutput = new Gson().toJson(rs);
        
        return Response.ok(jsonOutput).build();
    }
    
    @GET
    @Path("/attributesList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response attributesList() throws SQLException{
    
        GenericResponse<AttributesPlainOIM> rs = new GenericResponse<>();

        InstanceInformationService instSvc = new InstanceInformationService();

        rs.setState("SUCCESS");
        rs.setData(instSvc.attrsPlain());
        rs.setMessages(Arrays.asList(""));
        
          // Convertimos el objeto a un String JSON real
        String jsonOutput = new Gson().toJson(rs);
        
        return Response.ok(jsonOutput).build();
        
    }

    @GET
    @Path("/completeInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeInfo(@QueryParam("appName") String appName) throws Exception {

        GenericResponse<InstanceInfoJson> rs = new GenericResponse<>();

        InstanceInformationService instSvc = new InstanceInformationService();

        rs.setState("SUCCESS");
        rs.setData(instSvc.instanceInfoAll(appName));
        rs.setMessages(Arrays.asList(""));

        // Convertimos el objeto a un String JSON real
        String jsonOutput = new Gson().toJson(rs);

        return Response.ok(jsonOutput).build();
    }

    
    @GET
    @Path("/InstancesPlain")
    @Produces(MediaType.APPLICATION_JSON)
    public Response instancesPlain(@QueryParam("attributeOIM") String attributeOIM) throws Exception{
        GenericResponse<InstancePlainOIM> rs = new GenericResponse<>();

        InstanceInformationService instSvc = new InstanceInformationService();
        
        rs.setState("SUCCESS");
        rs.setData(instSvc.instancesPlain(attributeOIM));
        rs.setMessages(Arrays.asList(""));
        
        // Convertimos el objeto a un String JSON real
        String jsonOutput = new Gson().toJson(rs);

        return Response.ok(jsonOutput).build();
    }
    
}
