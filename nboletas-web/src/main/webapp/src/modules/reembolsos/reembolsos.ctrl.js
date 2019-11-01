/* global windows */

(function (ng) {
    var mod = ng.module("reembolsosModule");
    mod.constant("reembolsosContext", "api/reembolsos");
    mod.controller('reembolsoCtrl', ['$scope', '$http', 'reembolsosContext', '$state',
        function ($scope, $http, reembolsosContext, $state) {
            $http.get(reembolsosContext).then(function (response) {
                $scope.reembolsosRecords = response.data;
            });
            if (($state.params.reembolsoId !== undefined) && ($state.params.reembolsoId !== null)) {
                $http.get(reembolsosContext + '/' + $state.params.reembolsoId).then(function (response) {
                    $scope.currentReembolso = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


