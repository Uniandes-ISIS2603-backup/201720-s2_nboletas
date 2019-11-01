/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.constant("comentariosContext", "api/comentarios");
    mod.controller('comentariosNewCtrl', ['$scope', '$http', 'comentariosContext', '$state', '$rootScope',
        function ($scope, $http, comentariosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createComentario = function () {
                $http.post(comentariosContext, {
                    comentario: $scope.comentarioComentario,
                    fecha: $scope.comentarioFecha                                        
                }).then(function (response) {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

