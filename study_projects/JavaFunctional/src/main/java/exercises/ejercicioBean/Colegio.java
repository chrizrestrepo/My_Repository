package exercises.ejercicioBean;

import java.util.List;

public class Colegio {
    private String nombreColegio;
    private List<Curso> listaCursos;

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public Colegio(String nombreColegio) {
        this.nombreColegio = nombreColegio;
    }

    public String getNombreColegio() {
        return nombreColegio;
    }

    public void setNombreColegio(String nombreColegio) {
        this.nombreColegio = nombreColegio;
    }
}
