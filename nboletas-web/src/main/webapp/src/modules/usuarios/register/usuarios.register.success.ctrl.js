(function (ng) {
    var mod = angular.module('usuarioModule');
    mod.controller('usuariosRegisterSuccessCtrl', ['$scope','$state',success]);
    
    function success($scope, $state){
        console.log($state.params.usuarioId);
        if (($state.params.usuarioId !== undefined) && ($state.params.usuarioId !== null)) {
            $scope.userId = $state.params.usuarioId;
        }
        
    }
    
})(angular);