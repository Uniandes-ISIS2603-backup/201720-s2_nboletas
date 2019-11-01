
(
        function (ng) {
            var mod = ng.module("boletasModule");
            mod.constant("boletasContext", "api/boletas");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("funcionesContext", "api/funciones");
            mod.constant("sillasContext", "api/sillas");
            mod.controller('boletaUpdateCtrl', ['$scope', '$http', 'boletasContext', 'usuariosContext', 'funcionesContext', 'sillasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, boletasContext, usuariosContext, funcionesContext, sillasContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idBoleta = $state.params.boletaId;

                    //Consulto el autor a editar.
                    $http.get(boletasContext + '/' + idBoleta).then(function (response) {
                        var boleta = response.data;
                        $scope.boleta.precio= boleta.precio;
                        $scope.boleta.vendida = boleta.vendida;
                        $scope.boleta.usuario = boleta.usuario;
                        $scope.boleta.funcion = boleta.funcion;
                        $scope.boleta.silla = boleta.silla;
                    });
                $scope.updateBoleta = function () {
                    $http.get(usuariosContext + '/' + $scope.boleta.usuario.id).then(function (response) {
                        $scope.boleta.usuario = response.data;
                    });
                    $http.get(funcionesContext + '/' + $scope.boleta.funcion.id).then(function (response) {
                        $scope.boleta.funcion = response.data;
                    });
                    $http.get(sillasContext + '/' + $scope.boleta.silla.id).then(function (response) {
                        $scope.boleta.silla = response.data;
                    });
                    $http.put(boletasContext + "/" + idBoleta, $scope.boleta).then(function (response) {
                        $state.go('boletasList', {boletaId: response.data.id}, {reload: true});
                    });
                };
                }
            ]);
        }
)(angular);

