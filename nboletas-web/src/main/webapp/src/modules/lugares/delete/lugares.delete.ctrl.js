(function (ng) {
    var mod = ng.module("lugaresModule");
    mod.constant("lugaresContext", "api/lugares");
    mod.controller('lugaresDeleteCtrl', ['$scope', '$http', 'lugaresContext', '$state',
        function ($scope, $http, lugaresContext, $state) {
            var idLugar = $state.params.lugarId;
            $scope.deleteLugar = function () {
                $http.delete(lugaresContext + '/' + idLugar, {}).then(function (response) {
                    $state.go('lugaresList', {lugarId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);