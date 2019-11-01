/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("sillasModule");
    mod.constant("sillasContext", "api/sillas");
    mod.controller('sillasNewCtrl', ['$scope', '$http', 'sillasContext', '$state', '$rootScope',
        function ($scope, $http, sillasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createSilla = function () {
                $http.post(sillasContext, {costo:$scope.sillaCosto,imagen:$scope.sillaImagen}).then(function (response) {
                    $state.go('sillasList', {sillaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

