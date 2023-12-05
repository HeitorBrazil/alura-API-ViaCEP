package com.HeitorBrazil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        try {

            System.out.println("------Leitor de CEP------");
            System.out.print("Insira o CEP: ");
            String cep = tec.nextLine();
            cep = cep.trim().replaceAll("-", "").replaceAll(" ", "");
            String query = "https://viacep.com.br/ws/" + cep + "/json/";

            if(cep.length() != 8 || !onlyNumbers(cep)) {
                throw new CepErradoException("Houve um erro no CEP inserido");
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(query)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            EnderecoAPI enderecoAPI = gson.fromJson(response.body(), EnderecoAPI.class);
            EnderecoVO enderecoVO = new EnderecoVO(enderecoAPI);

            System.out.println("-------CEP ENCONTRADO-------\n" + enderecoVO);


            FileWriter writer = new FileWriter("cep.json");
            writer.write(gson.toJson(enderecoVO));
            writer.close();

        } catch (IOException | InterruptedException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    private static boolean onlyNumbers(String cep) {
        try{
            int num = Integer.parseInt(cep);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
