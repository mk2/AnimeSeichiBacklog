define [
    'app/Module'
], (Module) ->
    class EventObserver extends Module

        @instance: null

        @getInstance: ->
            EventObserver.instance ?= new EventObserverBody()

        class EventObserverBody extends Module

            subscribers: []

            constructor: () ->
                @subscribers = []

            notify: (event, options) ->
                eventFired = false
                for subscriber in @subscribers when subscriber.event is event
                    @debug "FIRED [EVENT] #{event.name}:#{event.description}"
                    subscriber.callback?.apply subscriber.thiz, [options]
                    eventFired = true

                @assert eventFired, "NO SUBSCRIBERS [EVENT] #{event}"
                @

            subscribe: (event, thiz, callback) ->
                @debug "ADD SUBSCRIBER ON:[EVENT] #{event} WITH:[CALLBACK] #{callback}"
                @subscribers.push {'event': event, 'callback': callback, 'thiz': thiz}
                @

