# spring-clamav-starter

A stater to facilitate usage of ClamAv

## Usage

Add spring-bceid-starter as a maven dependency

```xml
 <dependencies>
    <dependency>
        <groupId>ca.bc.gov.open</groupId>
        <artifactId>spring-clamav-starter</artifactId>
        <version>0.1.4</version>
    </dependency>
</dependencies>
```

## How to use the service

The following code should be used to implement this component:

```java

@autowired
private ClamAvService clamAvService

@Service
public class FileScan {

    public void Scan(InputStream inputStream) {

        try {
            clamAvService.scan(inputStream);
        } catch (VirusDetectedException e) {
            // file is infected
        }

    }
}

```


## Configuration

| name | definition | required |
| --- | --- | --- |
| [bcgov.clamav.service.host](#bcgovclamavservicehost) | String | No |
| [bcgov.clamav.service.port](#bcgovclamavserviceport) | String | No |
| [bcgov.clamav.service.timeout](#bcgovclamavservicetimeout) | String | No |

#### cgov.clamav.service.host

* Value type is String

Default value is **localhost**

Sets the port of the host client

#### bcgov.clamav.service.port

* Value type is Integer

Default value is **3310**

Sets the port of the clamAv client

#### bcgov.clamav.service.timeout

* Value type is Integer

Default value is **500**

Sets the timeout of the clamAv client
