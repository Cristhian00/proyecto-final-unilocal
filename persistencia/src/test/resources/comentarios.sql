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

-- ------------------------------ Usuarios ----------------------------------------------------
insert into usuario(cedula, email, nombre, nickname, contrasenia)
    values ("111", "cristhian@gmail.com", "Cristhian Ortiz", "cris", "admin");

insert into usuario(cedula, email, nombre, nickname, contrasenia)
    values ("112", "alejandra@gmail.com", "Alejandra Ladino", "aleja", "admin");

insert into usuario(cedula, email, nombre, nickname, contrasenia)
    values ("113", "camilo@gmail.com", "Camilo Garcia", "cami", "admin");

insert into usuario(cedula, email, nombre, nickname, contrasenia)
    values ("114", "pedro@gmail.com", "Pedro Lopez", "pepe", "admin");

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