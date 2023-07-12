package org.example.utils;


import org.example.entity.service.DetallePedido;

import java.util.ArrayList;

public class Carrito {
    //Creamos un arrayList de la clase detallePedido
    private static final ArrayList<DetallePedido> detallePedidos = new ArrayList<>();

    //Método para agregar productos al carrito(bolsa)
    public static String agregarProductos(DetallePedido detallePedido) {
        int index = 0;
        boolean b = false;
        for (DetallePedido dp : detallePedidos) {
            if (dp.getProducto().getId() == detallePedido.getProducto().getId()) {
                detallePedidos.set(index, detallePedido);
                b = true;
                return "El producto ha sido agregado al carrito, se actualizará la cantidad";
            }
            index++;
        }
        if (!b) {
            detallePedidos.add(detallePedido);
            return "El producto ha sido agregado al carrito con éxito";
        }
        return ". . . . ";
    }

    //Método para eliminar un producto del carrito(bolsa)
    public static void eliminar(final int idp) {
        DetallePedido dpE = null;
        for (DetallePedido dp : detallePedidos) {
            if (dp.getProducto().getId() == idp) {
                dpE = dp;
                break;
            }
        }
        if (dpE != null) {
            detallePedidos.remove(dpE);
            System.out.println("Se elimino el producto del detalle de pedido");
        }
    }

    //Método para conseguir los detalles del pedido
    public static ArrayList<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    //Método para limpiar
    public static void limpiar() {
        detallePedidos.clear();
    }

}