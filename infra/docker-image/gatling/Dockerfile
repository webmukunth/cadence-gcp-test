FROM openjdk:11-slim

ARG GATLING_VERSION="3.2.1"
ENV GVERSION="$GATLING_VERSION"
ENV GATLING_HOME /opt/gatling
ENV PATH ${GATLING_HOME}/bin:$PATH

COPY docker-cmd.sh /

# Install gcloud
RUN set -exu && \
    \
    # install utils
    apt-get update -y && apt-get install -y --no-install-recommends curl unzip && \
    \
    # Download and install gatling
    curl -ksSL -o /tmp/gatling-${GVERSION}.zip \
      https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/${GVERSION}/gatling-charts-highcharts-bundle-${GVERSION}-bundle.zip && \
    cd /tmp && \
    unzip gatling-${GVERSION}.zip && \
    mkdir -p ${GATLING_HOME} && \
    mv gatling-charts-highcharts-bundle-${GVERSION}/* ${GATLING_HOME} && \
    \
    # Clean up
    apt-get -y autoremove && \
    apt-get -y clean && \
    apt-get -y remove gcc python-dev python-setuptools gnupg2 unzip curl && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf ~/.config/gcloud && \
    rm -vrf /tmp/* ${GATLING_HOME}/results  ${GATLING_HOME}/user-files ${GATLING_HOME}/target && \
    mkdir -v ${GATLING_HOME}/results  ${GATLING_HOME}/user-files ${GATLING_HOME}/target && \
    chmod +x /docker-cmd.sh

# Smoke test
RUN set -ex && java -version && gatling.sh && ulimit -a

VOLUME ["/opt/gatling/conf", "/opt/gatling/results", "/opt/gatling/user-files"]

CMD [ "/docker-cmd.sh" ]
