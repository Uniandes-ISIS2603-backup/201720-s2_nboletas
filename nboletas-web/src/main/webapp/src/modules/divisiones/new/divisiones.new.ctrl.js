/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("divisionesModule");
    mod.constant("divisionesContext", "api/divisiones");
    mod.controller('divisionesNewCtrl', ['$scope', '$http', 'divisionesContext', '$state', '$rootScope',
        function ($scope, $http, divisionesContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createDivision = function () {
                $http.post(divisionesContext, {name:$scope.divisionNombre,imagen:$scope.divisionImagen}).then(function (response) {
                    $state.go('divisionesList', {divisionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

