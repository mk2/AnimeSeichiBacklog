###
    Application class
    Having all viewControllers used in the application.
    This class instance can be accessed via window.system.app.
###
define [
    'app/controller/MapViewController',
    'app/controller/DrawViewController',
    'app/controller/RemarkViewController',
    'app/controller/FeatureFormViewController',
    'app/EventObserver',
    'app/Module'
], (MapViewController, DrawViewController, RemarkViewController, FeatureFormViewController, EventObserver, Module) ->
    class AppDelegate extends Module

        # View Controllers

        drawViewController: null

        remarkViewController: null

        mapViewController: null

        featureFormViewController: null

        eventObserver: null

        constructor: (roles, initBbox) ->
            @eventObserver = EventObserver.getInstance()

            @mapViewController = new MapViewController 'map',
                initBbox: initBbox
                maxZoom : 18
                roles   : roles

            map = @mapViewController.map

            if $.inArray(ROLE_USER, roles) isnt -1
                @drawViewController = new DrawViewController map
                @featureFormViewController = new FeatureFormViewController map, 'featureFormViewSidebar'

            @remarkViewController = new RemarkViewController map, 'remarkViewSidebar'

        showFeatureFormView: (isEditable) ->
            @featureFormViewController?.showViewControl()

        showRemarkView: () ->
            @remarkViewController?.showViewControl()
