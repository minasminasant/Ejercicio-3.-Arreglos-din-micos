import java.lang.management.PlatformManagedObject;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Tallerautomotriz taller = new Tallerautomotriz();

    public static void main(String[] args) {
        System.out.println(" Sistema de control de ordenes de serivio . UVG taller AUTOMOTRIZ");
        int opcion;

        do { 
            Menu();
            opcion = leerEntero("elige una opcion");

            switch (opcion) {
                case 1:
                    registrarorden();
                    break;
                case 2:
                    consultaOrdenServicio();
                    break;
                case 3:
                    BuscaOrdenServicio();
                    break;
                case 4:
                    modificarOrden();
                    break;
                case 5:
                    cancelarOrden();
                    break;
                case 6:
                    consultarplaca();
                    break;
                case 7:
                    reporteDeCostos();
                    break;
                case 8:
                    OrdenMayor();
                    break;
                case 9:
                    cantidadOrdenes();
                    break;
                case 10:
                    System.out.println("Gracias por usar este sistema");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }   
            System.out.println("");
        }
        while(opcion != 10);
        sc.close();
    }

    private static void Menu(){
        System.out.println("Ordenes activas:" + taller.getCantidadOrdenes());
        System.out.println("1. Registrar orden");
        System.out.println("2. consultar ordenes");
        System.out.println("3. buscar ordens");
        System.out.println("4. nodificar orden");
        System.out.println("5. cancelar orden");
        System.out.println("6. consultar ordenes por placa");
        System.out.println("7. reporte de costos");
        System.out.println("8. orden con mayor costo");
        System.out.println("9. cantidad de ordenes");
        System.out.println("10. salir");
    }

    private static void registrarorden(){
        System.out.println("");
        System.out.println("Registrar una nueva orden");
        int numeroDeOrden = leerEntero("numero de orden:");
        System.out.println("Nombre del propietario:");
        String NombredePropietario = sc.nextLine();
        System.out.println("Descripcion del servicio");
        String DescripcionDeServicio = sc.nextLine();
        double Costo = leerCosto("Costo estimado:");

        try {
            taller.registrarOrden(numeroDeOrden, NombredePropietario, placaVehiculo, DescripcionDeServicio, Costo);
            System.out.println("orden registrada con exito");
        } catch (DatosInvalidosException e) {
            System.out.println("No se a podido registrar la orden:" + e.getMessage());
        } finally{
            System.out.println("Proceso de registrar finaliza");
        }
    }

    private static void consultaOrdenServicio(){
        System.out.println(" orden registradas");
        List<OrdenServicio> ordenes = taller.consultaOrdenServicio();
        if(ordenes.isEmpty()){
            System.out.println("No Hay ordnes registradas");
            return;
        }
        for(int i = 0; i < ordenes.size(); i++){
            System.out.println(ordenes.get(i));
        }
    }

    private static void BuscaOrdenServicio(){
        int numeroDeOrden = leerEntero("Numero de ordenes buacra:");
        try {
            OrdenServicio orden = taller.BuscaOrdenServicio(numeroDeOrden);
            System.out.println("orden encontrada:");
            System.out.println(orden);
        } catch (OrdenNoEncontradaException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Busqueda finaliza");
        }
    }

    private static void modificarOrden(){
        int numeroDeOrden = leerEntero("Numero de orden por modificar:");
        System.out.println("Nuwva descripcion del servicio:");
        String nuevaDescripcion = sc.nextLine();

        double nuevoCosto = leerCosto("Nuevo costo:");
         try {
             taller.modificarOrden(numeroDeOrden, nuevaDescripcion, nuevoCosto);
             System.out.println("Orden modificada");
         } catch (OrdenNoEncontradaException e) {
            System.out.println("Nose se puede modificar" + e.getMessage());
         } catch (DatosInvalidosException){
            System.out.println("No se puede modificar:" + e.getMessage());
         } finally{
            System.out.println("proceso finalizado");
         } 
    }

    private static void cancelarOrden(){
        int numerodeOrden = leerEntero("nuemero de orden a cancelar:");
        try {
            taller.cancelarOrden(numerodeOrden);
            System.out.println("orden cancelda");
        } catch (OrdenNoEncontradaException e) {
            System.out.println("no se puede cancar la orden" + e.getMessage());
        } finally {
            System.out.println("proceso finalizado");
        }
    }

    private static void consultarplaca(){
        System.out.println("placa del vehiculo:");
        String placa = sc.nextLine();
        List<OredenServicio> coincencias = taller.consultarplaca(placa);

        if(coincencias.isEmpty()){
            System.out.println("No hay ordenes registradas para " + placa);
            return;
        }
        System.out.println("ordenes encontradas para placa" + placa);
        for(int i = 0; i < coincencias.size(); i++){
            System.out.println(coincencias.get(i));
        }
    }

    private static void reporteDeCostos(){
        System.out.println("Valor total de ordenes:" + taller.calcularTotal());
        System.out.println("Costo promedio de ordenes:" + taller.calcularcostopromedio());
    }

    private static void OrdenMayor(){
        OrdenServicio orden = taller.obetnerOrdenMayor();
        if(orden == null){
            System.out.println("no hay ordenes");
            return;
        }
        System.out.println("orden con el costo estidmaso alto:");
        System.out.println(orden);
    }

    private static void cantidadOrdenes(){
        System.out.println("cantidad de ordenes registradas:" + taller.getCantidadOrdenes());
    }

    private static int leerEntero(String mensaje){
        System.out.println(mensaje);
        while(!sc.hasNextInt()){
            System.out.println("Ingresa un numero entero");
            sc.next();
            System.out.println(mensaje);
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static double leerCosto(String mensaje){
        double costo = 0;
        boolean valorvalido = false;
        do { 
           System.out.println(mensaje);
           String texto = sc.nextLine();

           try {
               costo = Double.parseDouble(texto);
               valorvalido = true;
           } catch (NumeroFormatException e) {
            System.out.println("Entrada invalida");
           } 
        } 
        while(!valorvalido);
        return costo;
    }
}