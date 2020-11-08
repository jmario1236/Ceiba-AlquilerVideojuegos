select id,codigo,nombre,genero,precio,stock
from videojuego where upper(codigo) like upper(:criterio) or upper(nombre) like upper(:criterio)