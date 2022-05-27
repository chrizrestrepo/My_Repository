package ejercicioBean;

public class Alumno extends Persona {
    private Colegio nombreColegio;
    private Curso nombreCurso;

    public Alumno(String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
    }

    public Colegio getNombreColegio() {
        return nombreColegio;
    }

    public void setNombreColegio(Colegio nombreColegio) {
        this.nombreColegio = nombreColegio;
    }

    public Curso getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(Curso nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}
