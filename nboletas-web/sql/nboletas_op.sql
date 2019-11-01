delete from ReembolsoEntity;
delete from EnvioEntity;
delete from ComentarioEntity;
delete from BoletaEntity;
delete from OrganizadorEntity;
delete from FuncionEntity;
delete from EspectaculoEntity;
delete from ArtistaEntity;
delete from SillaEntity;
delete from DivisionDeLugarEntity;
delete from LugarEntity;
delete from EspectaculoEntity_OrganizadorEntity;
delete from UsuarioEntity;

/* Mis numero son del 0 al 99 (df.riveros11)**/
insert into FuncionEntity (id, fecha) values (0, '10/10/2017');
insert into SillaEntity (id, costo) values (0, 20.1);
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (0, 'aa', 'aa', 'Hugo', 'hs.hernandez@domain.onion', 'Argentina', 'newYork');
insert into BoletaEntity (id, precio, vendida, funcion_id, silla_id, usuario_id) values (0, 20.23, 1, 0, 0, 0);

insert into BoletaEntity (id, precio, vendida) values (1, 10.23, 1);

insert into BoletaEntity (id, precio, vendida) values (2, 30.01, 1);
insert into EnvioEntity (id, direccion, boleta_id) values (1,'Calle 43 #7-26 - Aprt:101', 2);
insert into EnvioEntity (id, direccion) values (2,'Calle 43 #7-26 - Aprt:101');

insert into BoletaEntity (id, precio, vendida) values (3, 10.00, 1);
insert into ReembolsoEntity(id, valor, boleta_id) values (1, 100.00, 3);
insert into ReembolsoEntity(id, valor) values (2, 100.00);

insert into BoletaEntity(id, precio, vendida) values (4, 55, 1);
insert into ComentarioEntity(id, comentario, fecha, espectaculo_id, boleta_id) values (1, 'ajá tu qué', '2017-12-4', null, 4);
insert into ComentarioEntity(id, comentario, fecha) values (2, 'ajá tu qué', '2017-12-4');


insert into OrganizadorEntity(id, imagen, nombreEmpresa) values (0, 'http://www.linuxpreview.org/wp-content/uploads/2016/12/predicciones-linux-300x200.jpg', 'Tu, NO OTRA VEZ');

insert into OrganizadorEntity(id, imagen, nombreEmpresa) values (1, 'http://www.linuxpreview.org/wp-content/uploads/2016/12/linux-kernel-4-9-300x162.jpg', 'Khè hace');
insert into OrganizadorEntity(id, imagen, nombreEmpresa) values (2, 'https://experienciadigital.es/wp-content/uploads/2017/08/linux-696x422.jpg', 'ECHEVERRY');
insert into EspectaculoEntity(id, descripcion, nombre) values (0, 'aloja', 'barrannquilla');
insert into EspectaculoEntity(id, descripcion, nombre) values (1, 'erda', 'erdax2');

insert into ESPECTACULOENTITY_ORGANIZADORENTITY (ESPECTACULOS_ID, ORGANIZADOR_ID) values (0, 1);
insert into ESPECTACULOENTITY_ORGANIZADORENTITY (ESPECTACULOS_ID, ORGANIZADOR_ID) values (1, 2);

 /* Jeison (100-199)**/

/*---------Usuarios---------**/
insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) 
values (100, 'jm.contreras10', '123456789', 'Jeison Contreras', 'jm.contreras10@uniandes.edu.co', 'Colombia', 'Bogotá',1,'');

insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) 
values (101, 'sbaldosa', '123456789', 'Santiago Baldosa', 'sbaldosa@unichile.edu.ch', 'Chile', 'Santiago',0,'');

insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) 
values (102, 'am.valero10', '123456789', 'Angelo Valero', 'am.valero@uniandes.edu.co', 'Colombia', 'Bogotá',0,'');

insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) 
values (103, 'fc.alvarez10', '123456789', 'Fabian Alvarez', 'fc.alvarez@uniandes.edu.co', 'Colombia', 'Bogotá',0,'');

insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) 
values (104, 'ja.gomez1', '123456789', 'Jorge Gomez', 'ja.gomez1@uniandes.edu.co', 'Colombia', 'Bogotá',1,'');

insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) 
values (105, 'jf.ramos', '123456789', 'Felipe Ramos', 'jf.ramos@uniandes.edu.co', 'Colombia', 'Bogotá',0,'');

insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) 
values (106, 'df.riveros11', '123456789', 'Diego', 'df.riveros11@uniandes.edu.co', 'Colombia', 'Bogotá',1,'');

/*-----Boletas(Compradas)**/
insert into BoletaEntity (id, precio, vendida, usuario_id) values (100, 300.00, 1, 100);
insert into BoletaEntity (id, precio, vendida, usuario_id) values (101, 200.00, 1, 101);
insert into BoletaEntity (id, precio, vendida, usuario_id) values (102, 300.00, 1,102);
insert into BoletaEntity (id, precio, vendida, usuario_id) values (103, 400.00, 1, 102);
insert into BoletaEntity (id, precio, vendida, usuario_id) values (104, 500.00, 1, 103);
insert into BoletaEntity (id, precio, vendida, usuario_id) values (105, 600.00, 1, 105);
insert into BoletaEntity (id, precio, vendida, usuario_id) values (106, 100.00, 1, 104);
/*-----Boletas(Reembolsadas)-----**/
insert into BoletaEntity (id, precio, vendida, usuario_id) values (107, 200.00, 1, 104);
insert into BoletaEntity (id, precio, vendida, usuario_id) values (108, 300.00, 1,106);
/*-----Boletas(Sin comprar)**/
insert into BoletaEntity (id, precio, vendida) values (109, 300.00, 1);
insert into BoletaEntity (id, precio, vendida) values (110, 300.00, 1);
/*------Reembolsos**/
insert into ReembolsoEntity(id, valor, boleta_id, usuario_id) values (100, 300.00, 107, 104);
insert into ReembolsoEntity(id, valor, boleta_id, usuario_id) values (101, 10.00, 108, 106);

 /* Fabian (200-299)**/

insert into DivisionDeLugarEntity (id,nombre) values (200,'Noroccidental alta');
insert into DivisionDeLugarEntity (id,nombre) values (210,'Suroccidental alta');
insert into DivisionDeLugarEntity (id,nombre) values (220,'Noroccidental baja');
insert into DivisionDeLugarEntity (id,nombre) values (230,'Suroccidental baja');
insert into DivisionDeLugarEntity (id,nombre) values (240,'VIP');

insert into SillaEntity (id,costo,division_id) values (200,20.1,200);
insert into SillaEntity (id,costo,division_id) values (210,260.5,210);
insert into SillaEntity (id,costo,division_id) values (220,30.8,220);
insert into SillaEntity (id,costo,division_id) values (230,1000.5,230);

insert into BoletaEntity (id, precio, vendida, silla_id) values (200, 98000, 0, 200);
insert into BoletaEntity (id, precio, vendida, silla_id) values (210, 34000, 1, 210);
insert into BoletaEntity (id, precio, vendida, silla_id) values (220, 645000, 0, 220);
insert into BoletaEntity (id, precio, vendida, silla_id) values (230, 75000, 1, 230);

/* Jorge (300-399) */
insert into LugarEntity (id, direccion, ubicacion, tipo) values (300,'Teatro GranColombia', 'Bogota', 'TEATRO');
insert into LugarEntity (id, direccion, ubicacion, tipo) values (301,'Teatro Chico', 'Bogota', 'TEATRO');
insert into LugarEntity (id, direccion, ubicacion, tipo) values (302,'Teatro Manuela Beltran', 'Bogota', 'TEATRO');
insert into LugarEntity (id, direccion, ubicacion, tipo) values (303,'Friends Stadium', 'Switzerland', 'ESTADIO');

insert into DivisionDeLugarEntity (id, nombre) values (310, 'Preferencial');
insert into DivisionDeLugarEntity (id, nombre, lugar_id) values (312, 'Regular', 301);
insert into DivisionDeLugarEntity (id, nombre, lugar_id) values (314, 'Preferencial', 302);
insert into DivisionDeLugarEntity (id, nombre, lugar_id) values (316, 'Regular', 303);

insert into EspectaculoEntity(id, descripcion, nombre) values (330, 'Para toda la familia', 'Circus');

insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id) values (320, '10/10/2014', '3:00 pm', 330, 300);
insert into FuncionEntity (id, fecha, hora, lugar_id) values (321, '10/10/2014', '8:00 pm', 301);
insert into FuncionEntity (id, fecha, hora, espectaculo_id) values (322, '10/10/2014', '4:45 pm', 330);
insert into FuncionEntity (id, fecha, hora) values (323, '10/10/2014', '4:45 pm');

insert into BoletaEntity (id, precio, vendida, funcion_id) values (340, 100.00, 0, 320);
insert into BoletaEntity (id, precio, vendida) values (341, 120.00, 0);




 /* Angelo (400-499)**/
insert into LugarEntity (id, direccion, ubicacion, tipo) values (400,'Teatro Jorge Eliecer Gaitan', 'Bogota', 'TEATRO');

insert into SillaEntity (id, costo) values (400, 48000);
insert into SillaEntity (id, costo) values (401, 49000);
insert into SillaEntity (id, costo) values (402, 20000);
insert into SillaEntity (id, costo) values (403, 35000);

insert into EspectaculoEntity(id, descripcion, nombre) values (400, 'Alci Acosta llega a Bogotá después de su exitosa presentación en Quito,  un espectáculo de gran acogida por parte de sus seguidores y amigos.' , 'Alci Acosta Sinfónico');
insert into EspectaculoEntity(id, descripcion, nombre) values (401, 'invitación al epicentro mismo de Vetusta Morla para asistir en primera persona a una reafirmación de su esencia', 'VETUSTA MORLA');

insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id) values (400, '11/11/2017', '3:00 pm', 400, 400);
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id) values (401, '12/11/2017', '4:00 pm', 401, 400);

insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) values (400, 'sasha11', '12345', 'Sasha', 'Sasha11@domain.onion', 'Colombia', 'Bogota',0,'');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) values (401, 'fernanda11', '123', 'Fer', 'Fer11@domain.onion', 'Colombia', 'Bogota',0,'');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) values (402, 'miyu11', '12', 'miyu', 'miyu11@domain.onion', 'Colombia', 'Bogota',0,'');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) values (403, 'julian12', '1234', 'juliancho', 'juliancho12@domain.onion', 'Colombia', 'Bogota',0,'');

insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id) values (400, 98000, 1, 400,400,400);
insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id) values (401, 99000, 1, 401,401,401);
insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id) values (402, 65000, 1, 402,402,400);
insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id) values (403, 75000, 1, 403,403,401);

insert into EnvioEntity (id, direccion, boleta_id, imagen) values (400,'Calle 43 #7-26 - Aprt:101',403,'http://lorempixel.com/400/200/transport/1');
insert into EnvioEntity (id, direccion, boleta_id, imagen) values (401, 'Cra 68i #26.48 - Casa',402,'http://lorempixel.com/400/200/transport/2');
insert into EnvioEntity (id, direccion, boleta_id, imagen) values (402,'Cl. 8 Sur #71d:20',401,'http://lorempixel.com/400/200/transport/3');
insert into EnvioEntity (id, direccion, boleta_id, imagen) values (403, 'Carrera 43 # 70A-50 SUR',400,'http://lorempixel.com/400/200/transport/4');

insert into ReembolsoEntity(id, valor, boleta_id) values (400, 1000, 400);

insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (400,'Muy buen espectaculo','10/10/2017',400,400,'http://lorempixel.com/400/200/nightlife/1');
insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (401,'horror de evento','10/31/2017',401,401,'http://lorempixel.com/400/200/nightlife/2');
insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (402,'me gusto mucho asistir','11/01/1989',400,402,'http://lorempixel.com/400/200/nightlife/3');
insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (403,'me senti muy bien en el evento','06/20/2017',401,403,'http://lorempixel.com/400/200/nightlife/4');


