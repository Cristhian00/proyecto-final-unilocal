package co.edu.uniquindio.unilocal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

@SpringBootApplication
public class WebApplication{

    private final JavaMailSender emailSender;

    public WebApplication(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    /*
    @Override
    public void run(String... args) throws Exception {

        String mensaje = "Este es una prueba de mensaje ID=" + UUID.randomUUID().toString();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("cristhian.6661@gmail.com");
        msg.setSubject("Mensaje de correo electronico");
        msg.setText(mensaje);

        emailSender.send(msg);
    }
     */
}
