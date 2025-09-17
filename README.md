# Trustify Dependency Analytics API Specification
Trustify Dependency Analytics API Specification

## OpenAPI v5

Find the OpenAPI definition for V5 under the [/api](./api) folder

## Generated data model

The Java and Javascript data models are generated at build time using the
[openapi-generator-maven-plugin](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin)

Run `mvn package` to generate it.

## Use the Java generated data model

The packages are published to the GitHub maven repository. Make sure to add it to your settings or to your project configuration.

```xml
<dependency>
  <groupId>com.github.guacsec</groupId>
  <artifactId>trustify-da-api</artifactId>
  <version>1.0.19-SNAPSHOT</version>
</dependency>
```

## Use the Javascript data model

Configuring NPM to look in GHPR for the `guacsec` namespace is done by adding `@guacsec:registry=https://npm.pkg.github.com`
to _.npmrc_ in the project root or user home.

```bash
echo "@guacsec:registry=https://npm.pkg.github.com" >> .npmrc
```

Then, add it to your project as follows:

```bash
npm install @guacsec/trustify-da-api@1.0.19-SNAPSHOT
```
