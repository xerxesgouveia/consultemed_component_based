/**
 * 
 */
package br.com.consultemed.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */

@NamedQueries({ @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")})
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_PACIENTES")
public class Paciente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "NOME")
	private String nome;
	
	@Getter
	@Setter
	@Column(name = "CPF")
	private String cpf;
	
	@Getter
	@Setter
	@Column(name = "EMAIL")
	private String email;
	
	@Getter
	@Setter
	@Column(name = "TELEFONE")
	private String telefone;
	
}
