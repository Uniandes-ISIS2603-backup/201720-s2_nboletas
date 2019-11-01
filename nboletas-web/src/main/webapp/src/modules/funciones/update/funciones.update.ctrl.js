
(
        function (ng) {
            var mod = ng.module("funcionesModule");
            mod.constant("funcionesContext", "api/funciones");
            mod.constant("espectaculosContext", "api/espectaculos");
            mod.constant("lugaresContext", "api/lugares");
            mod.controller('funcionesUpdateCtrl', ['$scope', '$http', 'funcionesContext', 'espectaculosContext', 'lugaresContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, funcionesContext, espectaculosContext, lugaresContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    
                    var idFuncion = $state.params.funcionId;
                    
                    $http.get(funcionesContext + '/' + idFuncion).then(function (response) {
                        $scope.funcion = response.data;
                    });
                    
                    $http.get(espectaculosContext).then(function (response) {
                        $scope.espectaculosGetAll = response.data;
                    });
                    
                    $http.get(lugaresContext).then(function (response) {
                        $scope.lugaresGetAll = response.data;
                    });
                $scope.updateFuncion = function () {
                    $http.put(funcionesContext + "/" + idFuncion, $scope.funcion).then(function (response) {
                    $state.go('funcionesList', {funcionId: response.data.id}, {reload: true});
                });
                };
                }
            ]);
        }
)(angular);

