
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;

import java.util.Date;


/**
 *
 * @author yarllysson
 */

public class Relatorio {

    private String medicamento;
    private String numeroLote;
    private Long quantidade;
    private Date dataValidade;
    private String nomeProduto;
    private String nomeFornecedor;
    private Date dataEntrada;
    private Long valorUnitario;
    private Long valorTotal;
    
    private Date dataRetorno;
    private String nomePaciente;
    private String enderecoPaciente;
    private Long numeroPaciente;
    private String bairroPaciente;
    private String rgPaciente;
    private String orgemissorPaciente;
    private String telefonePaciente;
    private String nomemaePaciente;
    private String nomepaiPaciente;
    private Date datanascimentoPaciente;
    private Date dataadmissaoPaciente;
    
    private String nomeresponsavel;
    private String enderecoresponsavel;
    private Long numeroresponsavel;
    private String bairroresponsavel;
    private String rgresponsavel;
    private String orgemissorresponsavel;
    private String telefoneresponsavel;
    private Date datanascimentoresponsavel;
    
    
    
    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getEnderecoPaciente() {
        return enderecoPaciente;
    }

    public void setEnderecoPaciente(String enderecoPaciente) {
        this.enderecoPaciente = enderecoPaciente;
    }

    public Long getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(Long numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public String getBairroPaciente() {
        return bairroPaciente;
    }

    public void setBairroPaciente(String bairroPaciente) {
        this.bairroPaciente = bairroPaciente;
    }

    public String getRgPaciente() {
        return rgPaciente;
    }

    public void setRgPaciente(String rgPaciente) {
        this.rgPaciente = rgPaciente;
    }

    public String getOrgemissorPaciente() {
        return orgemissorPaciente;
    }

    public void setOrgemissorPaciente(String orgemissorPaciente) {
        this.orgemissorPaciente = orgemissorPaciente;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }

    public String getNomemaePaciente() {
        return nomemaePaciente;
    }

    public void setNomemaePaciente(String nomemaePaciente) {
        this.nomemaePaciente = nomemaePaciente;
    }

    public String getNomepaiPaciente() {
        return nomepaiPaciente;
    }

    public void setNomepaiPaciente(String nomepaiPaciente) {
        this.nomepaiPaciente = nomepaiPaciente;
    }

    public Date getDatanascimentoPaciente() {
        return datanascimentoPaciente;
    }

    public void setDatanascimentoPaciente(Date datanascimentoPaciente) {
        this.datanascimentoPaciente = datanascimentoPaciente;
    }

    public Date getDataadmissaoPaciente() {
        return dataadmissaoPaciente;
    }

    public void setDataadmissaoPaciente(Date dataadmissaoPaciente) {
        this.dataadmissaoPaciente = dataadmissaoPaciente;
    }

    public String getNomeresponsavel() {
        return nomeresponsavel;
    }

    public void setNomeresponsavel(String nomeresponsavel) {
        this.nomeresponsavel = nomeresponsavel;
    }

    public String getEnderecoresponsavel() {
        return enderecoresponsavel;
    }

    public void setEnderecoresponsavel(String enderecoresponsavel) {
        this.enderecoresponsavel = enderecoresponsavel;
    }

    public Long getNumeroresponsavel() {
        return numeroresponsavel;
    }

    public void setNumeroresponsavel(Long numeroresponsavel) {
        this.numeroresponsavel = numeroresponsavel;
    }

    public String getBairroresponsavel() {
        return bairroresponsavel;
    }

    public void setBairroresponsavel(String bairroresponsavel) {
        this.bairroresponsavel = bairroresponsavel;
    }

    public String getRgresponsavel() {
        return rgresponsavel;
    }

    public void setRgresponsavel(String rgresponsavel) {
        this.rgresponsavel = rgresponsavel;
    }

    public String getOrgemissorresponsavel() {
        return orgemissorresponsavel;
    }

    public void setOrgemissorresponsavel(String orgemissorresponsavel) {
        this.orgemissorresponsavel = orgemissorresponsavel;
    }

    public String getTelefoneresponsavel() {
        return telefoneresponsavel;
    }

    public void setTelefoneresponsavel(String telefoneresponsavel) {
        this.telefoneresponsavel = telefoneresponsavel;
    }

    public Date getDatanascimentoresponsavel() {
        return datanascimentoresponsavel;
    }

    public void setDatanascimentoresponsavel(Date datanascimentoresponsavel) {
        this.datanascimentoresponsavel = datanascimentoresponsavel;
    }
    
    
    
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    /*
     * public String gerarRelatorio() throws DAOException,
     * DAOValidationException, DAOConstraintException { List<Relatorio>
     * relatorio = Repository.query("teste"); ArrayList<Relatorio> r =
     * (ArrayList<Relatorio>) relatorio; GeradorRelatorio gerador = new
     * GeradorRelatorio(); gerador.gerarRelatorioMensal(r); return null;
     *
     *
     *
     * }
     */

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Long valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}