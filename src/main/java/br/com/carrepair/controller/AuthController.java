package br.com.carrepair.controller;

import br.com.carrepair.dominio.*;
import br.com.carrepair.infra.dao.AuthDAO;
import br.com.carrepair.service.AuthService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthController {

    private RepositorioAuth authDAO;
    private AuthService authService;
    private MensagemErro mensagemErro;

    public AuthController() {
        authDAO = new AuthDAO();
        authService = new AuthService(authDAO);
        mensagemErro = new MensagemErro();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticar(Login login) {
        try {
            Response.Status status;
            ClienteDTO clienteDTO  = authService.autenticar(login);
            if (clienteDTO == null) status = Response.Status.UNAUTHORIZED;
            else status = Response.Status.OK;
            return Response
                    .status(status)
                    .entity(clienteDTO)
                    .build();
        } catch (RuntimeException e) {
            mensagemErro.setMensagem("Erro ao realizar requisição: "+e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(mensagemErro)
                    .build();
        }
    }
}
