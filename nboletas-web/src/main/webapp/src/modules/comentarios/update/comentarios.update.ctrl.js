
(
        function (ng) {
            var mod = ng.module("comentariosModule");
            mod.constant("comentariosContext", "api/comentarios");
            mod.controller('comentarioUpdateCtrl', ['$scope', '$http', 'comentariosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, comentariosContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idComentario = $state.params.comentarioId;
                    
                    $http.get(comentariosContext + '/' + idComentario).then(function (response) {
                        var comentario = response.data;
                        
                    $scope.comentario.comentario = comentario.comentario;
                    $scope.comentario.fecha = comentario.fecha;
                    $scope.comentario.id = comentario.id;
                                       
                    });
                    
                $scope.updateComentario = function () {
                    $http.put(comentariosContext + "/" + idComentario, $scope.comentario).then(function (response) {
                    $state.go('comentariosList', {ComentarioId: response.data.id}, {reload: true});
                });
                };
                }
            ]);
        }
)(angular);

