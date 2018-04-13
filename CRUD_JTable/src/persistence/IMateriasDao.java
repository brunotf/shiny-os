package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Materias;

public interface IMateriasDao {

	public void insereMaterias(Materias mat) throws SQLException;
	public void atualizaMaterias(Materias mat) throws SQLException;
	public void excluiMaterias(Materias mat) throws SQLException;
	public Materias consultaMateria(Materias mat) throws SQLException;
	public List<Materias> consultaMaterias() throws SQLException;
	public int proximoId() throws SQLException;
	
}
