/**
 * RequireJS Configuration
 */
require.config({
    baseUrl: 'resources/scripts/lib',
    urlArgs: "asb=" + new Date(),
    paths: {
        app: '../app',
        text: '../plugin/text',
        i18n: '../plugin/i18n',
        'jquery': '//code.jquery.com/jquery-2.1.1',
        'leaflet': '//cdn.leafletjs.com/leaflet-0.7.3/leaflet',
        bootstrap: '//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min',
        'leaflet-draw': 'leaflet-draw/leaflet.draw'
    },
    shim: {
        bootstrap: {
            deps: ['jquery']
        },
        'L.Control.MousePosition': {
            deps: ['leaflet']
        },
        'L.Control.Sidebar': {
            deps: ['leaflet']
        },
        'leaflet-draw': {
            deps: ['leaflet']
        }
    }
});

require(['jquery', 'app/AppDelegate', 'bootstrap'], function ($, AppDelegate) {
    return $(function () {
        return window.system.app = new AppDelegate(window.system.userRoles, window.system.initBbox);
    });
});

