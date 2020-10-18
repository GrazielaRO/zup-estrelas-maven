package br.com.zup.programaprincipal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import br.com.zup.CarroDAO.CarroDao;
import br.com.zup.CarroPOJO.Carro;
import br.com.zup.conectionfactory.ConnectionFactory;

public class ProgramaPrincipal {

	public static void cabecalho() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("                  Estacionamento Estrelas                     ");
		System.out.println("--------------------------------------------------------------");
	}

	public static void menuPrincipal() {
		System.out.println("\n[1] - Dar entrada em um veículo");
		System.out.println("[2] - Dar saída em um veículo");
		System.out.println("[3] - Buscar um veículo");
		System.out.println("[4] - Alterar cor de um veículo");
		System.out.println("[5] - Listar veículos por marca");
		System.out.println("[6] - Listar veículos estacionados");
		System.out.println("[0] - Encerrar o programa");
	}

	public static void registrarEntradaVeiculo(Scanner sc, CarroDao carroDao) {

		System.out.print("\nInforme a placa do veículo: ");
		String placaVeiculo = sc.next();

		System.out.print("\nInforme a cor do veículo: ");
		String corVeiculo = sc.next();

		System.out.print("\nInforme a marca do veículo: ");
		String marcaVeiculo = sc.next();

		System.out.print("\nInforme o modelo do veículo: ");
		String modeloVeículo = sc.next();

		Carro carropojo = new Carro(placaVeiculo, corVeiculo, marcaVeiculo, modeloVeículo);

		carroDao.registraEntradaVeiculo(carropojo);

	}

	public static void registraSaidaVeiculo(Scanner sc, CarroDao carroDao) {

		System.out.print("\nInforme a placa do veículo: ");
		String placaVeiculo = sc.next();

		carroDao.registraSaidaVeículo(placaVeiculo);
	}

	public static void consultarVeiculo(Scanner sc, CarroDao carroDao) {

		System.out.print("\nInforme a placa do veículo: ");
		String placaVeiculo = sc.next();

		carroDao.buscarVeiculo(placaVeiculo);

	}
	
	public static void alterarCorDoVeiculo(Scanner sc, CarroDao carroDao) {
		
		System.out.print("\nInforme a placa do veículo que terá a cor alterada: ");
		String placaVeiculo = sc.next();
		
		System.out.print("\nQual será a nova cor do veículo? ");
		String novaCor = sc.next();
		
		carroDao.alteraCorDoVeiculo(placaVeiculo, novaCor);
	}
	
	public static void listarVeiculosPorMarca(Scanner sc, CarroDao carroDao) {
		
		System.out.print("\nInforme a marca de veículo que deseja consultar: ");
		String marcaVeiculo = sc.next();
		
		List<Carro> listaVeiculosPorMarca = carroDao.listaCarroPorMarca(marcaVeiculo);
		
		System.out.printf("\nRelação de carros da marca %s: \n\n", marcaVeiculo);
		
		for (Carro carros : listaVeiculosPorMarca) {
			System.out.println(carros);
		}
	}

	public static void listarVeiculosEstacionados(Scanner sc, CarroDao carroDao) {

		List<Carro> listaVeiculosEstacionados = carroDao.listarVeiculosEstacionados();

		System.out.println("\nRelação dos veículos estacionados: \n");

		for (Carro carros : listaVeiculosEstacionados) {
			System.out.println(carros);
		}
	}

	public static void main(String[] args) throws SQLException {

		Connection conn = new ConnectionFactory().getConnection();
		CarroDao carroDao = new CarroDao();
		Scanner sc = new Scanner(System.in);
		String opcao = "";

		cabecalho();
		do {
			menuPrincipal();
			System.out.print("\nEscolha uma das opções acima: ");
			opcao = sc.next();

			switch (opcao) {

			case "1":

				registrarEntradaVeiculo(sc, carroDao);

				break;

			case "2":

				registraSaidaVeiculo(sc, carroDao);

				break;

			case "3":

				consultarVeiculo(sc, carroDao);

				break;
				
			case "4":

				alterarCorDoVeiculo(sc, carroDao);

				break;

			case "5":

				listarVeiculosPorMarca(sc, carroDao);

				break;

			case "6":

				listarVeiculosEstacionados(sc, carroDao);

				break;

			case "0":

				System.out.println("\nObrigada por utilizar o nosso sistema! =D");

				break;

			default:

				System.out.println("\nOpção inválida. Escolha uma opção de 0 a 6.\n");

				break;
			}

		} while (!opcao.equals("0"));

		sc.close();
		conn.close();

	}

}
