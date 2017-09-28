delete from ReembolsoEntity;
delete from EnvioEntity;
delete from BoletaEntity;
delete from OrganizadorEntity;
delete from ComentarioEntity;
delete from FuncionEntity;
delete from EspectaculoEntity;
delete from ArtistaEntity;
delete from DivisionDeLugarEntity;
delete from SillaEntity;
delete from LugarEntity;
delete from EspectaculoEntity_OrganizadorEntity;
delete from UsuarioEntity;

insert into BoletaEntity (id, precio, vendida) values (2, 20.23, 1);


insert into OrganizadorEntity (id, nombreEmpresa) values (1, 'Yo');
insert into OrganizadorEntity (id, nombreEmpresa) values (2, 'YoYQue');
insert into OrganizadorEntity (id, nombreEmpresa) values (3, 'DondeEstoy');
insert into OrganizadorEntity (id, nombreEmpresa) values (4, 'TuOtraVez');

insert into EspectaculoEntity (id, nombre, descripcion) values (1, 'Septimo dia', 'Espectaculo de Cirque Du Soleil junto a Soda Estereo');
insert into EspectaculoEntity (id, nombre, descripcion) values (2, 'P.A.R.C.E', 'Gira de Juanes pro Colombia');
insert into EspectaculoEntity (id, nombre, descripcion) values (3, 'Concierto Shakira', 'Concierto');
insert into EspectaculoEntity (id, nombre, descripcion) values (4, 'Gira Portugal The Man', 'Concierto');

insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id,organizador_id) values (1,1);
insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id,organizador_id) values (3,1); 
insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id,organizador_id) values (4,3);
insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id,organizador_id) values (1,3); 

insert into EnvioEntity (id, direccion) values (100,'Calle 43 #7-26 - Aprt:101');
insert into EnvioEntity (id, direccion) values (200, 'Cra 68i #26.48 - Casa');
insert into EnvioEntity (id, direccion) values (300,'Cl. 8 Sur #71d:20');
insert into EnvioEntity (id, direccion) values (400, 'Carrera 43 # 70A-50 SUR');

insert into ComentarioEntity (id,comentario,fecha) values (100,'Muy buen espectaculo','10/10/2017');
insert into ComentarioEntity (id,comentario,fecha) values (200,'horror de evento','10/31/2017');
insert into ComentarioEntity (id,comentario,fecha) values (300,'me gusto mucho asistir','11/01/1989');
insert into ComentarioEntity (id,comentario,fecha) values (400,'me senti muy bien en el evento','06/20/2017');

insert into DivisionDeLugarEntity (id,nombre) values (10,'Noroccidental alta');
insert into DivisionDeLugarEntity (id,nombre) values (20,'Suroccidental alta');
insert into DivisionDeLugarEntity (id,nombre) values (30,'Noroccidental baja');
insert into DivisionDeLugarEntity (id,nombre) values (40,'Suroccidental baja');
insert into DivisionDeLugarEntity (id,nombre) values (50,'VIP');

insert into SillaEntity (id,costo) values (50,20.1);
insert into SillaEntity (id,costo) values (40,260.5);
insert into SillaEntity (id,costo) values (30,30.8);
insert into SillaEntity (id,costo) values (20,1000.5);

insert into LugarEntity (id,tipo) values (1,'CASA');
insert into LugarEntity (id,tipo) values (2,'APTO');
insert into LugarEntity (id,tipo) values (3,'TEATRO');
insert into LugarEntity (id,tipo) values (4,'FINCA');

insert into FuncionEntity (id,fecha,lugar_id) values (1,'10/10/2017',1);
insert into FuncionEntity (id,fecha,lugar_id) values (2,'10/10/2016',1);
insert into FuncionEntity (id,fecha,lugar_id) values (4,'10/10/2014',2);

insert into ArtistaEntity (id, nombre) values (1, 'Juanes');
insert into ArtistaEntity (id, nombre) values (2, 'Shakira');
insert into ArtistaEntity (id, nombre) values (3, 'Cirque du Soleil');
insert into ArtistaEntity (id, nombre) values (4, 'Portugal The Man');


insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (1, 'aa', '123', 'yo', 'email', 'pais', 'ciudad');
insert into BoletaEntity (id, precio, vendida, usuario_id, funcion_id, silla_id) values (1, 100.23, 0, 1, 1, 50);

insert into BoletaEntity (id, precio, vendida) values (3, 0.23, 0);
insert into EnvioEntity (id, direccion, boleta_id) values (1,'Calle 43 #7-26 - Aprt:101', 3);

insert into BoletaEntity (id, precio, vendida) values (4, 1.00, 1);
insert into ReembolsoEntity(id, valor, boleta_id) values (1, 100.00, 4);

insert into EspectaculoEntity (id, nombre, descripcion) values (1, 'Septimo dia', 'Espectaculo de Cirque Du Soleil junto a Soda Estereo');
insert into EspectaculoEntity (id, nombre, descripcion) values (2, 'P.A.R.C.E', 'Gira de Juanes pro Colombia');
insert into EspectaculoEntity (id, nombre, descripcion) values (3, 'Concierto Shakira', 'Concierto');
insert into EspectaculoEntity (id, nombre, descripcion) values (4, 'Gira Portugal The Man', 'Concierto');

insert into FuncionEntity (id, fecha, espectaculo_id) values (3,'10/10/2014',2);

>
