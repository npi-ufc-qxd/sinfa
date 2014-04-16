/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.ActionDescriptor;
import entities.annotations.EntityDescriptor;
import entities.annotations.PropertyDescriptor;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Dalmo
 */

@Entity
@EntityDescriptor(hidden = true, displayName="Adicionar Especialidade")
public class AddMedicamento  implements Serializable{
    
    @Id
    @PropertyDescriptor(hidden=true)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idAddMedicamento;
    
    @NotNull(message="Informe o Produto")
    @ManyToOne
    @PropertyDescriptor(displayName="Produto")
    private Produto addProduto;
    
    @NotEmpty(message="Informe a Dose/Dia/Mes")
    @PropertyDescriptor(displayName="Dose/Dia/Mes")
    private String dose;
    
    @ManyToOne
    private Cartao_de_Cadastro cartaoAddProduto;
    
    @ActionDescriptor(methodDisabled= "#{not autoEntityBackBean.formInEditMode}")
    public void removerProduto(){
        cartaoAddProduto.getAddMedicamentos().remove(this);
    }

    public Produto getAddProduto() {
        return addProduto;
    }

    public void setAddProduto(Produto addProduto) {
        this.addProduto = addProduto;
    }

    public Cartao_de_Cadastro getCartaoAddProduto() {
        return cartaoAddProduto;
    }

    public void setCartaoAddProduto(Cartao_de_Cadastro cartaoAddProduto) {
        this.cartaoAddProduto = cartaoAddProduto;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Long getIdAddMedicamento() {
        return idAddMedicamento;
    }

    public void setIdAddMedicamento(Long idAddMedicamento) {
        this.idAddMedicamento = idAddMedicamento;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddMedicamento other = (AddMedicamento) obj;
        if (this.idAddMedicamento != other.idAddMedicamento && (this.idAddMedicamento == null || !this.idAddMedicamento.equals(other.idAddMedicamento))) {
            return false;
        }
        if (this.addProduto != other.addProduto && (this.addProduto == null || !this.addProduto.equals(other.addProduto))) {
            return false;
        }
        if (this.cartaoAddProduto != other.cartaoAddProduto && (this.cartaoAddProduto == null || !this.cartaoAddProduto.equals(other.cartaoAddProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.idAddMedicamento != null ? this.idAddMedicamento.hashCode() : 0);
        hash = 53 * hash + (this.addProduto != null ? this.addProduto.hashCode() : 0);
        hash = 53 * hash + (this.cartaoAddProduto != null ? this.cartaoAddProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AddMedicamento{" + "cartaoAddProduto=" + cartaoAddProduto + '}';
    }
   
}
