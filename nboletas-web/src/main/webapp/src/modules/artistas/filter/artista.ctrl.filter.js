/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("artistasModule");
    mod.constant("artistasContext", "api/artistas");
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
    mod.controller('artistaCtrlFilter', ['$scope', '$http', 'artistasContext',
        function ($scope, $http, artistasContext) {
            
        }
    ]);
}
)(window.angular);

