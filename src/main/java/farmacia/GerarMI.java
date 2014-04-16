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
import relatorio.GeradorRelatorio;
import relatorio.Relatorio;
import relatorio.RelatorioMI;

/**
 *
 * @author UFC
 */
@Entity
@Views({
    @View(name = "GerarMI", title = "Gerar Relatório Medicamnetos Incineração",

    members = "["
    + "#dataInicio, #dataFim, #produto, #fornecedor; "
    + "gerarMedicamentosIncineracao();]",
    roles = "Administrador,Famaceutico,Atendente",
    template = "@PAGER", namedQuery="select new farmacia.GerarMI()" )
})
public class GerarMI implements Serializable{
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
   
   @ManyToOne
   private Produto produto;
   
   
   public File gerarMedicamentosIncineracao() {
       
  
        RelatorioMI dao = new RelatorioMI();
        List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim, produto);
        GeradorRelatorio gerador = new GeradorRelatorio();
        gerador.gerarMI((ArrayList<Relatorio>) results, dataInicio, dataFim, produto.toString());
        Format d = new SimpleDateFormat("dd-MM-yyyy");
        String data_Inicio = d.format(dataInicio);
        String data_Fim = d.format(dataFim);
        String produto = this.produto.toString();
        
        File file = new File("C:/Relatorios/medicamentosIncineracao("+ data_Inicio + "a"+ data_Fim +")"+ produto +".pdf");
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
   
}
