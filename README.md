# [Google's Business Messages: Java Client](https://github.com/google-business-communications/java-businessmessages)

[Business Messages](https://developers.google.com/business-communications/business-messages/guides/learn) is a mobile conversational channel that combines entry points on Google Maps, Search, and brand websites to create rich, asynchronous messaging experiences.

This document contains an [API reference](https://developers.google.com/business-communications/business-messages/reference/rest), samples,
and other resources useful to developing Java applications.
For additional help developing Business Messages applications, in Java and other languages, see our
[Business Messages quickstart](https://developers.google.com/business-communications/business-messages/guides/quickstarts/echo-agent)
guide.

## Setup Instructions
The Actions on Google Java/Kotlin library is hosted on Maven central.
To use the library in your project, add the following to the dependencies section of your
project’s build.gradle.

```
repositories {
   mavenCentral()
}

dependencies {
   compile group: 'com.google.apis', name: 'google-api-services-businessmessages', version: '1.25.0'
}
```


If using maven, add the following to your pom.xml file.

```xml
<dependency>
	<groupId>com.google.apis</groupId>
	<artifactId>google-api-services-businessmessages</artifactId>
	<version>1.25.0</version>
</dependency>
```


## Contributing

Contributions welcome! See the [Contributing Guide](https://github.com/google-business-communications/java-businessmessages/CONTRIBUTING.md).

## License

Apache Version 2.0

See [LICENSE](https://github.com/google-business-communications/java-businessmessages/LICENSE)
