
(
        function (ng) {
            var mod = ng.module("espectaculosModule");
            /** llame los context que necesite para el update**/
            mod.constant("espectaculosContext", "api/espectaculos");
            mod.constant("artistasContexts","api/artistas");
            mod.controller('espectaculoUpdateCtrl', ['$scope', '$http', 'espectaculosContext', 'organizadoresContexts',  '$state', '$rootScope', '$filter',
                function ($scope, $http, espectaculosContext, organizadoresContexts, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    
                    /** en idsOrganizadores guarda los ids de todos los organizadores **/
                    var idsOrganizadores = [];
                    /** en idsBorrar guarda los ids de los organizadores que va a quitar de la relacion **/
                    var idsBorrar = [];
                    var idEspectaculo = $state.params.espectaculoId;

   
                    $http.get(espectaculosContext + '/' + idEspectaculo).then(function (response) {
                        var espectaculo = response.data;
                        $scope.espectaculo.nombre= espectaculo.nombre;
                        $scope.espectaculo.imagen = espectaculo.imagen;
                        $scope.espectaculo.descripcion = espectaculo.descripcion;
                        
                        /** en este get encuentra los organizadores que tiene referencia al espectaculo **/
                        $http.get(espectaculosContext + '/' + idEspectaculo + '/organizadores').then(function(response){
                           $scope.espectaculo.organizadores = response.data;
                        });
                        $scope.getFiltro();
                    });
                    /** este filtro muestra los organizadores que no tiene relacion con el espectaculo **/
                    $scope.getFiltro = function() {
                    $http.get(organizadoresContexts).then(function (response) {
                    $scope.allOrganizadores = response.data;
                    $scope.espectaculosOrganizador = $scope.espectaculo.organizadores;
                    var filteredBooks = $scope.allOrganizadores.filter(function (allOrganizadores) {
                                return $scope.espectaculosOrganizador.filter(function (espectaculosOrganizador) {
                                    return espectaculosOrganizador.id === allOrganizadores.id;
                                }).length === 0;
                            });

                            $scope.allOrganizadoresShow = filteredBooks;
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
                        idsOrganizadores.push("" + data);
                    };
                        
                $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el book que no se va asociar, por eso se usa el splice que quita el id del book en el array idsBook
                        idsBorrar.push("" + data);
                        var index = idsOrganizadores.indexOf(data);
                        if (index > -1) {
                            idsOrganizadores.splice(index, 1);
                        }
                 };
                /** En este metodo debemos crear y eliminar las relaciones del organizador con el espectaculo**/
                $scope.updateEspectaculo = function () {
                    $scope.newOrganizadores();
                    $http.put(espectaculosContext + "/" + idEspectaculo,{
                        nombre: $scope.espectaculo.nombre,
                        imagen: $scope.espectaculo.imagen,
                        descripcion: $scope.espectaculo.descripcion
                    }).then(function (response) {
                        if (idsOrganizadores.length > 0) {
                            for(var all in $scope.espectaculosOrganizador){
                                $http.put(espectaculosContext + "/" + response.data.id + "/organizadores/" + $scope.espectaculosOrganizador[all].id ,$scope.espectaculosOrganizador[all]).then(function (response) {
                                });
                            }
                            }
                        if(idsBorrar.length > 0){
                            for(var leg in idsBorrar){
                                $http.delete(espectaculosContext + "/" + response.data.id + "/organizadores/" + parseInt(idsBorrar[leg])).then(function (response) {
                                });
                            }
                        }
                    $state.go('espectaculosList', {espectaculoId: response.data.id}, {reload: true});
                });
                };
                /** aca actualizamos los organizadores que van a estar relacionas con el espectaculo **/
                $scope.newOrganizadores = function () {
                        $scope.espectaculosOrganizador = [];
                        for (var ite in idsOrganizadores) {
                            for (var all in $scope.allOrganizadores) {
                                if ($scope.allOrganizadores[all].id === parseInt(idsOrganizadores[ite])) {
                                 $scope.espectaculosOrganizador.push($scope.allOrganizadores[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(angular);

