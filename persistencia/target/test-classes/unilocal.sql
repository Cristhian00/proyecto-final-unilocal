-- ------------------------------ Departamentos -----------------------------------------------
insert into departamento(nombre, pais) values ("Quindio", "Colombia");
insert into departamento(nombre, pais) values ("Risaralda", "Colombia");
insert into departamento(nombre, pais) values ("Cundinamarca", "Colombia");
insert into departamento(nombre, pais) values ("Caldas", "Colombia");
insert into departamento(nombre, pais) values ("Nariño", "Colombia");

-- ------------------------------ Ciudades ----------------------------------------------------
insert into ciudad(nombre, departamento_id) values ("Armenia", 1);
insert into ciudad(nombre, departamento_id) values ("Pereira", 2);
insert into ciudad(nombre, departamento_id) values ("Bogota DC", 3);
insert into ciudad(nombre, departamento_id) values ("Manizales", 4);
insert into ciudad(nombre, departamento_id) values ("Pasto", 5);

-- ------------------------------ Administradores ---------------------------------------------
insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("1", "tatiana@gmail.com", "Tatiana Arboleda", "tata", "admin");

insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("2", "andres@gmail.com", "Andres Elejalde", "andres", "admin");

insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("3", "sebastian@gmail.com", "Sebastian Medina", "sebas", "admin");

insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("4", "daniela@gmail.com", "Daniela Arenas", "dani", "admin");

-- ------------------------------ Moderadores -------------------------------------------------
insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("11", "diego@gmail.com", "Diego Valencia", "diego", "admin", 1);

insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("12", "pablo@gmail.com", "Pablo Leal", "pablo", "admin", 2);

insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("13", "mafe@gmail.com", "Maria Fernanda", "mafe", "admin", 3);

insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("14", "yurani@gmail.com", "Yurani", "yuyu", "admin", 4);

-- ------------------------------ Usuarios ----------------------------------------------------
insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("111", "cristhian@gmail.com", "Cristhian Ortiz", "cris", "admin", 1);

insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("112", "alejandra@gmail.com", "Alejandra Ladino", "aleja", "admin", 2);

insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("113", "camilo@gmail.com", "Camilo Garcia", "cami", "admin", 1);

insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("114", "pedro@gmail.com", "Pedro Lopez", "pepe", "admin", 4);

-- ------------------------------- Horarios ---------------------------------------------------
insert into horario(dia, hora_apertura, hora_cierre) values ("LUNES", "14:00", "22:00");
insert into horario(dia, hora_apertura, hora_cierre) values ("SABADO", "14:00", "22:00");
insert into horario(dia, hora_apertura, hora_cierre) values ("DOMINGO", "14:00", "22:00");
insert into horario(dia, hora_apertura, hora_cierre) values ("FESTIVO", "14:00", "22:00");

-- ----------------------------------- Tipos -------------------------------------------------
insert into tipo_lugar(nombre) values ("Hotel");
insert into tipo_lugar(nombre) values ("Cafeteria");
insert into tipo_lugar(nombre) values ("Comida Rápida");
insert into tipo_lugar(nombre) values ("Museo");
insert into tipo_lugar(nombre) values ("Restaurante");

-- ----------------------------------- Lugares -------------------------------------------------
insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo_lugar_id, usuario_creador_cedula)
    values ("Mocawa", 3, "Hotel de lujo", "APROBADO", "2000/12/01", 4.5499102, -75.6617799, 1, 111);

insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo_lugar_id, usuario_creador_cedula)
    values ("Hotel Armenia", 1, "Hospedaje barato", "PENDIENTE", "2010/05/23", 4.549028, -75.6623743, 1, 111);

insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo_lugar_id, usuario_creador_cedula)
    values ("Salome Café", 4, "Café bar", "RECHAZADO", "2016/04/10", 4.5671406, -75.7502047, 2, 112);

insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo_lugar_id, usuario_creador_cedula)
    values ("McNicko Burguer", 2, "Comidas rápidas", "PENDIENTE", "2019/01/01", 4.6213931, -75.7649512, 3, 112);

-- ------------------------------------- Comentarios ---------------------------------------------------------
insert into comentario(calificacion, fecha_comentario, mensaje, lugar_comentario_id, usuario_comentario_cedula)
    values (4, "2016/04/10", "Excelente servicio", 1, "111");

insert into comentario(calificacion, fecha_comentario, mensaje, lugar_comentario_id, usuario_comentario_cedula)
    values (3, "2016/04/10", "Mala atención", 2, "112");

insert into comentario(calificacion, fecha_comentario, mensaje, lugar_comentario_id, usuario_comentario_cedula)
    values (2, "2016/04/10", "No lo recomiendo", 3, "113");

insert into comentario(calificacion, fecha_comentario, mensaje, lugar_comentario_id, usuario_comentario_cedula)
    values (5, "2016/04/10", "Excelente servicio", 4, "111");

-- -------------------------------------- Favoritos -------------------------------------------------------------
insert into favorito(id_usuario, id_lugar) values ("111", 1);
insert into favorito(id_usuario, id_lugar) values ("112", 2);
insert into favorito(id_usuario, id_lugar) values ("111", 4);

-- -------------------------------------- Horario Lugares -------------------------------------------------------
insert into lugar_horarios(lugares_id, horarios_codigo) values (1, 1);
insert into lugar_horarios(lugares_id, horarios_codigo) values (1, 4);

insert into lugar_horarios(lugares_id, horarios_codigo) values (2, 2);
insert into lugar_horarios(lugares_id, horarios_codigo) values (2, 3);

insert into lugar_horarios(lugares_id, horarios_codigo) values (3, 1);
insert into lugar_horarios(lugares_id, horarios_codigo) values (3, 2);

insert into lugar_horarios(lugares_id, horarios_codigo) values (4, 3);
insert into lugar_horarios(lugares_id, horarios_codigo) values (4, 4);

-- ------------------------------------ Telefonos Lugar --------------------------------------------------------
insert into lugar_telefono(lugar_id, telefono, telefono_key) values (1, "3145423432", "1");
insert into lugar_telefono(lugar_id, telefono, telefono_key) values (1, "7542334", "2");

insert into lugar_telefono(lugar_id, telefono, telefono_key) values (2, "3214536253", "3");

insert into lugar_telefono(lugar_id, telefono, telefono_key) values (3, "3123456789", "4");
insert into lugar_telefono(lugar_id, telefono, telefono_key) values (3, "7465454", "5");

insert into lugar_telefono(lugar_id, telefono, telefono_key) values (4, "3123456712", "6");

-- ------------------------------------ Telefonos Usuarios ----------------------------------------------------
insert into usuario_telefono(usuario_cedula, telefono, telefono_key) values ("111", "3145423432", "1");
insert into usuario_telefono(usuario_cedula, telefono, telefono_key) values ("111", "7531213", "2");

insert into usuario_telefono(usuario_cedula, telefono, telefono_key) values ("112", "3156473834", "3");

insert into usuario_telefono(usuario_cedula, telefono, telefono_key) values ("113", "3165432314", "4");

insert into usuario_telefono(usuario_cedula, telefono, telefono_key) values ("114", "3115443242", "5");
insert into usuario_telefono(usuario_cedula, telefono, telefono_key) values ("114", "3178909898", "6");