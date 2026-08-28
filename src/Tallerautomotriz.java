import java.util.ArrayList;
import java.util.List;

public class Tallerautomotriz{
    private List<OrdenServicio> ordenes;

    public Tallerautomotriz(){
        this.ordenes = new ArrayList<OrdenServicio>();
    }

    private boolean HaynumeroDeOrden(int numerodeOrden){
        for(int i = 0; i < ordenes.size(); i++){
            if(ordenes.get(i).getnumeroDeOrden() == numerodeOrden){
                return true;
            }
        }
        return false;
    }
    public void registrarOrden(int numeroDeOrden, String NombredePropietario, String placaVehiculo, String DescripcionDeServicio, double Costo) throws DatosInvalidosException{
        if(HaynumeroDeOrden(numeroDeOrden)){
            throw new DatosInvalidosException("Ya existe una orden registrada la cual es:" + numeroDeOrden);
        }
        if(NombredePropietario == null || NombredePropietario.trim().isEmpty()){
            throw new DatosInvalidosException("El nombre del propietario no esta en validacion");
        }
        if(placaVehiculo == null || placaVehiculo.trim().isEmpty()){
            throw new DatosInvalidosException("La placa del vehiculo no debria estar vacia");
        }
        if(DescripcionDeServicio == null || DescripcionDeServicio.trim().isEmpty()){
            throw new DatosInvalidosException("La descripcion del servicio no puede estar vacia");
        }
        if(Costo <= 0){
            throw new DatosInvalidosException("EL costo no puede ser mayor que 0");
        }

        OrdenServicio nuevaOrden = new OrdenServicio(numeroDeOrden, NombredePropietario, placaVehiculo, DescripcionDeServicio, Costo);
        ordenes.add(nuevaOrden);
    }

    public List<OrdenServicio> consultaOrdenServicio(){
        return ordenes;
    }

    public OrdenServicio BuscaOrdenServicio(int numeroDeOrden) throws OrdenNoEncontradaException{
        for(int i = 0; i < ordenes.size(); i++){
            OrdenServicio orden = ordenes.get(i);
            if(orden.getnumeroDeOrden() == numeroDeOrden){
                return orden;
            }
        }

        throw new OrdenNoEncontradaException("No existe alguna orden registrada con el numero:" + numeroDeOrden);
    }

    public void modificarOrden(int numeroDeOrden, String nuevaDescripcion, double nuevoCosto) throws OrdenNoEncontradaException, DatosInvalidosException{
        OrdenServicio = BuscaOrdenServicio(numeroDeOrden);
        if(nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()){
            throw new DatosInvalidosException("la nueva descripcion no puede estar sin nada");
        }
        if(nuevoCosto <= 0){
            throw new DatosInvalidosException("el nuevo csoto no puede llegar a ser mayor que 0");
        }
        orden.setDescripcionDeServicio(nuevaDescripcion);
        orden.setCosto(nuevoCosto);
    }

    public List<OrdenServicio> consultarplaca(String placaVehiculo){
        List<OrdenServicio> coincidencias = new ArrayList<OrdenServicio>();
        for(int i = 0; i < ordenes.size(); i++){
            OrdenServicio orden = ordenes.get(i);
            if(orden.getplacaVehiculo().equalsIgnoreCase(placaVehiculo)){
                coincidencias.add(orden);
            }
        }
        return coincidencias;
    }

    public double calcularTotal(){
        double total = 0;
        for(int i = 0; i < ordenes.size(); i++){
            total = total + ordenes.get(i).getCosto();
        }
        return total;
    }

    public double calcularcostopromedio(){
        if(ordenes.isEmpty()){
            return 0;
        }
        return calcularTotal()/ordenes.size();
    }

    public OrdenServicio obetnerOrdenMayor(){
        if(ordenes.isEmpty()){
            return null;
        }
        OrdenServicio mayor = ordenes.get(0);
        for(int i = 1; i < ordene.size(); i++){
            if(ordenes.get(i).getCosto() > mayor.getCosto()){
                mayor = ordenes.get(i);
            }
        }
        return mayor;
    }

    public int getCantidadOrdenes(){
        return ordenes.size();
    }
}