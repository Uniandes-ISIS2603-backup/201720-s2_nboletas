/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = angular.module("espectaculosModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/espectaculos/';
            $urlRouterProvider.otherwise("/espectaculos");
            $stateProvider
                    .state('espectaculos', {
                        url: "/espectaculos",
                        abstract: true,
                        views: {
                            'mainView':{
                                templateUrl: basePath + 'espectaculos.html',
                                controller: 'espectaculoCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
                        
                    })
                    .state('espectaculosList', {
                        url: '/list',
                        parent: 'espectaculos',
                        views:{
                            'listView':{
                                templateUrl: basePath + 'espectaculos.list.html'
                            }
                        }
                    })
                    .state('espectaculoDetail' ,{
                        url: '/{espectaculoId:int}/detail',
                        parent: 'espectaculos',
                        param: {
                            espectaculoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'espectaculos.detail.html',
                                controller: 'espectaculoCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
                    })
                    .state('espectaculosCreate',{
                        url: '/create',
                        parent: 'espectaculos',
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/espectaculos.new.html',
                                controller: 'espectaculoNewCtrl'
                            }
                        }        
                    })
                    .state('espectaculosDelete',{
                       url: '/delete/{espectaculoId:int}',
                        parent: 'espectaculos',
                        param: {
                            espectaculosId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/espectaculos.delete.html',
                                controller: 'espectaculoDeleteCtrl'
                            }
                        }         
                    });
        }]);
})(window.angular);


