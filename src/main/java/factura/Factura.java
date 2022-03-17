package factura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List; import java.util.function.Predicate;

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
    public static void main(String[] args) {
        Facturaobj f=new Facturaobj("ordenador",1000 , new Date() , "A55B" , 1);
        Facturaobj f2=new Facturaobj("movil",300, new Date() , "C30C" , 2);
        Facturaobj f3=new Facturaobj("impresora",200, new Date() , "L22T" , 3);
        Facturaobj f4=new Facturaobj("imac",1500, new Date() , "M24F" , 4);


        List<Facturaobj> lista= new ArrayList<Facturaobj>();

        lista.add(f);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);

        /*Predicate<Facturaobj> predicado = new Predicate<Facturaobj>() {
            @Override
            public boolean test(Facturaobj t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.out.println("iteracion ");
                return t.getImporte()>300;//hacer comparativas
            }//busca x importe en la lista especifica y acrea importe mayo a 300 trae primero
        };*/
        //crea funcion de tipo objeto que maneja atributo haces test y filtras co n valor espe interar dentro de lista especifica

        /*Facturaobj facturaFiltro= lista.stream()
                .filter(predicado).findFirst().get();
        System.out.println("FACTURA UNICA "+facturaFiltro.getImporte());
        //trae primero , obtiene en la variable , filtrando x el predicado

        /*
        Factura facturaFiltro=lista.stream()
                .filter(elemento->elemento.getImporte()>300)
                .findFirst()
                .get();
        System.out.println(facturaFiltro.getImporte());
        */
    }
}
// date (hacer los otros 4)
