(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        //Nuestras Boletas Modules
        'boletasModule',
        'organizadoresModule',
        'funcionesModule',
        'espectaculosModule',
        'usuarioModule',
        'espectaculosModule', 
        'artistasModule',
        'lugaresModule',
        'divisionesModule',
        'sillasModule',
        'enviosModule',
        'comentariosModule',
        'reembolsosModule',
        //Extra Modules
        'rellenarModule'
        // Internal modules dependencies   
    ]);

    // Resuelve problemas de las promesas
    
    app.config(['$qProvider', function ($qProvider) {
        $qProvider.errorOnUnhandledRejections(false);
    }]);

    app.run(['$rootScope', '$transitions', function ($rootScope, $transitions) {

            $transitions.onSuccess({to: '*'}, function (trans) {

                var $state = trans.router.stateService;

                $rootScope.isAuthenticated = function () {
                    if (sessionStorage.getItem("userName") !== "null" && 
                            sessionStorage.getItem("userName") !== undefined) {
                        return true;
                    } else {
                        return false;
                    }
                };
                
                $rootScope.hasPermissions = function () {
                    if ($rootScope.isAuthenticated() && (sessionStorage.getItem("admon") === "true") ) {
                        return true;
                    }
                    return false;
                };
                
                $rootScope.cerrarSesion = function(){
                    if($rootScope.isAuthenticated()){
                        sessionStorage.setItem("userName",null);
                        sessionStorage.setItem("admon",null);
                        $state.go('espectaculosList');
                    }
                };

            });

        }]);

})(window.angular);
