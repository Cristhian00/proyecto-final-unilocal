package co.edu.uniquindio.unilocal.servicios;

public interface EmailServico {

    void SendSimpleMessage(String to, String asunto, String text);
}
