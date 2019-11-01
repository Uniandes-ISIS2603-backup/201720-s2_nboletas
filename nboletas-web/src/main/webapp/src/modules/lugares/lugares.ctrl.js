/* global windows */

(function (ng) {
    var mod = ng.module("lugaresModule");
    mod.controller('lugaresCtrl', ['$scope', '$http', 'lugaresContext', '$state',
        function ($scope, $http, lugaresContext, $state) {
            $http.get(lugaresContext).then(function (response) {
                $scope.lugaresGetAll = response.data;
            });

            if (($state.params.lugarId !== undefined) && ($state.params.lugarId !== null)) {
                $http.get(lugaresContext + '/' + $state.params.lugarId).then(function (response) {
                    $scope.currentLugar = response.data;
                });

                $http.get(lugaresContext + "/" + $state.params.lugarId + "/divisiones").then(function (response) {
                    $scope.divisionesLugar = response.data;
                });

                $http.get(lugaresContext + "/" + $state.params.lugarId + "/funciones").then(function (response) {
                    $scope.funcionesLugar = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


