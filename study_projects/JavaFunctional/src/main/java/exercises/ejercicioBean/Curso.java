package exercises.ejercicioBean;

import java.time.LocalDate;

public class Curso {
    enum EstadoCurso {ACTIVO, INACTIVO};

    private String nombreCurso;
    private Colegio nombreColegio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoCurso estadoCurso;

    public Curso(String nombreCurso, Colegio nombreColegio) {
        this.nombreCurso = nombreCurso;
        this.nombreColegio = nombreColegio;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Colegio getNombreColegio() {
        return nombreColegio;
    }

    public void setNombreColegio(Colegio nombreColegio) {
        this.nombreColegio = nombreColegio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoCurso getEstadoCurso() {
        return estadoCurso;
    }

    public void setEstadoCurso(EstadoCurso estadoCurso) {
        this.estadoCurso = estadoCurso;
    }
}
