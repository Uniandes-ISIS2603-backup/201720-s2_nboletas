(function (ng) {
    var mod = ng.module("sillasModule");
    mod.constant("sillasContext", "api/sillas");
    mod.controller('sillasDeleteCtrl', ['$scope', '$http', 'sillasContext', '$state',
        function ($scope, $http, sillasContext, $state) {
            var idsilla = $state.params.sillaId;
            $scope.deleteSilla = function () {
                $http.delete(sillasContext + '/' + idsilla, {}).then(function (response) {
                    console.log(response);
                    $state.go('sillasList', {sillaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

