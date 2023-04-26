# poc-backend-experimentation
back end dummy application for ABTesting experimentation

Since we are using Spring Boot 3.0.6,
the project use the JDK 17.

for the command line user you can start the project using:
`` mvn spring-boot:run``


# Running in Docker deployed at Marathon
**NOT NEEDED FOR LOCAL DEVELOPMENT - PROCEDURE IN PLACE ONLY WHEN YOU NEED AN APPLICATION REMOTELY DEPLOYED IN MARATHON IN YOUR OWN PERSONAL SPACE**

follow the steps below:

- Create your docker image and tag it

  `docker build . -t docker-apps-dev.artifactory.tsp.cld.touchtunes.com/poc-backend-experimentation:<USERNAME>-latest`

- Push your image to the artifactory (if you get authentication errors, make sure to run docker login command against the artifactory)

  `docker push docker-apps-dev.artifactory.tsp.cld.touchtunes.com/poc-backend-experimentation:<USERNAME>-latest`
- Deploy your image at Marathon (if you never did that, please make sure you setup everything from the Moji session)

  `python <PATH_TO_DEV_TOOLS_PROJ>/dev-tools/moji/tt-marathon-deploy --file platform/definition.yaml`
- The app should be available at:

  `https://<USERNAME>-poc-backend-experimentation.scaffold-workers-test-us.dev.cld.touchtunes.com`

## Moji - Push container to Execution Platform
> To deploy the marathon platform, you must first have installed the [dev-tools](https://wiki.touchtunes.com/display/XP/Marathon+Applications). Choose installation location and then:
> ```bash
> git clone ssh://[USERNAME]@gerrit-01.touchtunes.com:29418/dev-tools
> scp -p -P 29420 [USERNAME]@gerrit-01.touchtunes.com:hooks/commit-msg dev-tools/.git/hooks/
> ```

In order to run the final command, you need to have the following three programs installed
```bash
pip3 install requests
pip3 install pyyaml
pip3 install hiyapyco
```

To setup *Marathon* authentication, you need to create the file `$HOME/.tt.env` that contains the platform configuration:
```
#!/bin/sh
export TT_REGISTRY=docker-apps-dev.artifactory.tsp.cld.touchtunes.com
export TT_MARATHON_URL=https://marathon.scaffold-masters-test-us.dev.cld.touchtunes.com
export TT_CONTEXT=<USERNAME>
export TT_ENVIRONMENT=dev
export TT_COUNTRY=us
export TT_AUTH_USER=admin
export TT_AUTH_PASS=admin
```



