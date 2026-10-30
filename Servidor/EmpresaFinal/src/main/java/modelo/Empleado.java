package modelo;

import exceptions.DatosNoCorrectos;

/**
 * Clase que representa a un empleado de la empresa.
 */
public class Empleado extends Persona {

    private int categoria;
    public int anyosTrabajados;

    /**
     * Crea un empleado con la categoría y los años de trabajo iniciales.
     *
     * @param nombre nombre del empleado.
     * @param dni DNI del empleado.
     * @param sexo sexo del empleado.
     */
    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        categoria = 1;
        anyosTrabajados = 0;
    }

    /**
     * Crea un empleado con todos sus datos.
     *
     * @param nombre nombre del empleado.
     * @param dni DNI del empleado.
     * @param sexo sexo del empleado.
     * @param categoria categoría del empleado.
     * @param anyosTrabajados años trabajados.
     * @throws DatosNoCorrectos si la categoría o los años no son válidos.
     */
    public Empleado(String nombre, String dni, char sexo, int categoria, int anyosTrabajados) throws DatosNoCorrectos {
        super(nombre, dni, sexo);
        if ((categoria < 1 || categoria > 10) || anyosTrabajados < 0) {
            throw new DatosNoCorrectos("Datos no correctos");
        }
        this.categoria = categoria;
        this.anyosTrabajados = anyosTrabajados;
    }

    /**
     * Cambia la categoría del empleado.
     *
     * @param categoria nueva categoría.
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * Devuelve la categoría del empleado.
     *
     * @return categoría del empleado.
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Aumenta en uno los años trabajados.
     */
    public void incrAnyo() {
        anyosTrabajados++;
    }

    /**
     * Devuelve los datos del empleado en forma de texto.
     *
     * @return datos del empleado.
     */
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