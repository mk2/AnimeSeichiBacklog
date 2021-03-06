// Generated by CoffeeScript 1.7.1

/*
 DrawView is control for drawing geometries on map.
 */

(function () {
    var __hasProp = {}.hasOwnProperty,
        __extends = function (child, parent) {
            for (var key in parent) {
                if (__hasProp.call(parent, key)) child[key] = parent[key];
            }
            function ctor() {
                this.constructor = child;
            }

            ctor.prototype = parent.prototype;
            child.prototype = new ctor();
            child.__super__ = parent.prototype;
            return child;
        };

    define(['leaflet', 'app/Module', 'leaflet-draw', 'app/EventObserver', 'app/Events'], function (L, Module, LeafletDraw, EventObserver, Events) {
        var DrawViewController;
        return DrawViewController = (function (_super) {
            __extends(DrawViewController, _super);

            DrawViewController.prototype.drawView = null;

            DrawViewController.prototype.drawnItems = null;

            DrawViewController.prototype.viewControl = null;

            DrawViewController.prototype.eventObserver = null;

            DrawViewController.prototype.preDrawnLayer = null;

            function DrawViewController(map) {
                this.eventObserver = EventObserver.getInstance();
                this.drawnItems = new L.FeatureGroup();
                this.viewControl = new L.Control.Draw({
                    draw: {
                        circle: false
                    },
                    edit: {
                        featureGroup: this.drawnItems,
                        remove: false
                    }
                });
                map.addLayer(this.drawnItems);
                map.addControl(this.viewControl);
                map.on('draw:drawstart', (function (_this) {
                    return function (e) {
                        var _map;
                        _map = map;
                        return _this.drawStart(e, _map);
                    };
                })(this));
                map.on('draw:created', (function (_this) {
                    return function (e) {
                        var _map;
                        _map = map;
                        return _this.drawCreated(e, _map);
                    };
                })(this));
                map.on('draw.edited', (function (_this) {
                    return function (e) {
                        var _map;
                        _map = map;
                        return _this.drawEdited(e, _map);
                    };
                })(this));
            }

            DrawViewController.prototype.drawStart = function (e, map) {
                if (this.preDrawnLayer !== null) {
                    this.eventObserver.notify(Events.REMOVE_LAYER, {
                        layer: this.preDrawnLayer
                    });
                }
                this.log("draw started");
                return this.log(this.drawnItems);
            };

            DrawViewController.prototype.drawCreated = function (e, map) {
                var latLngBounds, layer, type;
                layer = e.layer;
                type = e.layerType;
                this.eventObserver.notify(Events.SET_GEOJSON, {
                    geojson: layer.toGeoJSON().geometry
                });
                latLngBounds = map != null ? map.getBounds() : void 0;
                this.eventObserver.notify(Events.SET_BBOX, {
                    bbox: latLngBounds
                });
                this.preDrawnLayer = layer;
                return this.drawnItems.addLayer(layer);
            };

            DrawViewController.prototype.drawEdited = function (e, map) {
                var latLngBounds, layer;
                layer = e.layers.getLayers()[0];
                this.eventObserver.notify(Events.SET_GEOJSON, {
                    geojson: layer.toGeoJSON().geometry
                });
                latLngBounds = map != null ? map.getBounds() : void 0;
                return this.eventObserver.notify(Events.SET_BBOX, {
                    bbox: latLngBounds
                });
            };

            return DrawViewController;

        })(Module);
    });

}).call(this);

//# sourceMappingURL=DrawViewController.map
