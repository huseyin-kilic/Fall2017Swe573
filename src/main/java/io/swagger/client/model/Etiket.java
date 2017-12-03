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
 * Etiket
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-29T05:46:48.775Z")
public class Etiket {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("adi")
  private String adi = null;

  @SerializedName("radi")
  private String radi = null;

  public Etiket id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Etiket id bilgisi.
   * @return id
  **/
  @ApiModelProperty(value = "Etiket id bilgisi.")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Etiket adi(String adi) {
    this.adi = adi;
    return this;
  }

   /**
   * Etiket adı bilgisi.
   * @return adi
  **/
  @ApiModelProperty(value = "Etiket adı bilgisi.")
  public String getAdi() {
    return adi;
  }

  public void setAdi(String adi) {
    this.adi = adi;
  }

  public Etiket radi(String radi) {
    this.radi = radi;
    return this;
  }

   /**
   * Etiket radı (slug) bilgisi.
   * @return radi
  **/
  @ApiModelProperty(value = "Etiket radı (slug) bilgisi.")
  public String getRadi() {
    return radi;
  }

  public void setRadi(String radi) {
    this.radi = radi;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Etiket etiket = (Etiket) o;
    return Objects.equals(this.id, etiket.id) &&
        Objects.equals(this.adi, etiket.adi) &&
        Objects.equals(this.radi, etiket.radi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, adi, radi);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Etiket {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    adi: ").append(toIndentedString(adi)).append("\n");
    sb.append("    radi: ").append(toIndentedString(radi)).append("\n");
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
