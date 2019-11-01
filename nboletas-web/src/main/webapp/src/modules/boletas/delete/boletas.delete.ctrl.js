(function (ng) {
    var mod = ng.module("boletasModule");
    mod.constant("boletasContext", "api/boletas");
    mod.controller('boletaDeleteCtrl', ['$scope', '$http', 'boletasContext', '$state',
        function ($scope, $http, boletasContext, $state) {
            var idBoleta = $state.params.boletaId;
            $scope.deleteBoleta = function () {
                $http.delete(boletasContext + '/' + idBoleta, {}).then(function (response) {
                    $state.go('boletasList', {boletaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

