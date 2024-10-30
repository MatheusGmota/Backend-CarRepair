package br.com.carrepair.controller;

import br.com.carrepair.dominio.*;
import br.com.carrepair.infra.dao.DiagnosticoDAO;
import br.com.carrepair.infra.dao.VeiculosDAO;
import br.com.carrepair.service.VeiculosService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("veiculos")
public class VeiculosController {

    private RepositorioVeiculos veiculosDAO;
    private RepositorioDiagnostico diagnosticoDAO;
    private VeiculosService veiculosService;
    private MensagemErro mensagemErro;

    public VeiculosController() {
        veiculosDAO = new VeiculosDAO();
        diagnosticoDAO = new DiagnosticoDAO();
        veiculosService = new VeiculosService(veiculosDAO, diagnosticoDAO);
        mensagemErro = new MensagemErro();
    }

    @Path("/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obter(@PathParam("idCliente") Long idCliente) {
        Response.Status status;
        try {
            Veiculos veiculo = veiculosService.obter(idCliente);
            if (veiculo == null) status = Response.Status.NOT_FOUND;
            else status = Response.Status.OK;
            return Response
                    .status(status)
                    .entity(veiculo)
                    .build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @Path("/{idCliente}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionar(
            @PathParam("idCliente") Long idCliente,
            Veiculos veiculos) {
        try {
            veiculosService.adicionar(veiculos, idCliente);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } catch (RuntimeException e) {
            Response.Status status;
            if (e.getMessage().contains("ORA-02291")) status = Response.Status.BAD_REQUEST;
            else status = Response.Status.INTERNAL_SERVER_ERROR;

            mensagemErro.setMensagem("Erro ao realizar requisição: "+e.getMessage());

            e.printStackTrace();

            return Response
                    .status(status)
                    .entity(mensagemErro)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Veiculos veiculos) {

        Response.Status status;
        try {
            veiculosService.atualizar(veiculos);
            status = Response.Status.CREATED;
            return Response
                    .status(status)
                    .build();
        } catch (RuntimeException e ) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
