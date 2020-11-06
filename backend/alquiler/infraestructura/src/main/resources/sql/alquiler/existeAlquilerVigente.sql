select count(1) from cliente cli 
join alquiler al on al.id_cliente = cli.id
where cli.id = :id and al.estado = 'VIGENTE'