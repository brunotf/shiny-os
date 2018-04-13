package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Materias;

public class MateriasDao implements IMateriasDao {

	private Connection c;
	
	public MateriasDao() throws ClassNotFoundException, SQLException{
		IGenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	@Override
	public void insereMaterias(Materias mat) throws SQLException {
		String sql = "INSERT INTO materias (nome, carga_horaria) VALUES (?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
//		ps.setInt(1, mat.getId());
		ps.setString(1, mat.getNome());
		ps.setInt(2, mat.getCarga());
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizaMaterias(Materias mat) throws SQLException {
		String sql = "UPDATE materias SET nome = ?, carga_horaria = ? WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, mat.getNome());
		ps.setInt(2, mat.getCarga());
		ps.setInt(3, mat.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiMaterias(Materias mat) throws SQLException {
		String sql = "DELETE FROMmaterias WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, mat.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public Materias consultaMateria(Materias mat) throws SQLException {
		String sql = "SELECT id, nome, carga_horaria FROM materias WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, mat.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			mat.setId(rs.getInt("id"));
			mat.setNome(rs.getString("nome"));
			mat.setCarga(rs.getInt("carga_horaria"));
		}
		rs.close();
		ps.close();
		return mat;
	}

	@Override
	public List<Materias> consultaMaterias() throws SQLException {
		List<Materias> listaMaterias = new ArrayList<Materias>();
		String sql = "SELECT id, nome, carga_horaria FROM materias";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Materias mat = new Materias();
			mat.setId(rs.getInt("id"));
			mat.setNome(rs.getString("nome"));
			mat.setCarga(rs.getInt("carga_horaria"));
			listaMaterias.add(mat);
		}
		rs.close();
		ps.close();
		return listaMaterias;
	}

	@Override
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(id) + 1 AS proximo_id FROM materias";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}

}
