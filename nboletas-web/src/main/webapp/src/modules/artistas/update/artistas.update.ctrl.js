
(
        function (ng) {
            var mod = ng.module("artistasModule");
            /** llame los context que necesite para el update**/
            mod.constant("artistasContext", "api/artistas");
            mod.constant("espectaculosContexts", "api/espectaculos");
            mod.controller('artistaUpdateCtrl', ['$scope', '$http', 'artistasContext', 'espectaculosContexts',  '$state', '$rootScope', '$filter',
                function ($scope, $http, artistasContext, espectaculosContexts, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    
                    /** en idsOrganizadores guarda los ids de todos los organizadores **/
                    var idsEspectaculos = [];
                    /** en idsBorrar guarda los ids de los organizadores que va a quitar de la relacion **/
                    var idsBorrar = [];
                    var idArtista = $state.params.artistaId;

   
                    $http.get(artistasContext + '/' + idArtista).then(function (response) {
                        var artista = response.data;
                        $scope.artista.nombre= artista.nombre;
                        $scope.artista.imagen = artista.imagen;
                        
                        /** en este get encuentra los organizadores que tiene referencia al espectaculo **/
                        $http.get(artistasContext + '/' + idArtista + '/espectaculos').then(function(response){
                           $scope.artista.espectaculos = response.data;
                        });
                        $scope.getFiltro();
                    });
                    /** este filtro muestra los organizadores que no tiene relacion con el espectaculo **/
                    $scope.getFiltro = function() {
                    $http.get(espectaculosContexts).then(function (response) {
                    $scope.allEspectaculos = response.data;
                    $scope.artistasEspectaculo = $scope.artista.espectaculos;
                    var filteredBooks = $scope.allEspectaculos.filter(function (allEspectaculos) {
                                return $scope.artistasEspectaculo.filter(function (artistasEspectaculo) {
                                    return artistasEspectaculo.id === allEspectaculos.id;
                                }).length === 0;
                            });

                            $scope.allEspectaculosShow = filteredBooks;
                     });};
                        
                /** esto ya es html 5 y ya esta pedefinido **/
                $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Cuando un book se añade al autor, se almacena su id en el array idsBook
                        idsEspectaculos.push("" + data);
                    };
                        
                $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el book que no se va asociar, por eso se usa el splice que quita el id del book en el array idsBook
                        idsBorrar.push("" + data);
                        var index = idsEspectaculos.indexOf(data);
                        if (index > -1) {
                            idsEspectaculos.splice(index, 1);
                        }
                 };
                /** En este metodo debemos crear y eliminar las relaciones del organizador con el espectaculo**/
                $scope.updateArtista = function () {
                    $scope.newEspectaculos();
                    $http.put(artistasContext + "/" + idArtista,{
                        nombre: $scope.artista.nombre,
                        imagen: $scope.artista.imagen,
                    }).then(function (response) {
                        if (idsEspectaculos.length > 0) {
                            for(var all in $scope.artistasEspectaculo){
                                $http.put(artistasContext + "/" + response.data.id + "/espectaculos/" + $scope.artistasEspectaculo[all].id ,$scope.artistasEspectaculo[all]).then(function (response) {
                                });
                            }
                            }
                        if(idsBorrar.length > 0){
                            for(var leg in idsBorrar){
                                $http.delete(artistasContext + "/" + response.data.id + "/espectaculos/" + parseInt(idsBorrar[leg])).then(function (response) {
                                });
                            }
                        }
                    $state.go('artistasList', {artistaId: response.data.id}, {reload: true});
                });
                };
                /** aca actualizamos los organizadores que van a estar relacionas con el espectaculo **/
                $scope.newEspectaculos = function () {
                        $scope.artistasEspectaculo = [];
                        for (var ite in idsEspectaculos) {
                            for (var all in $scope.allEspectaculos) {
                                if ($scope.allEspectaculos[all].id === parseInt(idsEspectaculos[ite])) {
                                 $scope.artistasEspectaculo.push($scope.allEspectaculos[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(angular);

