###
FeatureFormView is sidebar view control for posting feature data.
###
define [
    'leaflet',
    'app/Module',
    'L.Control.Sidebar',
    'app/EventObserver',
    'app/Events'
], (L, Module, Sidebar, EventObserver, Events) ->
    class FeatureFormViewController extends Module

        viewControlId: null

        viewControl: null

        eventObserver: null

        constructor: (map, @viewControlId) ->
            @viewControl = L.control.sidebar @viewControlId,
                position: 'left'
            map.addControl @viewControl
            map.on 'popupopen', (e) =>
                @onPopupOpen e
            map.on 'draw:created', (e) =>
                @showViewControl()
            @eventObserver = EventObserver.getInstance()
            @eventObserver.subscribe Events.SET_BBOX, @, @setBboxLatLngBounds
            @eventObserver.subscribe Events.SET_GEOJSON, @, @setLayerGeoJson

        showViewControl: () ->
            @viewControl.show()

        setBboxLatLngBounds: (options) ->
            @assert options.bbox isnt null, 'options.bbox is set!!'
            latLngBounds = options?.bbox
            @log "#{options}"
            @log "event fired #{latLngBounds}"
            $ '#_feature_bbox0'
            .attr 'value', latLngBounds?.getSouth()
            $ '#_feature_bbox1'
            .attr 'value', latLngBounds?.getWest()
            $ '#_feature_bbox2'
            .attr 'value', latLngBounds?.getNorth()
            $ '#_feature_bbox3'
            .attr 'value', latLngBounds?.getEast()

        setLayerGeoJson: (options) ->
            @assert options.geojson isnt null, 'options.geojson is null'
            geojson = options.geojson
            $ '#_feature_geomAsGeoJson'
            .attr 'value', JSON.stringify geojson if geojson isnt null

        onPopupOpen: (e) ->
            feature = e.popup.options.feature

            # set required data on each field at sidebar.
            $ '#_feature_geomAsGeoJson'
            .attr 'value', JSON.stringify feature.geometry
            $ '#_feature_featureId'
            .attr 'value', feature.properties.featureId
            $ '#_feature_featureName'
            .attr 'value', feature.properties.featureName
            $ '#_feature_featureDescription'
            .text feature.properties.featureDescription
            $ '#_feature_tags'
            .attr 'value', feature.properties.tags
