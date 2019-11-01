/* global windows */

(function (ng) {
    var mod = ng.module("espectaculosModule");
    mod.constant("espectaculosContext", "api/espectaculos");
    mod.controller('espectaculoCtrl', ['$scope', '$http', 'espectaculosContext', '$state',
        function ($scope, $http, espectaculosContext, $state) {
            $http.get(espectaculosContext).then(function (response) {
                $scope.espectaculosRecords = response.data;
            });
            if (($state.params.espectaculoId !== undefined) && ($state.params.espectaculoId !== null)) {
                $http.get(espectaculosContext + '/' + $state.params.espectaculoId).then(function (response) {
                    $scope.currentEspectaculo = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


