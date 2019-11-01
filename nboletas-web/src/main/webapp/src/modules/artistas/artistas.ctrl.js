/* global windows */

(function (ng) {
    var mod = ng.module("artistasModule");
    mod.constant("artistasContext", "api/artistas");
    mod.controller('artistaCtrl', ['$scope', '$http', 'artistasContext', '$state',
        function ($scope, $http, artistasContext, $state) {
            $http.get(artistasContext).then(function (response) {
                $scope.artistasRecords = response.data;
            });
            if (($state.params.artistaId !== undefined) && ($state.params.artistaId !== null)) {
                $http.get(artistasContext + '/' + $state.params.artistaId).then(function (response) {
                    console.log(response.data);
                    $scope.currentArtista = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


