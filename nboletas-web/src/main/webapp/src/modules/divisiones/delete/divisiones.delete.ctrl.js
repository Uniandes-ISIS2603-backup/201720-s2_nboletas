(function (ng) {
    var mod = ng.module("divisionesModule");
    mod.constant("divisionesContext", "api/divisiones");
    mod.controller('divisionesDeleteCtrl', ['$scope', '$http', 'divisionesContext', '$state',
        function ($scope, $http, divisionesContext, $state) {
            var idDivision = $state.params.divisionId;
            $scope.deleteDivision = function () {
                $http.delete(divisionesContext + '/' + idDivision, {}).then(function (response) {
                    console.log(response);
                    $state.go('divisionesList', {divisionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

