(function (ng) {
    var mod = angular.module('usuarioModule');
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuariosRegisterCtrl', ['$scope', '$http', 'usuariosContext', '$state', '$rootScope',register]);
    
    function register($scope, $http, usuariosContext, $state, $rootScope){
        $scope.creationFailed = false;
        $rootScope.edit = false;
        $scope.usr = "Error";
        
        $scope.registerUsuario = function () {
            
            var user = {
                userName: $scope.userName,
                password: $scope.password,
                nombreUsuario: $scope.nombreUsuario,
                email: $scope.email,
                pais: $scope.pais,
                ciudad: $scope.ciudad
            };
            
            $http.post(usuariosContext,user).then(function (response) {
                var datas = response.data;
                $state.go('usuarioRegisterSuccess',({userName: user.userName}));
            }),
            function(response) {
                $scope.creationFailed = true;
                $scope.data = response.data || 'Request failed';
                $scope.status = response.status;
            };
            
            
        };
        
    }

    
})(angular);
