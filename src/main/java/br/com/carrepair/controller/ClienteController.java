package br.com.carrepair.controller;

import br.com.carrepair.dominio.ClienteDTO;
import br.com.carrepair.dominio.MensagemErro;
import br.com.carrepair.dominio.RepositorioClientes;
import br.com.carrepair.infra.dao.ClienteDAO;
import br.com.carrepair.dominio.Cliente;
import br.com.carrepair.service.ClienteService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("clientes")
public class ClienteController  {

    private RepositorioClientes clienteDAO;
    private ClienteService clienteService;
    private MensagemErro mensagemErro;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
        clienteService = new ClienteService(clienteDAO);
        mensagemErro = new MensagemErro();
    }

    @GET
    @Path("/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterClientePorId(@PathParam("idCliente") Long id) {
            Response.Status status;
            try {
                Cliente cliente = clienteService.obterCliente(id);
                if (cliente == null) status = Response.Status.NOT_FOUND;
                else status = Response.Status.OK;
                return Response
                        .status(status)
                        .entity(cliente)
                        .build();
            } catch (RuntimeException e) {
                e.printStackTrace();
                return Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .build();
            }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterClientePorCpf(@QueryParam("cpf") long cpf) {
            Response.Status status;
            try {
                Cliente cliente = clienteService.obterCliente(cpf);
                if (cliente == null) status = Response.Status.NOT_FOUND;
                else status = Response.Status.OK;
                return Response
                        .status(status)
                        .entity(cliente)
                        .build();
            } catch (RuntimeException e) {
                e.printStackTrace();
                return Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .build();
            }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(Cliente cliente) {
        try {
            clienteService.adicionar(cliente);
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(clienteDTO)
                    .build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            Response.Status status;
            if (e.getMessage().contains("ORA-00001")) {
                status = Response.Status.NOT_ACCEPTABLE;
                mensagemErro.setMensagem("Email já utilizado.");
            }
            else {
                status = Response.Status.INTERNAL_SERVER_ERROR;
                mensagemErro.setMensagem("Erro ao realizar requisição");
            }
            return Response
                    .status(status)
                    .entity(mensagemErro)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPeca(Cliente cliente) {

        Response.Status status;
        try {
            clienteService.atualizar(cliente);
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
    @Path("/{idCliente}")
    public Response deletarPeca(@PathParam("idCliente") Long id) {
        try {
            clienteService.deletar(id);
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
