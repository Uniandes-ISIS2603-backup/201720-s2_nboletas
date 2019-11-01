/* global windows */

(function (ng) {
    var mod = ng.module("sillasModule");
    mod.constant("sillasContext", "api/sillas");
    mod.controller('sillasCtrl', ['$scope', '$http', 'sillasContext', '$state',
        function ($scope, $http, sillasContext, $state) {
            $http.get(sillasContext).then(function (response) {
                $scope.sillasRecords = response.data;
            });
            if (($state.params.sillaId !== undefined) && ($state.params.sillaId !== null)) {
                $http.get(sillasContext + '/' + $state.params.sillaId).then(function (response) {
                    console.log(response.data);
                    $scope.currentSilla = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


