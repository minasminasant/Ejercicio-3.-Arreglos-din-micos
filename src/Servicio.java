public class Servicio{
    private int numeroDeOrden;
    private String NombredePropietario;
    private String placaVehiculo;
    private String DescripcionDeServicio;
    private double Costo;

    public Servicio(int numeroDeOrden, String NombredePropietario, String placaVehivulo, String DescripcionDeServicio, double Costo){
        this.numeroDeOrden = numeroDeOrden;
        this.NombredePropietario = NombredePropietario;
        this.placaVehiculo= placaVehivulo;
        this.DescripcionDeServicio = DescripcionDeServicio;
        this.Costo = Costo;
    }

    public int getnumeroDeOrden(){
        return numeroDeOrden;
    }

    public String getNombredePropietario(){
        return NombredePropietario;
    }

    public String getplacaVehiculo(){
        return placaVehiculo;
    }

    public String getDescripcionDeServicio(){
        return DescripcionDeServicio;
    }

    public double getCosto(){
        return Costo;
    }

    public void setCosto(double Costo){
        this.Costo = Costo;
    }

    @Override
    public String toString(){
        return "orden:" + numeroDeOrden + "Propietario:" + NombredePropietario + "placa:" + placaVehiculo + "Servicio:" + DescripcionDeServicio + "Costo estimado:" + Costo;
    }
}