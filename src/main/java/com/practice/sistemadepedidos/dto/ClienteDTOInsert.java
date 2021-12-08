package com.practice.sistemadepedidos.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.practice.sistemadepedidos.entities.Cidade;
import com.practice.sistemadepedidos.entities.Cliente;
import com.practice.sistemadepedidos.entities.Endereco;
import com.practice.sistemadepedidos.entities.enums.TipoCliente;
import com.practice.sistemadepedidos.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteDTOInsert implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private static BCryptPasswordEncoder pe;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Email(message = "Email inválido")
	private String email;
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String cpfOuCnpj;
	private Integer tipo;
	@NotEmpty(message = "Preenchimento obrigatorio")	
	private String senha;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String logradouro;
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String numero;
	private String complemento;
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String bairro;
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String cep;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Long CidadeId;
	
	public ClienteDTOInsert() {
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	public Integer getTipo() {
		return tipo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getTelefone3() {
		return telefone3;
	}
	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	public Long getCidadeId() {
		return CidadeId;
	}
	public void setCidadeId(Long cidadeId) {
		CidadeId = cidadeId;
	}
	
	public static Cliente toEntity(ClienteDTOUpdate objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	public static Cliente toEntity(ClienteDTOInsert objDTO) {
		Cliente obj = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		Cidade cidade = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), obj, cidade);
		obj.getEnderecos().add(endereco);
		obj.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2()!=null) {
			obj.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3()!=null) {
			obj.getTelefones().add(objDTO.getTelefone3());
		}
		return obj;
	}
}
