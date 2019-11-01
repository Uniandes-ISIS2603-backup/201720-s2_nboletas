
(
        function (ng) {
            var mod = ng.module("sillasModule");
            mod.constant("sillasContext", "api/sillas");
            mod.controller('sillasUpdateCtrl', ['$scope', '$http', 'sillasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, sillasContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idsilla = $state.params.sillaId;

   
                    $http.get(sillasContext + '/' + idsilla).then(function (response) {
                        var silla = response.data;
                        $scope.silla.costo= silla.costo;
                    });
                $scope.updateSilla= function () {
                    $http.put(sillasContext + "/" + idsilla, $scope.silla).then(function (response) {
                    $state.go('sillasList', {sillaId: response.data.id}, {reload: true});
                });
                };
                }
            ]);
        }
)(angular);

