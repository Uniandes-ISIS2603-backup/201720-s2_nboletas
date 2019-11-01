(function (ng) {
    var mod = ng.module("espectaculosModule");
    mod.constant("espectaculosContext", "api/espectaculos");
    mod.controller('espectaculoDeleteCtrl', ['$scope', '$http', 'espectaculosContext', '$state',
        function ($scope, $http, espectaculosContext, $state) {
            var idEspectaculo = $state.params.espectaculoId;
            $scope.deleteEspectaculo = function () {
                $http.delete(espectaculosContext + '/' + idEspectaculo, {}).then(function (response) {
                    $state.go('espectaculosList', {espectaculoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

