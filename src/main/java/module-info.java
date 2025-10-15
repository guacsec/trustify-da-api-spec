module trustifyda.api {

    exports io.github.guacsec.trustifyda.api;
    exports io.github.guacsec.trustifyda.api.v5;
    exports io.github.guacsec.trustifyda.api.serialization;

    requires jakarta.annotation;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires packageurl.java;

    opens io.github.guacsec.trustifyda.api.v5 to com.fasterxml.jackson.databind;
    opens io.github.guacsec.trustifyda.api to com.fasterxml.jackson.databind;

}
