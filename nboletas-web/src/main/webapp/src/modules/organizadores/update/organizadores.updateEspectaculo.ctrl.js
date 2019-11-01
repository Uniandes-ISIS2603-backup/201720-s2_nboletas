/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(
        function (ng) {
            var mod = ng.module("organizadoresModule");
            mod.constant("organizadoresContexts", "api/organizadores");
            mod.constant("espectaculosContext", "espectaculos");
            mod.controller('organizadorUpdateEspectaculoCtrl', ['$scope', '$http', 'organizadoresContexts', '$state', '$rootScope', '$filter', 'espectaculosContext',
                function ($scope, $http, organizadoresContexts, $state, $rootScope, $filter, espectaculosContext) {
                    $rootScope.edit = true;
                    var idsEspectaculos = [];
                    var idsBorrar = [];
                    var idOrganizador= $state.params.organizadorId;

                $http.get(organizadoresContexts + '/' + idOrganizador).then(function (response) {
                    var organizador = response.data;
                    $scope.organizador.imagen = organizador.imagen;
                    $scope.organizador.nombreEmpresa = organizador.nombreEmpresa;
                    $http.get(organizadoresContexts + '/' + idOrganizador + '/espectaculos').then(function(response){
                        $scope.organizador.espectaculos = response.data;
                    });
                    $scope.getFiltro();
                });
                $scope.getFiltro = function() {
                    $http.get(espectaculosContext).then(function (response) {
                    $scope.allEspectaculos = response.data;
                    $scope.espectaculosOrganizador = $scope.organizador.espectaculos;
                    var filteredBooks = $scope.allEspectaculos.filter(function (allEspectaculos) {
                                return $scope.espectaculosOrganizador.filter(function (espectaculosOrganizador) {
                                    return espectaculosOrganizador.id === allEspectaculos.id;
                                }).length === 0;
                            });

                            $scope.allEspectaculosShow = filteredBooks;
                });};
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
                $scope.updateOrganizador = function () {
                    $scope.newEspectaculos();
                    $http.put(organizadoresContexts + "/" + idOrganizador,{
                        imagen: $scope.organizador.imagen,
                        nombreEmpresa: $scope.organizador.nombreEmpresa
                    }).then(function (response) {
                        if (idsEspectaculos.length > 0) {
                            for(var all in $scope.espectaculosOrganizador){
                                $http.put(organizadoresContexts + "/" + response.data.id + "/espectaculos/" + $scope.espectaculosOrganizador[all].id ,$scope.espectaculosOrganizador[all]).then(function (response) {
                                });
                            }
                            }
                        if(idsBorrar.length > 0){
                            for(var leg in idsBorrar){
                                $http.delete(organizadoresContexts + "/" + response.data.id + "/espectaculos/" + parseInt(idsBorrar[leg])).then(function (response) {
                                });
                            }
                        }
                    $state.go('organizadoresList', {organizadorId: response.data.id}, {reload: true});
                });
                };
                 $scope.newEspectaculos = function () {
                        $scope.espectaculosOrganizador = [];
                        for (var ite in idsEspectaculos) {
                            for (var all in $scope.allEspectaculos) {
                                if ($scope.allEspectaculos[all].id === parseInt(idsEspectaculos[ite])) {
                                 $scope.espectaculosOrganizador.push($scope.allEspectaculos[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(angular);


