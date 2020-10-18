	package br.com.zup.CarroDAO;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.zup.CarroPOJO.Carro;
//import br.com.zup.conectionfactory.ConnectionFactory;

public class CarroDao {

//	private Connection conn;
	EntityManager manager;

	public CarroDao() {

//		this.conn = new ConnectionFactory().getConnection();
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("carros");
		this.manager = managerFactory.createEntityManager();

	}

//	public void registraEntradaVeiculo(Carro carro) {
//
//		String sql = "insert into carro" + "(placa, cor, marca, modelo)" + "values (?, ?, ?, ?)";
//
//		try {
//			PreparedStatement stmt = conn.prepareStatement(sql);
//
//			stmt.setString(1, carro.getPlaca());
//			stmt.setString(2, carro.getCor());
//			stmt.setString(3, carro.getMarca());
//			stmt.setString(4, carro.getModelo());
//
//			stmt.execute();
//			stmt.close();
//
//			System.out.println("\nRegistro de entrada realizado com sucesso!\n");
//
//		} catch (SQLException e) {
//
//			System.out.println("\nErro ao registrar o carro. Verifique as informações inseridas.\n");
//			System.out.println(e.getMessage());
//
//		}
//	}
	
	public void registraEntradaVeiculo(Carro carro) {
		
		manager.getTransaction().begin();
		manager.persist(carro);
		manager.getTransaction().commit();
		
		System.out.println("\nRegistro de entrada realizado com sucesso!\n");
	}

//	public void registraSaidaVeículo(String placaVeiculo) {
//
//		String sql = "delete from carro where placa = ?";
//
//		try {
//			PreparedStatement stmt = conn.prepareStatement(sql);
//
//			stmt.setString(1, placaVeiculo);
//
//			stmt.execute();
//			stmt.close();
//
//			System.out.println("\nSaída Registrada com sucesso!\n");
//
//		} catch (SQLException e) {
//
//			System.out.println("\nErro ao registrar a saída. Verifique os dados informados!\n");
//			System.out.println(e.getMessage());
//		}
//	}
	
	public void registraSaidaVeículo(String placaVeiculo) {
		
		Carro carroSaida = manager.find(Carro.class, placaVeiculo);
		
		manager.getTransaction().begin();
		manager.remove(carroSaida);
		manager.getTransaction().commit();
		
		System.out.println("\nSaída Registrada com sucesso!\n");
	}

//	public Carro buscarVeiculo(String placaVeiculo) {
//
//		Carro carro = new Carro();
//
//		String sql = "select * from carro where placa = ?";
//
//		try {
//			PreparedStatement stmt = conn.prepareStatement(sql);
//
//			stmt.setString(1, placaVeiculo);
//
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//
//				carro.setPlaca(rs.getString("placa"));
//				carro.setCor(rs.getString("cor"));
//				carro.setMarca(rs.getString("marca"));
//				carro.setModelo(rs.getString("modelo"));
//			}
//			
//			System.out.println("\nO veículo está no estacionamento.");
//
//		} catch (SQLException e) {
//
//			System.out.println("\nO veículo não está no estacionamento ou a placa foi informada incorretamente.\n"
//					+ "Verifique os se a informação inserida está correta.\n");
//			System.out.println(e.getMessage());
//		}
//
//		return carro;
//	}
	
	public Carro buscarVeiculo(String placaVeiculo) {
		
		Carro carroBuscado = manager.find(Carro.class, placaVeiculo);
		
		System.out.println("\nO veículo está no estacionamento!");
		
		return carroBuscado;
	}
	
//	public List<Carro> listarVeiculosEstacionados(){
//		
//		List<Carro> carrosEstacionados = new ArrayList<Carro>();
//		
//		String sql = "select * from carro";
//		
//		try {
//			
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				Carro carro = new Carro();
//				
//				carro.setPlaca(rs.getString("placa"));
//				carro.setCor(rs.getString("cor"));
//				carro.setMarca(rs.getString("marca"));
//				carro.setModelo(rs.getString("modelo"));
//				
//				carrosEstacionados.add(carro);
//				
//			}
//			
//		} catch (SQLException e) {
//			
//			System.out.println("\nOcorreu um erro na busca!\n");
//			System.out.println(e.getMessage());
//		}
//		
//		return carrosEstacionados;
//	}
	
	public List<Carro> listarVeiculosEstacionados(){
		
		Query query = manager.createQuery("select c from Carro as c");
		
		List<Carro> carros = query.getResultList();
		
		return carros;
	}
	
	public List<Carro> listaCarroPorMarca(String marca){
		
		Query query = manager.createQuery("select c from Carro as c "
				+ "where c.marca = :marca");
		
		query.setParameter("marca", marca);
		
		List<Carro> carroMesmaMarca = query.getResultList();
		
		return carroMesmaMarca;	
	}
	
	public void alteraCorDoVeiculo(String placaVeiculo, String novaCor) {
		
		Carro carroCorAlterada = manager.find(Carro.class, placaVeiculo);
		
		String corAntiga = carroCorAlterada.getCor(); 
		
		carroCorAlterada.setCor(novaCor);
		
		manager.getTransaction().begin();
		manager.merge(carroCorAlterada);
		manager.getTransaction().commit();
		
		System.out.println("\nA cor do veículo de placa " + carroCorAlterada.getPlaca() + " foi alterada da cor " + corAntiga + " para a cor " + novaCor + ".");
	}
}
