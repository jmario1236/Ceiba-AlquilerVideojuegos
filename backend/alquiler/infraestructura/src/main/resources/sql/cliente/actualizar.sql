update cliente
set identificacion = :identificacion,
	nombre = :nombre,
	apellido = :apellido,
	telefono = :telefono,
	direccion = :direccion
where id = :id