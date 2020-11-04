create table videojuego (
 id int(11) not null auto_increment,
 codigo varchar(50) not null,
 nombre varchar(100) not null,
 genero varchar(100) null,
 precio double not null,
 stock int null,
 primary key (id)
);

create table cliente (
 id int(11) not null auto_increment,
 identificacion varchar(50) not null,
 nombre varchar(100) not null,
 apellido varchar(100) null,
 telefono varchar(50) not null,
 direccion varchar(100) null, 
 primary key (id)
);


/*create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);*/