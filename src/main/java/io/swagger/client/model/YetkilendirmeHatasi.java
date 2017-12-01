/*
 * Etkinlik.io API
 * Tüm etkinlikleri toplamayı, kategorize etmeyi ve ilgili kişilere duyurmayı amaç edinmiş bir projedir. Bu API dokümantasyonu ile bir çok kaynaktan ve organizatörden beslenerek bir araya getirdiğimiz etkinlik içeriğine entegre olarak uygulamalar geliştirebilirsiniz. 
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * YetkilendirmeHatasi
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-29T05:46:48.775Z")
public class YetkilendirmeHatasi {
  @SerializedName("sonuc")
  private Integer sonuc = null;

  @SerializedName("mesaj")
  private String mesaj = null;

  public YetkilendirmeHatasi sonuc(Integer sonuc) {
    this.sonuc = sonuc;
    return this;
  }

   /**
   * Get sonuc
   * @return sonuc
  **/
  @ApiModelProperty(value = "")
  public Integer getSonuc() {
    return sonuc;
  }

  public void setSonuc(Integer sonuc) {
    this.sonuc = sonuc;
  }

  public YetkilendirmeHatasi mesaj(String mesaj) {
    this.mesaj = mesaj;
    return this;
  }

   /**
   * Get mesaj
   * @return mesaj
  **/
  @ApiModelProperty(value = "")
  public String getMesaj() {
    return mesaj;
  }

  public void setMesaj(String mesaj) {
    this.mesaj = mesaj;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    YetkilendirmeHatasi yetkilendirmeHatasi = (YetkilendirmeHatasi) o;
    return Objects.equals(this.sonuc, yetkilendirmeHatasi.sonuc) &&
        Objects.equals(this.mesaj, yetkilendirmeHatasi.mesaj);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sonuc, mesaj);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class YetkilendirmeHatasi {\n");
    
    sb.append("    sonuc: ").append(toIndentedString(sonuc)).append("\n");
    sb.append("    mesaj: ").append(toIndentedString(mesaj)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

