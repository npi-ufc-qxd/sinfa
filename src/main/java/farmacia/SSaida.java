/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author UFC
 */

@Entity
@Views({
@View(name = "Setor",
        title = "Setor",
        filters = "[nomeSetor,Ctrl.DAO.filter()]",
        members= "["
        + "nomeSetor, tipo"
        + "]",
        roles = "Administrador,Famaceutico,Atendente",
        template = "@CRUD+@PAGER"
        )
})
public class SSaida implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @PropertyDescriptor(index = 1, hidden = true)
    private long idSetores;
    
    @NotNull(message="Informe o Setor")
    @PropertyDescriptor(displayName="Nome do Setor")
    private String nomeSetor;
    
    @ManyToOne
    @NotNull
    @PropertyDescriptor(displayName="Tipo de Saida")
    private TipoSaida tipo;

    public long getIdSetores() {
        return idSetores;
    }

    public void setIdSetores(long idSetores) {
        this.idSetores = idSetores;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public TipoSaida getTipo() {
        return tipo;
    }

    public void setTipo(TipoSaida tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SSaida other = (SSaida) obj;
        if ((this.nomeSetor == null) ? (other.nomeSetor != null) : !this.nomeSetor.equals(other.nomeSetor)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.nomeSetor != null ? this.nomeSetor.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return  nomeSetor ;
    }
    
    
}
