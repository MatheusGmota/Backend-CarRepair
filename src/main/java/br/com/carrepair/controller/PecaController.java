package br.com.carrepair.controller;

import br.com.carrepair.dominio.Peca;
import br.com.carrepair.dominio.RepositorioPecas;
import br.com.carrepair.infra.dao.PecaDAO;
import br.com.carrepair.service.PecaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("pecas")
public class PecaController {

    private RepositorioPecas pecaDAO;
    private PecaService pecaService;

    public PecaController() {
        pecaDAO = new PecaDAO();
        pecaService = new PecaService(pecaDAO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterTodas() {
        Response.Status status;
        ArrayList<Peca> pecas = pecaService.obterTodas();
        if (pecas == null) status = Response.Status.NOT_FOUND;
        else status = Response.Status.OK;
        return Response
                .status(status)
                .entity(pecas)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionar(Peca peca) {
        try {
            pecaService.adicionar(peca);
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPeca(Peca peca) {

        Response.Status status;
        try {
            pecaService.atualizar(peca);
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

    @DELETE
    @Path("/{idPeca}")
    public Response deletarPeca(@PathParam("idPeca") Long id) {
        try {
            pecaService.deletar(id);
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
