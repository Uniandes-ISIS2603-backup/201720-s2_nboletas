/* global windows */

(function (ng) {
    var mod = ng.module("enviosModule");
    mod.constant("enviosContext", "api/envios");
    mod.controller('envioCtrl', ['$scope', '$http', 'enviosContext', '$state',
        function ($scope, $http, enviosContext, $state) {
            $http.get(enviosContext).then(function (response) {
                $scope.enviosRecords = response.data;
            });
            if (($state.params.envioId !== undefined) && ($state.params.envioId !== null)) {
                $http.get(enviosContext + '/' + $state.params.envioId).then(function (response) {
                    $scope.currentEnvio = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


