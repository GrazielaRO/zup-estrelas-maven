package br.com.zup.programaprincipal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import CarroDAO.CarroDao;
import CarroPOJO.CarroPojo;
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
		System.out.println("[4] - Listar ve�culos estacionados");
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

		CarroPojo carropojo = new CarroPojo(placaVeiculo, corVeiculo, marcaVeiculo, modeloVe�culo);

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
	
	public static void listarVeiculosEstacionados(Scanner sc, CarroDao carroDao) {
		
		List<CarroPojo> listaVeiculosEstacionados = carroDao.listarVeiculosEstacionados();
		
		System.out.println("\nRela��o dos ve�culos estacionados: \n");
		
		for (CarroPojo carros : listaVeiculosEstacionados) {
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
				
				listarVeiculosEstacionados(sc, carroDao);

				break;

			case "0":

				System.out.println("\nObrigada por utilizar o nosso sistema! =D");

				break;

			default:

				System.out.println("\nOp��o inv�lida. Escolha uma op��o de 0 a 4.\n");

				break;
			}

		} while (!opcao.equals("0"));
		
		sc.close();
		conn.close();

	}

}
