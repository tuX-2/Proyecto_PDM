package com.example.projectpdm.factories;

import com.example.projectpdm.models.*;
public class ProductoFactory {
    public static Producto crearProducto(String tipo) {
        switch (tipo) {
            case "Fresco": return new QuesoFresco();
            // Otros productos en el futuro
            default: return null;
        }
    }
}
