(function (ng) {
    var mod = ng.module("reembolsosModule");
    mod.constant("reembolsosContext", "api/reembolsos");
    mod.controller('reembolsoDeleteCtrl', ['$scope', '$http', 'reembolsosContext', '$state',
        function ($scope, $http, reembolsosContext, $state) {
            var idReembolso = $state.params.reembolsoId;
            $scope.deleteReembolso = function () {
                $http.delete(reembolsosContext + '/' + idReembolso, {}).then(function (response) {
                    $state.go('reembolsosList', {reembolsoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

