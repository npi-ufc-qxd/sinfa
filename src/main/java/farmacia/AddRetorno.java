/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.ActionDescriptor;
import entities.annotations.EntityDescriptor;
import entities.annotations.PropertyDescriptor;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Dalmo
 */

@Entity
@EntityDescriptor(hidden = true, displayName="Adicionar Retorno")
public class AddRetorno implements Serializable {
    
    @Id
    @PropertyDescriptor(hidden=true)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idRetorno;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull(message="Informe a Data de Retorno")
    private Date dataRetono;

    @ManyToOne
    private Cartao_de_Cadastro cartaoAddRetorno;
   
    @ActionDescriptor(methodDisabled= "#{not autoEntityBackBean.formInEditMode}")
    public void removerRetorno(){
        cartaoAddRetorno.getAddRetornos().remove(this);
    }

    public Cartao_de_Cadastro getCartaoAddRetorno() {
        return cartaoAddRetorno;
    }

    public void setCartaoAddRetorno(Cartao_de_Cadastro cartaoAddRetorno) {
        this.cartaoAddRetorno = cartaoAddRetorno;
    }

    public Long getIdRetorno() {
        return idRetorno;
    }

    public void setIdRetorno(Long idRetorno) {
        this.idRetorno = idRetorno;
    }

    public Date getDataRetono() {
        return dataRetono;
    }

    public void setDataRetono(Date dataRetono) {
        this.dataRetono = dataRetono;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddRetorno other = (AddRetorno) obj;
        if (this.idRetorno != other.idRetorno && (this.idRetorno == null || !this.idRetorno.equals(other.idRetorno))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.idRetorno != null ? this.idRetorno.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "cartaoAddRetorno=" + cartaoAddRetorno ;
    }    
}
