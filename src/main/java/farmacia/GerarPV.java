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
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import relatorio.GeradorRelatorio;
import relatorio.Relatorio;
import relatorio.RelatorioPV;

/**
 *
 * @author pedro
 */
@Entity
@Views({
    @View(name = "GerarPV", title = "Gerar Relatório de Produtos Vencidos",

    members = "["
    + "#dataInicio, #dataFim; "
    + "gerarProdutosVencidos();]",
    roles = "Administrador,Famaceutico,Atendente",
    template = "@PAGER", namedQuery="select new farmacia.GerarPV()" )
})


public class GerarPV implements Serializable{
    @Id
    private Long id;

   @PropertyDescriptor(displayName="Data da Entrada")
   @NotNull(message="Informe a data de inicio")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date dataInicio ;
   
   @NotNull(message="Informe a data de termino")
   @PropertyDescriptor(displayName="Ate esta Data")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date dataFim;

   public File gerarProdutosVencidos() {
       RelatorioPV dao = new RelatorioPV();
       List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim);
       GeradorRelatorio gerador = new GeradorRelatorio();
       gerador.gerarPV((ArrayList<Relatorio>) results, dataInicio, dataFim);
       Format d = new SimpleDateFormat("dd-MM-yyyy");
       String data_Inicio = d.format(dataInicio);
       String data_Fim = d.format(dataFim);
        
       File file = new File("C:/Relatorios/produtosVencidos("+ data_Inicio + "-" + data_Fim+").pdf");
       return file;
   }
   
   
   //<editor-fold defaultstate="collapsed" desc="Gets e sets">
   public Date getDataInicio() {
       return dataInicio;
   }
   
   public void setDataInicio(Date dataInicio) {
       this.dataInicio = dataInicio;
   }
   
   public Date getDataFim() {
       return dataFim;
   }
   
   public void setDataFim(Date dataFim) {
       this.dataFim = dataFim;
   }
   
   
   public Long getId() {
       return id;
   }
   
   public void setId(Long id) {
       this.id = id;
   }
   //</editor-fold>
    
}
