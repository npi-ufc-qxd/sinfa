/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author NPI-UFC - Luis Lima
 */

@Entity
@Table(name="entradas")
@Views({
    @View(name = "Entrada",
        title = "Entrada",
        filters = "[dataEntrada, dataValidade, Ctrl.DAO.filter()]",
        members = "[Dados Entrada[dataEntrada; "
        + "tipoEntrada, origem],"
        + "Dados Produto [loteEntrada;"
        + "dataValidade;"
        + "quantidadeEmbalagem;"
        + "quantidadeTotalEntrada;"
        //+ "quantidadeTotalSaida;"
        + "valorUnitario;"
        + "valorTotal;"
        + "ppi;"
        + "ativo;"
        + "statusProduto;"
        + "fornecedorEntrada;"
        + "produtoEntrada;"
        + "observacoesEntrada;]]",
	roles = "Administrador,Famaceutico,Atendente",
        template = "@CRUD+@PAGER",
        rows = 5),
    
    @View(name = "Estoque",
        title = "Estoque Produto",
        filters = "[dataEntrada, dataValidade, Ctrl.DAO.filter()]",
        members = "[Dados Entrada[dataEntrada; "
        + "tipoEntrada, origem],"
        + "Dados Produto [loteEntrada;"
        + "dataValidade;"
        + "quantidadeEmbalagem;"
        + "quantidadeTotalEntrada;"
        + "quantidadeTotalSaida;]]",
        //+ "valorUnitario;"
        //+ "valorTotal;"
        //+ "ppi;"
        //+ "ativo;"
        //+ "statusProduto;"
        //+ "fornecedorEntrada;"
        //+ "produtoEntrada;"
        //+ "observacoesEntrada;]]",
	roles = "Administrador,Famaceutico,Atendente",
        template = "@CRUD+@PAGER",
        rows = 5)
})

public class Entrada_Produto implements Serializable {
    
    public enum PPI { Sim, Não }
    
    public enum Ativo { Sim, Não }
    
    public enum TipoEntrada{
        Compra,
        Doação
    }
    
    public enum Status{
        Disponivel,
        A_Vencer,
        Vencidos,
        Incinerado
    }
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idEntradas;
    
    @NotNull(message="Informe a Data da Entrada")
    @PropertyDescriptor(displayName="Data da Entrada")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEntrada ;
    
    // Novo campo ORIGEM. Autor: Alessandro 
    @NotNull(message="Informe a Origem")
    @PropertyDescriptor(displayName="Origem")
    private String origem;
    // Novo campo ORIGEM. Autor: Alessandro
    
    @NotNull(message="Informe a Data de Validade")
    @PropertyDescriptor(displayName="Data de Validade")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataValidade;
    
    @NotNull(message="Informe Lote")
    @PropertyDescriptor(displayName="Numero do Lote")
    private String loteEntrada;
    
    @NotNull(message="Informe Quantidade/embalagem")
    @PropertyDescriptor(displayName="Quantidade/Embalagem ")
    private Long quantidadeEmbalagem;
    
    @NotNull(message="Informe Quantidade Total Entrada")
    @PropertyDescriptor(displayName="Quantidade Total Entrada ")
    private Long quantidadeTotalEntrada;
    
    @PropertyDescriptor(displayName="Quantidade Total Saida", hidden=true)
    private Long quantidadeTotalSaida;
    
    @NotNull(message="Informe o Valor Unit.")
    @PropertyDescriptor(displayName="Valor Unit.", displayWidth = 15)
    @Column(length= 15)
    private double valorUnitario;
    
    @PropertyDescriptor(displayName="Valor Total", displayWidth = 15, hidden=true)
    @Column(length= 15)
    private double valorTotal;
    
    @NotNull(message="Informe o PPI")
    @PropertyDescriptor(displayName="PPI")
    private PPI ppi;
    
    @PropertyDescriptor(displayName="Ativo")
    @NotNull(message="Informe o Ativo")
    private Ativo ativo;
    
    @NotNull(message="Informe o Status do Medicamento")
    @PropertyDescriptor(displayName="Status")
    private Status statusProduto;
    
    @NotNull(message="Informe o tipo entrada")
    @PropertyDescriptor(displayName="Tipo de Entrada")
    private  TipoEntrada tipoEntrada;
    
    @ManyToOne
    @NotNull(message = "Informe o Fornecedor")
    @PropertyDescriptor(displayName="Fornecedor")
    private Fornecedor fornecedorEntrada ;
    
    @ManyToOne
    @NotNull(message = "Informe o Produto")
    @PropertyDescriptor(displayName="Produto")
    private Produto produtoEntrada;
   
    @Lob
    @PropertyDescriptor(displayName="Observações")
    private String observacoesEntrada;
    
    
    
    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
    
    public Long getQuantidadeTotalSaida() {
        return quantidadeTotalSaida;
    }

    public void setQuantidadeTotalSaida(Long quantidadeTotalSaida) {
        this.quantidadeTotalSaida = quantidadeTotalSaida;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Fornecedor getFornecedorEntrada() {
        return fornecedorEntrada;
    }

    public void setFornecedorEntrada(Fornecedor fornecedorEntrada) {
        this.fornecedorEntrada = fornecedorEntrada;
    }

    public Long getIdEntradas() {
        return idEntradas;
    }

    public void setIdEntradas(Long idEntradas) {
        this.idEntradas = idEntradas;
    }

    public String getLoteEntrada() {
        return loteEntrada;
    }

    public void setLoteEntrada(String loteEntrada) {
        this.loteEntrada = loteEntrada;
    }

    public String getObservacoesEntrada() {
        return observacoesEntrada;
    }

    public void setObservacoesEntrada(String observacoesEntrada) {
        this.observacoesEntrada = observacoesEntrada;
    }

    public PPI getPpi() {
        return ppi;
    }

    public void setPpi(PPI ppi) {
        this.ppi = ppi;
    }

    public Produto getProdutoEntrada() {
        return produtoEntrada;
    }

    public void setProdutoEntrada(Produto produtoEntrada) {
        this.produtoEntrada = produtoEntrada;
    }
    
    public Long getQuantidadeEmbalagem() {
        return quantidadeEmbalagem;
    }

    public void setQuantidadeEmbalagem(Long quantidadeEmbalagem) {
        this.quantidadeEmbalagem = quantidadeEmbalagem;
    }

    public Status getStatusProduto() {
        return statusProduto;
    }

    public void setStatusProduto(Status statusProduto) {
        this.statusProduto = statusProduto;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = quantidadeTotalEntrada*valorUnitario;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public Long getQuantidadeTotalEntrada() {
        return quantidadeTotalEntrada;
    }

    public void setQuantidadeTotalEntrada(Long quantidadeTotalEntrada) {
        this.quantidadeTotalEntrada = quantidadeTotalEntrada;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrada_Produto other = (Entrada_Produto) obj;
        if (this.idEntradas != other.idEntradas && (this.idEntradas == null || !this.idEntradas.equals(other.idEntradas))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.idEntradas != null ? this.idEntradas.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Numero Lote:" + loteEntrada + ", Nome Produto:" + produtoEntrada + ", Quantidade Disponivel=" + (quantidadeTotalEntrada - quantidadeTotalSaida) + '}';
    }

    

}
