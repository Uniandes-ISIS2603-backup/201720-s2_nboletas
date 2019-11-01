/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("enviosModule");
    mod.constant("enviosContext", "api/envios");
    mod.controller('enviosNewCtrl', ['$scope', '$http', 'enviosContext', '$state', '$rootScope',
        function ($scope, $http, enviosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createEnvio = function () {
                $http.post(enviosContext, {
                    direccion: $scope.envioDireccion
                }).then(function (response) {
                    $state.go('enviosList', {envioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

