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


create table alquiler (
 id int(11) not null auto_increment,
 id_cliente int(11) not null,
 fecha_alquiler date not null,
 fecha_maxima_entrega date null,
 fecha_entrega date null,
 estado varchar(20) null,
 total double null,
 subtotal double null,
 total_adicional double null,
 total_multa double null,
 primary key (id)
);


create table alquiler_item (
 id int(11) not null auto_increment,
 id_alquiler int(11) not null,
 id_videojuego int(11) not null,
 cantidad int not null,
 primary key (id)
);

