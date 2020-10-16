package CarroDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CarroPOJO.CarroPojo;
import br.com.zup.conectionfactory.ConnectionFactory;

public class CarroDao {

	// dar entrada / dar sa�da / buscar / listar todos

	private Connection conn;

	public CarroDao() {

		this.conn = new ConnectionFactory().getConnection();

	}

	public void registraEntradaVeiculo(CarroPojo carro) {

		String sql = "insert into carro" + "(placa, cor, marca, modelo)" + "values (?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, carro.getPlaca());
			stmt.setString(2, carro.getCor());
			stmt.setString(3, carro.getMarca());
			stmt.setString(4, carro.getModelo());

			stmt.execute();
			stmt.close();

			System.out.println("\nRegistro de entrada realizado com sucesso!\n");

		} catch (SQLException e) {

			System.out.println("\nErro ao registrar o carro. Verifique as informa��es inseridas.\n");
			System.out.println(e.getMessage());

		}
	}

	public void registraSaidaVe�culo(String placaVeiculo) {

		String sql = "delete from carro where placa = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placaVeiculo);

			stmt.execute();
			stmt.close();

			System.out.println("\nSa�da Registrada com sucesso!\n");

		} catch (SQLException e) {

			System.out.println("\nErro ao registrar a sa�da. Verifique os dados informados!\n");
			System.out.println(e.getMessage());
		}
	}

	public CarroPojo buscarVeiculo(String placaVeiculo) {

		CarroPojo carro = new CarroPojo();

		String sql = "select * from carro where placa = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placaVeiculo);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				carro.setPlaca(rs.getString("placa"));
				carro.setCor(rs.getString("cor"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));
			}
			
			System.out.println("\nO ve�culo est� no estacionamento.");

		} catch (SQLException e) {

			System.out.println("\nO ve�culo n�o est� no estacionamento ou a placa foi informada incorretamente.\n"
					+ "Verifique os se a informa��o inserida est� correta.\n");
			System.out.println(e.getMessage());
		}

		return carro;
	}
	
	public List<CarroPojo> listarVeiculosEstacionados(){
		
		List<CarroPojo> carrosEstacionados = new ArrayList<CarroPojo>();
		
		String sql = "select * from carro";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				CarroPojo carro = new CarroPojo();
				
				carro.setPlaca(rs.getString("placa"));
				carro.setCor(rs.getString("cor"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));
				
				carrosEstacionados.add(carro);
				
			}
			
		} catch (SQLException e) {
			
			System.out.println("\nOcorreu um erro na busca!\n");
			System.out.println(e.getMessage());
		}
		
		return carrosEstacionados;
	}
}
