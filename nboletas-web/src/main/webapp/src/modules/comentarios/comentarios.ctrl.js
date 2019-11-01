/* global windows */

(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.constant("comentariosContext", "api/comentarios");
    mod.controller('comentarioCtrl', ['$scope', '$http', 'comentariosContext', '$state',
        function ($scope, $http, comentariosContext, $state) {
            $http.get(comentariosContext).then(function (response) {
                $scope.comentariosRecords = response.data;
            });
            if (($state.params.comentarioId !== undefined) && ($state.params.comentarioId !== null)) {
                $http.get(comentariosContext + '/' + $state.params.comentarioId).then(function (response) {
                    $scope.currentcomentario = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


