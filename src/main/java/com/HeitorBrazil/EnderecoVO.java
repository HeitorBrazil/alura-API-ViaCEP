package com.HeitorBrazil;

public class EnderecoVO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public EnderecoVO(EnderecoAPI api) {
        if (cep.length() > 8) {
            throw new CepErradoException("Houve um erro no cep inserido");
        }
    }
}
