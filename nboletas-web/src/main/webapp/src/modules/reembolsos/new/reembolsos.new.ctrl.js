/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reembolsosModule");
    mod.constant("reembolsosContext", "api/reembolsos");
    mod.controller('reembolsoNewCtrl', ['$scope', '$http', 'reembolsosContext', '$state', '$rootScope',
        function ($scope, $http, reembolsosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createReembolso = function () {
                $http.post(reembolsosContext, {
                    valor: $scope.reembolso.valor
                }).then(function (response) {
                    $state.go('reembolsosList', {reembolsoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

