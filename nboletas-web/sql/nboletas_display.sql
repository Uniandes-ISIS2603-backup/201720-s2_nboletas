delete from ReembolsoEntity;
delete from EnvioEntity;
delete from ComentarioEntity;
delete from BoletaEntity;
delete from OrganizadorEntity;
delete from FuncionEntity;
delete from EspectaculoEntity_ArtistaEntity;
delete from EspectaculoEntity_OrganizadorEntity;
delete from EspectaculoEntity;
delete from ArtistaEntity;
delete from SillaEntity;
delete from DivisionDeLugarEntity;
delete from LugarEntity;
delete from EspectaculoEntity_OrganizadorEntity;
delete from UsuarioEntity;

insert into EspectaculoEntity (id, nombre, descripcion, imagen) values (1, 'Septimo dia', 'Espectaculo de Cirque Du Soleil junto a Soda Estereo', 'https://puntoticket-a.akamaihd.net/especiales/septimo-dia/img/septimo-dia.jpg');
insert into EspectaculoEntity (id, nombre, descripcion, imagen) values (2, 'P.A.R.C.E', 'Gira de Juanes por Colombia', 'http://images.coveralia.com/audio/j/Juanes-P_A_R_C_E_(Deluxe_Edition)-Frontal.jpg');
insert into EspectaculoEntity (id, nombre, descripcion, imagen) values (3, 'Concierto Shakira', 'Concierto', 'https://www.music-bazaar.com/album-images/vol8/461/461159/2278937-big/Shakira-Live-From-Paris-cover.jpg');
insert into EspectaculoEntity (id, nombre, descripcion, imagen) values (4, 'Gira Portugal The Man', 'Concierto', 'http://revistakuadro.com/wp-content/uploads/2015/03/b9185c948d.jpg');

insert into ArtistaEntity (id, nombre, imagen) values (1, 'Juanes', 'http://d3iln1l77n73l7.cloudfront.net/couch_images/attachments/000/083/784/original/JUANES.jpg?2016');
insert into ArtistaEntity (id, nombre, imagen) values (2, 'Shakira', 'http://assets.tiempo.com.mx/uploads/imagen/imagen/134341/Esto_delatari_a_el_fin_de_la_relacio_n_de_Shakira_y_Pique_.jpg');
insert into ArtistaEntity (id, nombre, imagen) values (3, 'Cirque du Soleil', 'https://www.cirquedusoleil.com/-/media/past-shows/assets/img/iris/slider/iris-act-kiriki.jpg');
insert into ArtistaEntity (id, nombre, imagen) values (4, 'Portugal The Man', 'http://cientounorevista.com/wp-content/uploads/2017/07/portugal_the_man_facebook.jpg');

insert into EspectaculoEntity_ArtistaEntity (artista_id, espectaculos_id) values (1,3);
insert into EspectaculoEntity_ArtistaEntity (artista_id, espectaculos_id) values (2,1);
insert into EspectaculoEntity_ArtistaEntity (artista_id, espectaculos_id) values (3,2);
insert into EspectaculoEntity_ArtistaEntity (artista_id, espectaculos_id) values (4,4);

insert into LugarEntity (id, direccion, ubicacion, tipo, imagen) values (1,'Coliseo El Campin', 'Bogota', 'COLISEO', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Bogota_Coliseo_El_Camp%C3%ADn.JPG/1200px-Bogota_Coliseo_El_Camp%C3%ADn.JPG');
insert into LugarEntity (id, direccion, ubicacion, tipo, imagen) values (2,'Teatro Nacional La Casellana', 'Bogota', 'TEATRO', 'https://res.cloudinary.com/civico/image/upload/c_fit,f_auto,fl_lossy,h_1200,q_auto:low,w_1200/v1457113325/entity/image/file/001/000/56d9c8ec820c65dcc6000001.jpg');
insert into LugarEntity (id, direccion, ubicacion, tipo, imagen) values (3,'Plaza de Bolivar', 'Bogota', 'PLAZA', 'https://upload.wikimedia.org/wikipedia/commons/5/56/Panor%C3%A1mica_Plaza_de_Bol%C3%ADvar_Bogot%C3%A1.jpg');

insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (1, '10/10/2017', '10:00 pm', 1, 1, 'https://www.bogotaesteatro.com/wp-content/uploads/2016/11/TEATRO-NACIONAL-LA-CASTELLANA.jpg');
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (2, '02/02/2018', '10:00 pm', 1, 1, 'https://www.shbarcelona.com/blog/en/wp-content/uploads/2015/03/Don%E2%80%99t-Miss-the-Opera-at-Gran-Teatre-del-Liceu-810x502.jpg');
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (3, '10/10/2017', '9:00 pm', 2, 3, 'http://static.wixstatic.com/media/415ec6_942c1831cc5f489f844b16d3e81bbd61.jpg/v1/fill/w_924,h_522,al_c,q_85,usm_0.66_1.00_0.01/415ec6_942c1831cc5f489f844b16d3e81bbd61.webp');
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (4, '02/02/2018', '9:00 pm', 2, 3, 'https://www.somosmalasana.com/wp-content/uploads/2013/07/IMG_7699.jpg');
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (5, '10/10/2017', '8:45 pm', 3, 3, 'https://scontent-sea1-1.cdninstagram.com/t51.2885-15/e35/16584831_1531369250209874_1119400938423975936_n.jpg?ig_cache_key=MTQ0ODcyMjk5MDY0NzkzMDM2MQ%3D%3D.2');
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (6, '02/02/2018', '8:45 pm', 3, 3, 'http://www.wonderlandgroup.es/wp-content/uploads/2013/04/teatro-caser-calderon1.jpg');
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (7, '10/10/2017', '8:45 pm', 4, 2, 'http://sanjuanciudadpatria.com/wp-content/uploads/2014/01/arte-15-Teatro-Tapia-1-800x514.jpg');
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (8, '02/02/2018', '8:45 pm', 4, 2, 'https://c1.staticflickr.com/4/3317/3316693590_f635a35a38_b.jpg');

insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad, imagen,admon) values (1, 'Fercho2715', '1234', 'Fernando', 'fercho2715@domain.onion', 'Colombia', 'Bogota', 'https://tmssl.akamaized.net//images/portrait/originals/3455-1450105043.jpg',0);
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad, imagen,admon) values (2, 'Lula11', '1234', 'Luisa', 'lula11@domain.onion', 'Colombia', 'Bogota', 'http://ru.fishki.net/picsw/102012/17/post/zombi/zombi-0003.jpg',0);
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad, imagen,admon) values (3, 'AlinoxThe3', '1234', 'Andres', 'alinoxthe3@domain.onion', 'Colombia', 'Bogota', 'https://2.bp.blogspot.com/-QnLlhx6McUU/VFqnbqWe2OI/AAAAAAAAnJ8/l5Vq0cfyYKc/s1600/frente%2Bgrande34.jpg',0);
insert into UsuarioEntity (id, userName, password, nombreUsuario, email, pais, ciudad, imagen,admon) values (4, 'Cubosx', '1234', 'Cubillos', 'cubosx@domain.onion', 'Colombia', 'Bogota', 'http://www.revistanos.cl/wp-content/uploads/2010/09/cubillos-IMG_0169.jpg',0);

insert into DivisionDeLugarEntity (id, nombre, lugar_id, imagen) values (1, 'Preferencial Norte', 1, 'http://backoffice.ticketbureau.org/static/imagenes/imagen/21365_orig.png');
insert into DivisionDeLugarEntity (id, nombre, lugar_id, imagen) values (2, 'Preferencial Sur', 2, 'https://images.tickethoy.com/images/esquemas/138/big0000.jpg');
insert into DivisionDeLugarEntity (id, nombre, lugar_id, imagen) values (3, 'Preferencial ', 3, 'https://1.bp.blogspot.com/-NKPX_On_BDM/WNgePl5x2yI/AAAAAAAAMw0/0-bJ-ZDTrPwHS2oMRh-av35XYrS2oukKQCLcB/s1600/mapa-estereo-picinic-2017.jpg');
insert into DivisionDeLugarEntity (id, nombre, lugar_id, imagen) values (4, 'Regular', 1, 'http://www.tuzonaelite.com/wp-content/uploads/2013/03/2.jpg');
insert into DivisionDeLugarEntity (id, nombre, lugar_id, imagen) values (5, 'Regular', 2, 'http://cde.peru21.pe/ima/0/0/4/6/4/464278.jpg');
insert into DivisionDeLugarEntity (id, nombre, lugar_id, imagen) values (6, 'Regular', 3, 'https://i1.wp.com/adondeirhoy.com/wp-content/uploads/2014/08/PLANO-RODEO-3-Estadi630836.jpg?ssl=1');

insert into SillaEntity (id, costo, division_id, imagen) values (1, 48000, 1, 'https://image.made-in-china.com/44f3j00wYUabfuHJPcJ/Theater-VIP-Cinema-Lecture-Hall-Seat-Auditorium-Chair-HX-WH521-.jpg');
insert into SillaEntity (id, costo, division_id, imagen) values (2, 49000, 1, 'https://co.all.biz/img/co/catalog/11899.jpeg');
insert into SillaEntity (id, costo, division_id, imagen) values (3, 20000, 2, 'https://intermueblespacios.com/wp-content/uploads/2017/05/Butaca-Roma-estadios.png');
insert into SillaEntity (id, costo, division_id, imagen) values (4, 35000, 2, 'http://www.infodeportes.com/fotos/2011/9/103.4633.2.399.250.jpg');
insert into SillaEntity (id, costo, division_id, imagen) values (5, 48000, 3, 'https://intermueblespacios.com/wp-content/uploads/2017/05/Butaca-Roma-estadios.png');
insert into SillaEntity (id, costo, division_id, imagen) values (6, 49000, 3, 'https://image.made-in-china.com/44f3j00wYUabfuHJPcJ/Theater-VIP-Cinema-Lecture-Hall-Seat-Auditorium-Chair-HX-WH521-.jpg');
insert into SillaEntity (id, costo, division_id, imagen) values (7, 20000, 4, 'https://intermueblespacios.com/wp-content/uploads/2017/05/Butaca-Roma-estadios.png');
insert into SillaEntity (id, costo, division_id, imagen) values (8, 35000, 4, 'https://co.all.biz/img/co/catalog/11899.jpeg');
insert into SillaEntity (id, costo, division_id, imagen) values (9, 48000, 5, 'https://image.made-in-china.com/44f3j00wYUabfuHJPcJ/Theater-VIP-Cinema-Lecture-Hall-Seat-Auditorium-Chair-HX-WH521-.jpg');
insert into SillaEntity (id, costo, division_id, imagen) values (10, 49000, 5, 'http://www.infodeportes.com/fotos/2011/9/103.4633.2.399.250.jpg');
insert into SillaEntity (id, costo, division_id, imagen) values (11, 20000, 6, 'https://co.all.biz/img/co/catalog/11899.jpeg');
insert into SillaEntity (id, costo, division_id, imagen) values (12, 35000, 6, 'https://intermueblespacios.com/wp-content/uploads/2017/05/Butaca-Roma-estadios.png');

insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id, imagen) values (1, 98000, 0, 1,1,1, 'https://marketingland.com/wp-content/ml-loads/2016/04/sp-tickets-600x393.png');
insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id, imagen) values (2, 99000, 0, 2,2,3, 'https://marketingland.com/wp-content/ml-loads/2016/04/sp-tickets-600x393.png');
insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id, imagen) values (3, 65000, 0, 3,3,5, 'https://marketingland.com/wp-content/ml-loads/2016/04/sp-tickets-600x393.png');
insert into BoletaEntity (id, precio, vendida, silla_id,usuario_id,funcion_id, imagen) values (4, 75000, 0, 4,4,7, 'https://marketingland.com/wp-content/ml-loads/2016/04/sp-tickets-600x393.png');

insert into OrganizadorEntity (id, imagen, nombreEmpresa) values (1, 'http://www.linuxpreview.org/wp-content/uploads/2016/12/predicciones-linux-300x200.jpg', 'INC');
insert into OrganizadorEntity (id, imagen, nombreEmpresa) values (2, 'http://www.linuxpreview.org/wp-content/uploads/2016/12/linux-kernel-4-9-300x162.jpg', 'BBC');
insert into OrganizadorEntity (id, imagen, nombreEmpresa) values (3, 'https://experienciadigital.es/wp-content/uploads/2017/08/linux-696x422.jpg', 'Network All');
insert into OrganizadorEntity (id, imagen, nombreEmpresa) values (4, 'https://www.giftcardsonsale.com.au/wp-content/uploads/2010/03/thegoodguys.jpg', 'The Good Guys');

insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id, organizador_id) values (1,1);
insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id, organizador_id) values (2,2);
insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id, organizador_id) values (3,3);
insert into EspectaculoEntity_OrganizadorEntity (espectaculos_id, organizador_id) values (4,4);

insert into ReembolsoEntity(id, valor, boleta_id, usuario_id, imagen) values (1, 300.00, 1, 1, 'https://st2.depositphotos.com/5266903/7877/v/950/depositphotos_78778914-stock-illustration-refund-icon-from-business-bicolor.jpg');
insert into ReembolsoEntity(id, valor, boleta_id, usuario_id, imagen) values (2, 10.00, 2, 2, 'https://st2.depositphotos.com/5266903/7877/v/950/depositphotos_78778914-stock-illustration-refund-icon-from-business-bicolor.jpg');
insert into ReembolsoEntity(id, valor, boleta_id, usuario_id, imagen) values (3, 300.00, 3, 3, 'https://st2.depositphotos.com/5266903/7877/v/950/depositphotos_78778914-stock-illustration-refund-icon-from-business-bicolor.jpg');
insert into ReembolsoEntity(id, valor, boleta_id, usuario_id, imagen) values (4, 10.00, 4, 4, 'https://st2.depositphotos.com/5266903/7877/v/950/depositphotos_78778914-stock-illustration-refund-icon-from-business-bicolor.jpg');

insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (1,'Muy buen espectaculo','10/10/2017',1,1, 'https://image.freepik.com/iconos-gratis/comentario-ios-7-simbolo-interfaz_318-33559.jpg');
insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (2,'horror de evento','10/31/2017',2,2, 'https://image.freepik.com/iconos-gratis/comentario-ios-7-simbolo-interfaz_318-33559.jpg');
insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (3,'me gusto mucho asistir','11/01/1989',3,3, 'https://image.freepik.com/iconos-gratis/comentario-ios-7-simbolo-interfaz_318-33559.jpg');
insert into ComentarioEntity (id,comentario,fecha, espectaculo_id, boleta_id, imagen) values (4,'me senti muy bien en el evento','06/20/2017',4,4, 'https://image.freepik.com/iconos-gratis/comentario-ios-7-simbolo-interfaz_318-33559.jpg');

insert into EnvioEntity (id, direccion, boleta_id, imagen) values (1,'Calle 43 #7-26 - Aprt:101',1, 'https://4.bp.blogspot.com/-Lg-PQp7eKpQ/UON9lZXShjI/AAAAAAAAAsA/wptqs7RwPjI/s1600/urna2.jpg');
insert into EnvioEntity (id, direccion, boleta_id, imagen) values (2, 'Cra 68i #26.48 - Casa',2, 'https://4.bp.blogspot.com/-Lg-PQp7eKpQ/UON9lZXShjI/AAAAAAAAAsA/wptqs7RwPjI/s1600/urna2.jpg');
insert into EnvioEntity (id, direccion, boleta_id, imagen) values (3,'Cl. 8 Sur #71d:20',3, 'https://4.bp.blogspot.com/-Lg-PQp7eKpQ/UON9lZXShjI/AAAAAAAAAsA/wptqs7RwPjI/s1600/urna2.jpg');
insert into EnvioEntity (id, direccion, boleta_id, imagen) values (4, 'Carrera 43 # 70A-50 SUR',4, 'https://4.bp.blogspot.com/-Lg-PQp7eKpQ/UON9lZXShjI/AAAAAAAAAsA/wptqs7RwPjI/s1600/urna2.jpg');


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
/*-----Boletas(Sin comprar)**/
insert into BoletaEntity (id, precio, vendida) values (109, 300.00, 1);
insert into BoletaEntity (id, precio, vendida) values (110, 300.00, 1);
/*------Reembolsos**/
insert into ReembolsoEntity(id, valor, boleta_id, usuario_id) values (100, 300.00, 107, 104);

/* Recursos para borrar! **/
insert into FuncionEntity (id, fecha, hora, espectaculo_id, lugar_id, imagen) values (20, '10/10/2017', '10:00 pm', 1, 1, 'https://upload.wikimedia.org/wikipedia/commons/0/05/Palco_TeatroReal.jpg');
insert into OrganizadorEntity (id, imagen, nombreEmpresa) values (20, 'https://vignette.wikia.nocookie.net/criminal-case-grimsborough/images/b/b1/Delete_Icon.png/revision/latest?cb=20141216101607', 'Delete Inc.');
insert into BoletaEntity (id, precio, vendida, silla_id, usuario_id, funcion_id, imagen) values (20, 10000, 1, 1,1,1, 'https://marketingland.com/wp-content/ml-loads/2016/04/sp-tickets-600x393.png');
insert into LugarEntity (id, direccion, ubicacion, tipo, imagen) values (10, 'Parque de la 93', 'Bogota', 'PARQUE', 'http://www.eltiempo.com/contenido///bogota/IMAGEN/IMAGEN-14026597-2.png');
insert into DivisionDeLugarEntity (id, nombre, lugar_id, imagen) values (20, 'Regular', 3, 'https://i1.wp.com/adondeirhoy.com/wp-content/uploads/2014/08/PLANO-RODEO-3-Estadi630836.jpg?ssl=1');
insert into SillaEntity (id, costo, division_id, imagen) values (20, 48000, 1, 'https://image.made-in-china.com/44f3j00wYUabfuHJPcJ/Theater-VIP-Cinema-Lecture-Hall-Seat-Auditorium-Chair-HX-WH521-.jpg');
insert into USUARIOENTITY (id, userName, password, nombreUsuario, email, pais, ciudad,admon,imagen) values (106, 'df.riveros11', '123456789', 'Diego', 'df.riveros11@uniandes.edu.co', 'Colombia', 'Bogotá',1,'');







