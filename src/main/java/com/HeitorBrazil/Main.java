package com.HeitorBrazil;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner tec = new Scanner(System.in);

        System.out.println("------Leitor de CEP------");
        System.out.println("Insira o CEP: ");
        String cep = tec.nextLine().trim().replaceAll("-", "").replaceAll(" ", "");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("viacep.com.br/ws/" + cep + "/json/")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        EnderecoAPI enderecoAPI = gson.fromJson(response.body(), EnderecoAPI.class);
        EnderecoVO enderecoVO = new EnderecoVO(enderecoAPI);
    }
}
