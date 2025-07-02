package com.example.calcuquesito.builders;

import com.example.calcuquesito.models.PrecioVenta;

public class PrecioVentaBuilder {
    private double materiaPrima;
    private double materiales;
    private double manoObra;
    private double costos;
    private double margen;

    public PrecioVentaBuilder setMateriaPrima(double v) { this.materiaPrima = v; return this; }
    public PrecioVentaBuilder setMateriales(double v) { this.materiales = v; return this; }
    public PrecioVentaBuilder setManoObra(double v) { this.manoObra = v; return this; }
    public PrecioVentaBuilder setCostos(double v) { this.costos = v; return this; }
    public PrecioVentaBuilder setMargen(double v) { this.margen = v; return this; }

    public PrecioVenta build() {
        double total = materiaPrima + materiales + manoObra + costos;
        double finalPrecio = total * (1 + margen / 100);
        return new PrecioVenta(finalPrecio);
    }
}