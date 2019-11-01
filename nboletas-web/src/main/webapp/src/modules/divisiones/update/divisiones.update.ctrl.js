
(
        function (ng) {
            var mod = ng.module("divisionesModule");
            mod.constant("divisionesContext", "api/divisiones");
            mod.controller('divisionesUpdateCtrl', ['$scope', '$http', 'divisionesContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, divisionesContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idDivision = $state.params.divisionId;

   
                    $http.get(divisionesContext + '/' + idDivision).then(function (response) {
                        var division = response.data;
                        $scope.division.name= division.name;
                        $scope.division.imagen= division.imagen;
                    });
                $scope.updateDivision= function () {
                    $http.put(divisionesContext + "/" + idDivision, {
                        name:$scope.division.name,
                        imagen:$scope.division.imagen
                    }).then(function (response) {
                    $state.go('divisionesList', {divisionId: response.data.id}, {reload: true});
                });
                };
                }
            ]);
        }
)(angular);

