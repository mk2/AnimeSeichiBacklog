###
MapView is hoding map object, and treating popups.
###
define [
    'leaflet', 'doT', 'jquery', 'app/Module', 'app/leaflet-control/NotificationControl',
    'L.Control.MousePosition', 'text!app/dot-template/popupOnMapView.html',
    'app/EventObserver', 'app/Events'
], (L, doT, $, Module, NotificationControl, LControlMousePosition, popupTemplate
    EventObserver, Events) ->
    class MapViewController extends Module

        map: null

        mapId: null

        geoJsonLayer: null

        popupTemplateFunc: doT.template(popupTemplate)

        eventObserver: null

        ###
            @param mapId mapdivのid
            @param options = {
                maxZoom: max zoom Level
                initBbox[4]: latlng of initial map view area
            }
        ###
        constructor: (@mapId, @options) ->
            @eventObserver = EventObserver.getInstance()
            @eventObserver.subscribe Events.REMOVE_LAYER, @, @removeLayer

            @map = L.map mapId, zoomControl: false
            .fitBounds([
                    [@options.initBbox[0], @options.initBbox[1]],
                    [@options.initBbox[2], @options.initBbox[3]]
                ])
            L.tileLayer 'http://{s}.tile.cloudmade.com/95b6020b5a5c443da89d923d8706d168/997/256/{z}/{x}/{y}.png',
                attribution: 'Map data &copy'
                maxZoom: @options.maxZoom
            .addTo @map

            @setupMapControlls()
            @updateFeaturesOnMap()

        addLayer: (layer) ->
            @map.addLayer layer

        addEventHandler: (eventName, eventHandler, thiz) ->
            @map.on eventName, (eventHandler.bind(thiz))

        addControl: (control) ->
            @map.addControl control

        setupMapControlls: () ->

            # set each events
            @map.on 'dragend', (e) =>
                @updateFeaturesOnMap()
            @map.on 'zoomend', (e) =>
                @updateFeaturesOnMap()

            notificationControl = new NotificationControl()
            @map.addControl notificationControl

            zoomControl = L.control.zoom()
            @map.addControl zoomControl


            mousePositionControl = L.control.mousePosition()
            @map.addControl mousePositionControl


        removeLayer: (options) ->
            layer = options?.layer
            @log options.layer
            @log 'remove layer event'
            @map.removeLayer layer if layer isnt null

        ###
            Update all features on map.
        ###
        updateFeaturesOnMap: () ->
            latLngBounds = @map.getBounds()

            $.getJSON "/f/xr/#{latLngBounds.getWest()}/#{latLngBounds.getSouth()}/#{latLngBounds.getEast()}/#{latLngBounds.getNorth()}/", (data, textStatus, jqXHR) =>
                if @geoJsonLayer isnt null
                    @map.removeLayer @geoJsonLayer
                    @geoJsonLayer = null

                # CAUTION: The following method will be executed immediately after getting json data.
                onEachFeature = (feature, layer) =>
                    layer.bindPopup @popupTemplateFunc(feature.properties),
                        minWidth: 300
                        maxWidth: 450
                        autoPan: true
                        keepInView: true
                        feature: feature

                @geoJsonLayer = L.geoJson data,
                    onEachFeature: onEachFeature
                .addTo @map
