/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("lugaresModule", []);
    mod.constant("lugaresContext", "api/lugares");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/lugares/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/lugaresList");

            $stateProvider.state('lugaresList', {
                url: '/lugares/list',
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugares.list.html',
                        controller: 'lugaresCtrl',
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
            }).state('lugaresUpdate', {
                url: '/lugares/update/{lugarId:int}',
                param: {
                    lugarId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/lugares.update.html',
                        controller: 'lugaresUpdateCtrl'
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
            }).state('lugaresCreate', {
                url: '/lugares/create',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/lugares.new.html',
                        controller: 'lugaresNewCtrl'
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
            }).state('lugaresDelete', {
                url: '/lugares/delete/{lugarId:int}',

                param: {
                    funcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/lugares.delete.html',
                        controller: 'lugaresDeleteCtrl'
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
            }).state('lugaresDetail', {
                url: '/lugares/{lugarId:int}/detail',

                param: {
                    funcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'lugares.detail.html',
                        controller: 'lugaresCtrl',
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
            });
        }]);
})(window.angular);



