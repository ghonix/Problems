package event

import java.util.*

internal interface EventListener {
    fun onEvent(event: String?)
}

internal class EventManager {
    var eventMap: MutableMap<String, MutableList<EventListener>> = HashMap()
    @Synchronized
    fun addListener(event: String, listener: EventListener) { // O(1)
        var eventListeners = eventMap[event]
        if (eventListeners == null) {
            eventListeners = Vector()
            eventMap[event] = eventListeners
        }
        eventListeners.add(listener)
    }

    @Synchronized
    fun removeListener(event: String, listener: EventListener) { // O(n). Usingn Set makes it
        val eventListeners = eventMap[event]
        if (eventListeners == null) {
            // Error
        } else {
            var found = false
            for (currentListener in eventListeners) {
                if (currentListener === listener) { // not ideal .equals or .hashcode is better
                    eventListeners.remove(listener)
                    found = true
                    break
                }
            }
            if (!found) {
                // Throw
            }
        }
    }

    @Synchronized
    fun notify(event: String) { // O(n)
        // check if event is not null or empty
        val eventListeners: List<EventListener>? = eventMap[event]
        if (eventListeners == null) {
            return
        } else {
            val copy: List<EventListener> = Vector(eventListeners)
            for (currentListener in copy) {
                currentListener.onEvent(event)
            }
        }
    }

    companion object {
        private var INSTANCE: EventManager? = null

        @get:Synchronized
        val instance: EventManager?
            get() {
                if (INSTANCE == null) {
                    INSTANCE = EventManager()
                }
                return INSTANCE
            }
    }
}

internal class ContactBadgeEventListener : EventListener {
    override fun onEvent(event: String?) {
        println("Show the badge!")
        EventManager.instance!!.removeListener("CONTACTS_ADDED", this)
    }
}

internal object Solution {
    @JvmStatic
    fun main(args: Array<String>) {
        val eventManager = EventManager.instance
        val listener: EventListener = ContactBadgeEventListener()
        eventManager!!.addListener("CONTACTS_ADDED", listener)
        eventManager.notify("RANDOM_EVENT")
        eventManager.notify("CONTACTS_ADDED")
        eventManager.notify("CONTACTS_ADDED")
    }
}