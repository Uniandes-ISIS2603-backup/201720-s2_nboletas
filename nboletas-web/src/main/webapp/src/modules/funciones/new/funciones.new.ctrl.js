/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("funcionesModule");
    mod.constant("funcionesContext", "api/funciones");
    mod.constant("espectaculosContext", "api/espectaculos");
    mod.constant("lugaresContext", "api/lugares");
    mod.controller('funcionesNewCtrl', ['$scope', '$http', 'funcionesContext', 'espectaculosContext', 'lugaresContext', '$state', '$rootScope',
        function ($scope, $http, funcionesContext, espectaculosContext, lugaresContext, $state, $rootScope) {
            $rootScope.edit = false;

            $http.get(espectaculosContext).then(function (response) {
                $scope.espectaculosGetAll = response.data;
            });

            $http.get(lugaresContext).then(function (response) {
                $scope.lugaresGetAll = response.data;
            });

            $scope.createFuncion = function () {
                $http.post(funcionesContext, {
                    hora: $scope.funcionHora,
                    espectaculo: {
                        id: $scope.funcionEspectaculo
                    },
                    lugar: {
                        id: $scope.funcionLugar
                    }
                }).then(function (response) {
                    $state.go('funcionesList', {funcionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

