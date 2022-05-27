package ejercicioBean;

public class Profesor extends Persona {
    private int salario;
    private Curso curso;

    public Profesor(String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
