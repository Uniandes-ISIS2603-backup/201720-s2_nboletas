(function (ng) {
    var mod = ng.module("enviosModule");
    mod.constant("enviosContext", "api/envios");
    mod.controller('envioDeleteCtrl', ['$scope', '$http', 'enviosContext', '$state',
        function ($scope, $http, enviosContext, $state) {
            var idEnvio = $state.params.envioId;
            $scope.id_envio = idEnvio;
            $scope.deleteEnvio = function () {
                $http.delete(enviosContext + '/' + idEnvio, {}).then(function (response) {
                    $state.go('enviosList', {envioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

