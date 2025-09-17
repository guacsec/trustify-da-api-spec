module exhort.api {

    exports com.github.guacsec.exhort.api;
    exports com.github.guacsec.exhort.api.v4;
    exports com.github.guacsec.exhort.api.serialization;

    requires jakarta.annotation;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires packageurl.java;

    opens com.github.guacsec.exhort.api.v4 to com.fasterxml.jackson.databind;
    opens com.github.guacsec.exhort.api to com.fasterxml.jackson.databind;

}
