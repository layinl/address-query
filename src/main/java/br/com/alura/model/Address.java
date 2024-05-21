package br.com.alura.model;

import com.google.gson.annotations.SerializedName;

public record Address (
  @SerializedName("cep")
  String postalCode,
  @SerializedName("logradouro")
  String addressFirstLine,
  @SerializedName("complemento")
  String addressSecondLine,
  @SerializedName("bairro")
  String district,
  @SerializedName("localidade")
  String city,
  @SerializedName("uf")
  String state
) {

}
