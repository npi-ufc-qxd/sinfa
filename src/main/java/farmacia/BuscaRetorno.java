/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.File;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import relatorio.*;

/**
 *
 * @author Bruno
 */

@Entity
@Views({
    @View(name = "BuscaRetorno", title = "BuscaRetorno",
    members = "[#dataInicio, #dataFim, fazerBusca()]",
    roles = "Administrador,Famaceutico,Atendente",
    template = "@PAGER", namedQuery="select new farmacia.BuscaRetorno()" )
})

public class BuscaRetorno implements Serializable{
    @Id
    private Long id;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @NotNull(message="Informe a Data da Entrada")
    @PropertyDescriptor(displayName="Data da Entrada")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
  
    @NotNull(message="Informe a Data de limite da busca")
    @PropertyDescriptor(displayName="Data Limite")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;
        
    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BuscaRetorno other = (BuscaRetorno) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.dataInicio != other.dataInicio && (this.dataInicio == null || !this.dataInicio.equals(other.dataInicio))) {
            return false;
        }
        if (this.dataFim != other.dataFim && (this.dataFim == null || !this.dataFim.equals(other.dataFim))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.dataInicio != null ? this.dataInicio.hashCode() : 0);
        hash = 53 * hash + (this.dataFim != null ? this.dataFim.hashCode() : 0);
        return hash;
    }
    
        
    public File fazerBusca(){
        
        Gerar_RRetorno dao = new Gerar_RRetorno();
        List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim);
        GeradorRelatorio gerador = new GeradorRelatorio();
        gerador.gerarRelatorioRetorno((ArrayList<Relatorio>) results, dataInicio, dataFim);
        Format d = new SimpleDateFormat("dd-MM-yyyy");
        String data_Inicio = d.format(dataInicio);
        String data_Fim = d.format(dataFim);
        
        File file = new File("C:/Relatorios/RetornosPacientes("+ data_Inicio + "-" + data_Fim+").pdf");
        return file;
        
    }  
 }
       
        
   
        
    
