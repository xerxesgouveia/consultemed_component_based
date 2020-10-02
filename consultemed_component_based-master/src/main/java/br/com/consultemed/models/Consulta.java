/**
 * 
 */
package br.com.consultemed.models;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */

@NamedQueries({ @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")})
@NoArgsConstructor
@Entity
@Table(name = "TB_CONSULTAS")
public class Consulta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Inject
	@ManyToOne()
	@JoinColumn(name="id_medico", referencedColumnName = "id", nullable=false)
	private Medico medico;
	
	@Getter
	@Setter
	@ManyToOne()
	@JoinColumn(name="id_paciente", referencedColumnName = "id", nullable=false)
	private Paciente paciente;
	
	@Getter
	@Setter
	@ManyToOne()
	@JoinColumn(name="id_funcionario", referencedColumnName = "id", nullable=false)
	private Funcionario funcionario;
	
	@Getter
	@Setter
	@OneToOne()
	@JoinColumn(name="id_agendamento", referencedColumnName = "id", nullable=false)
	private Agendamento agendamento;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
