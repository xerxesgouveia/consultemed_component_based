package br.com.usuario;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
@Data
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	@Length(min=3, max=20, message="O nome não pode ser vazio")
	private String nome;
	@Column
	private String telefone;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	@NotNull
	@Valid
	private Usuario usuario;
		
}
