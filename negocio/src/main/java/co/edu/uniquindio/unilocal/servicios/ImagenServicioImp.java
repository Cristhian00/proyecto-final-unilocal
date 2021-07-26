package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Imagen;
import co.edu.uniquindio.unilocal.repositorios.ImagenRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenServicioImp implements  ImagenServicio{

    private final ImagenRepo imagenRepo;

    public ImagenServicioImp(ImagenRepo imagenRepo){
        this.imagenRepo = imagenRepo;
    }
    @Override
    public Imagen registrarImagen(Imagen m) throws Exception {

        if(m == null){
            throw new Exception("Debe haber una imagen seleccionada");
        }
        if(m.getUrl() == null || m.getUrl().isEmpty()){
            throw new Exception("Debe ingresar la URL de la imagen");
        }
        if(m.getUrl().length() > 255){
            throw new Exception("La URL de la imagen debe tener menos de 255 caracteres");
        }
        if(m.getLugar() == null){
            throw new Exception("Debe asociar la imagen con un lugar");
        }
        return imagenRepo.save(m);
    }

    @Override
    public Imagen modificarImagen(Imagen m) throws Exception {

        if(m==null){
            throw new Exception("Debe haber una imagen para modificar");
        }
        Optional<Imagen> imagen = imagenRepo.findById(m.getId());
        if(imagen.isEmpty()){
            throw new Exception("No existe una imagen con ese ID registrada");
        }
        if(m.getUrl() == null || m.getUrl().isEmpty()){
            throw new Exception("Debe ingresar la URL de la imagen");
        }
        if(m.getUrl().length() > 255){
            throw new Exception("La URL de la imagen debe tener menos de 255 caracteres");
        }
        if(m.getLugar() == null){
            throw new Exception("Debe asociar la imagen con un lugar");
        }
        return imagenRepo.save(m);
    }

    @Override
    public boolean eliminarImagen(int id) throws Exception {

        Optional<Imagen> m = imagenRepo.findById(id);
        if(m.isEmpty()){
            throw new Exception("No hay ninguna imagen con ese ID registrada");
        }
        imagenRepo.delete(m.get());
        return true;
    }

    @Override
    public Imagen obtenerImagen(int id) throws Exception {

        Optional<Imagen> m = imagenRepo.findById(id);
        if(m.isEmpty()){
            throw new Exception("No hay ninguna imagen con ese ID registrada");
        }
        return m.get();
    }

    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepo.findAll();
    }
}
