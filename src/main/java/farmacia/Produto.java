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
 * @author NPI-UFC - Luis Lima
 */
@Entity
@Views({
@View(name = "Produto",
        title = "Produto",
        filters = "[nomeProduto,Ctrl.DAO.filter()]",
        members= "["
        + "nomeProduto"
        + "]",
        roles = "Administrador,Famaceutico,Atendente",
        template = "@CRUD+@PAGER"
        )
})
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @PropertyDescriptor(index = 1, hidden = true)
    private long idProduto;
    
    @NotNull(message="Informe o Nome")
    @PropertyDescriptor(displayName="Nome do Produto")
    private String nomeProduto;
    
    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if ((this.nomeProduto == null) ? (other.nomeProduto != null) : !this.nomeProduto.equals(other.nomeProduto)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.nomeProduto != null ? this.nomeProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return  nomeProduto;
    }
    
}
