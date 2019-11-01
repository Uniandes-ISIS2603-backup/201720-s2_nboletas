(function (ng) {
    var mod = ng.module("funcionesModule");
    mod.constant("funcionesContext", "api/funciones");
    mod.controller('funcionesDeleteCtrl', ['$scope', '$http', 'funcionesContext', '$state',
        function ($scope, $http, funcionesContext, $state) {
            var idFuncion = $state.params.funcionId;
            $scope.deleteFuncion = function () {
                $http.delete(funcionesContext + '/' + idFuncion, {}).then(function (response) {
                    $state.go('funcionesList', {funcionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);