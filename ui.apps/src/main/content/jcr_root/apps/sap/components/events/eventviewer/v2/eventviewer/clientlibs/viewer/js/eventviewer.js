
Vue.component("events_nav", {
    props: {
        dataPath: String,
    },
    data() {
        return {
            sortEvent: "topic",
            topic: true,
            type: false
        };
    },
    methods: {
        requestTopic: function () {
            this.sortEvent = "topic";
            this.$root.sortEvent = this.sortEvent;
            this.topic = true;
            this.type = false;
            this.$root.getResponse();
        },
        requestType: function () {
            this.sortEvent = "type";
            this.$root.sortEvent = this.sortEvent;
            this.topic = false;
            this.type = true;
            this.$root.getResponse();
        },
    },
    created: function () {
        let servletSelector = ".sort";
        let servletExtension = ".json";

        this.$root.dataPath = this.dataPath.concat(
            servletSelector,
            servletExtension
        );
        this.$root.sortEvent = this.sortEvent;
        this.$root.getResponse();
    },
    template:
        '<div class="navbar events_nav">' +
        '<div class="navbar-inner">' +
        '<span class="brand">View By:</span>' +
        '<ul class="nav" id="event_sort" data-sort="topic" data-method="GET" :data-path="dataPath">' +
        '<li :class="{active: topic}" id="events__view-topic"><span @click="requestTopic">Topic</span></li>' +
        '<li :class="{active: type}" id="events__view-type"><span @click="requestType">Type</span></li>' +
        '</ul>' +
        '</div>' +
        '</div>',
});

Vue.component("all-events", {
    props: {
        eventsValue: "",
    },
    template:
        '<div class="row event-listing">' +
        '<div class="span3" v-for="(value, name) in eventsValue">' +
        '<h3>{{ name }}<event-icon :icon="name"></event-icon></h3>' +
        '<event-column :value="value"></event-column>' +
        '</div>' +
        '</div>',
});


Vue.component("event-icon", {
    props: {
        icon: String,
    },
    computed: {
        classIcon: function () {

            let icon = '';

            if (this.$props.icon == 'Database') {
                icon = 'icon-hdd'
            }
            if (this.$props.icon == 'Cloud') {
                icon = 'icon-cloud'
            }
            if (this.$props.icon == 'Mobile') {
                icon = 'icon-mobile-phone'
            }
            if (this.$props.icon == 'Other Topics') {
                icon = 'icon-calendar'
            }

            return icon;
        }
    },
    template:
        '<i :class="classIcon"></i>',
});

Vue.component("event-column", {
    props: {
        value: Array,
    },
    template:
        '<ul class="unstyled" >' +
        '<li class="event-list" v-for="item in this.$props.value">' +
        '<element-event :element="item"></element-event>' +
        '</li>' +
        '</ul>',
});

Vue.component("element-event", {
    props: {
        element: Object,
    },
    data() {
        return {
            description: "",
            eventdate: ""
        };
    },
    computed: {
        classIcon: function () {

            let icon = '';

            if (this.$props.element.topic == 'Database') {
                icon = 'icon-hdd'
            }
            if (this.$props.element.topic == 'Cloud') {
                icon = 'icon-cloud'
            }
            if (this.$props.element.topic == 'Mobile') {
                icon = 'icon-mobile-phone'
            }
            if (this.$props.element.topic == 'Other Topics') {
                icon = 'icon-calendar'
            }

            return icon;
        },
        selectTopic: function () {
            let result = false;
            if (this.$root.sortEvent == "type") {
                result = true;
            }

            return result;
        }

    },
    methods: {
        formatDate() {
            return new Date(this.$props.element.eventStartDate).toLocaleString('en-US', {
                day: '2-digit',
                month: 'long'
            });
        },
    },
    template:
        '<div>' +
        '<i v-if="selectTopic" class="icon" v-bind:class="classIcon"></i>' +
        '<span class="date" type="date" v-html="formatDate()"></span>' +
        '<h4><a :href="element.titleLink" :rel="element.typeOfOpen" v-html="element.title"></a></h4>' +
        '<p class="event-description" v-html="element.description"></p>' +
        '</div>',

})

var app = new Vue({
    el: "#eventviewer-v2",
    data: {
        dataPath: "",
        sortEvent: "",
        events: "",
    },

    methods: {
        getResponse() {
            this.requestByParam(this.sortEvent);
        },
        requestByParam: function (byParam) {
            this.$http
                .get(this.dataPath, {
                    params: { sortEvent: this.sortEvent },
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                    },
                })
                .then((response) =>
                    response.json().then((data) => {
                        this.events = data;
                        this.emptyMessage = "";
                    })
                );
        },
    },
});

