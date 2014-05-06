###
A Notification is displayed at left top corner of screen, information of the notification
is passed to client via a jsp model at page loading.
###
define ['leaflet', 'jquery'], (L, $) ->
    NotificationControl = L.Control.extend
        options:
            position: 'topleft'

        onAdd: (map) ->
            container = L.DomUtil.create('div', 'notification-control')
            $ "<div>#{window.system.notification}&NonBreakingSpace;<a href='/'>[Top Page]</a></div>"
            .appendTo container
            return container
