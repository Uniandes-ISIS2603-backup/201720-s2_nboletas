/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = angular.module("reembolsosModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reembolsos/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/reembolsos");
            $stateProvider
                    .state('reembolsosList', {
                        url: '/list',
                        views:{
                            'listView':{
                                templateUrl: basePath + 'reembolsos.list.html',
                                controller: 'reembolsoCtrl',
                                controllerAs: 'ctrl'
                            },
                            'miniPostView': {
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView': {
                                templateUrl: basePathHtml + 'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    })
                    .state('reembolsoDetail',{
                        url: '/{reembolsoId:int}/detail',
                        param: {
                            reembolsoId: null
                        },
                        views: {
                           
                            'detailView': {
                                templateUrl: basePath + 'reembolsos.detail.html',
                                controller: 'reembolsoCtrl',
                                controllerAs: 'ctrl'
                            },
                            'miniPostView': {
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView': {
                                templateUrl: basePathHtml + 'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }       
                    })
                    .state('reembolsosCreate',{
                        url: '/create',
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/reembolsos.new.html',
                                controller: 'reembolsoNewCtrl'
                            },
                            'miniPostView': {
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView': {
                                templateUrl: basePathHtml + 'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }        
                    })
                    .state('reembolsoUpdate', {
                        url: '/update/{reembolsoId:int}',
                        param: {
                            reembolsoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/update/reembolsos.update.html',
                                controller: 'reembolsoUpdateCtrl'
                            },
                            'miniPostView': {
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView': {
                                templateUrl: basePathHtml + 'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    })
                    .state('reembolsoDelete',{
                       url: '/delete/{reembolsoId:int}',
                        param: {
                            reembolsoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/reembolsos.delete.html',
                                controller: 'reembolsoDeleteCtrl'
                            },
                            'miniPostView': {
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView': {
                                templateUrl: basePathHtml + 'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }         
                    });
        }]);
})(window.angular);
