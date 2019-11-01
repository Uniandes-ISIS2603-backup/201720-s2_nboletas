(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuarioDeleteCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        function ($scope, $http, usuariosContext, $state) {
            var idUsuario = $state.params.usuarioId;
            $scope.deleteUsuario = function () {
                $http.delete(usuariosContext + '/' + idUsuario, {}).then(function (response) {
                    $state.go('espectaculosList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

