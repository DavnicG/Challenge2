package principal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Datos;
import service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
//    private ConsumoAPI consumoApi = new ConsumoAPI();

    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/46a60648de864235dfb802c3/latest/";
    private String mon;
    private String conv;
    private JsonObject jsonObject = new JsonObject();
    private float valor;


//Inicia el programa
    public void inimenu (){

        var opcion = -1;
        while (opcion != 7) {
//Inicia el menu
            var menu = """            
            ***************************************************
            Sea bienvenido/a al Conversor de Moneda
            1) Dolar ---> Peso Argentino
            2) Peso Argentino ---> Dolar
            3) Dolar ---> Real Brasileño
            4) Real Brasileño ---> Dolar;
            5) Dolar ---> Peso Colombiano
            6) Peso Colombiano ---> Dolar
            7) Salir
            Elija una opcion valida
            ***************************************************

            """;
            System.out.println(menu);

            opcion = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Ingrese el valor que desea converir: ");
            valor = teclado.nextFloat();
            teclado.nextLine();
//Casos de conversion
                switch (opcion){
                    case 1:
                        mon = "USD";
                        conv = "ARS";
                        System.out.println("El valor "+valor+" ["+mon+"]"+" corresponde al valor " +
                                "final de ---> "+ getDatosMon()+" ["+conv+"]");
                        break;
                    case 2:
                        mon = "ARS";
                        conv = "USD";
                        System.out.println("El valor "+valor+" ["+mon+"]"+" corresponde al valor " +
                                "final de ---> "+ getDatosMon()+" ["+conv+"]");
                        break;
                    case 3:
                        mon = "USD";
                        conv = "BRL";
                        System.out.println("El valor "+valor+" ["+mon+"]"+" corresponde al valor " +
                                "final de ---> "+ getDatosMon()+" ["+conv+"]");
                        break;
                    case 4:
                        mon = "BRL";
                        conv = "USD";
                        System.out.println("El valor "+valor+" ["+mon+"]"+" corresponde al valor " +
                                "final de ---> "+ getDatosMon()+" ["+conv+"]");
                        break;
                    case 5:
                        mon = "USD";
                        conv = "COP";
                        System.out.println("El valor "+valor+" ["+mon+"]"+" corresponde al valor " +
                                "final de ---> "+ getDatosMon()+" ["+conv+"]");
                        break;
                    case 6:
                        mon = "COP";
                        conv = "USD";
                        System.out.println("El valor "+valor+" ["+mon+"]"+" corresponde al valor " +
                                "final de ---> "+ getDatosMon()+" ["+conv+"]");
                        break;
                    case 7:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
        }
    }

    public Float getDatosMon() {


        var json = ConsumoAPI.obtenerDatos(URL_BASE+mon);
        jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject conversion = jsonObject.getAsJsonObject("conversion_rates");
        var vmon = conversion.get(mon).getAsString();
        var vconv = conversion.get(conv).getAsString();



        return conversor(valor,vmon,vconv);
    }

    public Float conversor (Float valor, String vmon,String vconv){



        float mon = Float.parseFloat(vmon);
        System.out.println(vmon);
        float conv = Float.parseFloat(vconv);
        System.out.println(vconv);

        float ValorConvertido = (valor*mon)*conv;
        return ValorConvertido;

    }


}
