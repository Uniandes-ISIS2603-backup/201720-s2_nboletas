
(
        function (ng) {
            var mod = ng.module("enviosModule");
            mod.constant("enviosContext", "api/envios");
            mod.controller('envioUpdateCtrl', ['$scope', '$http', 'enviosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, enviosContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idEnvio = $state.params.envioId;
                    
                    $http.get(enviosContext + '/' + idEnvio).then(function (response) {
                        var envio = response.data;
                        
                     $scope.envio.direccion = envio.direccion;
                     $scope.envio.id = envio.id;

                    });
                    
                $scope.updateEnvio = function () {
                    $http.put(enviosContext + "/" + idEnvio, $scope.envio).then(function (response) {
                    $state.go('enviosList', {EnvioId: response.data.id}, {reload: true});
                });
                };
                }
            ]);
        }
)(angular);

