# spring-bceid-starter

A stater to facilitate usage of bceid service.

## Usage

Add spring-bceid-starter as a maven dependency

```xml
 <dependencies>
    <dependency>
        <groupId>ca.bc.gov.open</groupId>
        <artifactId>spring-bceid-starter</artifactId>
        <version>0.1.2</version>
    </dependency>
</dependencies>
```

## Configuration

| name | definition | required |
| --- | --- | --- |
| [bcgov.bceid.service.uri](#cgovbceidserviceuri) | String | Yes |
| [bcgov.bceid.service.username](#bcgovbceidserviceusername) | String | Yes |
| [bcgov.bceid.service.password](#bcgovbceidservicepassword) | String | Yes |
| [bcgov.bceid.service.onlineServiceId](#bcgovbceidserviceonlineServiceId) | String | Yes |

#### bcgov.bceid.service.uri

* Value type is String

Sets the BCeID service URI

#### bcgov.bceid.service.username

* Value type is String

Sets the username used to set basic authentication on bceid service

#### bcgov.bceid.service.password

* Value type is String

Sets the password used to set basic authentication on the BCeID service

#### bcgov.bceid.service.onlineServiceId

* Value type is String

Sets the onlineServiceId
