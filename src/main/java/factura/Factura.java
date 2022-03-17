package factura;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; import java.util.function.Predicate;
import java.util.stream.Stream;

class Facturaobj{
    String descripcion;
    int precio;
    Date fecha;
    String codFactura;
    int cantidad;

    Facturaobj(String descripcion, int precio, Date fecha, String codFactura, int cantidad){
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.codFactura = codFactura;
        this.cantidad = cantidad;
    }

    int getImporte(){
        return precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public int getCantidad() {
        return cantidad;
    }
}

class FacturaUtils {
    public static Predicate < Facturaobj > filtroCodFact(String codFactura) {
        return (Facturaobj factura) -> {
        return factura.getCodFactura().equals(codFactura);
        };
    }

    public static Predicate < Facturaobj > filtroCantidad(int cantidad, String operacion) {
        if (operacion == "<" ){
            return ( Facturaobj factura ) -> {
              return factura.getCantidad() < cantidad ;
            };
        } else if (operacion == "=" ) {
            return (Facturaobj factura) -> {
                return factura.getCantidad() == cantidad;
            };
        } else {
            return  (Facturaobj factura) -> {
                return  factura.getCantidad() > cantidad;
            } ;
        }
    }

    public static Predicate < Facturaobj > filtroFecha(Date fecha , String operacion) {
        if (operacion == "<") {
            return (Facturaobj factura) -> {
                return factura.getFecha().before(fecha);
            };
        } else {
            return (Facturaobj factura) -> {
                return factura.getFecha().after(fecha);
            };
        }
    }
}

public class Factura {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Facturaobj f=new Facturaobj("ordenador",1000 , formatter.parse("2020-12-01") , "A55B" , 1);
        Facturaobj f2=new Facturaobj("movil",300, formatter.parse("2020-12-02") , "C30C" , 2);
        Facturaobj f3=new Facturaobj("impresora",200, formatter.parse("2020-12-03") , "L22T" , 3);
        Facturaobj f4=new Facturaobj("imac",1500, formatter.parse("2020-12-04") , "M24F" , 4);


        List<Facturaobj> lista= new ArrayList<Facturaobj>();

        lista.add(f);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);

        Facturaobj fact1 = lista.stream().filter(FacturaUtils.filtroCodFact("M24F")).findFirst().get();
        System.out.println("la factura con el codigo especifico es: "+fact1.getCodFactura());

        Stream<Facturaobj> fact2 = lista.stream().filter(FacturaUtils.filtroCantidad(2, ">"));
            fact2.forEach((Facturaobj facturaMostrar) -> {
                System.out.println("Codigo de factura " +facturaMostrar.getCodFactura() +" Y su cantidad es: "+facturaMostrar.getCantidad());
            });

        Stream<Facturaobj> fact3 = lista.stream().filter(FacturaUtils.filtroFecha(formatter.parse("2020-12-01"), ">" ));
        fact3.forEach((Facturaobj factura3Mostrar) -> {
            System.out.println("Codigo de factura " +factura3Mostrar.getCodFactura() +" Y su fecha es: "+factura3Mostrar.getFecha());
        });
    }
}

