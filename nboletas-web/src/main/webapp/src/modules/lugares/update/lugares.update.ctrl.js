
(
        function (ng) {
            var mod = ng.module("lugaresModule");
            mod.constant("lugaresContext", "api/lugares");
            mod.controller('lugaresUpdateCtrl', ['$scope', '$http', 'lugaresContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, lugaresContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idLugar = $state.params.lugarId;
                    
                    $http.get(lugaresContext + '/' + idLugar).then(function (response) {
                        var lugar = response.data;
                        $scope.lugar.direccion = lugar.direccion;
                        $scope.lugar.tipo = lugar.tipo;
                        $scope.lugar.ubicacion = lugar.ubicacion;
                    });
                $scope.updateLugar = function () {
                    $http.put(lugaresContext + "/" + idLugar, $scope.lugar).then(function (response) {
                    $state.go('lugaresList', {lugarId: response.data.id}, {reload: true});
                });
                };
                }
            ]);
        }
)(angular);

