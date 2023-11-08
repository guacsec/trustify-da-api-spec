module exhort.api {

    exports com.redhat.exhort.api;
    exports com.redhat.exhort.api.v3;
    exports com.redhat.exhort.api.v4;
    exports com.redhat.exhort.api.serialization;

    requires jakarta.annotation;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;

    requires transitive com.fasterxml.jackson.databind;
    requires transitive packageurl.java;

    opens com.redhat.exhort.api.v4 to com.fasterxml.jackson.databind;
    opens com.redhat.exhort.api.v3 to com.fasterxml.jackson.databind;
    opens com.redhat.exhort.api to com.fasterxml.jackson.databind;

}
