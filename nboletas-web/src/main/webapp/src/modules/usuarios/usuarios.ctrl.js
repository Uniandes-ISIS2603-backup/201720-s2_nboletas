(function (ng) {
    var mod = angular.module('usuarioModule');
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuarioCtrl', ['$scope', '$http', 'usuariosContext','$state', controladorUsuario]);
    
    function controladorUsuario($scope, $http, usuariosContext, $state) {
        
        $http.get(usuariosContext).then(function (response) {
            $scope.users = response.data;
        },
        function(response) {
            $scope.data = response.data || 'Request failed';
            $scope.status = response.status;
        });
             
        if (($state.params.usuarioId!== undefined) && ($state.params.usuarioId !== null)) {
            $http.get(usuariosContext + '/' + $state.params.usuarioId).then(function (response) {
                $scope.currentUser = response.data;
                $http.get(usuariosContext + '/' + $scope.currentUser.id + '/boletas').then(function(response){
                    $scope.boletas = response.data;
                });
            });
        }
        

        
    }
    
})(angular);
