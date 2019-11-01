
(
        function (ng) {
            var mod = ng.module("reembolsosModule");
            mod.constant("reembolsosContext", "api/boletas");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("boletasContext", "api/boletas");
            mod.controller('reembolsoUpdateCtrl', ['$scope', '$http', 'reembolsosContext', 'usuariosContext', 'boletasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, reembolsosContext, usuariosContext, boletasContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idReembolso = $state.params.reembolsoId;

                    //Consulto el autor a editar.
                    $http.get(reembolsosContext + '/' + idReembolso).then(function (response) {
                        var reembolso = response.data;
                        $scope.reembolso.valor= reembolso.valor;
                        $scope.reembolso.usuario = reembolso.usuario;
                        $scope.reembolso.boleta = reembolso.boleta;
                    });
                $scope.updateReembolso = function () {
                    $http.get(usuariosContext + '/' + $scope.reembolso.usuario.id).then(function (response) {
                        $scope.reembolso.usuario = response.data;
                    });
                    $http.get(boletasContext + '/' + $scope.reembolso.boleta.id).then(function (response) {
                        $scope.reembolso.boleta = response.data;
                    });
                    $http.put(reembolsosContext + "/" + idReembolso, $scope.reembolso).then(function (response) {
                    $state.go('reembolsosList', {reembolsoId: response.data.id}, {reload: true});
                });
                };
                }
            ]);
        }
)(angular);

