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

-- ------------------------------ Usuarios ---------------------------------------
insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("111", "cristhian@gmail.com", "Cristhian Ortiz", "cris", "admin", 1);

insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("112", "alejandra@gmail.com", "Alejandra Ladino", "aleja", "admin", 2);

insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("113", "camilo@gmail.com", "Camilo Garcia", "cami", "admin", 3);

insert into usuario(cedula, email, nombre, nickname, contrasenia, ciudad_usuario_id)
    values ("114", "pedro@gmail.com", "Pedro Lopez", "pepe", "admin", 4);