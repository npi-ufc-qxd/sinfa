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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import config.Configuracao;
import relatorio.GeradorRelatorio;
import relatorio.Relatorio;
import relatorio.RelatorioPF;

/**
 *
 * @author UFC
 */
@Entity
@Views({
    @View(name = "GerarPF", title = "Gerar Relatório Produto Fornecedor",

    members = "["
    + "#dataInicio, #dataFim, #fornecedor; "
    + "gerarProdutosFornecedor();]",
    roles = "Administrador,Famaceutico,Atendente",
    template = "@PAGER", namedQuery="select new farmacia.GerarPF()" )
})
public class GerarPF implements Serializable{
    @Id
    private Long id;
    
   @PropertyDescriptor(displayName="Data da Entrada")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date dataInicio ;
   
   @PropertyDescriptor(displayName="Ate esta Data")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date dataFim;
   
   @ManyToOne
   private Fornecedor fornecedor;
   
   public File gerarProdutosFornecedor() {
       
  
        RelatorioPF dao = new RelatorioPF();
        List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim, fornecedor);
        GeradorRelatorio gerador = new GeradorRelatorio();
        gerador.gerarPF((ArrayList<Relatorio>) results, dataInicio, dataFim);
        Format d = new SimpleDateFormat("dd-MM-yyyy");
        String data_Inicio = d.format(dataInicio);
        String data_Fim = d.format(dataFim);
        
        File file = new File(Configuracao.CLASSPATH + "relatorios/produtosFornecedor("+ data_Inicio + "-" + data_Fim+").pdf");
        return file;
        
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
   
}
