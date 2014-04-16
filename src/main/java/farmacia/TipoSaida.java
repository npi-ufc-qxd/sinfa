/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author UFC
 */
@Entity
@Views({
@View(name = "TipoSaida",
        title = "TipoSaida",
        filters = "[nomeTipoSaida,Ctrl.DAO.filter()]",
        members= "["
        + "nomeTipoSaida"
        + "]",
        roles = "Administrador,Famaceutico,Atendente",
        template = "@CRUD+@PAGER"
        )})
public class TipoSaida implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @PropertyDescriptor(index = 1, hidden = true)
    private long idTipoSaida;
    
    @NotNull(message="Informe o Nome")
    @PropertyDescriptor(displayName="Nome do Tipo de Saida")
    private String nomeTipoSaida;

    public long getIdTipoSaida() {
        return idTipoSaida;
    }

    public void setIdTipoSaida(long idTipoSaida) {
        this.idTipoSaida = idTipoSaida;
    }

    public String getNomeTipoSaida() {
        return nomeTipoSaida;
    }

    public void setNomeTipoSaida(String nomeTipoSaida) {
        this.nomeTipoSaida = nomeTipoSaida;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoSaida other = (TipoSaida) obj;
        if ((this.nomeTipoSaida == null) ? (other.nomeTipoSaida != null) : !this.nomeTipoSaida.equals(other.nomeTipoSaida)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.nomeTipoSaida != null ? this.nomeTipoSaida.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return nomeTipoSaida ;
    }
    
    
}
