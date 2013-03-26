function EventManager() {
	this.eventHandlers = [];
}
EventManager.STUDENT_CREATED = 'student_create';
EventManager.STUDENT_UPDATED = 'student_update';
EventManager.COURSE_CREATED = 'course_create';
EventManager.COURSE_UPDATED = 'course_update';
EventManager.LOCATION_CREATED = 'location_create';
EventManager.LOCATION_UPDATED = 'location_update';

EventManager.prototype = new EventManager();
EventManager.prototype.registerHandler = function(eventType, handler) {
	if(eventType != undefined && eventType != '' && handler != undefined && handler != '') {
		var handlers = this.eventHandlers[eventType];
		if(handlers == undefined) {
			handlers = [];
			this.eventHandlers[eventType] = handlers;
		}
		handlers.push(handler);
	}
};
EventManager.prototype.notifyEvent = function(event) {
	if(event != undefined && event != '') {
		var handlers = this.eventHandlers[event];
		if(handlers != undefined) {
			for (var i in handlers) {
				var handler = handlers[i];
				if(typeof handler === 'function') {
					handler();
				}
			}
		}
	}
};
EventManager.instance = null;
EventManager.getInstance = function() {
	if(EventManager.instance == null) EventManager.instance = new EventManager();
	return EventManager.instance;
};