package modelo;

/**
 * Clase que representa a una persona.
 */
public class Persona {

    public String nombre;
    public String dni;
    public char sexo;

    /**
     * Crea una persona con nombre, DNI y sexo.
     *
     * @param nombre nombre de la persona.
     * @param dni DNI de la persona.
     * @param sexo sexo de la persona.
     */
    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Crea una persona con nombre y sexo.
     *
     * @param nombre nombre de la persona.
     * @param sexo sexo de la persona.
     */
    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Establece el DNI de la persona.
     *
     * @param dni nuevo DNI.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Devuelve los datos de la persona en forma de texto.
     *
     * @return datos de la persona.
     */
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}