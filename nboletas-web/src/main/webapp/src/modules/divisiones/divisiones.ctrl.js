/* global windows */

(function (ng) {
    var mod = ng.module("divisionesModule");
    mod.constant("divisionesContext", "api/divisiones");
    mod.controller('divisionesCtrl', ['$scope', '$http', 'divisionesContext', '$state',
        function ($scope, $http, divisionesContext, $state) {
            $http.get(divisionesContext).then(function (response) {
                $scope.divisionesRecords = response.data;
            });
            if (($state.params.divisionId !== undefined) && ($state.params.divisionId !== null)) {
                $http.get(divisionesContext + '/' + $state.params.divisionId).then(function (response) {
                    console.log(response.data);
                    $scope.currentDivision = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


