insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("1", "tatiana@gmail.com", "Tatiana Arboleda", "tata", "admin");

insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("2", "andres@gmail.com", "Andres Elejalde", "andres", "admin");

insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("3", "sebastian@gmail.com", "Sebastian Medina", "sebas", "admin");

insert into administrador(cedula, email, nombre, nickname, contrasenia)
    values ("4", "daniela@gmail.com", "Daniela Arenas", "dani", "admin");


insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("11", "diego@gmail.com", "Diego Valencia", "diego", "admin", 1);

insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("12", "pablo@gmail.com", "Pablo Leal", "pablo", "admin", 2);

insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("13", "mafe@gmail.com", "Maria Fernanda", "mafe", "admin", 3);

insert into moderador(cedula, email, nombre, nickname, contrasenia, administrador_cedula)
    values ("14", "yurani@gmail.com", "Yurani", "yuyu", "admin", 4);