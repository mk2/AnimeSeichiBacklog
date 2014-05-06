/**
 * RequireJS Configuration
 */
require.config({
    baseUrl: 'resources/scripts/lib',
    urlArgs: "asb=" + new Date(),
    paths  : {
        app           : '../app',
        text          : '../plugin/text',
        i18n          : '../plugin/i18n',
        jquery        : 'jquery-2.1.0',
        leaflet       : 'leaflet',
        underscore    : 'underscore',
        bootstrap     : 'bootstrap-3.1.1-dist/js/bootstrap.min',
        'leaflet-draw': 'leaflet-draw/leaflet.draw'
    },
    shim   : {
        bootstrap                : {
            deps: ['jquery']
        },
        'L.Control.MousePosition': {
            deps: ['leaflet']
        },
        'L.Control.Sidebar'      : {
            deps: ['leaflet']
        },
        'leaflet-draw'           : {
            deps: ['leaflet']
        }
    }
});

require(['jquery', 'app/AppDelegate', 'bootstrap'], function ($, AppDelegate) {
    return $(function () {
        return window.system.app = new AppDelegate(window.system.userRoles, window.system.initBbox);
    });
});

