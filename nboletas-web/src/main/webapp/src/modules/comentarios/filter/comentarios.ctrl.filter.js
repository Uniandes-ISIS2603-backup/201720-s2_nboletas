/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.constant("comentariosContext", "api/comentarios");
    mod.filter('yesNo', function() {
        return function(value) {
        if (value === true) {
            return 'Yes';
        } else if (value === false) {
            return 'No';
        } else {
            return '';
        }
        };
    }); 
    mod.controller('comentarioCtrlFilter', ['$scope', '$http', 'comentariosContext',
        function ($scope, $http, comentariosContext) {
            
        }
    ]);
}
)(window.angular);

