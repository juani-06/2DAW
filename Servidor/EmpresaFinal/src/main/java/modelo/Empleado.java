package modelo;

import exceptions.DatosNoCorrectos;

public class Empleado extends Persona {

    private int categoria;
    public int anyosTrabajados;


    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        categoria = 1;
        anyosTrabajados = 0;
    }

    public Empleado(String nombre, String dni, char sexo, int categoria, int anyosTrabajados) throws DatosNoCorrectos {
        super(nombre, dni, sexo);
        if ((categoria <1 || categoria >10) || anyosTrabajados < 0){
            throw new DatosNoCorrectos("Datos no correctos");
        }
        this.categoria = categoria;
        this.anyosTrabajados = anyosTrabajados;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getCategoria() {
        return categoria;
    }

    public void incrAnyo (){
        anyosTrabajados++;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                ", categoria=" + categoria +
                ", anyosTrabajados=" + anyosTrabajados;
    }
}
