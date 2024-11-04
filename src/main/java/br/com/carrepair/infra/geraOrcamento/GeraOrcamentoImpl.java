package br.com.carrepair.infra.geraOrcamento;

import br.com.carrepair.dominio.GeraOrcamento;
import br.com.carrepair.dominio.OrcamentoDTO;
import br.com.carrepair.dominio.Veiculos;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class GeraOrcamentoImpl implements GeraOrcamento {

    @Override
    public OrcamentoDTO obterOrcamento(Veiculos veiculos) {
        OkHttpClient clienteHttp = new OkHttpClient().newBuilder().build();

        // montar uma requisicao
        Request requisicao = new Request.Builder()
                .url("http://127.0.0.1:5000/prever?marca="+veiculos.getMarca()+"&modelo="+veiculos.getModelo()+"&ano_modelo="+veiculos.getAno()+"&quilometragem="+veiculos.getQuilometragem())
                .method("GET", null)
                .build();

        // montar uma resposta
        try {
            Response resposta = clienteHttp.newCall(requisicao).execute();
            ResponseBody body = resposta.body();
            String jsonDeResposta = body.string();

            Gson gson = new Gson();
            return gson.fromJson(jsonDeResposta, OrcamentoDTO.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
