/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = angular.module("enviosModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/envios/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/enviosList");
            $stateProvider
                    .state('enviosList', {
                        url: '/envios/list',
                        views:{
                            'listView':{
                                templateUrl: basePath + 'envios.list.html',
                                controller: 'envioCtrl',
                                controllerAs: 'ctrl'
                            },
                            'miniPostView':{
                               templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    })
                    .state('envioDetail',{
                        url: '/envios/{envioId:int}/detail',
                        param: {
                            envioId: null
                        },
                        views: {                         
                            'detailView': {
                                templateUrl: basePath + 'envios.detail.html',
                                controller: 'envioCtrl',
                                controllerAs: 'ctrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }       
                    })
                    .state('enviosCreate',{
                        url: '/envios/create',
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/envios.new.html',
                                controller: 'enviosNewCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }        
                    })
                    .state('envioUpdate', {
                        url: '/envios/update/{envioId:int}',
                        param: {
                            envioId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/update/envios.update.html',
                                controller: 'envioUpdateCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    })
                    .state('envioDelete',{
                       url: '/envios/delete/{envioId:int}',
                        param: {
                            envioId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/envios.delete.html',
                                controller: 'envioDeleteCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }         
                    });
        }]);
})(window.angular);

