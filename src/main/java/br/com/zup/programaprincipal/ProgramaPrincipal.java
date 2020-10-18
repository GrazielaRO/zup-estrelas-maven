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
		System.out.println("\n[1] - Dar entrada em um ve�culo");
		System.out.println("[2] - Dar sa�da em um ve�culo");
		System.out.println("[3] - Buscar um ve�culo");
		System.out.println("[4] - Alterar cor de um ve�culo");
		System.out.println("[5] - Listar ve�culos por marca");
		System.out.println("[6] - Listar ve�culos estacionados");
		System.out.println("[0] - Encerrar o programa");
	}

	public static void registrarEntradaVeiculo(Scanner sc, CarroDao carroDao) {

		System.out.print("\nInforme a placa do ve�culo: ");
		String placaVeiculo = sc.next();

		System.out.print("\nInforme a cor do ve�culo: ");
		String corVeiculo = sc.next();

		System.out.print("\nInforme a marca do ve�culo: ");
		String marcaVeiculo = sc.next();

		System.out.print("\nInforme o modelo do ve�culo: ");
		String modeloVe�culo = sc.next();

		Carro carropojo = new Carro(placaVeiculo, corVeiculo, marcaVeiculo, modeloVe�culo);

		carroDao.registraEntradaVeiculo(carropojo);

	}

	public static void registraSaidaVeiculo(Scanner sc, CarroDao carroDao) {

		System.out.print("\nInforme a placa do ve�culo: ");
		String placaVeiculo = sc.next();

		carroDao.registraSaidaVe�culo(placaVeiculo);
	}

	public static void consultarVeiculo(Scanner sc, CarroDao carroDao) {

		System.out.print("\nInforme a placa do ve�culo: ");
		String placaVeiculo = sc.next();

		carroDao.buscarVeiculo(placaVeiculo);

	}
	
	public static void alterarCorDoVeiculo(Scanner sc, CarroDao carroDao) {
		
		System.out.print("\nInforme a placa do ve�culo que ter� a cor alterada: ");
		String placaVeiculo = sc.next();
		
		System.out.print("\nQual ser� a nova cor do ve�culo? ");
		String novaCor = sc.next();
		
		carroDao.alteraCorDoVeiculo(placaVeiculo, novaCor);
	}
	
	public static void listarVeiculosPorMarca(Scanner sc, CarroDao carroDao) {
		
		System.out.print("\nInforme a marca de ve�culo que deseja consultar: ");
		String marcaVeiculo = sc.next();
		
		List<Carro> listaVeiculosPorMarca = carroDao.listaCarroPorMarca(marcaVeiculo);
		
		System.out.printf("\nRela��o de carros da marca %s: \n\n", marcaVeiculo);
		
		for (Carro carros : listaVeiculosPorMarca) {
			System.out.println(carros);
		}
	}

	public static void listarVeiculosEstacionados(Scanner sc, CarroDao carroDao) {

		List<Carro> listaVeiculosEstacionados = carroDao.listarVeiculosEstacionados();

		System.out.println("\nRela��o dos ve�culos estacionados: \n");

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
			System.out.print("\nEscolha uma das op��es acima: ");
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

				System.out.println("\nOp��o inv�lida. Escolha uma op��o de 0 a 6.\n");

				break;
			}

		} while (!opcao.equals("0"));

		sc.close();
		conn.close();

	}

}
