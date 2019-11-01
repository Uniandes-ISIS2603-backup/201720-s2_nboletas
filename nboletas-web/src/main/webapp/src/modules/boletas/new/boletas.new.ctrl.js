/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("boletasModule");
    mod.constant("boletasContext", "api/boletas");
    mod.controller('boletaNewCtrl', ['$scope', '$http', 'boletasContext', '$state', '$rootScope',
        function ($scope, $http, boletasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createBoleta = function () {
                $http.post(boletasContext, {
                    precio: $scope.boletaPrecio,
                    vendida: $scope.boletaVendida
                }).then(function (response) {
                    $state.go('boletasList', {boletaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

