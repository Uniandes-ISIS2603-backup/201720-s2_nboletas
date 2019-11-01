
(
        function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuariosContext", "api/usuarios");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', '$rootScope',
                
                function ($scope, $http, usuariosContext, $state, $rootScope) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioId;
   
                    $http.get(usuariosContext + '/' + idUsuario).then(function (response) {
                        var usuarioRegistrado = response.data;
                        $scope.usuario.nombreUsuario= usuarioRegistrado.nombreUsuario;
                        $scope.usuario.email=usuarioRegistrado.email;
                        $scope.usuario.ciudad=usuarioRegistrado.ciudad;
                        $scope.usuario.pais=usuarioRegistrado.pais;
                        $scope.usuario.password=usuarioRegistrado.password;
                        $scope.usuario.userName=usuarioRegistrado.userName;
                        $scope.usuario.imagen=usuarioRegistrado.imagen;
                    });
                    
                $scope.updateUsuario= function(){
                        $http.put(usuariosContext + "/" + idUsuario, $scope.usuario).then(function (response) {
                        $state.go('myAccount', {usuarioId: response.data.id}, {reload: true});
                    });
                };
                
                }
            ]);
        }
)(angular);

