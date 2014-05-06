###
RemarkView is holding all remarks.
###
define [
    'leaflet', 'doT', 'jquery', 'app/Module', 'L.Control.Sidebar',
    'text!app/dot-template/featureOnRemarkView.html!strip',
    'text!app/dot-template/remarkOnRemarkView.html!strip'
], (L, doT, $, Module, LControlSidebar, featureTemplate, remarkTemplate) ->
    class RemarkViewController extends Module

        viewControl: null

        viewControlId: null

        featureTemplateFunc: doT.template(featureTemplate)

        remarkTemplateFunc: doT.template(remarkTemplate)

        constructor: (map, @viewControlId) ->
            @viewControl = L.control.sidebar @viewControlId,
                position: 'right'
            map.addControl @viewControl
            map.on 'popupopen', (e) =>
                _map = map
                @onPopupOpen e, _map

        showViewControl: () ->
            @viewControl.show()

        onPopupOpen: (e, map) ->
            feature = e.popup.options.feature

            @setBuiltFeatureTemplateOn '#feature-remark', feature.properties

            featureId = feature.properties.featureId

            @setBuiltRemarksTemplateOn '#remarks', featureId

            $ '#_remark_featureId'
            .attr 'value', featureId

            latLngBounds = map?.getBounds()
            $ '#_remark_bbox0'
            .attr 'value', latLngBounds?.getSouth()
            $ '#_remark_bbox1'
            .attr 'value', latLngBounds?.getWest()
            $ '#_remark_bbox2'
            .attr 'value', latLngBounds?.getNorth()
            $ '#_remark_bbox3'
            .attr 'value', latLngBounds?.getEast()

            @log 'popup open catched'


        setBuiltFeatureTemplateOn: (divId, featureProperties) ->
            $ divId
            .html @featureTemplateFunc(featureProperties)

        setBuiltRemarksTemplateOn: (divId, featureId) ->
            $.getJSON "/r/xr/#{featureId}/", (data, status, jqXHR) =>
                remarks = data
                html = @toHtmlFromRemarks(remarks)
                $ divId
                .html html

        toHtmlFromRemarks: (remarks) ->
            html = ""
            for remark in remarks
                html += @remarkTemplateFunc(remark)
            return html
