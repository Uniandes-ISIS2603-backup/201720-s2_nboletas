/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = angular.module("divisionesModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/divisiones/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/divisionesList");
            $stateProvider
                    .state('divisionesList', {
                        url: '/divisiones/list',
                        views:{
                            'listView':{
                                templateUrl: basePath + 'divisiones.list.html',
                                controller: 'divisionesCtrl',
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
                    .state('divisionesDetail' ,{
                        url: '/divisiones/{divisionId:int}/detail',
                        param: {
                            divisionId: null
                        },
                        
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'divisiones.detail.html',
                                controller: 'divisionesCtrl',
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
                    .state('divisionesCreate',{
                        url: '/divisiones/create',
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/divisiones.new.html',
                                controller: 'divisionesNewCtrl'
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
                    .state('divisionesUpdate', {
                        url: '/divisiones/update/{divisionId:int}',
                        param: {
                            divisionId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/update/divisiones.update.html',
                                controller: 'divisionesUpdateCtrl'
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
                    .state('divisionesDelete',{
                       url: '/divisiones/delete/{divisionId:int}',
                        param: {
                            divisionId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/divisiones.delete.html',
                                controller: 'divisionesDeleteCtrl'
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


