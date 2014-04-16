/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.ActionDescriptor;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.ManyToOne;
import methods.DesativarPacientes;

/**
 *
 * @author NPI-UFC - Dalmo e Luis Lima
 */
@Entity
@Views({
    @View(name = "Cartao",
    title = "Cartão de Cadastro",
    filters = "[nome, rg, ativo, Ctrl.DAO.filter()]",
    members = "["
    + "Indentificador do Paciente[nome; dataDeNascimento; endereco; numero; bairro;"
    + "nomeDoPai; nomeDaMae; telefone; rg; orgEmissor; dataDeAdmissao, tipo; ativo;];"
    + " Responsavel Pelo Paciente[nomeDoResponsavel; "
    + "dataDeNascimentoDoResponsavel; "
    + "enderecoDoResponsavel; "
    + "numeroDoResponsavel; "
    + "bairroDoResponsavel; "
    + "telefoneDoResponsavel;"
    + " rgDoResponsavel; "
    + "orgEmissorDoResponsavel;];"
    + "adicionarMedicamento(); "
    + "'Produtos do Paciente':addMedicamentos<addProduto, dose,removerProduto()>;"
    + "adicionarRetorno(); "
    + "'Retorno do Paciente':addRetornos<dataRetono,removerRetorno()>;"
    + "]"
    ,
	roles = "Administrador,Famaceutico,Atendente",
    template = "@CRUD+@PAGER",
    rows = 5),
    
    @View(name = "Ativacao",
    title = "Cartão de Cadastro",
    filters = "[nome, rg, ativo, Ctrl.DAO.filter()]",
    members = "["
    + "desativarPacientes();"
    + "Indentificador do Paciente[nome; dataDeNascimento; endereco; numero; bairro;"
    + "nomeDoPai; nomeDaMae; telefone; rg; orgEmissor; dataDeAdmissao, tipo; ativo;];"
    + " Responsavel Pelo Paciente[nomeDoResponsavel; "
    + "dataDeNascimentoDoResponsavel; "
    + "enderecoDoResponsavel; "
    + "numeroDoResponsavel; "
    + "bairroDoResponsavel; "
    + "telefoneDoResponsavel;"
    + " rgDoResponsavel; "
    + "orgEmissorDoResponsavel;];"
    + "adicionarMedicamento(); "
    + "'Produtos do Paciente':addMedicamentos<addProduto, dose,removerProduto()>;"
    + "adicionarRetorno(); "
    + "'Retorno do Paciente':addRetornos<dataRetono,removerRetorno()>;"
    + "]"
    ,
	roles = "Administrador",
    template = "@CRUD+@PAGER",
    rows = 5)
})
public class Cartao_de_Cadastro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull(message = "Informe o Nome")
    @PropertyDescriptor(displayName = "Nome")
    @Column(length = 45)
    private String nome;
    
    @NotNull(message = "Informe a Data de Nacimento")
    @Temporal(javax.persistence.TemporalType.DATE)
    @PropertyDescriptor(displayName = "Data de Nascimento")
    private Date dataDeNascimento;
    
    @NotNull(message = "Informe o Endereço")
    @Column(length = 45)
    @PropertyDescriptor(displayName = "Endereço")
    private String endereco;
    
    @NotNull(message = "Informe o Numero do Endereço")
    @Column(length = 15)
    @PropertyDescriptor(displayName = "Numero do Endereço")
    private long numero;
    
    @NotNull(message = "Informe o Bairro")
    @Column(length = 45)
    @PropertyDescriptor(displayName = "Bairro")
    private String bairro;
    
    @NotNull(message = "Informe o Nome do Pai")
    @Column(length = 45)
    @PropertyDescriptor(displayName = "Nome do Pai")
    private String nomeDoPai;
    
    @NotNull(message = "Informe o Nome da Mãe")
    @Column(length = 45)
    @PropertyDescriptor(displayName = "Nome da Mãe")
    private String nomeDaMae;
    
    @NotNull(message = "Informe o Telefone")
    @Column(length = 15)
    @PropertyDescriptor(mask = "(99)9999-9999", displayWidth = 15)
    private String telefone;
    
    @NotNull(message = "Informe o RG")
    @Column(length = 15)
    @PropertyDescriptor(displayName = "RG")
    private String rg;
    
    @NotNull(message = "Informe o Org. Emissor")
    @Column(length = 15)
    @PropertyDescriptor(displayName = "Org. Emissor")
    private String orgEmissor;
    
    @NotNull(message = "Informe o Data da Admissão")
    @Temporal(javax.persistence.TemporalType.DATE)
    @PropertyDescriptor(displayName = "Data de Admissão")
    private Date dataDeAdmissao;
    
    //Responsavel Pelo Paciente
    @NotNull(message = "Informe o Nome do Responsavel")
    @Column(length = 45)
    @PropertyDescriptor(displayName = "Nome")
    private String nomeDoResponsavel;
    
    @NotNull(message = "Informe a Data de Nacimento do Responsavel")
    @Temporal(javax.persistence.TemporalType.DATE)
    @PropertyDescriptor(displayName = "Data de Nascimento")
    private Date dataDeNascimentoDoResponsavel;
    
    @NotNull(message = "Informe o Endereço do Responsavel")
    @Column(length = 45)
    @PropertyDescriptor(displayName = "Endereço do Responsavel")
    private String enderecoDoResponsavel;
    
    @NotNull(message = "Informe o Numero do Endereço do Responsavel")
    @Column(length = 15)
    @PropertyDescriptor(displayName = "Numero do Endereço")
    private long numeroDoResponsavel;
    
    @NotNull(message = "Informe o Bairro do Responsavel")
    @Column(length = 45)
    @PropertyDescriptor(displayName = "Bairro")
    private String bairroDoResponsavel;
    
    @NotNull(message = "Informe o Telefone do Responsavel")
    @Column(length = 15)
    @PropertyDescriptor(mask = "(99)9999-9999", displayWidth = 15)
    private String telefoneDoResponsavel;
    
    @NotNull(message = "Informe o RG do Responsavel")
    @Column(length = 15)
    @PropertyDescriptor(displayName = "RG")
    private String rgDoResponsavel;
    
    @NotNull(message = "Informe o Org. Emissor do RG do Responsavel")
    @Column(length = 15)
    @PropertyDescriptor(displayName = "Org. Emissor")
    private String orgEmissorDoResponsavel;
    
    @ManyToOne
    @NotNull
    @PropertyDescriptor(displayName="Tipo da Saida")
    private TipoSaida tipo;
    
    @PropertyDescriptor(displayName = "Ativo")
    private boolean ativo;
    
    @ActionDescriptor(displayName="Desativar Pacientes",value="Desativar Pacientes")
    public String desativarPacientes(){
        DesativarPacientes dp = new DesativarPacientes();
        return dp.desativarPacientes();
        //return "redirect:main.jsf?view=bean.Produtor@ManterProdutor";
        //return "Desativar Pacientes";
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
    public TipoSaida getTipo() {
        return tipo;
    }

    public void setTipo(TipoSaida tipo) {
        this.tipo = tipo;
    }
    
    @OneToMany(cascade= CascadeType.ALL, mappedBy="cartaoAddProduto")
    @PropertyDescriptor(displayName="Adicionar Produto")
    private List<AddMedicamento> addMedicamentos = new ArrayList<AddMedicamento>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartaoAddRetorno")
    @PropertyDescriptor(displayName = "Adicionar Retorno")
    private List<AddRetorno> addRetornos = new ArrayList<AddRetorno>();

    @ActionDescriptor(methodDisabled = "#{not autoEntityBackBean.formInEditMode}")
    public void adicionarRetorno() {
        AddRetorno addR = new AddRetorno();
        addR.setCartaoAddRetorno(this);
        addRetornos.add(addR);
    }
    
    @ActionDescriptor(methodDisabled = "#{not autoEntityBackBean.formInEditMode}")
    public void adicionarMedicamento() {
        AddMedicamento addM = new AddMedicamento();
        addM.setCartaoAddProduto(this);
        addMedicamentos.add(addM);
    }

    public List<AddMedicamento> getAddMedicamentos() {
        return addMedicamentos;
    }

    public void setAddMedicamentos(List<AddMedicamento> addMedicamentos) {
        this.addMedicamentos = addMedicamentos;
    }

    public List<AddRetorno> getAddRetornos() {
        return addRetornos;
    }

    public void setAddRetornos(List<AddRetorno> addRetornos) {
        this.addRetornos = addRetornos;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairroDoResponsavel() {
        return bairroDoResponsavel;
    }

    public void setBairroDoResponsavel(String bairroDoResponsavel) {
        this.bairroDoResponsavel = bairroDoResponsavel;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Date dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeNascimentoDoResponsavel() {
        return dataDeNascimentoDoResponsavel;
    }

    public void setDataDeNascimentoDoResponsavel(Date dataDeNascimentoDoResponsavel) {
        this.dataDeNascimentoDoResponsavel = dataDeNascimentoDoResponsavel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnderecoDoResponsavel() {
        return enderecoDoResponsavel;
    }

    public void setEnderecoDoResponsavel(String enderecoDoResponsavel) {
        this.enderecoDoResponsavel = enderecoDoResponsavel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    public String getNomeDoPai() {
        return nomeDoPai;
    }

    public void setNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
    }

    public String getNomeDoResponsavel() {
        return nomeDoResponsavel;
    }

    public void setNomeDoResponsavel(String nomeDoResponsavel) {
        this.nomeDoResponsavel = nomeDoResponsavel;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public long getNumeroDoResponsavel() {
        return numeroDoResponsavel;
    }

    public void setNumeroDoResponsavel(long numeroDoResponsavel) {
        this.numeroDoResponsavel = numeroDoResponsavel;
    }

    public String getOrgEmissor() {
        return orgEmissor;
    }

    public void setOrgEmissor(String orgEmissor) {
        this.orgEmissor = orgEmissor;
    }

    public String getOrgEmissorDoResponsavel() {
        return orgEmissorDoResponsavel;
    }

    public void setOrgEmissorDoResponsavel(String orgEmissorDoResponsavel) {
        this.orgEmissorDoResponsavel = orgEmissorDoResponsavel;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgDoResponsavel() {
        return rgDoResponsavel;
    }

    public void setRgDoResponsavel(String rgDoResponsavel) {
        this.rgDoResponsavel = rgDoResponsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneDoResponsavel() {
        return telefoneDoResponsavel;
    }

    public void setTelefoneDoResponsavel(String telefoneDoResponsavel) {
        this.telefoneDoResponsavel = telefoneDoResponsavel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cartao_de_Cadastro other = (Cartao_de_Cadastro) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return   nome ;
    }
    
    
}
