package br.com.alura.model;

import com.google.gson.annotations.SerializedName;

/**
 * The Address record represents basic address information
 * @param postalCode the postal code, or CEP
 * @param addressFirstLine the first address line. Generally contains the
 *                         street's name or a square
 * @param addressSecondLine the second address line. Rarely used to inform
 *                          extra data about the postal code, like road
 *                          side or interval
 * @param district the district
 * @param city the city
 * @param state the federative unit
 */
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
