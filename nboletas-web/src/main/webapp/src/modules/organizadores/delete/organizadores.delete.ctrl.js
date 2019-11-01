(function (ng) {
    var mod = ng.module("organizadoresModule");
    mod.constant("organizadoresContext", "api/boletas");
    mod.controller('organizadorDeleteCtrl', ['$scope', '$http', 'organizadoresContext', '$state',
        function ($scope, $http, organizadoresContext, $state) {
            var idOrganizador = $state.params.organizadorId;
            $scope.deleteOrganizador = function () {
                $http.delete(organizadoresContext + '/' + idOrganizador, {}).then(function (response) {
                    $state.go('organizadoresList', {boletaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);