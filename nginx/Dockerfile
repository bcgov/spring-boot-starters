FROM nginx:1.31.5

USER root

RUN apt-get update

# S2I scripts location and app root
ENV STI_SCRIPTS_PATH=/usr/libexec/s2i \
    APP_ROOT=/opt/app-root \
    HOME=/opt/app-root/src

ENV PATH="${APP_ROOT}/bin:${PATH}"

RUN mkdir -p ${STI_SCRIPTS_PATH} ${HOME}

# Copy S2I scripts
COPY s2i/bin/ ${STI_SCRIPTS_PATH}/
RUN chmod -R a+rx ${STI_SCRIPTS_PATH}

# --- OpenShift permission fixes ---
RUN mkdir -p /run /var/cache/nginx /var/log/nginx && \
    touch /run/nginx.pid && \
    chown -R 1001:0 /run /var/cache/nginx /var/log/nginx /etc/nginx /usr/share/nginx/html ${APP_ROOT} && \
    chmod -R g+rwX /run /var/cache/nginx /var/log/nginx /etc/nginx /usr/share/nginx/html ${APP_ROOT}

COPY nginx/nginx-cfg/default.conf /etc/nginx/conf.d/default.conf
    # --- Remove "user nginx;" directive (OpenShift runs as non-root) ---
RUN sed -i 's/^user/# user/' /etc/nginx/nginx.conf

# --- Change Nginx to listen on port 8080 instead of 80 ---
RUN sed -i 's/listen       80;/listen       8080;/' /etc/nginx/conf.d/default.conf || true
RUN sed -i 's/listen 80;/listen 8080;/' /etc/nginx/conf.d/default.conf || true

WORKDIR ${HOME}

# Arbitrary UID support for OpenShift
USER 1001
EXPOSE 8080

CMD ["/usr/libexec/s2i/run"]