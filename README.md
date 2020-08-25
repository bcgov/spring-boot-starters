# spring-boot-starters
A collection of Spring boot starters

# Current Recommendation for Storage and use in Openshift

Currently, the most efficient way to store these starters for use is in your project's Nexus artifactory. Please see the sample Openshift Template for pushing items to Nexus if you do not know how to.

Once all starter jars are stored in Nexus, make sure to alter your project's build config to reference Nexus through the environment variable `MAVEN_MIRROR_URL` with the value `http://nexus:8081/repository/${YOUR_GROUP_HERE}`

# Project Integration

To integrate into a Git Action Pipeline, the following Git Action Jobs are required: 

```bash
      - name: Checkout Spring SFTP Starter Repository
        uses: actions/checkout@v2
        with:
          repository: bcgov/spring-boot-starters
          path: spring-boot-starters
          ref: vX.X.X

      - name: Build Spring SFTP Starter
        run: mvn install -P all --file ./spring-boot-starters/src/pom.xml
```
      
Make sure to update the ref in the checkout action to point to the latest release available. These actions will make all starters available to your project through the Git Action .m2 directory.

# How to Update this Repository

Are you adding a new starter to this repository? When a PR is created, do the following:

1. Make sure to update all version numbers to the incremented version required.
2. Create a version referencing the newest version.
3. Publish the version.
4. Update your Git Actions to reference the correct tag (ref)
5. Ensure your git actions build correctly.
