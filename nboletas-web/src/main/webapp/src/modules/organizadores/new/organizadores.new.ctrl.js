/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("organizadoresModule");
    mod.constant("organizadoresContext", "api/organizadores");
    mod.controller('organizadorNewCtrl', ['$scope', '$http', 'organizadoresContext', '$state', '$rootScope',
        function ($scope, $http, organizadoresContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createOrganizador = function () {
                $http.post(organizadoresContext, {
                    nombreEmpresa: $scope.organizadorNombreEmpresa
                }).then(function (response) {
                    $state.go('organizadoresList', {organizadorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

