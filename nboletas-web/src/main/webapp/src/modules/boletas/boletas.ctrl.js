    /* global windows */

(function (ng) {
    var mod = ng.module("boletasModule");
    mod.constant("boletasContext", "api/boletas");
    mod.controller('boletaCtrl', ['$scope', '$http', 'boletasContext', '$state',
        function ($scope, $http, boletasContext, $state) {
            $http.get(boletasContext).then(function (response) {
                $scope.boletasRecords = response.data;
            });
            if (($state.params.boletaId !== undefined) && ($state.params.boletaId !== null)) {
                $http.get(boletasContext + '/' + $state.params.boletaId).then(function (response) {
                    $scope.currentBoleta = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


