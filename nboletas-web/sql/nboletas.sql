delete from BoletaEntity;

delete from EnvioEntity;
delete from ComentarioEntity;

insert into EnvioEntity (id, direccion) values (100,'Calle 43 #7-26 - Aprt:101');
insert into EnvioEntity (id, direccion) values (200, 'Cra 68i #26.48 - Casa');
insert into EnvioEntity (id, direccion) values (300,'Cl. 8 Sur #71d:20');
insert into EnvioEntity (id, direccion) values (400, 'Carrera 43 # 70A-50 SUR');

insert into ComentarioEntity (id,comentario,fecha) values (100,'Muy buen espectaculo','10/12/17');
insert into ComentarioEntity (id,comentario,fecha) values (200,'horror de evento','31/10/17');
insert into ComentarioEntity (id,comentario,fecha) values (300,'me gusto mucho asistir','01/11/17');
insert into ComentarioEntity (id,comentario,fecha) values (400,'me senti muy bien en el evento','20/09/17');
