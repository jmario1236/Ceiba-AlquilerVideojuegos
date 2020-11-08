select al.fecha_alquiler, 
		al.fecha_maxima_entrega, 
		al.fecha_entrega, 
		al.estado,
		al.total,
		al.subtotal,
		al.total_adicional,
		al.total_multa,
		al.id
from alquiler al
where al.id_cliente = :id and al.estado = 'VIGENTE';