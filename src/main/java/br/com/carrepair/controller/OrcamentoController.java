package br.com.carrepair.controller;

import br.com.carrepair.dominio.Orcamento;
import br.com.carrepair.dominio.RepositorioOrcamentos;
import br.com.carrepair.dominio.RepositorioPecas;
import br.com.carrepair.infra.dao.OrcamentoDAO;
import br.com.carrepair.infra.dao.PecaDAO;
import br.com.carrepair.service.OrcamentoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("orcamento")
public class OrcamentoController {

    private RepositorioOrcamentos orcamentoDAO;
    private RepositorioPecas pecasDAO;
    private OrcamentoService orcamentoService;

    public OrcamentoController() {
        orcamentoDAO = new OrcamentoDAO();
        pecasDAO = new PecaDAO();
        orcamentoService = new OrcamentoService(orcamentoDAO, pecasDAO);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obter(@QueryParam("idCliente") Long idCliente, @QueryParam("idVeiculo") Long idVeiculo) {
        Response.Status status;
        try {
            Orcamento orcamento = orcamentoService.obter(idCliente, idVeiculo);
            if (orcamento == null) status = Response.Status.NOT_FOUND;
            else status = Response.Status.OK;
            return Response
                    .status(status)
                    .entity(orcamento)
                    .build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionar(Orcamento orcamento) {
        try {
            orcamentoService.adicionar(orcamento);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } catch (RuntimeException e) {

            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Orcamento orcamento) {

        Response.Status status;
        try {
            orcamentoService.atualizar(orcamento);
            status = Response.Status.CREATED;
            return Response
                    .status(status)
                    .entity(orcamento)
                    .build();
        } catch (RuntimeException e ) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{idOrcamento}")
    public Response deletar(@PathParam("idOrcamento") Long idOrcamento) {
        try {
            orcamentoService.deletar(idOrcamento);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
