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

-- ------------------------------- Horarios ---------------------------------------------------
insert into horario(dia, hora_apertura, hora_cierre) values ("LUNES", "14:00", "22:00");
insert into horario(dia, hora_apertura, hora_cierre) values ("SABADO", "14:00", "22:00");
insert into horario(dia, hora_apertura, hora_cierre) values ("DOMINGO", "14:00", "22:00");
insert into horario(dia, hora_apertura, hora_cierre) values ("FESTIVO", "14:00", "22:00");

-- ------------------------------- Usuarios ----------------------------------------------------
insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
values ("111", "cristhian@gmail.com", "Cristhian Ortiz", "cris", "admin", 1);

insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
values ("112", "alejandra@gmail.com", "Alejandra Ladino", "aleja", "admin", 2);

-- ----------------------------------- Lugares -------------------------------------------------
insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo, usuario_creador_cedula)
    values ("Mocawa", 3, "Hotel de lujo", "APROBADO", "2000/12/01", 4.5499102, -75.6617799, "HOTEL", 111);

insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo, usuario_creador_cedula)
    values ("Hotel Armenia", 1, "Hospedaje barato", "PENDIENTE", "2010/05/23", 4.549028, -75.6623743, "HOTEL", 111);

insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo, usuario_creador_cedula)
    values ("Salome Café", 4, "Café bar", "RECHAZADO", "2016/04/10", 4.5671406, -75.7502047, "CAFETERIA", 112);

insert into lugar(nombre, ciudad_lugar_id, descripcion, estado, fecha_creacion, latitud, longitud, tipo, usuario_creador_cedula)
    values ("McNicko Burguer", 2, "Comidas rápidas", "PENDIENTE", "2019/01/01", 4.6213931, -75.7649512, "COMIDA_RAPIDA", 112);
