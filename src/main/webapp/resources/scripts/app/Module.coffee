###
Class for capability of mixin.
see http://minghai.github.io/library/coffeescript/03_classes.html
###
define [], () ->
    moduleKeywords = ['extended', 'included']

    class Module

        # Access method for console object immediately
        assert        : ->
            console?.assert?.apply console, arguments
        log           : ->
            console?.log?.apply console, arguments if window.system.debug
        debug         : ->
            console?.debug?.apply console, arguments if window.system.debug
        warn          : ->
            console?.warn?.apply console, arguments
        error         : ->
            console?.error?.apply console, arguments
        group         : ->
            console?.group?.apply console, arguments
        groupCollapsed: ->
            console?.groupCollapsed?.apply console, arguments
        groupEnd      : ->
            console?.groupEnd?.apply console, arguments
        time          : ->
            console?.time?.apply console, arguments
        timeEnd       : ->
            console?.timeEnd?.apply console, arguments

        @extend: (obj) ->
            for key, value of obj when key not in moduleKeywords
                @[key] = value

            obj.extended?.apply(@)
            @

        @include: (obj) ->
            for key, value of obj when key not in moduleKeywords
                # Assign properties to the prototype
                @::[key] = value

            obj.included?.apply(@)
            @
