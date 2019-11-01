delete from ReembolsoEntity;
delete from EnvioEntity;
delete from OrganizadorEntity;
delete from ComentarioEntity;
delete from BoletaEntity;
delete from FuncionEntity;
delete from EspectaculoEntity_OrganizadorEntity;
delete from EspectaculoEntity_ArtistaEntity;
delete from EspectaculoEntity;
delete from ArtistaEntity;
delete from SillaEntity;
delete from DivisionDeLugarEntity;
delete from LugarEntity;
delete from UsuarioEntity;



insert into BoletaEntity (id, precio, vendida) values (2, 20.23, 1);


insert into OrganizadorEntity (id, nombreEmpresa) values (1, 'Yo');
insert into OrganizadorEntity (id, nombreEmpresa) values (2, 'YoYQue');
insert into OrganizadorEntity (id, nombreEmpresa) values (3, 'DondeEstoy');
insert into OrganizadorEntity (id, nombreEmpresa) values (4, 'TuOtraVez');

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

insert into SillaEntity (id,costo,division_id) values (50,20.1,10);
insert into SillaEntity (id,costo,division_id) values (40,260.5,20);
insert into SillaEntity (id,costo,division_id) values (30,30.8,30);
insert into SillaEntity (id,costo,division_id) values (20,1000.5,20);

insert into LugarEntity (id,tipo) values (1,'CASA');
insert into LugarEntity (id,tipo) values (2,'APTO');
insert into LugarEntity (id,tipo) values (3,'TEATRO');
insert into LugarEntity (id,tipo) values (4,'FINCA');

insert into FuncionEntity (id,fecha,lugar_id) values (1,'10/10/2017',1);
insert into FuncionEntity (id,fecha,lugar_id) values (2,'10/10/2016',1);
insert into FuncionEntity (id,fecha,lugar_id) values (4,'10/10/2014',2);

insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (1, 'aa', '123', 'yo', 'email', 'pais', 'ciudad');
insert into BoletaEntity (id, precio, vendida, usuario_id, funcion_id, silla_id) values (1, 100.23, 0, 1, 1, 50);

insert into BoletaEntity (id, precio, vendida) values (3, 0.23, 0);
insert into EnvioEntity (id, direccion, boleta_id) values (1,'Calle 43 #7-26 - Aprt:101', 3);

insert into BoletaEntity (id, precio, vendida) values (4, 1.00, 1);
insert into ReembolsoEntity(id, valor, boleta_id) values (1, 100.00, 4);

/*=====================================================================Entidad Usuario=========================================================*/
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (69, 'hs.hernandez', 'socketame123', 'Hugo', 'hs.hernandez@domain.onion', 'Argentina', 'newYork');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (195, 'mcoils0', 'Bl42q8FjKH', 'Maddy', 'mlaimable0@last.fm', 'Zimbabwe', 'Insiza');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (323, 'dandriveau0', 'Vhnm0Q', 'Dar', 'dwebling0@wordpress.com', 'Philippines', 'Kalandagan');
/*=====================================================================Entidad Reembolso =========================================================*/
insert into ReembolsoEntity(id, valor) values (52, 30000.00);
insert into ReembolsoEntity(id, valor) values (45245, 1550000.00);
insert into ReembolsoEntity(id, valor) values (453452786, 340000.00);
/*=====================================================================Entidad Usuario=========================================================*/
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (100, 'miUsuario', 'niClave', 'miNombre', 'miEmail', 'miPais', 'miCiudad');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (852, 'miUsuario.10', 'niClave', 'miNombrex1', 'miEmail@domain.com', 'miPaisA', 'miCiudadA');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (7865782, 'miUsuario.50', 'niClave', 'miNombrex2', 'miEmailx@domain.onion', 'miPaisB', 'miCiudadB');

insert into BoletaEntity (id, precio, vendida, usuario_id, funcion_id, silla_id) values (1995, 20000, 0, 1, 1, 50);
insert into BoletaEntity (id, precio, vendida, usuario_id, funcion_id, silla_id) values (2001, 30000, 0, 1, 1, 40);

insert into ReembolsoEntity(id, valor) values (8, 30.00);
insert into ReembolsoEntity(id, valor) values (520, 150.00);
insert into ReembolsoEntity(id, valor) values (54204, 340.00);
/*=====================================================================================================================================================*/
insert into BoletaEntity (id, precio, vendida) values (90, 98000, 0);
insert into ComentarioEntity (id,comentario,fecha, boleta_id) values (1,'Final inesperado!! :O','10/10/2017',90);

insert into DivisionDeLugarEntity(id, nombre) values (60,'palcos');
insert into SillaEntity( id, costo, division_id) values (150, 2.0,60);


insert into DivisionDeLugarEntity(id, nombre) values (70,'palcos 2');
insert into SillaEntity( id, costo, division_id) values (160,8.76,70);

insert into SillaEntity(id, costo) values (170, 30.21);
insert into SillaEntity(id, costo) values (180, 560.21);
insert into BoletaEntity (id, precio, vendida, silla_id) values (1560, 98000, 0, 170);
insert into BoletaEntity (id, precio, vendida, silla_id) values (1400, 98000, 0, 180);

/*=====================================================Relacion Usuario-Boleta==========================================================================*/
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (103, 'usuarioAsignado', 'claveAsignada', 'nombreAsignado', 'emailAsignado', 'paisAsignado', 'ciudadAsignada');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (102, 'userabc', 'claveabc','nombreAbc', 'emailABC', 'paisAbc', 'ciudadAbc');

insert into BoletaEntity (id, precio, vendida,usuario_id) values (45, 1.00, 1,103);
insert into BoletaEntity (id, precio, vendida,usuario_id) values (25, 1.00, 1,103);
insert into BoletaEntity (id, precio, vendida,usuario_id) values (103, 1.00, 1,102);
/*======================================================================================================================================================*/

insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (104, 'aaa', 'aa1', 'nombreAsignado', 'emailAsignado', 'paisAsignado', 'ciudadAsignada');
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad) values (105, 'bbb', 'aa11','nombreAbc', 'emailABC', 'paisAbc', 'ciudadAbc');
insert into ReembolsoEntity(id, valor, usuario_id) values (105, 30.00, 104);

insert into EspectaculoEntity (id, nombre, descripcion) values (1, 'Septimo dia', 'Espectaculo de Cirque Du Soleil junto a Soda Estereo');
insert into EspectaculoEntity (id, nombre, descripcion) values (2, 'P.A.R.C.E', 'Gira de Juanes por Colombia');
insert into EspectaculoEntity (id, nombre, descripcion) values (3, 'Concierto Shakira', 'Concierto');
insert into EspectaculoEntity (id, nombre, descripcion) values (4, 'Gira Portugal The Man', 'Concierto');

insert into ArtistaEntity (id, nombre) values (1, 'Juanes');
insert into ArtistaEntity (id, nombre) values (2, 'Shakira');
insert into ArtistaEntity (id, nombre) values (3, 'Cirque du Soleil');
insert into ArtistaEntity (id, nombre) values (4, 'Portugal The Man');

insert into FuncionEntity (id, fecha, espectaculo_id) values (3,'10/10/2014',2);

