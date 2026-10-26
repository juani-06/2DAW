package fichero;

import modelo.Nomina;
import modelo.Empleado;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FicheroSalarios {
    public void escribir(ArrayList<Empleado> empleados) throws IOException {

        DataOutputStream fichero =new DataOutputStream(
                new FileOutputStream("sueldos.dat")
        );

        for (Empleado e : empleados) {
            fichero.writeUTF(e.dni);
            fichero.writeInt(Nomina.sueldo(e));
        }
    }
}