/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.EntityDescriptor;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author NPI-UFC - Luis Lima
 */


@Entity
@EntityDescriptor(
displayName="Fornecedor",
pluralDisplayName="Fornecedores")
@Table(name="fornecedor")

@Views({
@View(name = "Fornecedor",
        title = "Fornecedor",
        filters = "[nomeFantasiaFornecedor,Ctrl.DAO.filter()]",
        members= "[Fornecedor [nomeFantasiaFornecedor; "
        + "contatoResponsavel; "
        + "nomeRazaoSocial];"
        + "Endereço Fornecedor [enderecoFornecedor;"
        + "cidadeFornecedor;"
        + "bairroFornecedor;"
        + "cepFornecedor];"
        + "Dados Fornecedor [telefone1Fornecedor;"
        + "telefone2Fornecedor;"
        + "dataCadastroFornecedor;"
        + "observacaoFornecedor;"
        + "juridicaFisica;"
        + "habilitado;"
        + "cnpj;"
        + "cpf;]]",
        roles = "Administrador,Famaceutico,Atendente",
        template = "@CRUD+@PAGER",
        rows = 5
        )
  
})
public class Fornecedor implements Serializable{
    
    public enum JuridicaFisica{
        JURIDICA,
        FISICA
    }
    
    public enum Habilitado{
        SIM,
        NAO
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @PropertyDescriptor(index = 1, hidden = true)
    private Long idFornecedor;
    
    @NotEmpty(message="Informe o Nome Fantasia Fornecedor")
    @PropertyDescriptor(index= 2, displayName="Nome Fantasia fornecedor")
    private String nomeFantasiaFornecedor;
    
    @NotEmpty(message="Informe o Contato/Responsavel")
    @PropertyDescriptor(index= 3,displayName="Contato/Responsavel")
    private String contatoResponsavel;
    
    @NotEmpty(message="Informe o Razao Social")
    @PropertyDescriptor(index= 4,displayName="Razao Social")
    private String nomeRazaoSocial;
    
    @NotEmpty(message="Informe Endereco")
    @PropertyDescriptor(index= 5,displayName="Endereco")
    private String enderecoFornecedor;
    
    @NotEmpty(message="Informe Cidade")
    @PropertyDescriptor(index= 6,displayName="Cidade")
    private String cidadeFornecedor;
    
    @NotEmpty(message="Informe Bairro")
    @PropertyDescriptor(index= 7,displayName="Bairro")
    private String bairroFornecedor;
    
    @NotEmpty(message = "Informe o CEP")
    @Column(length = 15)
    @PropertyDescriptor(index= 8,displayName = "CEP", mask="99999-999")
    private String cepFornecedor;
    
    @Column(length = 15)
    @PropertyDescriptor(index= 9,displayName = "Telefone 1", mask="(99)9999-9999")
    private String telefone1Fornecedor;
    
    @Column(length = 15)
    @PropertyDescriptor(index= 10,displayName = "Telefone 2", mask="(99)9999-9999")
    private String telefone2Fornecedor;
    
    @NotNull(message = "Informe a Data do Cadastro")
    @PropertyDescriptor(index= 11, displayName = "Data Cadastro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastroFornecedor = new Date();
    
    @Lob
    @PropertyDescriptor(index= 17, displayName="Observacao")
    private String observacaoFornecedor;
    
    @PropertyDescriptor(index= 12, displayName="Pessoa Juridica/Fisica")
    private JuridicaFisica juridicaFisica;
    
    @PropertyDescriptor(index= 13, displayName="Habilidado")
    private Habilitado habilitado;
    
    @PropertyDescriptor(index= 14,displayName = "CNPJ", mask="99.999.999/9999-99")
    private String cnpj;
    
    @PropertyDescriptor(index= 15,displayName = "CGF")
    private String cgf;
    
    @Column(length = 15)
    @PropertyDescriptor(index= 16,displayName = "CPF", mask="999.999.999-99")
    private String cpf;

    public String getBairroFornecedor() {
        return bairroFornecedor;
    }

    public void setBairroFornecedor(String bairroFornecedor) {
        this.bairroFornecedor = bairroFornecedor;
    }

    public String getCepFornecedor() {
        return cepFornecedor;
    }

    public void setCepFornecedor(String cepFornecedor) {
        this.cepFornecedor = cepFornecedor;
    }

    public String getCgf() {
        return cgf;
    }

    public void setCgf(String cgf) {
        this.cgf = cgf;
    }

    public String getCidadeFornecedor() {
        return cidadeFornecedor;
    }

    public void setCidadeFornecedor(String cidadeFornecedor) {
        this.cidadeFornecedor = cidadeFornecedor;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(String contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataCadastroFornecedor() {
        return dataCadastroFornecedor;
    }

    public void setDataCadastroFornecedor(Date dataCadastroFornecedor) {
        this.dataCadastroFornecedor = dataCadastroFornecedor;
    }

    public String getEnderecoFornecedor() {
        return enderecoFornecedor;
    }

    public void setEnderecoFornecedor(String enderecoFornecedor) {
        this.enderecoFornecedor = enderecoFornecedor;
    }

    public Habilitado getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Habilitado habilitado) {
        this.habilitado = habilitado;
    }

    public Long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Long idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public JuridicaFisica getJuridicaFisica() {
        return juridicaFisica;
    }

    public void setJuridicaFisica(JuridicaFisica juridicaFisica) {
        this.juridicaFisica = juridicaFisica;
    }

    public String getNomeFantasiaFornecedor() {
        return nomeFantasiaFornecedor;
    }

    public void setNomeFantasiaFornecedor(String nomeFantasiaFornecedor) {
        this.nomeFantasiaFornecedor = nomeFantasiaFornecedor;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getObservacaoFornecedor() {
        return observacaoFornecedor;
    }

    public void setObservacaoFornecedor(String observacaoFornecedor) {
        this.observacaoFornecedor = observacaoFornecedor;
    }

    public String getTelefone1Fornecedor() {
        return telefone1Fornecedor;
    }

    public void setTelefone1Fornecedor(String telefone1Fornecedor) {
        this.telefone1Fornecedor = telefone1Fornecedor;
    }

    public String getTelefone2Fornecedor() {
        return telefone2Fornecedor;
    }

    public void setTelefone2Fornecedor(String telefone2Fornecedor) {
        this.telefone2Fornecedor = telefone2Fornecedor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (this.idFornecedor != other.idFornecedor && (this.idFornecedor == null || !this.idFornecedor.equals(other.idFornecedor))) {
            return false;
        }
        if ((this.nomeFantasiaFornecedor == null) ? (other.nomeFantasiaFornecedor != null) : !this.nomeFantasiaFornecedor.equals(other.nomeFantasiaFornecedor)) {
            return false;
        }
        if ((this.nomeRazaoSocial == null) ? (other.nomeRazaoSocial != null) : !this.nomeRazaoSocial.equals(other.nomeRazaoSocial)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.idFornecedor != null ? this.idFornecedor.hashCode() : 0);
        hash = 23 * hash + (this.nomeFantasiaFornecedor != null ? this.nomeFantasiaFornecedor.hashCode() : 0);
        hash = 23 * hash + (this.nomeRazaoSocial != null ? this.nomeRazaoSocial.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return  nomeFantasiaFornecedor;
    }
    
}
