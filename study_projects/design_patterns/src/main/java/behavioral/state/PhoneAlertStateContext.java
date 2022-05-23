package main.java.behavioral.state;

/**
 * para implementar el patron de diseño State es necesaria la creación de una interfaz que defina la logica según el tipo
 * de estado del Objeto Context (el ejemplo se realiza con el estado de las alertas de un telefono), por lo que se es
 * necesario crear una clase la cual tenga como parametro una referencia hacia a la interfaz, para luego darle un valor
 * por defecto al mismo... asi que en el metodo set simplemente le decimos a la clase Context con que implementacion debe
 * responder según el estado enviado al metodo setter, por lo tanto sera esta clase Context quien resuelva la respuesta
 * mediante el uso de la implementacion dada
 */
public class PhoneAlertStateContext {

    private PhoneAlertState currentAlertState;

    public PhoneAlertStateContext(){
        currentAlertState = new Sound();
    }

    public void setState(PhoneAlertState state){
        currentAlertState = state;
    }

    public void alert(){
        currentAlertState.alert(this);
    }
}
