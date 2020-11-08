select id,identificacion,nombre,apellido,telefono,direccion
from cliente where upper(nombre) like upper(:criterio) or upper(identificacion) like upper(:criterio)