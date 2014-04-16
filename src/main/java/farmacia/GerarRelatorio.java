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
import relatorio.*;

/**
 *
 * @author UFC
 */

@Entity
@Views({
    @View(name = "GerarRelatorio", title = "Gerar Relatóriode Medicamentos Vencidos Incinerados",

    members = "["
    + "#dataInicio, #dataFim, #produto, #fornecedor; "
    + "gerarVencidosIncinerados();]",
    roles = "Administrador,Famaceutico,Atendente",
    template = "@PAGER", namedQuery="select new farmacia.GerarRelatorio()" )
})
public class GerarRelatorio implements Serializable {
    @Id
    private Long id;
    
    @PropertyDescriptor(displayName="Desta Data")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    
    @PropertyDescriptor(displayName="Até esta Data")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;
    
    @ManyToOne
    private Fornecedor fornecedor;
    
    @ManyToOne
    private Produto produto;
  
    
    
    public File gerarVencidosIncinerados() {
        RelatorioVI dao = new RelatorioVI();

        List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim, produto, fornecedor);
        GeradorRelatorio gerador = new GeradorRelatorio();
        gerador.gerarVI((ArrayList<Relatorio>) results, dataInicio, dataFim);
        Format d = new SimpleDateFormat("dd-MM-yyyy");
        String data_Inicio = d.format(dataInicio);
        String data_Fim = d.format(dataFim);

        File file = new File("C:/Relatorios/vencidosIncinerados("+ data_Inicio + "-" + data_Fim+").pdf");
        return file;
    }
    
    
    
/* Forma de fazer sem ter o donwload do arquivo!!  
    public void gerarVencidosIncinerados(){
                
        RelatorioVI dao = new RelatorioVI();
        
         
        List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim, produto, fornecedor);
//         List<Relatorio> results = dao.getRelatorios(new Data);
         GeradorRelatorio gerador = new GeradorRelatorio();
         gerador.gerarRelatorioMensal((ArrayList<Relatorio>) results, dataInicio, dataFim);
         Format d = new SimpleDateFormat("dd-MM-yyyy");
             String data_Inicio = d.format(dataInicio);
             String data_Fim = d.format(dataFim);
        try { 
            
            //         gerador.gerarBPA((ArrayList<Relatorio>) results, dataInicio, dataFim);
                     Desktop.getDesktop().open( new File( "/Users/UFC/Documents/NetBeansProjects/FQ/web/vencidosIncinerados("+ data_Inicio + "-" + data_Fim+").pdf" ) );
//             Desktop.getDesktop().open( new File( "Relatorio_Mensal("+ data_Inicio + "-" + data_Fim+").pdf" ) );
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
    }
  
    public void gerarProdutosFornecedor(){
         RelatorioPF dao = new RelatorioPF();
         List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim, fornecedor);
          GeradorRelatorio gerador = new GeradorRelatorio();
          gerador.gerarBPA((ArrayList<Relatorio>) results, dataInicio, dataFim);
          Format d = new SimpleDateFormat("dd-MM-yyyy");
             String data_Inicio = d.format(dataInicio);
             String data_Fim = d.format(dataFim);
        try { 
            
            //         gerador.gerarBPA((ArrayList<Relatorio>) results, dataInicio, dataFim);
                  Desktop.getDesktop().open( new File( "/Users/UFC/Documents/NetBeansProjects/FQ/web/produtosFornecedor("+ data_Inicio + "-" + data_Fim+").pdf" ) );
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void gerarMedicamentosIncineracao(){
         RelatorioMI dao = new RelatorioMI();
         List<Relatorio> results = dao.getRelatorios(dataInicio, dataFim, produto);
          GeradorRelatorio gerador = new GeradorRelatorio();
          gerador.gerarMI((ArrayList<Relatorio>) results, dataInicio, dataFim);
          Format d = new SimpleDateFormat("dd-MM-yyyy");
             String data_Inicio = d.format(dataInicio);
             String data_Fim = d.format(dataFim);
        try { 
            
            //         gerador.gerarBPA((ArrayList<Relatorio>) results, dataInicio, dataFim);
                  Desktop.getDesktop().open( new File( "/Users/UFC/Documents/NetBeansProjects/FQ/web/medicamentosIncineracao("+ data_Inicio + "-" + data_Fim+").pdf" ) );
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
*/
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    void gerarRE(List<Relatorio> results) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}