/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("artistasModule");
    mod.constant("artistasContext", "api/artistas");
    mod.controller('artistaNewCtrl', ['$scope', '$http', 'artistasContext', '$state', '$rootScope',
        function ($scope, $http, artistasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createArtista = function () {
                $http.post(artistasContext, {
                    nombre: $scope.artistaNombre,
                    imagen: $scope.artistaImagen
                }).then(function (response) {
                    $state.go('artistasList', {artistaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

