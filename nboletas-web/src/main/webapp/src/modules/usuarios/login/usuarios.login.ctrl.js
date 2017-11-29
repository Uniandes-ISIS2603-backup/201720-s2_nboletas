(function (ng) {
    var mod = ng.module('usuarioModule');
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuarioLoginCtrl', ['$scope', '$http', 'usuariosContext','$state', '$rootScope', login]);
    
    function login($scope, $http, usuariosContext, $rootScope, $state){
        
        $scope.user = {};
        $scope.data = {};
        $scope.loginFailed=false;

        $scope.autenticar = function () {
            if (($scope.username !== undefined) && ($scope.username !== null) && 
                    ($scope.password !== undefined) && ($scope.password !== null)) {
                
                //Geting USER by username
                $http.get(usuariosContext + '/' + $scope.username)
                //Response Correct
                .then(function (response) {
                    $scope.user = response.data;
                    if($scope.user.userName === $scope.username && $scope.user.password === $scope.password){
                        sessionStorage.setItem("userName", $scope.user.userName);
                        sessionStorage.setItem("admon",$scope.user.admon);
                          $rootScope.currentLogedUser = $scope.user;
                    }else{
                        $scope.loginFailed = true;
                        $scope.data = response.data || 'Request failed';
                        $scope.status = "Contraseña incorrecta.";
                    }
                },
                //Kain Response, Nicht Response
                function (response) {
                    $scope.loginFailed = true;
                    $scope.data = response.data || 'Request failed';
                    $scope.status = "Usuario no encontrado.";
                });
                if(!$scope.loginFailed){
                    $state.go('espectaculosList',{});
                }
            }
        };
    }
    
})(angular);
