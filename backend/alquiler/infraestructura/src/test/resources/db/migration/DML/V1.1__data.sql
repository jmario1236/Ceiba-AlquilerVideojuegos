insert into videojuego(id,codigo, nombre,genero,precio,stock) values (1,'MR-B1', 'Super Mario bros','Plataforma',3000,10);
insert into videojuego(id,codigo, nombre,genero,precio,stock) values (2,'RR-B2', 'Rabbit bros','Plataforma',2000,10);
insert into videojuego(id,codigo, nombre,genero,precio,stock) values (3,'RM-B2', 'Rabbit Monster','Plataforma',4000,10);
-- Insercion para clientes
insert into cliente (id,identificacion, nombre,apellido,telefono,direccion) values (1,'123456789', 'Alvaro','Petro','34569852','Centro');
insert into cliente (id,identificacion, nombre,apellido,telefono,direccion) values (2,'114335420', 'Alex','Pedrito','34569852','Centro');
insert into cliente (id,identificacion, nombre,apellido,telefono,direccion) values (3,'114333333', 'Petruccion','Jay','xxx69852','Centro');


insert into alquiler (id,id_cliente, fecha_alquiler,fecha_maxima_entrega,fecha_entrega,estado, total, subtotal, total_adicional, total_multa) 
values (1,3, '2020-11-7','2020-11-10',null,'VIGENTE', 6000, 6000,0,0);

insert into alquiler_item (id,id_alquiler, id_videojuego,cantidad) 
values (1,1, 2,1);

insert into alquiler_item (id,id_alquiler, id_videojuego,cantidad) 
values (2,1, 3,1);
