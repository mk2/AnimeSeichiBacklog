###
DrawView is control for drawing geometries on map.
###
define [
    'leaflet',
    'app/Module',
    'leaflet-draw',
    'app/EventObserver',
    'app/Events'
], (L, Module, LeafletDraw, EventObserver, Events) ->
    class DrawViewController extends Module

        drawView: null

        drawnItems: null

        viewControl: null

        eventObserver: null

        preDrawnLayer: null

        constructor: (map) ->
            @eventObserver = EventObserver.getInstance()
            @drawnItems = new L.FeatureGroup()
            @viewControl = new L.Control.Draw
                draw:
                    circle: false
                edit:
                    featureGroup: @drawnItems
                    remove: false
            map.addLayer @drawnItems
            map.addControl @viewControl
            map.on 'draw:drawstart', (e) =>
                _map = map
                @drawStart e, _map
            map.on 'draw:created', (e) =>
                _map = map
                @drawCreated e, _map
            map.on 'draw.edited', (e) =>
                _map = map
                @drawEdited e, _map

        drawStart: (e, map) ->
            if @preDrawnLayer isnt null
                @eventObserver.notify Events.REMOVE_LAYER,
                    layer: @preDrawnLayer
            @log "draw started"
            @log @drawnItems

        drawCreated: (e, map) ->
            layer = e.layer
            type = e.layerType

            @eventObserver.notify Events.SET_GEOJSON,
                geojson: layer.toGeoJSON().geometry

            latLngBounds = map?.getBounds()

            @eventObserver.notify Events.SET_BBOX,
                bbox: latLngBounds

            @preDrawnLayer = layer

            @drawnItems.addLayer layer

        drawEdited: (e, map) ->
            layer = e.layers.getLayers()[0]

            @eventObserver.notify Events.SET_GEOJSON,
                geojson: layer.toGeoJSON().geometry

            latLngBounds = map?.getBounds()

            @eventObserver.notify Events.SET_BBOX,
                bbox: latLngBounds
